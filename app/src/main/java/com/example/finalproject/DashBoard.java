package com.example.finalproject;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

public class DashBoard extends Fragment {

    private SQLiteDatabase db;
    private Cursor cursor;
    private String tableName="ARTICLE";
    int counter=0;
    //TODO: Convert these arrays to Lists, not best practise to use static sizes.
    String[] title=new String[5];
    String[] type=new String[5];
    String[] description=new String[5];
    int[] imageIds=new int[5];
    public DashBoard() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RecyclerView dashboardRecycler=(RecyclerView)inflater.inflate(R.layout.fragment_dash_board,container,false);
        SQLiteOpenHelper databaseHelper=new DatabaseHelper(getActivity());
        try{
            db=databaseHelper.getReadableDatabase();
            cursor=db.query(tableName,new String[]{"NAME","TYPE","DESCRIPTION","IMAGE_RESOURCE_ID"},null,null,null,null,null);
            if (cursor.moveToFirst()){
                while(!cursor.isAfterLast()){
                    title[counter]=cursor.getString(0);
                    type[counter]=cursor.getString(1);
                    description[counter]=cursor.getString(2);
                    imageIds[counter]=cursor.getInt(3);
                    counter++;
                    cursor.moveToNext();
                }
            }
        }catch (SQLiteException e){
            Toast.makeText(getActivity(),"Database unavailable",Toast.LENGTH_SHORT).show();
        }
        CaptionedImagesAdapter adapter=new CaptionedImagesAdapter(title,type,imageIds);
        dashboardRecycler.setAdapter(adapter);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        dashboardRecycler.setLayoutManager(layoutManager);
        adapter.setListener(new CaptionedImagesAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getActivity(),"Clicked",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(),DashBoard_Detail.class);
                Bundle bundle=new Bundle();
                bundle.putString("title",title[position]);
                bundle.putString("type",type[position]);
                bundle.putString("description",description[position]);
                bundle.putInt("image",imageIds[position]);
                intent.putExtra("bundle",bundle);
                startActivity(intent);
            }
        });
        return dashboardRecycler;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        db.close();
        cursor.close();
    }
}
