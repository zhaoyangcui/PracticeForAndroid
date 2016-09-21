package app.company.com.a21_contentprovider;

import android.content.ContentResolver;
import android.net.Uri;
import android.view.View;

/**
 * Created by Administrator on 2016/9/21.
 */
public class DeleteListener implements View.OnClickListener {
    private ContentResolver resolver = MainActivity.getResolver();
    @Override
    public void onClick(View v) {
        String word="Banana";
        Uri uri = Uri.parse(Words.Word.CONTENT_URI_STRING_DELETE_WORD + "/" + word);
        resolver.delete(uri, null, null);
    }
}
