package com.course.myapplication1204;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Blob;
import java.util.ArrayList;

public class DBManager extends SQLiteOpenHelper {
    static final String DIARY_DB = "Diary.db";
    static final String DIARY_TABLE = "Diaries";
    private static final String TAG = "DBManager";
    private SQLiteDatabase db;
    Context context = null;
    private static DBManager dbManager = null;
    static final String CREATE_DB = " CREATE TABLE " + DIARY_TABLE + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " + " name TEXT NOT NULL, stdin TEXT NOT NULL, lati TEXT NOT NULL, longi TEXT NOT NULL, image BLOB );";
    public static DBManager getInstance(Context context) {
        if(dbManager == null) {
            dbManager = new DBManager(context, DIARY_DB, null, 1);
        }
        return dbManager;
    }

    public DBManager(Context context, String dbName, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbName, factory, version);
        this.context = context;
    }
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
    }
    public long insert(ContentValues addValue) {
        return getWritableDatabase().insert(DIARY_TABLE, null, addValue);
    }
    public Cursor query(String [] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy){
        return getReadableDatabase().query(DIARY_TABLE, columns, selection, selectionArgs, groupBy, having, orderBy);
    }
    public int delete(String whereClause, String[] whereArgs)
    {
        return getWritableDatabase().delete(DIARY_TABLE, whereClause, whereArgs);
    }
//
//    public ArrayList<DiaryInfo> selectAll() {
//        ArrayList<DiaryInfo> result = new ArrayList<DiaryInfo>();
//
//        try {
//            Cursor cursor = db.rawQuery("select NAME, AUTHOR, CONTENTS from " + DIARY_TABLE, null);
//            for (int i = 0; i < cursor.getCount(); i++) {
//                cursor.moveToNext();
//                String name = cursor.getString(0);
//                String stdin = cursor.getString(1);
//                String lati = cursor.getString(2);
//                String longi = cursor.getString(3);
//                byte[] image = cursor.getBlob(4);
//
//                DiaryInfo info = new DiaryInfo(name, stdin, lati,longi,image);
//                result.add(info);
//            }
//
//        } catch(Exception ex) {
//            Log.e(TAG, "Exception in executing insert SQL.", ex);
//        }
//
//        return result;
//    }



}
