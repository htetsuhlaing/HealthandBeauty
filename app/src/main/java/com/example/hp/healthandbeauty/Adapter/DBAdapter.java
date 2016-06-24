package com.example.hp.healthandbeauty.Adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.hp.healthandbeauty.Model.DBModel;
import com.example.hp.healthandbeauty.MysqliteDB;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 4/4/2016.
 */
public class DBAdapter {
    private String title;
    private String jid;
    private String description;
    private String date;
    private String image;
    private String filter;
    MysqliteDB mysqliteDB;

    public DBAdapter(Context context){
        mysqliteDB = new MysqliteDB(context);
    }
    public DBAdapter(String jid,String image,String title,String description,String filter){
        this.image=image;
        this.jid=jid;
        this.title=title;
        this.description=description;
        this.filter=filter;
    }

    public long insert(DBModel dbModel) throws ParseException {
        long id;
        SQLiteDatabase sqldb= mysqliteDB.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(MysqliteDB.ID,dbModel.getID());
        contentValues.put(MysqliteDB.JID,dbModel.getJID());
        contentValues.put(MysqliteDB.Title,dbModel.getTitle());
        contentValues.put(MysqliteDB.Description,dbModel.getDescription());
        contentValues.put(MysqliteDB.Image,dbModel.getImage());
        contentValues.put(MysqliteDB.Filter,dbModel.getFilter());
        id=sqldb.insert(MysqliteDB.TABLE_NAME, null, contentValues);
        Log.d("mylog","insertimage : "+dbModel.getImage());
        sqldb.close();
        return id;
    }
    public List<DBModel>getData() throws ParseException {
        List<DBModel> dbModelList=new ArrayList<DBModel>();
        SQLiteDatabase sqldb=mysqliteDB.getWritableDatabase();
        String selectQuery=" SELECT * FROM "+MysqliteDB.TABLE_NAME;
        Log.d("mylog", "show " + selectQuery);
        Cursor c=sqldb.rawQuery(selectQuery,null);
        if(c.moveToFirst()){
            do{
                DBModel dbModel = new DBModel();
                dbModel.setID(c.getString(0));
                dbModel.setJID(c.getString(1));
                dbModel.setTitle(c.getString(2));
                dbModel.setDescription(c.getString(3));
                dbModel.setImage(c.getString(4));
                dbModel.setFilter(c.getString(6));
                dbModelList.add(dbModel);

            }while (c.moveToNext());
            Log.d("mylog","list "+dbModelList.size());
            Log.d("mylog","result "+dbModelList);
        }
        sqldb.close();
        return dbModelList;
    }
    public void deletedb(DBModel dbModel){
        SQLiteDatabase sqldb=mysqliteDB.getWritableDatabase();
        String deletequery="DELETE FROM "+ mysqliteDB.TABLE_NAME + " WHERE " +mysqliteDB.ID + "= '"+ dbModel.getID() + "'";
        Log.d("mylog","delete "+deletequery);
        Cursor cursor=sqldb.rawQuery(deletequery,null);
        Log.d("mylog","Length" + cursor.getCount());
        if(cursor.moveToFirst()){
        }
        sqldb.close();
return;
    }
    
}

