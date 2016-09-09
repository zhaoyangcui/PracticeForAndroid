package com.example.sunnny.a25_bindservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private LocalBinder binder = new LocalBinder();
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.d("Debug","Bind");
        return binder;
    }
    public class LocalBinder extends Binder {
        Service getService() {
            return MyService.this;
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("Debug","Unbind");
        return super.onUnbind(intent);
    }
    public int add(int num1, int num2){
        return num1+num2;
    }
}
