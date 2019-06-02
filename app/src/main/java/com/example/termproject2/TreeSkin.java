package com.example.termproject2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TreeSkin extends AppCompatActivity {
    Intent in;
    String skin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_skin);

        in = new Intent();
        in=getIntent();
        skin=in.getStringExtra("SKIN");


    }

    public void gotree(View view){
        setResult(RESULT_CANCELED);
        finish();
    }
    public void  buy(View view){
        Intent intent= new Intent();
        intent.putExtra("SKIN","1");
        setResult(RESULT_OK,intent);
        finish();



    }
}
