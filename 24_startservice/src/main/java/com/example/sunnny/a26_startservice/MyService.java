package com.example.sunnny.a26_startservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }
    public void onCreate() {
        super.onCreate();
        Log.d("Debug","Start the Service OnCreate");
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Debug","OnStartCommand"+"Data is:"+intent.getStringExtra("mydata"));
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Debug","OnDestory");
    }
}
