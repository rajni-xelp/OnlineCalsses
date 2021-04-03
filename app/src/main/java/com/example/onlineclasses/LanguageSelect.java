package com.example.onlineclasses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Locale;

public class LanguageSelect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_select);
        TextView tv_english=findViewById(R.id.tv_english);
        TextView tv_hindi=findViewById(R.id.tv_hindi);
        TextView tv_kannad=findViewById(R.id.tv_kannad);
        AppCompatImageView iv_selectlanguage=findViewById(R.id.iv_selectlanguage);
        Glide.with(this).load(R.drawable.languageselect).centerCrop().into(iv_selectlanguage);
        tv_english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("en-GB");
                Constants.languageSelected="english";
                Intent intent=new Intent(LanguageSelect.this,MainActivity.class);
                startActivity(intent);
            }
        });

        tv_hindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("hi");
                Constants.languageSelected="hindi";
                Intent intent=new Intent(LanguageSelect.this,MainActivity.class);
                startActivity(intent);
            }
        });
        tv_kannad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("kn");
                Constants.languageSelected="kannada";
                Intent intent=new Intent(LanguageSelect.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setLocale( String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());

    }
}