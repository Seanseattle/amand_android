package com.example.sean.dingdang_and;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmClockReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent myAlarmClock_Service = new Intent(context, myAlarmClockService.class);
        context.startService(myAlarmClock_Service);
    }
}
