package com.example.termproject2;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Family_Quest extends AppCompatActivity {
    DBHelper helper,helper2;
    SQLiteDatabase db,db2;
    Cursor cursor;
    Cursor cursor2;
    ListView listView;
    TextView textView;
    String[] arr= new String[10];
    int cnt=0,point=0;
    Intent in;
    String id, familycode;

    Button button;
    String quest[]={"부모님 안마해드리기 ","가족에게 맛있는 저녁 만들어 주기","가족들에게 감사인사 전하기" , "사랑한다고 말하기" ,"가족과 포옹하기", "이번 주 힘들었던 점들을 나누기",
            "가족들과 재밌는 영화보기" , "어렸을 적 사진 보기" , "다함께 집 청소 하기" , "함께 빨래널기" ,"가족과 포옹하기",
            "자녀에게 용돈주기","가족과 함께 친척에게 놀러가기"}; //13개
    int questCnt=0;



    String quest2[]={"가족들과 주말에 약속잡기","가족들과 재밌는 영화보기","다함께 집 청소 하기" ,"자녀에게 용돈주기","가족과 함께 친척에게 놀러가기"};
    int questCnt2=0,questTarget2=0;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("우리가족 퀘스트");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family__quest);
        textView=findViewById(R.id.textView7);
        button=findViewById(R.id.button7);

        in = new Intent();
        in=getIntent();
        id=in.getStringExtra("ID2");



        listView=(ListView)findViewById(R.id.listView2);
        helper =new DBHelper(this);
        helper2 =new DBHelper(this);

        try{
            db = helper.getWritableDatabase();
        }
        catch (SQLException ex){
            db= helper.getReadableDatabase();
        }
        cursor = db.rawQuery("SELECT  id, familycode, current,point FROM contacts WHERE id='"+id +"';",null);


        while(cursor.moveToNext() ){
            String asd=cursor.getString(0);


            if(id.equals(asd)){
                familycode=cursor.getString(1);
                point= cursor.getInt(3);
                break;
            }
        }


        try{
            db2 = helper2.getWritableDatabase();
        }
        catch (SQLException ex){
            db2= helper2.getReadableDatabase();
        }


        cursor2=db2.rawQuery("SELECT  id, current FROM contacts WHERE familycode='"+familycode +"';",null);
        cursor2.moveToFirst();
        while(cursor2.moveToNext() ){
            String id2=cursor2.getString(0);
            String current=cursor2.getString(1);

            arr[cnt]=id2+" : " + current +"   ";
            cnt++;
        }

        String arr2[]=new String[cnt];
        for(int i=0;i<cnt;i++){
            arr2[i]=arr[i];
        }

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arr2);
        listView.setAdapter(adapter);

    }

    public void week(View view){

        final PopupMenu popup=new PopupMenu(this,button);
        popup.getMenuInflater().inflate(R.menu.popup,popup.getMenu());

        popup.getMenu().add(quest[0]);
        popup.getMenu().add(quest[1]);
        popup.getMenu().add(quest[2]);

        popup.setOnMenuItemClickListener(
            new PopupMenu.OnMenuItemClickListener(){
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Toast.makeText(getApplicationContext(),item.getTitle()+"를 완료하셨습니다.\n"+"경험치10EXP를 획득하셨습니다.",Toast.LENGTH_SHORT).show();


                 //   db.execSQL("UPDATE contacts SET point=point+10 ,current=  "+ " WHERE id ='" + id + "';");
                    db.execSQL("UPDATE contacts SET point=point+10 ,current='"+item.getTitle()+"'  "+ " WHERE id ='" + id + "';");
                   // db.close();

                    questCnt+=3;
                    quest[0]=quest[questCnt];
                    quest[1]=quest[questCnt+1];
                    quest[2]=quest[questCnt+2];
                    if(questCnt>=9) questCnt=0;



                    return true;

                }
            });


        popup.show();

    }

}
