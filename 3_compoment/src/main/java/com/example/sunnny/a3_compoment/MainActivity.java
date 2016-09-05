package com.example.sunnny.a3_compoment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edt;
    private Button btn_change;
    private TextView textView;
    private CheckBox ch_red;
    private CheckBox ch_blue;
    private RadioButton rd_yes;
    private RadioButton rd_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt = (EditText) findViewById(R.id.edittext);
        btn_change = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);
        ch_red = (CheckBox) findViewById(R.id.red);
        ch_blue = (CheckBox) findViewById(R.id.blue);
        rd_yes = (RadioButton) findViewById(R.id.yes);
        rd_no = (RadioButton) findViewById(R.id.no);

        btn_change.setOnClickListener(this);
        ch_red.setOnClickListener(this);
        ch_blue.setOnClickListener(this);
        rd_yes.setOnClickListener(this);
        rd_no.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.button:
                edt.setText("hello");
            case R.id.red:
                textView.setText("red");
            case R.id.blue:
                textView.setText("blue");
            case R.id.yes:
                textView.setText("yes");


        }


    }
}
