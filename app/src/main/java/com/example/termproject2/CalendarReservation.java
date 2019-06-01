package com.example.termproject2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CalendarReservation extends AppCompatActivity {
    EditText editText;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_reservation);
        editText=findViewById(R.id.editText);
        editText2=findViewById(R.id.editText5);


    }
    public  void  verify(View view){
        String title=editText.getText().toString();
        String content=editText2.getText().toString();

        Intent intent= new Intent();
        intent.putExtra("TITLE",title);
        intent.putExtra("CONTENT",content);
        setResult(RESULT_OK,intent);
        finish();
    }
    public  void cancel(View view){
        setResult(RESULT_CANCELED);
        finish();

    }
}
