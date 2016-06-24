package com.example.hp.healthandbeauty;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by HP on 4/4/2016.
 */
public class MysqliteDB extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="MyDBName.db";
    public static final String TABLE_NAME="favourite";
    private static final int DATABASE_VERSION=1;
    public static final String ID="id";
    public static final String JID="jid";
    public static final String Title="title";
    public static final String Description="description";
    public static final String Image="image";
    public static final String Date="date";
    public static final String Filter="filter";

    private static final String CREATE_TABLE="CREATE TABLE " + TABLE_NAME + " ( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + JID + " INT, " + Title + " TEXT, " + Description + " TEXT, " + Image + " TEXT, " + Date + " DATE, " + Filter + " TEXT " + ") ";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+ TABLE_NAME;
    public MysqliteDB(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
     db.execSQL(CREATE_TABLE);
        Log.d("mylog","create table ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
     db.execSQL(DROP_TABLE);
        Log.d("mylog","drop ");
    }
}
