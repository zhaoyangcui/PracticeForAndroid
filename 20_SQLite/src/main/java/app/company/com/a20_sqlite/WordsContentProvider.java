package app.company.com.a20_sqlite;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

public class WordsContentProvider extends ContentProvider {

    private static final int MULTIPLE_WORDS = 1;
    private static final int SINGLE_WORD = 2;
    private static final int DELETE_WORD = 3;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private WordsDBHelper words_db_helper;

    static {
        uriMatcher.addURI(Words.AUTHORITY, Words.Word.PATH_SINGLE, SINGLE_WORD);
        uriMatcher.addURI(Words.AUTHORITY, Words.Word.PATH_MULTIPLE, MULTIPLE_WORDS);
        uriMatcher.addURI(Words.AUTHORITY, Words.Word.PATH_DELETE_WORD,DELETE_WORD);
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        words_db_helper = MainActivity.getWords_db_helper();
        int count = 0;
        SQLiteDatabase db = words_db_helper.getReadableDatabase();
        String whereClause;
        switch (uriMatcher.match(uri)){
            case MULTIPLE_WORDS:
                count = db.delete(Words.Word.TABLE_NAME,selection,selectionArgs);
                break;
            case SINGLE_WORD:
                whereClause = Words.Word._ID+"="+uri.getPathSegments().get(1);
                count = db.delete(Words.Word.TABLE_NAME,whereClause,selectionArgs);
                break;
            case DELETE_WORD:
                //此处设置指定单词的名称进行删除
                whereClause = Words.Word.COLUMN_NAME_WORD+" = "+"\""+uri.getPathSegments().get(1)+"\"";
                db.execSQL("delete from "+Words.Word.TABLE_NAME+" where "+whereClause+";");
                break;
            default:
                throw new IllegalArgumentException("Unknow uri"+uri);
        }

        getContext().getContentResolver().notifyChange(uri,null);
        return count;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case MULTIPLE_WORDS:
                return Words.Word.MINE_TYPE_MULTIPLE;
            case SINGLE_WORD:
                return Words.Word.MINE_TYPE_SINGLE;
            default:
                throw new IllegalArgumentException("Unknow Uri:" + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        words_db_helper = MainActivity.getWords_db_helper();
        SQLiteDatabase db = words_db_helper.getWritableDatabase();
        Log.d("Debug","Insert");
        long id = db.insert(Words.Word.TABLE_NAME, null, values);
        if (id > 0) {
            Uri newUri = ContentUris.withAppendedId(Words.Word.CONTENT_URI, id);
            getContext().getContentResolver().notifyChange(newUri, null);
            return newUri;
        }
        throw new SQLException("Failed to insert row into"+uri);
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        words_db_helper = MainActivity.getWords_db_helper();
        Log.d("Debug","Query");
        SQLiteDatabase db = words_db_helper.getWritableDatabase();
        if (db == null){
            Log.d("Debug","DB is null");
        }
        Log.d("Debug","Query1");
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        Log.d("Debug","Query2");

        qb.setTables(Words.Word.TABLE_NAME);
        switch (uriMatcher.match(uri)) {
            case MULTIPLE_WORDS:
                return db.query(Words.Word.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
            case SINGLE_WORD:
                qb.appendWhere(Words.Word._ID + "=" + uri.getPathSegments().get(1));
                return qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
            default:
                throw new IllegalArgumentException("Unkonwn Uri:" + uri);
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        String segment;
        words_db_helper = MainActivity.getWords_db_helper();
        SQLiteDatabase db = words_db_helper.getReadableDatabase();
        int count = 0;
        switch (uriMatcher.match(uri)){
            case MULTIPLE_WORDS:
                count = db.update(Words.Word.TABLE_NAME,values,selection,selectionArgs);
                break;
            case SINGLE_WORD:
                segment = uri.getPathSegments().get(1);
                count = db.update(Words.Word.TABLE_NAME, values, Words.Word._ID+"="+segment, selectionArgs);
                break;
            case DELETE_WORD:
                segment = uri.getPathSegments().get(1);
                db.execSQL("Update "+Words.Word.TABLE_NAME+" set "+Words.Word.COLUMN_NAME_MEANING+"= "+"\""+values.get(Words.Word.COLUMN_NAME_MEANING) +"\" , "+ Words.Word.COLUMN_NAME_SAMPLE+"= "+"\""+values.get(Words.Word.COLUMN_NAME_SAMPLE)+"\" where word = "+"\""+segment+"\"");
                break;
            default:
                throw new IllegalArgumentException("Unkonwn Uri:" + uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return count;
    }
}
