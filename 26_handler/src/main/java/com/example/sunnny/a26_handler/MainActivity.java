package com.example.sunnny.a26_handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Handler mainHandler;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.num);
        mainHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                text.setText((Integer) msg.obj + "");
            }
        };
        new Thread(new Task()).start();
    }

    class Task implements Runnable {
        private int number = 0;
        @Override
        public void run() {
            for (; number <= 90; ) {
                Message message = mainHandler.obtainMessage();
                message.obj = number;
                number = number + 15;
                mainHandler.sendMessage(message);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}