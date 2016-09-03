package com.example.sunnny.a2_testlogcat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String FIRST_TAG = "Tag";
    private static final String SECOND_TAG = "LogcatTest";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(FIRST_TAG,"The first test");
        Log.i(SECOND_TAG,"The second test");
    }
}
