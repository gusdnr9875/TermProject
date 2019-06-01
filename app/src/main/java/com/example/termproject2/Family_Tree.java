package com.example.termproject2;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class Family_Tree extends AppCompatActivity {
    Intent in;
    DBHelper helper,helper2;
    SQLiteDatabase db,db2;
    Cursor cursor;
    Cursor cursor2;
    String id, familycode;
    ImageView imageView;
    ListView listView;
    String[] arr= new String[10];

    int cnt=1,total=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("가족 나무");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family__tree);
        imageView=(ImageView)findViewById(R.id.imageView);
        in = new Intent();
        in=getIntent();
        id=in.getStringExtra("ID1");

        listView=(ListView)findViewById(R.id.listView);
        helper =new DBHelper(this);
        helper2 =new DBHelper(this);

        try{
            db = helper.getWritableDatabase();
        }
        catch (SQLException ex){
            db= helper.getReadableDatabase();
        }

        cursor = db.rawQuery("SELECT  id, familycode, current FROM contacts WHERE id='"+id +"';",null);

        while(cursor.moveToNext() ){
            String asd=cursor.getString(0);


            if(id.equals(asd)){
               familycode=cursor.getString(1);
                break;
            }
        }
        arr[0]="가족코드: "+familycode;



        try{
            db2 = helper2.getWritableDatabase();
        }
        catch (SQLException ex){
            db2= helper2.getReadableDatabase();
        }

        cursor2=db2.rawQuery("SELECT  id, familycode, point FROM contacts WHERE familycode='"+familycode +"';",null);
        cursor2.moveToFirst();
        while(cursor2.moveToNext() ){
            String id2=cursor2.getString(0);
            String family=cursor2.getString(1);
            int point=cursor2.getInt(2);

            total+= point;
            arr[cnt]=id2+" : "+Integer.toString(point)+"EXP   ";
            cnt++;


        }
        arr[cnt]="총경험치 : "+Integer.toString(total)+"EXP    ";
        cnt++;
        String arr2[]=new String[cnt];
        for(int i=0;i<cnt;i++){
            arr2[i]=arr[i];
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arr2);
        listView.setAdapter(adapter);



        total=1;
        if(total>0)
            imageView.setImageResource(R.drawable.b);

    }



}
