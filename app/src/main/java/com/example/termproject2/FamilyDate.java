package com.example.termproject2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FamilyDate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family__date);
    }

    public  void  link1(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://korean.visitseoul.net/attractions/%EC%97%AC%EC%9D%98%EB%8F%84-%EA%B3%B5%EC%9B%90_/2161"));
        startActivity(intent);

    }
    public  void  link2(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.63.co.kr/"));
        startActivity(intent);
    }
}
