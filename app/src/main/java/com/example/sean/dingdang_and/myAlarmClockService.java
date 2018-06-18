package com.example.sean.dingdang_and;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class myAlarmClockService extends Service {
    public myAlarmClockService() {
    }
    private List<Message> myAlarmClockList=new ArrayList<>();
    private long timeGetTime;
    private static final String TAG = "myAlarmClockService";
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
       return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                myAlarmClockList = DataSupport.where("type = ?", "clocklist").find(Message.class);
                if(myAlarmClockList==null || myAlarmClockList.size()==0){
                    for(int i=0;i<1;i++){
                        Message message = new Message("20时05分", (long)1528968178, "clocklist");
                        myAlarmClockList.add(message);
                    }
                }
                try{
                    for(int i=0;i<myAlarmClockList.size();i++){
                        Message message=myAlarmClockList.get(i);
                        String set_clock=message.getMessage();
                        Log.d(TAG, "set_clock "+set_clock);
                        timeGetTime = new Date().getTime();
                        SimpleDateFormat sdfTwo = new SimpleDateFormat("HH时mm分",
                                Locale.getDefault());
                        String now_time = sdfTwo.format(timeGetTime);
                        Log.d(TAG, "now_time"+now_time);
                        if(set_clock.equals(now_time))
                        {
                            Log.d(TAG, "come in");
                            NotificationManager manager=(NotificationManager) getSystemService (NOTIFICATION_SERVICE);
                            Log.d(TAG, "run ");
                            Notification notification=new NotificationCompat.Builder(getApplicationContext())
                                    .setContentTitle("AlarmClock")
                                    .setContentText("")
                                    .setWhen(System.currentTimeMillis())
                                    .setSmallIcon(R.mipmap.ic_launcher)
                                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                                    .setVibrate(new long[]{0,1000,1000,1000})
                                    .setPriority(NotificationCompat.PRIORITY_MAX)
                                    .setDefaults(Notification.DEFAULT_SOUND)
                                    .setDefaults(Notification.DEFAULT_LIGHTS)
                                    .build();
                            Log.d(TAG, "success");
                            manager.notify(1,notification);
                        }
                    }
                }catch (Exception e){
                    Log.d(TAG, "exception");
                    e.printStackTrace();
                }
            }
        }).start();
        AlarmManager manger=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent i=new Intent(this,myAlarmClockService.class);//广播接收
        //PendingIntent pendingIntent=PendingIntent.getActivity(MainActivity2_Text.this, 0, intent, 0);//意图为开启活动
        PendingIntent pendingIntent=PendingIntent.getService(this, 0, i, 0);//意图为开启广播
        long triggerAtTime = System.currentTimeMillis();
        triggerAtTime=triggerAtTime+20*1000;
        manger.set(AlarmManager.RTC_WAKEUP, triggerAtTime, pendingIntent);
        return super.onStartCommand(intent, flags, startId);
    }
}
