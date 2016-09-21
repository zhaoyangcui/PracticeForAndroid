package app.company.com.a20_sqlite;

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
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static WordsDBHelper getWords_db_helper() {
        return words_db_helper;
    }

    private static WordsDBHelper words_db_helper;
    private ListView lv_word;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        words_db_helper = new WordsDBHelper(this);
        lv_word = (ListView) findViewById(R.id.lv_words);
        inflater = getLayoutInflater().from(MainActivity.this);
        //在列表中显示全部单词
        List<Map<String,String>> items = getAll();
        setWordsListView(items);
        registerForContextMenu(lv_word);
    }

    private List<Map<String,String>> getAll(){
        List<Map<String,String>> items = new LinkedList<>();
        SQLiteDatabase sqldb = words_db_helper.getReadableDatabase();
        String order = Words.Word.COLUMN_NAME_WORD+" DESC";
        Cursor c = sqldb.query(Words.Word.TABLE_NAME,new String[]{Words.Word.COLUMN_NAME_WORD,Words.Word.COLUMN_NAME_MEANING,Words.Word.COLUMN_NAME_SAMPLE},null,null,null,null,order);
        String str_word = "";
        String str_meaning = "";
        int[] a = {};
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
        SimpleAdapter adapter = new SimpleAdapter(this,items,R.layout.list_view_item,new String[]{
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
        getMenuInflater().inflate(R.menu.menu_sql,menu);
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
            case R.id.menu_refresh:
                //刷新界面
                setWordsListView(getAll());
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

    private void updateWords(String old_name,String name,String meaning,String sample){
        String sql = "update words set word = ?,meaning = ?,sample = ? where word = ?";
        SQLiteDatabase db = words_db_helper.getWritableDatabase();
        db.execSQL(sql,new String[]{name,meaning,sample,old_name});
    }
    private void deleteAll(){
        String sql = "delete from words";
        SQLiteDatabase db = words_db_helper.getWritableDatabase();
        db.execSQL(sql);
    }

    private void inflateAddDialog(){
        View add_view = inflater.inflate(R.layout.dialog_add,null);
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
        final View view_delete = inflater.inflate(R.layout.dialog_delete,null);
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
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.context_menu_edit:
                inflateEditDialog(item);
                break;
            case R.id.context_menu_del:
                deleteSelectedWord(item);
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void inflateEditDialog(MenuItem item){
        View itemView;
        final String str_word;
        String str_meaning;
        String str_sample;
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        itemView = info.targetView;
        str_word = ((TextView)itemView.findViewById(R.id.tv_name)).getText().toString();
        str_meaning = ((TextView)itemView.findViewById(R.id.tv_meaning)).getText().toString();
        str_sample = ((TextView)itemView.findViewById(R.id.tv_sample)).getText().toString();
        Log.d("Debug",str_word+str_meaning+str_sample);
        View view = getLayoutInflater().from(this).inflate(R.layout.dialog_add,null);
        final EditText et_word = (EditText) view.findViewById(R.id.et_word);
        final EditText et_meaning = (EditText) view.findViewById(R.id.et_meaning);
        final EditText et_sample = (EditText) view.findViewById(R.id.et_sample);
        et_word.setText(str_word);
        Log.d("Debug","*************"+str_word);
        et_meaning.setText(str_meaning);
        et_sample.setText(str_sample);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("修改单词数据").setView(view).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setPositiveButton("修改", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String str_new_word = "";
                String str_new_meaning = "";
                String str_new_sample = "";
                str_new_word = et_word.getText().toString();
                str_new_meaning = et_meaning.getText().toString();
                str_new_sample = et_sample.getText().toString();
                updateWords(str_word,str_new_word,str_new_meaning,str_new_sample);
                setWordsListView(getAll());
            }
        }).show();
    }

    private void deleteSelectedWord(MenuItem item){
        String str_delete_word = "";
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        View view = info.targetView;
        str_delete_word = ((TextView)view.findViewById(R.id.tv_name)).getText().toString();
        deleteWords(str_delete_word);
        setWordsListView(getAll());
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu,menu);
    }
}
