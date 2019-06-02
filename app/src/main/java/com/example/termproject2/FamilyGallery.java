package com.example.termproject2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.termproject2.R;

import java.io.InputStream;
import java.util.ArrayList;

public class FamilyGallery extends AppCompatActivity {
    FamilyGalleryAdapter adapter = new FamilyGalleryAdapter(FamilyGallery.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family__gallery);

        GridView family_album;
        Button button_plus;

        family_album = findViewById(R.id.family_album);
        button_plus = findViewById(R.id.gallery_plus);

        family_album.setAdapter(adapter);


        button_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                try{
                    InputStream input = getContentResolver().openInputStream(data.getData());
                    Bitmap image = BitmapFactory.decodeStream(input);
                    input.close();

                    adapter.plusImage(image);
                }
                catch(Exception e){ e.printStackTrace(); }
            }
        }
    }
}

/* 이미지 추가 어댑터 */
class FamilyGalleryAdapter extends BaseAdapter {
    ImageView imageView;
    Context mContext;
    private ArrayList<Bitmap> imgs = new ArrayList<>();

    public FamilyGalleryAdapter(Context context) {
        this.mContext = context;
    }

    public void plusImage(Bitmap bitmap){
        imgs.add(bitmap);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() { return imgs.size(); }

    @Override
    public Object getItem(int position) { return imgs.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView==null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(300, 300));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        }
        else{
            imageView = (ImageView) convertView;
        }
        imageView.setImageBitmap(imgs.get(position));

        return imageView;
    }

}