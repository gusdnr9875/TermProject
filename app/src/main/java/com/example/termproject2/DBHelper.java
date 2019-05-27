package com.example.termproject2;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DBHelper extends SQLiteOpenHelper {
    private static  final  String DATABASE_NAME = "mycontacts.db";
    private static final  int DATABASE_VERSION =2;

    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE contacts ( pid INTEGER PRIMARY KEY" +" AUTOINCREMENT, id TEXT, passwd TEXT, familycode TEXT, point INTEGER, current TEXT);");
    }
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }
}