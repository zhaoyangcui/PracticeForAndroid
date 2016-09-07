package com.example.sunnny.a17_sharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private final static String SharedFileName="new";
    private final static String Key_name="name";
    private final static String Key_id="id";

    private Button button_write;
    private Button button_read;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences=getSharedPreferences(SharedFileName, MODE_PRIVATE);
        editor=preferences.edit();
        button_read = (Button) findViewById(R.id.read);
        button_write = (Button) findViewById(R.id.write);
        button_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString(Key_name, "Cindy");
                editor.putString(Key_id, "2014011446");
                editor.apply();
            }
        });
        button_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_name = preferences.getString(Key_name, "Cindy");
                String str_id = preferences.getString(Key_id, "2014011446");
                    Toast.makeText(MainActivity.this, "Name:" + str_name + "ID:" + str_id, Toast.LENGTH_LONG).show();
            }
        });
    }
}
