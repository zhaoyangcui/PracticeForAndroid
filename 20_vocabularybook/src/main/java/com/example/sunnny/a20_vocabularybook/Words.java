package com.example.sunnny.a20_vocabularybook;

import android.provider.BaseColumns;

/**
 * Created by sunnny on 2016/9/8.
 */
public class Words {
    public static abstract class Word implements BaseColumns {
        public static final String TABLE_NAME = "words";
        public static final String COLUMN_NAME_WORD = "word";
        public static final String COLUMN_NAME_MEANING = "meaning";
        public static final String COLUMN_NAME_SAMPLE = "sample";
    }
}

