package com.example.finalproject;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CaptionedImagesAdapter extends RecyclerView.Adapter<CaptionedImagesAdapter.ViewHolder>{
    private String[] captions;
    private int[] imageIds;
    private String[] descriptions;
    private Listener listener;

    interface Listener{
        void onClick(int position);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout linearLayout;
        public ViewHolder(LinearLayout l){
            super(l);
            linearLayout=l;
        }
    }

    public CaptionedImagesAdapter(String[] captions,String[] descriptions, int[] imageIds){
        this.captions=captions;
        this.imageIds=imageIds;
        this.descriptions=descriptions;
    }

    @Override
    public int getItemCount() {
        return captions.length;
    }

    public void setListener(Listener listener){
        this.listener=listener;
    }


    @Override
    public CaptionedImagesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout linearLayout=(LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
        return new ViewHolder(linearLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        LinearLayout linearLayout=holder.linearLayout;
        ImageView imageView=(ImageView) linearLayout.findViewById(R.id.recycler_image);
//        Drawable drawable= ContextCompat.getDrawable(linearLayout.getContext(),imageIds[position]);
//        imageView.setImageDrawable(drawable);
        imageView.setImageResource(imageIds[position]);
        imageView.setContentDescription(captions[position]);
        TextView textView=(TextView) linearLayout.findViewById(R.id.title);
        TextView description=(TextView)linearLayout.findViewById(R.id.description);
        textView.setText(captions[position]);
        description.setText(descriptions[position]);
        textView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.onClick(position);
                }
            }
        });

    }
}

