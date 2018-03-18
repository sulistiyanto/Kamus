package com.tubandev.kamus.Database;

import android.provider.BaseColumns;

/**
 * Created by sulistiyanto on 17/03/18.
 */

public class DatabaseContract {

    static String TABLE_NAME_IN_EN = "table_in_en";
    static String TABLE_NAME_EN_IN = "table_en_in";

    static final class MahasiswaColumns implements BaseColumns {

        static String WORD = "word";
        static String DESC = "description";

    }
}
