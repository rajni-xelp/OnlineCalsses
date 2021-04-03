package com.example.onlineclasses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;

import java.util.Locale;

import static androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class MainActivity extends AppCompatActivity {
    private String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    SharedPreferences sharedPreferences;
    TextView tv_readPercent_1;
    TextView tv_readPercent_2;
    TextView tv_readPercent_3;
    TextView tv_readPercent_4;
    TextView tv_readPercent_5;
    TextView tv_readPercent_6;
    TabLayout tb_layout;
    ViewPager top_viewpager;
    Toolbar custom_toolbar;
    String language=Constants.languageSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        top_viewpager=findViewById(R.id.top_viewpager);
        tb_layout=findViewById(R.id.tb_layout);
        custom_toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(custom_toolbar);
        LinearLayout ll_block_1=findViewById(R.id.ll_block_1);
        LinearLayout ll_block_2=findViewById(R.id.ll_block_2);
        LinearLayout ll_block_3=findViewById(R.id.ll_block_3);
        LinearLayout ll_block_4=findViewById(R.id.ll_block_4);
        LinearLayout ll_block_5=findViewById(R.id.ll_block_5);

         tv_readPercent_1=findViewById(R.id.tv_readPercent_1);
         tv_readPercent_2=findViewById(R.id.tv_readPercent_2);
         tv_readPercent_3=findViewById(R.id.tv_readPercent_3);
         tv_readPercent_4=findViewById(R.id.tv_readPercent_4);
         tv_readPercent_5=findViewById(R.id.tv_readPercent_5);
         tv_readPercent_6=findViewById(R.id.tv_readPercent_6);
        sharedPreferences=getSharedPreferences("onlineClassesVideoList",MODE_PRIVATE);
        TopViewpagerAdapter topViewpagerAdapter=new TopViewpagerAdapter(getSupportFragmentManager(),BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        top_viewpager.setAdapter(topViewpagerAdapter);
        tb_layout.setupWithViewPager(top_viewpager);
        AppCompatImageView iv_1=findViewById(R.id.iv_1);
        AppCompatImageView iv_2=findViewById(R.id.iv_2);
        AppCompatImageView iv_3=findViewById(R.id.iv_3);
        AppCompatImageView iv_4=findViewById(R.id.iv_4);
        AppCompatImageView iv_5=findViewById(R.id.iv_5);
        AppCompatImageView iv_6=findViewById(R.id.iv_6);

        Glide.with(this).load(R.drawable.math_homepage).centerCrop().into(iv_1);
        Glide.with(this).load(R.drawable.android).centerCrop().into(iv_2);
        Glide.with(this).load(R.drawable.science).centerCrop().into(iv_3);
        Glide.with(this).load(R.drawable.science).centerCrop().into(iv_4);
        Glide.with(this).load(R.drawable.history).centerCrop().into(iv_5);
        Glide.with(this).load(R.drawable.math_homepage).centerCrop().into(iv_6);

        requestAllPermissions();
        ll_block_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SummaryActivity.class);
                Constants.subject="Android";
                startActivity(intent);
            }
        });
        ll_block_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SummaryActivity.class);
                Constants.subject="Math";
                startActivity(intent);
            }
        });
//        ll_block_4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               Intent intent=new Intent(MainActivity.this,SummaryActivity.class);
//                Constants.subject="History";
//                startActivity(intent);
//            }
//        });
        ll_block_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SummaryActivity.class);
                Constants.subject="Science";
                startActivity(intent);
            }
        });
    }

    public void requestAllPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                    || (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            ) {
                ActivityCompat.requestPermissions(this, permissions, 101);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        switch (requestCode) {

            case 101: {
                try {
                    if ((ActivityCompat.checkSelfPermission(this,
                            Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                            && (ActivityCompat.checkSelfPermission(this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                    ) {
                    } else {
                        finish();
                        Toast.makeText(MainActivity.this,"All permission are mandatory",Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                }

            }

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(sharedPreferences.getBoolean("Android",false))
            tv_readPercent_1.setText(getString(R.string._65));
        if(sharedPreferences.getBoolean("Math",false))
            tv_readPercent_2.setText(getString(R.string._30));
        if(sharedPreferences.getBoolean("Science",false))
            tv_readPercent_5.setText(getString(R.string._65));

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