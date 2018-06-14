package com.example.sean.dingdang_and;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent messageService = new Intent(context, MessageService.class);
        context.startService(messageService);
    }
}
