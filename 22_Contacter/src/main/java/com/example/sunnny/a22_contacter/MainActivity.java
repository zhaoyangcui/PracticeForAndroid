package com.example.sunnny.a22_contacter;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleAdapter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Cursor cursor_name;
    private Cursor cursor_number;
    private ContentResolver contentResolver;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contentResolver = getContentResolver();
        btn = (Button) findViewById(R.id.contact);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id;
                String name;
                String number;
                cursor_name = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
                while (cursor_name.moveToNext()) {
                    id = cursor_name.getString(cursor_name.getColumnIndex(ContactsContract.Contacts._ID));
                    name = cursor_name.getString(cursor_name.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    Log.d("Debug", name);
                    cursor_number = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + id, null, null);
                    while (cursor_number.moveToNext()) {
                        number = cursor_number.getString(cursor_number.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        if (!number.equals("") || number != null) {
                            Log.d("Debug", number);
                        } else
                        number = cursor_number.getString(cursor_number.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    }
                }
            }
        });
    }
}
