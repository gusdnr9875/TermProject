package com.example.termproject2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBCalendar extends SQLiteOpenHelper {

    private static  final  String DATABASE_NAME = "mycalendar.db";
    private static final  int DATABASE_VERSION =2;

    public DBCalendar(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE calendar ( pid INTEGER PRIMARY KEY" +" AUTOINCREMENT, content TEXT, familycode TEXT);");
    }
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }
}
