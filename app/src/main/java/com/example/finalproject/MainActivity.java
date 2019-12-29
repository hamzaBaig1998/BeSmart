package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView bottomNavigationView;
    FragmentManager fragmentManager;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView=(BottomNavigationView)findViewById(R.id.navigation);
        loadFragment(new DashBoard());
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }
    private void loadFragment(Fragment fragment){
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.myContainer,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item){
        int selectedItem=item.getItemId();
        fragmentManager=getSupportFragmentManager();
        fragment=fragmentManager.findFragmentById(R.id.myContainer);
        switch(selectedItem){
            case R.id.facts:
                fragment=new DashBoard();
                loadFragment(fragment);
                return true;
            case R.id.quiz:
                fragment=new QuizQuestions();
                loadFragment(fragment);
                return true;
            case R.id.profile:
                fragment=new Profile();
                loadFragment(fragment);
                return true;
        }
        return false;
    }

}
