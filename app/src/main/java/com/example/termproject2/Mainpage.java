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

}
