package com.example.sunnny.a20_vocabularybook;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private WordsDBHelper words_db_helper;
    private ListView lv_word;
    private LayoutInflater inflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        words_db_helper = new WordsDBHelper(this);
        lv_word = (ListView) findViewById(R.id.lv_words);
        registerForContextMenu(lv_word);
        inflater = getLayoutInflater().from(MainActivity.this);
        //在列表中显示全部单词
        List<Map<String,String>> items = getAll();
        setWordsListView(items);
    }
    private List<Map<String,String>> getAll(){
        List<Map<String,String>> items = new LinkedList<>();
        SQLiteDatabase sqldb = words_db_helper.getReadableDatabase();
        String order = Words.Word.COLUMN_NAME_WORD+" DESC";
        Cursor c = sqldb.query(Words.Word.TABLE_NAME,new String[]{Words.Word.COLUMN_NAME_WORD,Words.Word.COLUMN_NAME_MEANING,Words.Word.COLUMN_NAME_SAMPLE},null,null,null,null,order);
        String str_word = "";
        String str_meaning = "";
        String str_sample = "";
        Map<String,String> map_item;
        while (c.moveToNext()){
            map_item = new HashMap<>();
            str_word = c.getString(c.getColumnIndex(Words.Word.COLUMN_NAME_WORD));
            str_meaning = c.getString(c.getColumnIndex(Words.Word.COLUMN_NAME_MEANING));
            str_sample = c.getString(c.getColumnIndex(Words.Word.COLUMN_NAME_SAMPLE));
            map_item.put(Words.Word.COLUMN_NAME_WORD,str_word);
            map_item.put(Words.Word.COLUMN_NAME_MEANING,str_meaning);
            map_item.put(Words.Word.COLUMN_NAME_SAMPLE,str_sample);
            Log.d("Debug",str_word+"*"+str_meaning+"*"+str_sample);
            items.add(map_item);
        }
        return items;
    }

    private void setWordsListView(List<Map<String,String>> items){
        SimpleAdapter adapter = new SimpleAdapter(this,items,R.layout.listview_item,new String[]{
                Words.Word.COLUMN_NAME_WORD,
                Words.Word.COLUMN_NAME_MEANING,
                Words.Word.COLUMN_NAME_SAMPLE},new int[]{R.id.tv_name,R.id.tv_meaning,R.id.tv_sample});
        ListView list = (ListView) findViewById(R.id.lv_words);
        list.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        words_db_helper.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_db,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menu_add:
                inflateAddDialog();
                break;
            case R.id.menu_delete:
                inflateDeleteDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void insertWords(String name,String meaning,String sample){
        Log.d("Debug","insert:"+name);
        String sql = "insert into words(word,meaning,sample)values(?,?,?)";
        SQLiteDatabase db = words_db_helper.getWritableDatabase();
        db.execSQL(sql,new String[]{name,meaning,sample});
    }

    private void deleteWords(String name){
        String sql = "delete from words where word = ?";
        SQLiteDatabase db = words_db_helper.getWritableDatabase();
        db.execSQL(sql,new String[]{name});
    }

    private void deleteAll(){
        String sql = "delete from words";
        SQLiteDatabase db = words_db_helper.getWritableDatabase();
        db.execSQL(sql);
    }

    private void inflateAddDialog(){
        View add_view = inflater.inflate(R.layout.add_dialog,null);
        final EditText et_name = (EditText) add_view.findViewById(R.id.et_word);
        final EditText et_meaning = (EditText) add_view.findViewById(R.id.et_meaning);
        final EditText et_sample = (EditText) add_view.findViewById(R.id.et_sample);
        AlertDialog.Builder alert_builder = new AlertDialog.Builder(this);
        alert_builder.setView(add_view).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setPositiveButton("增加", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String word = et_name.getText().toString();
                String meaning = et_meaning.getText().toString();
                String sample = et_sample.getText().toString();
                insertWords(word,meaning,sample);
                Toast.makeText(MainActivity.this,"插入成功",Toast.LENGTH_LONG).show();
                //刷新当前界面
                setWordsListView(getAll());
            }
        }).show();

    }

    private void inflateDeleteDialog(){
        final View view_delete = inflater.inflate(R.layout.delete_dialog,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("全部删除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteAll();
                setWordsListView(getAll());
                Toast.makeText(MainActivity.this,"全部删除",Toast.LENGTH_LONG).show();
            }
        }).setNegativeButton("删除", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                EditText et_delete = (EditText) view_delete.findViewById(R.id.et_del_word);
                String str_word = et_delete.getText().toString();
                deleteWords(str_word);
                setWordsListView(getAll());
                Toast.makeText(MainActivity.this,"删除:"+str_word,Toast.LENGTH_LONG).show();
            }
        }).setView(view_delete).show();

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_context,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onContextItemSelected(item);
    }
}
