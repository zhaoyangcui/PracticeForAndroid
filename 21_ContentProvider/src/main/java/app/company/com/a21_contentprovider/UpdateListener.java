package app.company.com.a21_contentprovider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.view.View;

/**
 * Created by Administrator on 2016/9/21.
 */
public class UpdateListener implements View.OnClickListener {
    private ContentResolver resolver = MainActivity.getResolver();
    @Override
    public void onClick(View v) {
        String strWord="Banana";
        String strMeaning="banana";
        String strSample="This banana is very nice.";
        ContentValues values = new ContentValues();
        values.put(Words.Word.COLUMN_NAME_WORD, strWord);
        values.put(Words.Word.COLUMN_NAME_MEANING, strMeaning);
        values.put(Words.Word.COLUMN_NAME_SAMPLE, strSample);
        Uri uri = Uri.parse(Words.Word.CONTENT_URI_STRING_DELETE_WORD + "/" + strWord);
        resolver.update(uri, values, null, null);


    }
}
