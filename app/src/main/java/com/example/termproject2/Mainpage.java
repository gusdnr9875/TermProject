package com.example.termproject2;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Mainpage extends AppCompatActivity {

    String id,skin="0";
    TextView textView;
    static final int GET_STRING =1;
    String familycode;
    DBHelper helper;
    SQLiteDatabase db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("메인 페이지");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        textView=(TextView)findViewById(R.id.textView);
        Intent in;
         in = new Intent();
         in=getIntent();
        id=in.getStringExtra("ID");
         textView.setText(id.toString()+"님 환영합니다.");

        helper =new DBHelper(this);

        try{
            db = helper.getWritableDatabase();
        }
        catch (SQLException ex){
            db= helper.getReadableDatabase();
        }

        cursor = db.rawQuery("SELECT  id, familycode, current FROM contacts WHERE id='"+id +"';",null);

        while(cursor.moveToNext() ){
            familycode= cursor.getString(1);
            break;
        }




    }



    /*나무 버튼 클릭*/
    public void tree(View target){
        Intent in = new Intent(Mainpage.this, FamilyTree.class);
        in.putExtra("ID1",id);
        in.putExtra("SKIN",skin);
        setResult(RESULT_OK,in);
        startActivityForResult(in,GET_STRING);

    }
    /*퀘스트 버튼 클릭*/
    public void quest(View target){
        Intent in2 = new Intent(getApplicationContext(), FamilyQuest.class);
        in2.putExtra("ID2",id);
        setResult(RESULT_OK,in2);
        startActivityForResult(in2,GET_STRING);
    }
    /*갤러리 버튼 클릭*/
    public void gallery(View target){
        Intent intent = new Intent(getApplicationContext(), com.example.termproject2.FamilyGallery.class);
        startActivity(intent);
    }
    /*일정 버튼 클릭*/
    public void calendar(View target){
        Intent in2 = new Intent(getApplicationContext(), FamilyCalendar.class);
        in2.putExtra("ID2",id);
        in2.putExtra("FAMILYCODE2",familycode);
        setResult(RESULT_OK,in2);
        startActivityForResult(in2,GET_STRING);
    }
    /*데이트코스 버튼 클릭*/
    public void date(View target){
        Intent intent = new Intent(getApplicationContext(), FamilyDate.class);
        startActivity(intent);
    }
    /*나가기 버튼 클릭(미완성)*/
    public void exit(View target){
       finish();
    }



    @Override
    protected  void onActivityResult(int requestCode,int resultCode, Intent data){
        if(requestCode==GET_STRING)
            if(resultCode==RESULT_OK){
                //skin="1";

            }
    }


}
