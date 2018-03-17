package com.tubandev.kamus.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.tubandev.kamus.Model.KamusModel;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.tubandev.kamus.Database.DatabaseContract.MahasiswaColumns.DESC;
import static com.tubandev.kamus.Database.DatabaseContract.MahasiswaColumns.WORD;
import static com.tubandev.kamus.Database.DatabaseContract.TABLE_NAME;

/**
 * Created by sulistiyanto on 17/03/18.
 */

public class KamusHelper {

    private Context context;
    private DatabaseHelper dataBaseHelper;

    private SQLiteDatabase database;

    public KamusHelper(Context context) {
        this.context = context;
    }

    public KamusHelper open() throws SQLException {
        dataBaseHelper = new DatabaseHelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dataBaseHelper.close();
    }

    public ArrayList<KamusModel> getListDataByName(String nama) {
        Cursor cursor = database.query(TABLE_NAME, null, WORD + " LIKE ?",
                new String[]{nama}, null, null, _ID + " ASC", null);
        cursor.moveToFirst();
        ArrayList<KamusModel> arrayList = new ArrayList<>();
        KamusModel kamusModel;
        if (cursor.getCount() > 0) {
            do {
                kamusModel = new KamusModel();
                kamusModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                kamusModel.setWord(cursor.getString(cursor.getColumnIndexOrThrow(WORD)));
                kamusModel.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DESC)));

                arrayList.add(kamusModel);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public KamusModel getDataByName(String nama) {
        Cursor cursor = database.query(TABLE_NAME, null, WORD + " LIKE ?",
                new String[]{nama}, null, null, _ID + " ASC", null);
        cursor.moveToFirst();

        KamusModel kamusModel = new KamusModel();
        if (cursor.getCount() > 0) {
            do {
                kamusModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                kamusModel.setWord(cursor.getString(cursor.getColumnIndexOrThrow(WORD)));
                kamusModel.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DESC)));
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return kamusModel;
    }

    public void beginTransaction() {
        database.beginTransaction();
    }

    public void setTransactionSuccess() {
        database.setTransactionSuccessful();
    }

    public void endTransaction() {
        database.endTransaction();
    }

    public void insertTransaction(KamusModel kamusModel) {
        String sql = "INSERT INTO " + TABLE_NAME + " (" + WORD + ", " + DESC
                + ") VALUES (?, ?)";
        SQLiteStatement stmt = database.compileStatement(sql);
        stmt.bindString(1, kamusModel.getWord());
        stmt.bindString(2, kamusModel.getDescription());
        stmt.execute();
        stmt.clearBindings();
    }
}
