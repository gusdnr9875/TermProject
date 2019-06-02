package com.example.termproject2;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;
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

    // notification
    public static final int NOTIFICATION_ID =1;
   //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("로그인 화면");
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

        // 알림설정
        NotificationCompat.Builder builder= new NotificationCompat.Builder(this,"default");
        builder.setSmallIcon(R.drawable.heart);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);

        builder.setContentIntent(pendingIntent);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.heart));
        builder.setContentTitle("FamilyCation");
        builder.setContentText("1");
        builder.setDefaults(Notification.DEFAULT_VIBRATE);
        builder.setPriority(Notification.PRIORITY_DEFAULT);
        builder.setAutoCancel(true);
        builder.setSubText("퀘스트를 완료해 멋진 가족나무를 만들어 보세요");

        NotificationManager notificationManager =(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID,builder.build());

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