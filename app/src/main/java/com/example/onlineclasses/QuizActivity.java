package com.example.onlineclasses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class QuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        BottomNavigationView bottomNavigationView=findViewById(R.id.nav_view_new);
        FragmentTransaction fragmentTransaction1=getSupportFragmentManager().beginTransaction();
        ChapterDetailsFragment chapterDetailsFragment=new ChapterDetailsFragment();
        fragmentTransaction1.add(R.id.rl_container,chapterDetailsFragment);
        fragmentTransaction1.commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.session_media:
                        ChapterDetailsFragment chapterDetailsFragment=new ChapterDetailsFragment();
                        FragmentTransaction fragmentTransaction2=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction2.replace(R.id.rl_container,chapterDetailsFragment);
                        fragmentTransaction2.commit();
                        return true;

                    case R.id.coach:
                        QuizFragment quizFragment=new QuizFragment();
                        FragmentTransaction fragmentTransaction3=getSupportFragmentManager().beginTransaction();
                        fragmentTransaction3.replace(R.id.rl_container,quizFragment);
                        fragmentTransaction3.commit();
                        return true;
                }
                return false;
            }
        });

    }
}