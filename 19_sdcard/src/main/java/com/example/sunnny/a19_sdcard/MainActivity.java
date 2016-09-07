package com.example.sunnny.a19_sdcard;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button bt_write;
    private Button bt_read;
    private static final String FILE_NAME = "file";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_read = (Button) findViewById(R.id.read);
        bt_write = (Button) findViewById(R.id.write);
        bt_write.setOnClickListener(this);
        bt_read.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.write:
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    File mydirectory = Environment.getExternalStorageDirectory();
                    File file = new File(mydirectory.getPath() + "/file.txt");
                    try {
                        FileOutputStream fos = openFileOutput(file.getName(),MODE_PRIVATE);
                        BufferedOutputStream bos = new BufferedOutputStream(fos);
                        bos.write("Cindy".getBytes());
                        bos.write("2014011446".getBytes());
                        bos.flush();
                        bos.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.read:
                File mydirectory = Environment.getExternalStorageDirectory();
                File file = new File(mydirectory.getPath() + "/file.txt");
                try {
                    FileInputStream fis = openFileInput(file.getName());
                    BufferedInputStream bis = new BufferedInputStream(fis);
                    StringBuilder stringBuilder = new StringBuilder();
                    byte[] readData = new byte[128];
                    while (bis.read(readData) != -1) {
                        stringBuilder.append(new String(readData));
                    }
                    bis.close();
                    Toast.makeText(this, stringBuilder.toString(), Toast.LENGTH_LONG).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
