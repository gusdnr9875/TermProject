package com.example.termproject2;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



public class Signup extends AppCompatActivity {

    DBHelper helper;
    SQLiteDatabase db;


    EditText id;
    EditText passwd;
    EditText familycode;
    RadioGroup radioGroup;
    RadioButton radioButton,radioButton2,radioButton3,radioButton4;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        helper = new DBHelper(this);

        try{
            db = helper.getWritableDatabase();
        }
        catch (SQLException ex){
            db= helper.getReadableDatabase();
        }

        id=(EditText)findViewById(R.id.editText2);
        passwd=(EditText)findViewById(R.id.editText4);
        familycode=(EditText)findViewById(R.id.editText3);

        radioButton=(RadioButton)findViewById(R.id.radioButton);
        radioButton2=(RadioButton)findViewById(R.id.radioButton2);
        radioButton3=(RadioButton)findViewById(R.id.radioButton4);
        radioButton4=(RadioButton)findViewById(R.id.radioButton2);

        radioGroup=(RadioGroup)findViewById(R.id.radiogroup);

        button=(Button)findViewById(R.id.button);
    }


    void sign(View view){
        String id2 = id.getText().toString();
        String passwd2 = passwd.getText().toString();
        String familycode2 = familycode.getText().toString();
        String family1;
        if(radioButton.isChecked())
            family1="1";
        else if (radioButton2.isChecked())
            family1="2";
        else
            family1="3";


        db.execSQL("INSERT INTO contacts VALUES ( null, '" + id2 +"', '"+ passwd2 + "', '"+familycode2+"', '"+0+"', '"+"미실시"+ "');");
        Toast.makeText(getApplicationContext(),"성공적으로 추가되었습니다.",Toast.LENGTH_SHORT).show();

        finish();
    }

}
