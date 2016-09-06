package com.example.sunnny.a12_dilverdata_toandfro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AnotherActivity extends AppCompatActivity {
    private Button button2;
    private TextView textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);
        button2 = (Button) findViewById(R.id.another);
        textView2 = (TextView) findViewById(R.id.tv);
        Intent intent = getIntent();
        String get = intent.getStringExtra("sex");
        textView2.setText(get);
        intent.putExtra("name","Cindy");
        setResult(1,intent);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
