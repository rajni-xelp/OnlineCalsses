package com.example.onlineclasses;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;

import java.util.Locale;

import static androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class SummaryActivity extends AppCompatActivity {
   ViewPager chapter_viewpager;
   TabLayout selector_tabs;
    TextView tv_startSession;
    String language=Constants.languageSelected;
    Toolbar custom_toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        TextView tv_subject=findViewById(R.id.tv_subject);
        tv_startSession=findViewById(R.id.tv_startSession);
        custom_toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(custom_toolbar);
        AppCompatImageView iv_summary=findViewById(R.id.iv_summary);
        Glide.with(this).load(R.drawable.math_summary).centerCrop().into(iv_summary);
        SharedPreferences sharedPreferences=getSharedPreferences("onlineClassesVideoList",MODE_PRIVATE);
        switch (Constants.subject)
        {
            case "Android" :
                tv_subject.setText(getString(R.string.android));
                if(sharedPreferences.getBoolean("Android",false))
                    tv_startSession.setText(R.string.cont);
                break;

            case "Math" :
                tv_subject.setText(getString(R.string.math));
                if(sharedPreferences.getBoolean("Math",false))
                    tv_startSession.setText(R.string.cont);
                break;

            case "History" :
                tv_subject.setText(getString(R.string.history));
                break;

            case "Science" :
                tv_subject.setText(getString(R.string.science));
                if(sharedPreferences.getBoolean("Science",false))
                    tv_startSession.setText(R.string.cont);
                break;
        }
        chapter_viewpager=findViewById(R.id.chapter_viewpager);
        selector_tabs=findViewById(R.id.selector_tabs);
        chapter_viewpager.setAdapter(new ChapterViewpagerAdapter(getSupportFragmentManager(),BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT));
        selector_tabs.setupWithViewPager(chapter_viewpager);
        tv_startSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tv_startSession.getText().toString().equalsIgnoreCase(getString(R.string.startsession))) {
                    showAlertBoxBeforeFinishing();
                }
                else
                {
                    Intent intent=new Intent(SummaryActivity.this,DetailsActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public  void showAlertBoxBeforeFinishing()
    {
        AlertDialog.Builder alertbuilder= new AlertDialog.Builder(this);
        LayoutInflater layoutInflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.alert_for_starting_session,null,false);
        TextView tv_alertmsg=view.findViewById(R.id.tv_alertmsg);
        Button ok=view.findViewById(R.id.ok);
        TextView cancel=view.findViewById(R.id.tv_cancel);
        alertbuilder.setView(view);
        final AlertDialog  alertDialog=alertbuilder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_startSession.setText(R.string.cont);
                alertDialog.dismiss();
                alertDialog.hide();
                Intent intent=new Intent(SummaryActivity.this,DetailsActivity.class);
                startActivity(intent);


            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                alertDialog.hide();

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!Constants.languageSelected.equalsIgnoreCase(language)) {
            language = "hindi";
            Locale locale = new Locale("hi");
            Locale.setDefault(locale);
            Resources resources = getResources();
            Configuration config = resources.getConfiguration();
            config.setLocale(locale);
            resources.updateConfiguration(config, resources.getDisplayMetrics());
            recreate();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_main_menus,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.ln_english:
                setLocale("en-GB");
                Constants.languageSelected="english";
                Toast.makeText(getApplicationContext(),"English selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.ln_hindi:
                setLocale("hi");
                Constants.languageSelected="hindi";
                Toast.makeText(getApplicationContext(),"Hindi selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.ln_kannanda:
                setLocale("kn");
                Constants.languageSelected="kannada";
                Toast.makeText(getApplicationContext(),"Kannada selected", Toast.LENGTH_SHORT).show();
                return true;

            default:

                super.onOptionsItemSelected(item);

        }
        return true;
    }

    public void setLocale( String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
        recreate();
    }
}