package com.course.myapplication1204;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DiaryContents extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_contents);

        TextView tx1 = (TextView)findViewById(R.id.textView10); /*TextView선언*/
        TextView tx2 = (TextView)findViewById(R.id.textView11);
        TextView tx3 = (TextView)findViewById(R.id.textView13);
        ImageView img = (ImageView)findViewById(R.id.imageView);

        Intent intent = getIntent(); /*데이터 수신*/

        String name = intent.getExtras().getString("name"); /*String형*/
        tx1.setText(name);

        String stdin = intent.getExtras().getString("stdin"); /*String형*/
        tx2.setText(stdin);

        String array[] = intent.getExtras().getStringArray("array"); /*배열*/

        Bitmap image = getImage(intent.getExtras().getByteArray("image"));
        img.setImageBitmap(image);


        String add_array="";
        for(int i=0;i<array.length;i++){
            add_array+=array[i]+" ";
        }
        tx3.setText(add_array);


        tx3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("latitude",array[0]);
                intent.putExtra("longitude",array[1]);
                startActivity(intent);


            }
        });


    }
    // convert from byte array to bitmap
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
}