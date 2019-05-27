package com.example.termproject2;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    DBHelper helper;
    SQLiteDatabase db;

    EditText editText;
    EditText editText2;
    String id,passwd,familycode,member;
    static final int GET_STRING =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper =new DBHelper(this);


        try{
            db = helper.getWritableDatabase();
        }
        catch (SQLException ex){
            db= helper.getReadableDatabase();
        }

        editText=findViewById(R.id.id);
        editText2=findViewById(R.id.passwd);


    }

    public  void Login(View view)  {
        Cursor cursor;

        id=editText.getText().toString(); //값을 얻어옵니다.
        passwd = editText2.getText().toString(); //값을 얻어 옵니다.



       cursor = db.rawQuery("SELECT  id, passwd FROM contacts WHERE id='"+id +"';",null);
       Toast.makeText(getApplicationContext(),"성asd ",Toast.LENGTH_SHORT).show();

       while(cursor.moveToNext() ){
           String asd=cursor.getString(1);
           Toast.makeText(getApplicationContext(),id+" "+asd,Toast.LENGTH_SHORT).show();

           if(passwd.equals(asd)){
               Intent in = new Intent(MainActivity.this,Mainpage.class);
               in.putExtra("ID",id);
               setResult(RESULT_OK,in);
               startActivityForResult(in,GET_STRING);
               break;
           }
       }

    }

    public void signup(View view){
        Intent in = new Intent(MainActivity.this,Signup.class);
        startActivity(in);
    }

}