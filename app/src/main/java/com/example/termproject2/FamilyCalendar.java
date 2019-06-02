package com.example.termproject2;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;





public class FamilyCalendar extends AppCompatActivity {
    static final  int GET_STRING=1;

    Intent in;
    String id;
    Button button;
    CalendarView calendarView;
    int year,month,day;
    TextView textView;
    String title,content,familycode;



    DBCalendar helper,helper2;
    SQLiteDatabase db,db2;
    Cursor cursor;


    String arr[]= new String[10];
    int arr_cnt=0;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("가족 일정");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family__calendar);
        button=findViewById(R.id.button9);
        calendarView= (CalendarView) findViewById(R.id.calendarView);
        listView=(ListView)findViewById(R.id.listview10);

        in = new Intent();
        in=getIntent();
        id=in.getStringExtra("ID2");
        familycode=in.getStringExtra("FAMILYCODE2");

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

        helper =new DBCalendar(this);

        try{
            db = helper.getWritableDatabase();
        }
        catch (SQLException ex){
            db= helper.getReadableDatabase();
        }



        cursor = db.rawQuery("SELECT  content, familycode FROM calendar WHERE familycode='"+familycode +"';",null);

        while(cursor.moveToNext() ){
            String asd=cursor.getString(0);
            arr[arr_cnt++]=asd;

        }


        String arr2[]=new String[arr_cnt];
        for(int i=0;i<arr_cnt;i++){
            arr2[i]=arr[i];
        }
        if(arr_cnt>0) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr2);
            listView.setAdapter(adapter);

        }



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
                String cona=Integer.toString(year)+"/"+Integer.toString(month)+"/"+Integer.toString(day)+": "+title+"-"+content;

                Toast.makeText(getApplicationContext(),arr[0],Toast.LENGTH_SHORT).show();

                db.execSQL("INSERT INTO calendar VALUES ( null, '" + cona+"', '"+ familycode +"');");

               // finish();


            }

    }



}
