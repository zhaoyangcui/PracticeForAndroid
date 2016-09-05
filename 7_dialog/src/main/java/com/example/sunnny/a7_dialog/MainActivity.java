package com.example.sunnny.a7_dialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button bt_dialog;
    private Button bt_login;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_dialog = (Button) findViewById(R.id.dialog);
        bt_login = (Button) findViewById(R.id.login);
        tv = (TextView) findViewById(R.id.tv);
        bt_dialog.setOnClickListener(this);
        bt_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final String[] mydata = new String[]{"确认", "取消"};

        switch (v.getId()) {
            case R.id.dialog:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("dialog").setItems(mydata, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        tv.setText("User choose" + mydata[i]);
                    }
                }).show();
                break;
            case R.id.login:
                LayoutInflater inflater = getLayoutInflater().from(MainActivity.this);
                final View login_view = inflater.inflate(R.layout.login, null);
                final AlertDialog.Builder alert_builder = new AlertDialog.Builder(this);
                    alert_builder.setView(login_view).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    }).setPositiveButton("登陆", new DialogInterface.OnClickListener() {
                        @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText et_username = (EditText) login_view.findViewById(R.id.et_name);
                        EditText et_password = (EditText) login_view.findViewById(R.id.et_password);
                        String username = et_username.getText().toString();
                        String password = et_password.getText().toString();
                        if (username.equals("cindy") && password.equals("1234")) {
                            Toast.makeText(MainActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this,"登陆失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                }).show();
                break;


        }
    }
}
