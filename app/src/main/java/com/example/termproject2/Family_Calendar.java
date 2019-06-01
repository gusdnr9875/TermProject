package com.example.termproject2;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;





public class Family_Calendar extends AppCompatActivity {
    static final  int GET_STRING=1;

    Intent in;
    String id;
    Button button;
    CalendarView calendarView;
    int year,month,day;
    TextView textView;
    String title,content;
    String[] arr= {"","","","",""};
    int arr_cnt=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("가족 일정");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family__calendar);
        button=findViewById(R.id.button9);
        calendarView= (CalendarView) findViewById(R.id.calendarView);


        in = new Intent();
        in=getIntent();
        id=in.getStringExtra("ID2");


        final Calendar calendar=Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH);
        day=calendar.get(Calendar.DAY_OF_MONTH);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() { //캘린더뷰가 클릭되면 해당 리스너의
            public void onSelectedDayChange(CalendarView view, int year1,    //해당함수가 호출됩니다.
                                            int month1, int dayOfMonth1) {
                year = year1;   //설정된 날짜의 값을  year,month, dayofMonth에 저장해주는 것입니다.
                month = month1;
                day = dayOfMonth1;
                }
        });

    }


    public  void  reservation(View view){
        Intent intent = new Intent(getApplicationContext(), CalendarReservation.class);
        startActivityForResult(intent,GET_STRING);

    }

    @Override
    protected  void onActivityResult(int requestCode,int resultCode, Intent data){
        if(requestCode==GET_STRING)
            if(resultCode==RESULT_OK){
                title=data.getStringExtra("TITLE");
                content=data.getStringExtra("CONTENT");
                arr[arr_cnt++]=Integer.toString(year)+"/"+Integer.toString(month)+"/"+Integer.toString(day)+": "+title+"-"+content;

                Toast.makeText(getApplicationContext(),arr[0],Toast.LENGTH_SHORT).show();


            }

    }



}
