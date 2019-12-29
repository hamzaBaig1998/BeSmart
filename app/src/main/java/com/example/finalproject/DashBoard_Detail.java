package com.example.finalproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DashBoard_Detail extends AppCompatActivity {
    TextView title,type,description;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board__detail);
        Bundle bundle=getIntent().getBundleExtra("bundle");
        title=(TextView)findViewById(R.id.title);
        type=(TextView)findViewById(R.id.type);
        description=(TextView)findViewById(R.id.description);
        imageView=(ImageView)findViewById(R.id.article_image);
        imageView.setImageResource(bundle.getInt("image"));
        imageView.setContentDescription(bundle.getString("title"));
        title.setText(bundle.getString("title"));
        type.setText(bundle.getString("type"));
        description.setText(bundle.getString("description"));
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }
}
