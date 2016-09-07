package com.example.sunnny.a27_broad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by sunnny on 2016/9/7.
 */
public class Receive extends BroadcastReceiver {
    private Intent intent;
    public void onReceive(Context context, Intent intent) {
        this.intent = intent;
        String mydata = intent.getStringExtra("broadcast");
        Toast.makeText(context, mydata, Toast.LENGTH_LONG).show();
    }
}
