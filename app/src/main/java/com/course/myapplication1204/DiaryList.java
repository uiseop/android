package com.course.myapplication1204;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class DiaryList extends AppCompatActivity {
    MyAdapter adapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_list);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(DiaryList.this);
        recyclerView.setLayoutManager(layoutManager);


        String[] columns = new String[]{"_id", "name", "stdin","lati","longi","image"};
        ArrayList<DiaryInfo> items = new ArrayList<>();
        Cursor c = getContentResolver().query(MyContentProvider.CONTENT_URI, columns, null, null, null, null);
        if (c != null) {

            while(c.moveToNext()) {
                int id = c.getInt(0);
                String name = c.getString(1);
                String stdin = c.getString(2);
                String lati = c.getString(3);
                String longi = c.getString(4);
                byte[] image = c.getBlob(5);
                items.add(new DiaryInfo(name,stdin,lati,longi,image));
            };
            c.close();
        }
        adapter = new MyAdapter(items);
        recyclerView.setAdapter(adapter);

    }

    // convert from byte array to bitmap
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

//    public void getDB(View view) {
//        String[] columns = new String[]{"_id", "name", "stdin","lati", "longi"};
//        ArrayList<DiaryInfo> items = new ArrayList<DiaryInfo>();
//        Cursor c = getContentResolver().query(MyContentProvider.CONTENT_URI, columns, null, null, null, null);
//        if (c != null) {
//
//            while(c.moveToNext()) {
//                int id = c.getInt(0);
//                String name = c.getString(1);
//                String stdin = c.getString(2);
//                String lati = c.getString(3);
//                String longi = c.getString(4);
//                items.add(new DiaryInfo(name,stdin,lati,longi));
//            };
//            c.close();
//        adapter.setItems(items);
//        adapter.notifyDataSetChanged();
//        }
//    }
}