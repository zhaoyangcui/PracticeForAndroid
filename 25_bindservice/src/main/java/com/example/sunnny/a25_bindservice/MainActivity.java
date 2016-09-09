package com.example.sunnny.a25_bindservice;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class  MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button bt_start;
    private Button bt_stop;
    private Button bt_use;
    private MyService myService;
    private ServiceConnection serviceConnection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_start = (Button) findViewById(R.id.start);
        bt_stop = (Button) findViewById(R.id.stop);
        bt_use = (Button) findViewById(R.id.use);
        bt_start.setOnClickListener(this);
        bt_stop.setOnClickListener(this);
        bt_use.setOnClickListener(this);
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.d("Debug","Service Connected");
                myService = (MyService) ((MyService.LocalBinder)iBinder).getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                Log.d("Debug","Service Disconnected");
            }
        };

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this,MyService.class);
        switch (v.getId()){
            case R.id.start:
                bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE);
                break;
            case R.id.stop:
                unbindService(serviceConnection);
                break;
            case R.id.use:
                Toast.makeText(MainActivity.this,"The data is "+myService.add(20,20),Toast.LENGTH_LONG).show();
                break;
        }
    }
}
