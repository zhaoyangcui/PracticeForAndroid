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

        switch (v.getId()) {
            case R.id.button:
                edt.setText("hello");
                break;

            case R.id.red:
                if (ch_red.isChecked() && !ch_blue.isChecked())
                    textView.setText(((CheckBox) v).getText().toString() + "Choosed");
                else if (ch_red.isChecked() && ch_blue.isChecked())
                    textView.setText("blue and red choosed");
                else if (!ch_red.isChecked() && ch_blue.isChecked())
                    textView.setText("blue Choosed");
                else
                    textView.setText("Nothing Choosed");
                break;
            case R.id.blue:
                if (ch_red.isChecked() && !ch_blue.isChecked())
                    textView.setText("red Choosed");
                else if (ch_red.isChecked() && ch_blue.isChecked())
                    textView.setText("blue and red choosed");
                else if (!ch_red.isChecked() && ch_blue.isChecked())
                    textView.setText(((CheckBox) v).getText().toString()+"Choosed");
                else
                    textView.setText("Nothing Choosed");
                break;
            case R.id.yes:
                //...
                textView.setText(((RadioButton) v).getText().toString());
                break;

            case R.id.no:
                textView.setText(((RadioButton) v).getText().toString());
                break;


        }


    }
}
