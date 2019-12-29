package com.example.finalproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class Profile extends Fragment {

    ImageView imageView;
    EditText name;
    EditText email;
    EditText phone;
    private SQLiteDatabase db;
    private Cursor cursor;
    private String tableName="PROFILE";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_profile, container, false);
        name=(EditText)view.findViewById(R.id.name);
        email=(EditText)view.findViewById(R.id.email);
        phone=(EditText)view.findViewById(R.id.phone);
        imageView=(ImageView)view.findViewById(R.id.profile_pic);
        SQLiteOpenHelper databaseHelper=new DatabaseHelper(getActivity());
        try{
            db=databaseHelper.getReadableDatabase();
            cursor=db.query(tableName,new String[]{"NAME","EMAIL","PHONE","IMAGE_RESOURCE_ID"},null,null,null,null,null);
            if (cursor.moveToFirst()){
                name.setText(cursor.getString(0));
                email.setText(cursor.getString(1));
                phone.setText(cursor.getString(2));
                imageView.setImageResource(cursor.getInt(3));
                imageView.setContentDescription("Profile Picture");
            }
        }catch (SQLiteException e){
            Toast.makeText(getActivity(),"Database unavailable",Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }
}
