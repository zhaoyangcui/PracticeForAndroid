package com.example.sunnny.a26_startservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_start;
    private Button btn_stop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_start = (Button) findViewById(R.id.start);
        btn_stop = (Button) findViewById(R.id.stop);
        btn_start.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this,MyService.class);
        switch (v.getId()){
            case R.id.start:
                intent.putExtra("mydata","200");
                startService(intent);
                break;
            case R.id.stop:
                stopService(intent);
                break;
        }
    }
}
