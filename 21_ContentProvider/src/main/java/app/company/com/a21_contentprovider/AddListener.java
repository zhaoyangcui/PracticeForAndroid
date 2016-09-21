package app.company.com.a21_contentprovider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.view.View;

/**
 * Created by Administrator on 2016/9/21.
 */
public class AddListener implements View.OnClickListener {
    private ContentResolver resolver = MainActivity.getResolver();
    @Override
    public void onClick(View v) {
        String str_word = "Banana";
        String str_meaning = "香蕉";
        String str_sample = "This is a banana";
        ContentValues values = new ContentValues();
        values.put(Words.Word.COLUMN_NAME_WORD,str_word);
        values.put(Words.Word.COLUMN_NAME_MEANING,str_meaning);
        values.put(Words.Word.COLUMN_NAME_SAMPLE,str_sample);
        Uri newUri = resolver.insert(Words.Word.CONTENT_URI,values);
    }
}
