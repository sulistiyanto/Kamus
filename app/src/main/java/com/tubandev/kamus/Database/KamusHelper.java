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
import static com.tubandev.kamus.Database.DatabaseContract.TABLE_NAME_EN_IN;
import static com.tubandev.kamus.Database.DatabaseContract.TABLE_NAME_IN_EN;

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
        return this;
    }

    public void close() {
        dataBaseHelper.close();
    }

    public ArrayList<KamusModel> getListDataByName(String nama, String key) {
        database = dataBaseHelper.getWritableDatabase();
        String table;
        if (key.equals("in")) {
            table = TABLE_NAME_IN_EN;
        } else {
            table = TABLE_NAME_EN_IN;
        }

        Cursor cursor = database.rawQuery("select * from " + table + " where " + WORD + " like '%"
                + nama + "%' order by " + _ID + " asc limit 30", null);
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
        dataBaseHelper.close();
        return arrayList;
    }

    public void insertTransaction(KamusModel kamusModel, String key) {
        String table;
        if (key.equals("in")) {
            table = TABLE_NAME_IN_EN;
        } else {
            table = TABLE_NAME_EN_IN;
        }
        String sql = "INSERT INTO " + table + " (" + WORD + ", " + DESC
                + ") VALUES (?, ?)";
        SQLiteStatement stmt = database.compileStatement(sql);
        stmt.bindString(1, kamusModel.getWord());
        stmt.bindString(2, kamusModel.getDescription());
        stmt.execute();
        stmt.clearBindings();
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

}
