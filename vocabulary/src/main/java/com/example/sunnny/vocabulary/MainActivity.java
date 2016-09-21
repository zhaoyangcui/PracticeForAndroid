package com.example.sunnny.vocabulary;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private WordsDBHelper wordsDBHelper;
    private SQLiteDatabase SQLdb;
    private ListView lv_words;
    private SimpleAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wordsDBHelper = new WordsDBHelper(this,"words_db",null,1);
        SQLdb = wordsDBHelper.getWritableDatabase();
        lv_words = (ListView) findViewById(R.id.lv_words);
//        adapter = new SimpleAdapter(this,);
    }
    public void insert(String sql){
        SQLdb.execSQL(sql);
    }
    public Cursor select(String sql){
        Cursor cursor = SQLdb.query("words_db",new String[]{"word","translation","explanation"},null,null,null,null,null);
        return cursor;
    }

    public List cursor2list (Cursor cursor){
        List list_result = new ArrayList<Map<String,String>>();
        while(cursor.moveToNext()){
            Map map = new HashMap<String,String>();
            map.put(id,)

        }

    }



}
