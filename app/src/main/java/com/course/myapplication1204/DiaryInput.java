package com.course.myapplication1204;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class DiaryInput extends AppCompatActivity {

    private static final int REQUEST_CODE = 0;
    private ImageView imageView;
    int i = 0;
    Bitmap img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_input);

        Button button = findViewById(R.id.button6);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        imageView = findViewById(R.id.imageView);

        imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intents = new Intent();
                intents.setType("image/*");

                intents.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intents, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                try {
                    i = 1;
                    InputStream in = getContentResolver().openInputStream(data.getData());

                    img = BitmapFactory.decodeStream(in);
                    in.close();

                    imageView.setImageBitmap(img);
                } catch (Exception e) {

                }
            } else if (resultCode == RESULT_CANCELED) {
//                imageView.setImageBitmap();
                Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
            }
        }
    }


    public void addDB(View view) {
        ContentValues addValues = new ContentValues();

        addValues.put(MyContentProvider.NAME,
                ((EditText)findViewById(R.id.editText1)).getText().toString());

        addValues.put(MyContentProvider.STDIN,
                ((EditText)findViewById(R.id.editText2)).getText().toString());

        addValues.put(MyContentProvider.LATI,
                ((EditText)findViewById(R.id.editText3)).getText().toString());

        addValues.put(MyContentProvider.LONGI,
                ((EditText)findViewById(R.id.editText4)).getText().toString());
        Bitmap b;
        if(i==0)
            b = BitmapFactory.decodeResource(getResources(), R.drawable.noimagefound);
        else{
            b = img;
            i = 0;
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        byte[] img = bos.toByteArray();

        addValues.put(MyContentProvider.IMAGE,img);


        getContentResolver().insert(MyContentProvider.CONTENT_URI,
                addValues);
        Toast.makeText(getBaseContext(),
                "Record Added", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }




}