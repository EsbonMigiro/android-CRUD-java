package com.example.crudsqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper( Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table UserDetails(name Text primary key, contact Text, dof Text)" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists UserDetails");

    }

    public Boolean inserUserDetails(String name, String contact, String dof){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("contact", contact);
        contentValues.put("dof", dof);

        long result = DB.insert("UserDetails", null, contentValues);
        return result != -1;
    }
    public Boolean updateUserDetails(String name, String contact, String dof){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("contact", contact);
        contentValues.put("dof", dof);

        Cursor cursor = DB.rawQuery("select * from UserDetails where name = ?", new String[]{name});

        if(cursor.getCount()>0){
            long result = DB.update("UserDetails", contentValues, "name=?", new String[] {name});
            return result != -1;

        }else {
            return false;
        }


    }

    public Boolean deleteUserDetails(String name){
        SQLiteDatabase DB = this.getWritableDatabase();


        Cursor cursor = DB.rawQuery("select * from UserDetails where name = ?", new String[]{name});

        if(cursor.getCount()>0){
            long result = DB.delete("UserDetails", "name=?", new String[] {name});
            return result != -1;

        }else {
            return false;
        }


    }

    public Cursor getData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from userDetails", null);
        return cursor;
    }



}
