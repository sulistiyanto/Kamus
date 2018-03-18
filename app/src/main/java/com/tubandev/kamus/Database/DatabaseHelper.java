package com.tubandev.kamus.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.tubandev.kamus.Database.DatabaseContract.MahasiswaColumns.DESC;
import static com.tubandev.kamus.Database.DatabaseContract.MahasiswaColumns.WORD;
import static com.tubandev.kamus.Database.DatabaseContract.TABLE_NAME_EN_IN;
import static com.tubandev.kamus.Database.DatabaseContract.TABLE_NAME_IN_EN;

/**
 * Created by sulistiyanto on 17/03/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "dbkamus";

    private static final int DATABASE_VERSION = 1;

    public static String CREATE_TABLE_IN_EN = "create table " + TABLE_NAME_IN_EN +
            " (" + _ID + " integer primary key autoincrement, " +
            WORD + " text not null, " +
            DESC + " text not null);";

    public static String CREATE_TABLE_EN_IN = "create table " + TABLE_NAME_EN_IN +
            " (" + _ID + " integer primary key autoincrement, " +
            WORD + " text not null, " +
            DESC + " text not null);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_IN_EN);
        db.execSQL(CREATE_TABLE_EN_IN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_IN_EN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_EN_IN);
        onCreate(db);
    }
}
