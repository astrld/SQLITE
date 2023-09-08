package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_Name = "userDB";

    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "users";

    private static final String FIRST_NAME_COL = "firstName";

    private static final String LAST_NAME_COL = "lastName";

    private static final String PHONE_NUMBER_COL = "phoneNumber";

    public DBHandler(Context context){
        super(context,DB_Name,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String query = "CREATE TABLE " + TABLE_NAME + "("
                + FIRST_NAME_COL + "TEXT,"
                + LAST_NAME_COL + "TEXT,"
                + PHONE_NUMBER_COL + "TEXT)";
        db.execSQL(query);
    }

    public void addNewUser(String firstName, String lastName, String phoneNumber){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(FIRST_NAME_COL,firstName);
        values.put(LAST_NAME_COL,lastName);
        values.put(PHONE_NUMBER_COL,phoneNumber);

        db.insert(TABLE_NAME,null,values);

        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


}
