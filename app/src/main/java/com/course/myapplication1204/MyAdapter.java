package com.course.myapplication1204;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView myText;

        MyViewHolder(View view){
            super(view);

            myText = view.findViewById(R.id.itemView);
        }
    }

    private ArrayList<DiaryInfo> mySchoolList;

    MyAdapter(ArrayList<DiaryInfo> schools){
        this.mySchoolList = schools;
    }

    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);

        return new MyViewHolder(v);
    }

    public void onBindViewHolder(MyAdapter.MyViewHolder holder, final int position){
        MyViewHolder myViewHolder = (MyViewHolder)holder;

        myViewHolder.myText.setText(mySchoolList.get(position).getName());


        myViewHolder.myText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String array[] = {mySchoolList.get(position).getLati(),mySchoolList.get(position).getLongi()};

                Context context = v.getContext();
                Intent intent = new Intent(context.getApplicationContext(), DiaryContents.class);
                intent.putExtra("name",mySchoolList.get(position).getName());
                intent.putExtra("stdin",mySchoolList.get(position).getStdin());
                intent.putExtra("array",array);
                intent.putExtra("image",mySchoolList.get(position).getImage());
                context.startActivity(intent);
            }
        });
    }

    public  int getItemCount(){
        return mySchoolList.size();
    }

}
