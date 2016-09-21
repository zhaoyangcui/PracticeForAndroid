package app.company.com.a21_contentprovider;

import android.content.ContentResolver;
import android.database.Cursor;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2016/9/21.
 */
public class QueryListener implements View.OnClickListener {
    private ContentResolver resolver = MainActivity.getResolver();

    public QueryListener(){
    }

    @Override
    public void onClick(View v) {
        Log.d("Debug","Query Click");
        Cursor cursor = resolver.query(Words.Word.CONTENT_URI,new String[]{
                Words.Word._ID,Words.Word.COLUMN_NAME_WORD,
                Words.Word.COLUMN_NAME_MEANING,
                Words.Word.COLUMN_NAME_SAMPLE},
                null,null,null);

        if (cursor == null){
            Log.d("Debug","找不到数据");
            return ;
        }

        StringBuilder builder = new StringBuilder();
        while (cursor.moveToNext()){
            builder.append("\nID:"+cursor.getInt(cursor.getInt(cursor.getColumnIndex(Words.Word._ID)))+",");
            builder.append("单词:"+cursor.getString(cursor.getColumnIndex(Words.Word.COLUMN_NAME_WORD))+",");
            builder.append("含义:"+cursor.getString(cursor.getColumnIndex(Words.Word.COLUMN_NAME_MEANING))+",");
            builder.append("解释:"+cursor.getString(cursor.getColumnIndex(Words.Word.COLUMN_NAME_SAMPLE)));
        }
        Log.d("Debug",builder.toString());
    }
}
