package com.example.termproject2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Mainpage extends AppCompatActivity {
    Intent in;
    String id;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        textView=(TextView)findViewById(R.id.textView);

         in = new Intent();
         in=getIntent();
        id=in.getStringExtra("ID");
         textView.setText(id.toString()+"님 환영합니다.");

    }



    /*나무 버튼 클릭*/
    public void tree(View target){
        Intent intent = new Intent(getApplicationContext(), Family_Tree.class);
        startActivity(intent);
    }
    /*퀘스트 버튼 클릭*/
    public void quest(View target){
        Intent intent = new Intent(getApplicationContext(), Family_Quest.class);
        startActivity(intent);
    }
    /*갤러리 버튼 클릭*/
    public void gallery(View target){
        Intent intent = new Intent(getApplicationContext(), Family_Gallery.class);
        startActivity(intent);
    }
    /*일정 버튼 클릭*/
    public void calendar(View target){
        Intent intent = new Intent(getApplicationContext(), Family_Calendar.class);
        startActivity(intent);
    }
    /*데이트코스 버튼 클릭*/
    public void date(View target){
        Intent intent = new Intent(getApplicationContext(), Family_Date.class);
        startActivity(intent);
    }
    /*나가기 버튼 클릭(미완성)*/
    public void exit(View target){
       finish();
    }

}
