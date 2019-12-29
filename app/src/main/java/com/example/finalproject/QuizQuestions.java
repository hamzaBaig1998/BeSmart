package com.example.finalproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class QuizQuestions extends Fragment {
    private SQLiteDatabase db;
    private Cursor cursor;
    private String tableName="QUIZ";
    ProgressBar progressBar;
    AlertDialog alertDialog;
    AlertDialog.Builder builder;
    int progress=0;
    TextView question;
    TextView scoreText;
    int score=0;
    CheckBox firstChoice;
    CheckBox secondChoice;
    CheckBox thirdChoice;
    CheckBox fourthChoice;
    Button next;
    int questionCounter=0;
    String optionSelected;
    int counter;
    //TODO:Convert this array to arraylist, not best practise to use a static size here.
    Quiz quizzes[]=new Quiz[5];

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_quiz_questions, container, false);
        progressBar=(ProgressBar)view.findViewById(R.id.progress_horizontal);
        question=(TextView)view.findViewById(R.id.question);
        scoreText=(TextView)view.findViewById(R.id.score);
        firstChoice=(CheckBox)view.findViewById(R.id.firstChoice);
        secondChoice=(CheckBox)view.findViewById(R.id.secondChoice);
        thirdChoice=(CheckBox)view.findViewById(R.id.thirdChoice);
        fourthChoice=(CheckBox)view.findViewById(R.id.fourthChoice);
        next=(Button)view.findViewById(R.id.next);

        progressBar.setMax(100);
        progressBar.setProgress(progress);
        builder=new AlertDialog.Builder(getActivity());
        SQLiteOpenHelper databaseHelper=new DatabaseHelper(getActivity());
        try{
            db=databaseHelper.getReadableDatabase();
            cursor=db.query(tableName,new String[]{"QUESTION","CHOICE1","CHOICE2","CHOICE3","CHOICE4","CORRECT"},null,null,null,null,null);
            //Check this if there are problems with data.
            //App was tested with static data
            Quiz quiz;
            if (cursor.moveToFirst()){
                while(!cursor.isAfterLast()){
                    quiz=new Quiz(0,cursor.getString(0),new String[]{cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)},cursor.getString(5));
//                    quizzes[counter].setQuestion(cursor.getString(0));
//                    quizzes[counter].setChoices(new String[]{cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)});
//                    quizzes[counter].setCorrect(cursor.getString(5));
                    quizzes[counter]=quiz;
                    counter++;
                    cursor.moveToNext();
                }
            }
        }catch (SQLiteException e){
            Toast.makeText(getActivity(),"Database unavailable",Toast.LENGTH_SHORT).show();
        }
        updateData();


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questionCounter++;
                if(questionCounter>=quizzes.length){
//                    Intent intent=new Intent(getActivity(),BeSmart.class);
//                    intent.putExtra("score",score);
//                    startActivity(intent);
                    builder.setMessage("Your final score is="+score);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            alertDialog.hide();
                        }
                    });
                    builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            alertDialog.hide();
                        }
                    });
                    alertDialog=builder.create();
                    alertDialog.show();
                }else{
                    updateData();
                    removeChecked();
                    enableCheckBoxes();
                }
            }
        });
        firstChoice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
                if (isChecked){
                    disableCheckBoxes();
                    optionSelected=firstChoice.getText().toString();
                    judge(optionSelected);
                }
            }
        });
        secondChoice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
                if (isChecked){
                    disableCheckBoxes();
                    optionSelected=secondChoice.getText().toString();
                    judge(optionSelected);
                }
            }
        });
        thirdChoice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
                if (isChecked){
                    disableCheckBoxes();
                    optionSelected=thirdChoice.getText().toString();
                    judge(optionSelected);
                }
            }
        });
        fourthChoice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
                if (isChecked){
                    disableCheckBoxes();
                    optionSelected=fourthChoice.getText().toString();
                    judge(optionSelected);
                }
            }
        });
        return view;
    }
    public void judge(String optionSelected){
        if(quizzes[questionCounter].getCorrect().equals(optionSelected)){
            progressBar.setProgress(progress+=20);
            DrawableCompat.setTint(progressBar.getProgressDrawable(),Color.GREEN);
            progressBar.setProgressBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
            Toast.makeText(getActivity(),optionSelected+" is = to "+quizzes[questionCounter].getCorrect(),Toast.LENGTH_SHORT).show();
            score+=10;
        }else{
            progressBar.setProgress(progress+=20);
            DrawableCompat.setTint(progressBar.getProgressDrawable(),Color.RED);
            progressBar.setProgressBackgroundTintList(ColorStateList.valueOf(Color.GRAY));
            Toast.makeText(getActivity(),optionSelected+" is not = to "+quizzes[questionCounter].getCorrect(),Toast.LENGTH_SHORT).show();
            score-=10;
        }
    }
    public void updateData(){
        question.setText(quizzes[questionCounter].getQuestion());
        firstChoice.setText(quizzes[questionCounter].getChoices()[0]);
        secondChoice.setText(quizzes[questionCounter].getChoices()[1]);
        thirdChoice.setText(quizzes[questionCounter].getChoices()[2]);
        fourthChoice.setText(quizzes[questionCounter].getChoices()[3]);
        scoreText.setText("Your score is="+score);
    }
    public void removeChecked(){
        firstChoice.setChecked(false);
        secondChoice.setChecked(false);
        thirdChoice.setChecked(false);
        fourthChoice.setChecked(false);
    }
    public void disableCheckBoxes(){
        firstChoice.setEnabled(false);
        secondChoice.setEnabled(false);
        thirdChoice.setEnabled(false);
        fourthChoice.setEnabled(false);
    }

    public void enableCheckBoxes(){
        firstChoice.setEnabled(true);
        secondChoice.setEnabled(true);
        thirdChoice.setEnabled(true);
        fourthChoice.setEnabled(true);
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        getFragmentManager().beginTransaction().detach(this).attach(this).commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        db.close();
        cursor.close();
    }
}
