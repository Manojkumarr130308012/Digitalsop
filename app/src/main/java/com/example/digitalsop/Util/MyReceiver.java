package com.example.digitalsop.Util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.digitalsop.MainActivity;
import com.example.digitalsop.Splash;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // assumes WordService is a registered service
        Intent i = new Intent(context, Splash.class);
        context.startService(i);
    }
}