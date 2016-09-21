package com.example.sunnny.vocabulary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sunnny on 2016/9/10.
 */
public class WordsDBHelper extends SQLiteOpenHelper {
    private final String str_create = "create table tb_words (id int primary key autoincrement,words string,translate string,explanation string)";
    private final String str_drop = "drop table tb_word";
    public WordsDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(str_create);
    }
//数据库升级
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(str_drop);
        onCreate(db);
    }
}
