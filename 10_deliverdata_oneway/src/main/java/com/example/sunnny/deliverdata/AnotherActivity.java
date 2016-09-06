package com.example.sunnny.deliverdata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AnotherActivity extends AppCompatActivity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);
        textView = (TextView) findViewById(R.id.tv);
        Intent i = getIntent();
        String sex = i.getStringExtra("sex");
        textView.setText(sex);
    }
}
