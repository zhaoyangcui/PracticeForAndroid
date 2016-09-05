package com.example.sunnny.cyclelife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Debug", "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Debug", "onStop: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Debug", "onResume: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Debug", "onRestart: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Debug", "onStart: ");
    }
}
