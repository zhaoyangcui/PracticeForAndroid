package app.company.com.a21_contentprovider;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Administrator on 2016/9/8.
 */
public class Words {
    public static final String AUTHORITY = "app.company.com.wordsprovider";

    public static abstract class Word implements BaseColumns {
        public static final String TABLE_NAME = "words";
        public static final String COLUMN_NAME_WORD = "word";
        public static final String COLUMN_NAME_MEANING = "meaning";
        public static final String COLUMN_NAME_SAMPLE = "sample";

        public static final String MIME_DIR_PREFIX = "vnd.android.cursor.dir";
        public static final String MIME_ITEM_PREFIX = "vnd.android.cursor.item";
        public static final String MINE_ITEM = "vnd.bistu.cs.se.word";
        public static final String MINE_TYPE_SINGLE = MIME_ITEM_PREFIX + "/" + MINE_ITEM;
        public static final String MINE_TYPE_MULTIPLE = MIME_DIR_PREFIX + "/" + MINE_ITEM;
        public static final String PATH_SINGLE = "word/#";
        public static final String PATH_MULTIPLE = "word";
        public static final String PATH_DELETE_WORD = "wordname";
        public static final String CONTENT_URI_STRING = "content://" + AUTHORITY + "/" + PATH_MULTIPLE;
        public static final String CONTENT_URI_STRING_DELETE_WORD = "content://" + AUTHORITY + "/" + PATH_DELETE_WORD;
        public static final Uri CONTENT_URI = Uri.parse(CONTENT_URI_STRING);
    }
}
