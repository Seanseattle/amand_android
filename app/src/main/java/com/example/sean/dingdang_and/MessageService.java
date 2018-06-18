package com.example.sean.dingdang_and;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MessageService extends Service {
    private static String EMAIL = "1234567@qq.com";
    public MessageService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private static final String TAG = "MessageService";
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient httpClient = new OkHttpClient();
                Request request = new Request.Builder().url("http://101.132.144.11:8000/android/" + EMAIL + "/get_messages/").build();
                try {
                    Log.d(TAG, "run: ");
                    Response response = httpClient.newCall(request).execute();
                    if (response.code() == 200) {
                        JSONObject jsonObject = new JSONObject((Map) response.body());
                        JSONArray jsonArray = jsonObject.getJSONArray("msg");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject temp = jsonArray.getJSONObject(i);
                            new Message(temp.getString("message"), temp.getLong("time"), temp.getString("type")).save();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }).start();
        AlarmManager manger=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent i=new Intent(this,AlarmReceiver.class);//广播接收
        //PendingIntent pendingIntent=PendingIntent.getActivity(MainActivity2_Text.this, 0, intent, 0);//意图为开启活动
        PendingIntent pendingIntent=PendingIntent.getService(this, 0, i, 0);//意图为开启广播
        long triggerAtTime = SystemClock.elapsedRealtime();//开机至今的时间毫秒数
        triggerAtTime=triggerAtTime+10;//比开机至今的时间增长10秒
        manger.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pendingIntent);//设置为开机至今的模式，时间，PendingIntent
        return super.onStartCommand(intent, flags, startId);
    }
}
