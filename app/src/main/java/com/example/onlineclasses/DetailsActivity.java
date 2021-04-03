package com.example.onlineclasses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailsActivity extends AppCompatActivity implements Player.EventListener {
    SharedPreferences sharedPreferences;
    PlayerView playerview;
    String fileName = "";
    String subjectWithLanguage = "coding_english";
    List<String> videoUrl;
    int selectedPosition = 0;
    Gson gson;
    String baseurl = "https://s3.ca-central-1.amazonaws.com/";
    SimpleExoPlayer privious_player;
    static ProgressBar progressbar_1, progressbar_2 ,pbar_singledownload;
    static TextView tv_offline_1, tv_offline_2;
    static RelativeLayout rl_offline_chapterdownload ,rl_session_1_download ,rl_session_2_download;
    static TextView tv_offline;
    static AppCompatImageView iv_download_chapter ,iv_download_1 ,iv_download_2;
    public static Context context;
    Toolbar custom_toolbar;
    String language=Constants.languageSelected;
    ProgressBar progress_buffering;
    TextView tv_subject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        context=DetailsActivity.this;
        progress_buffering=findViewById(R.id.progress_buffering);
        TextView tv_viewDetails1 = findViewById(R.id.tv_viewDetails1);
        TextView tv_viewDetails2 = findViewById(R.id.tv_viewDetails2);
        tv_subject=findViewById(R.id.tv_subject);
        rl_offline_chapterdownload = findViewById(R.id.rl_offline_chapterdownload);
        iv_download_chapter=findViewById(R.id.iv_download_chapter);
        iv_download_1=findViewById(R.id.iv_download_1);
        iv_download_2=findViewById(R.id.iv_download_2);
        rl_session_1_download=findViewById(R.id.rl_session_1_download);
        rl_session_2_download=findViewById(R.id.rl_session_2_download);
        tv_offline_1 = findViewById(R.id.tv_offline_1);
        tv_offline_2 = findViewById(R.id.tv_offline_2);
        pbar_singledownload=findViewById(R.id.pbar_singledownload);
        custom_toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(custom_toolbar);
        AppCompatImageView iv_download_1 = findViewById(R.id.iv_download_1);
        AppCompatImageView iv_download_2 = findViewById(R.id.iv_download_2);
        progressbar_1 = findViewById(R.id.progressbar_1);
        progressbar_2 = findViewById(R.id.progressbar_2);
        rl_offline_chapterdownload.setVisibility(View.GONE);
        if (Constants.chapter_downloadig == 1) {
            iv_download_1.setVisibility(View.INVISIBLE);
            progressbar_1.setVisibility(View.VISIBLE);
        }

        if (Constants.chapter_downloadig == 2) {
            iv_download_2.setVisibility(View.INVISIBLE);
            progressbar_2.setVisibility(View.VISIBLE);
        }

        tv_viewDetails1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Constants.subject.equalsIgnoreCase("Math")) {
                    Constants.chapterSelected = "math_integration";
                } else {
                    Constants.chapterSelected = "Android_basic";
                }
                Intent intent = new Intent(DetailsActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });

        tv_viewDetails2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Constants.subject.equalsIgnoreCase("Math")) {
                    Constants.chapterSelected = "math_differentiation";
                } else {
                    Constants.chapterSelected = "Activity_lifecycle";
                }
                Intent intent = new Intent(DetailsActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });

        playerview = findViewById(R.id.playerview);
        TextView tv_viewmore_1 = findViewById(R.id.tv_viewmore_1);
        TextView tv_viewmore_2 = findViewById(R.id.tv_viewmore_2);

        TextView tv_chapter_1 = findViewById(R.id.tv_chapter_1);
        TextView tv_chapter_2 = findViewById(R.id.tv_chapter_2);

        tv_offline = findViewById(R.id.tv_offline);
        TextView tv_chapter_1_subchapter_1 = findViewById(R.id.tv_chapter_1_subchapter_1);
        TextView tv_chapter_1_subchapter_2 = findViewById(R.id.tv_chapter_1_subchapter_2);
        TextView tv_chapter_2_subchapter_1 = findViewById(R.id.tv_chapter_2_subchapter_1);
        TextView tv_chapter_2_subchapter_2 = findViewById(R.id.tv_chapter_2_subchapter_2);

        RelativeLayout rl_chapter_1_subchapter_1 = findViewById(R.id.rl_chapter_1_subchapter_1);
        RelativeLayout rl_chapter_1_subchapter_2 = findViewById(R.id.rl_chapter_1_subchapter_2);
        RelativeLayout rl_chapter_2_subchapter_1 = findViewById(R.id.rl_chapter_2_subchapter_1);
        RelativeLayout rl_chapter_2_subchapter_2 = findViewById(R.id.rl_chapter_2_subchapter_2);

        LinearLayout ll_chapter_1 = findViewById(R.id.ll_chapter_1);
        LinearLayout ll_chapter_2 = findViewById(R.id.ll_chapter_2);

        if (Constants.subject.equalsIgnoreCase("Android") && Constants.languageSelected.equalsIgnoreCase("english")) {
            baseurl = "https://s3.ca-central-1.amazonaws.com/";
        } else {
            baseurl = "http://13.235.209.150/";
        }
        gson = new Gson();
        videoUrl = new ArrayList<>();
        sharedPreferences = getSharedPreferences("onlineClassesVideoList", MODE_PRIVATE);
        SharedPreferences.Editor readPercentageditor = sharedPreferences.edit();

        switch (Constants.subject) {
            case "Android":
                tv_subject.setText(getString(R.string.android));
                tv_chapter_1.setText(getString(R.string._1_android_basic));
                tv_chapter_2.setText(getString(R.string._2_activity_lifecycle));
                tv_chapter_1_subchapter_1.setText(getString(R.string._1_1_androidbasic_chapter_1));
                tv_chapter_1_subchapter_2.setText(getString(R.string._1_2_androidbasic_chapter_2));
                tv_chapter_2_subchapter_1.setText(getString(R.string._2_1_activitylifecycle_chapter_1));
                tv_chapter_2_subchapter_2.setText(getString(R.string._2_2_activitylifecycle_chapter_2));

                break;

            case "Math":
                tv_subject.setText(getString(R.string.math));
                tv_chapter_1.setText(getString(R.string._1_integrations));
                tv_chapter_2.setText(getString(R.string._2_differentiation));
                tv_chapter_1_subchapter_1.setText(getString(R.string._1_1_Integration_chapter_1));
                tv_chapter_1_subchapter_2.setText(getString(R.string._1_2_Integration_chapter_2));
                tv_chapter_2_subchapter_1.setText(getString(R.string._2_1_differentation_chapter_1));
                tv_chapter_2_subchapter_2.setText(getString(R.string._2_2_differentation_chapter_2));
                break;

            case "History":
                tv_subject.setText(getString(R.string.history));
                tv_chapter_1.setText(getString(R.string._1_ancient));
                tv_chapter_2.setText(getString(R.string._2_medieval));
                tv_chapter_1_subchapter_1.setText(getString(R.string._1_1_Ancient_chapter_1));
                tv_chapter_1_subchapter_2.setText(getString(R.string._1_2_Ancient_chapter_2));
                tv_chapter_2_subchapter_1.setText(getString(R.string._2_1_medieval_chapter_1));
                tv_chapter_2_subchapter_2.setText(getString(R.string._2_2_medieval_chapter_2));
                break;

            case "Science":
                tv_subject.setText(getString(R.string.science));
                tv_chapter_1.setText(getString(R.string._1_science_basic));
                tv_chapter_2.setText(getString(R.string._2_waves));
                tv_chapter_1_subchapter_1.setText(getString(R.string._1_1_sciencebasic_chapter_1));
                tv_chapter_1_subchapter_2.setText(getString(R.string._1_2_sciencebasic_chapter_2));
                tv_chapter_2_subchapter_1.setText(getString(R.string._2_1_waves_chapter_1));
                tv_chapter_2_subchapter_2.setText(getString(R.string._2_2_waves_chapter_2));
                break;
        }

        switch (Constants.subject) {
            case "Android":
                readPercentageditor.putBoolean("Android", true);
                readPercentageditor.apply();
                if (Constants.languageSelected.equalsIgnoreCase("hindi")) {
                    subjectWithLanguage = "android_hindi";
                    videoUrl.add(Constants.androidvideo_hindi);
                    videoUrl.add(Constants.androidvideo_hindi);
                    videoUrl.add(Constants.androidvideo_hindi);
                    videoUrl.add(Constants.androidvideo_hindi);
                } else {
                    subjectWithLanguage = "android_english";
                    videoUrl.add(Constants.androidvideo_1);
                    videoUrl.add(Constants.androidvideo_2);
                    videoUrl.add(Constants.androidvideo_1);
                    videoUrl.add(Constants.androidvideo_3);
                }
                break;

            case "Math":
                readPercentageditor.putBoolean("Math", true);
                readPercentageditor.apply();
                if (Constants.languageSelected.equalsIgnoreCase("english")) {
                    subjectWithLanguage = "math_english";
                    videoUrl.add(Constants.mathvideo_1);
                    videoUrl.add(Constants.mathvideo_2);
                    videoUrl.add(Constants.mathvideo_2);
                    videoUrl.add(Constants.mathvideo_1);
                } else if (Constants.languageSelected.equalsIgnoreCase("hindi")) {
                    subjectWithLanguage = "math_hindi";
                    videoUrl.add(Constants.mathvideo_hindi_1);
                    videoUrl.add(Constants.mathvideo_hindi_2);
                    videoUrl.add(Constants.mathvideo_hindi_2);
                    videoUrl.add(Constants.mathvideo_hindi_1);
                } else {
                    subjectWithLanguage = "math_kannada";
                    videoUrl.add(Constants.mathvideo_kannada_2);
                    videoUrl.add(Constants.mathvideo_kannada_1);
                    videoUrl.add(Constants.mathvideo_kannada_2);
                    videoUrl.add(Constants.mathvideo_kannada_1);
                }

                break;

            case "History":
                readPercentageditor.putBoolean("History", true);
                readPercentageditor.apply();
                break;

            case "Science":
                readPercentageditor.putBoolean("Science", true);
                readPercentageditor.apply();
                if (Constants.languageSelected.equalsIgnoreCase("english")) {
                    subjectWithLanguage = "science_english";
                    videoUrl.add(Constants.science_1);
                    videoUrl.add(Constants.science_2);
                    videoUrl.add(Constants.science_2);
                    videoUrl.add(Constants.science_1);
                } else if(Constants.languageSelected.equalsIgnoreCase("hindi")){
                    subjectWithLanguage = "science_hindi";
                    videoUrl.add(Constants.science_Hindi_1);
                    videoUrl.add(Constants.science_Hindi_1);
                    videoUrl.add(Constants.science_Hindi_1);
                    videoUrl.add(Constants.science_Hindi_1);
                }
                else
                {
                    subjectWithLanguage = "science_kannada";
                    videoUrl.add(Constants.kannada_science_1);
                    videoUrl.add(Constants.kannada_science_1);
                    videoUrl.add(Constants.kannada_science_1);
                    videoUrl.add(Constants.kannada_science_1);
                }
                break;
        }

        String offlinePath1 = checkIfVideoisOffline("video1");
        String offlinePath2 = checkIfVideoisOffline("video2");
        String offlinePath3 = checkIfVideoisOffline("video3");
        String offlinePath4 = checkIfVideoisOffline("video4");

        rl_offline_chapterdownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!tv_offline.getText().toString().equalsIgnoreCase(getString(R.string.downloaded))) {
                    switch (Constants.selected_chapter) {
                        case 1:
                            Constants.select_with_language_1 = subjectWithLanguage + "_" + "video1.mp4";
                            break;
                        case 2:
                            Constants.select_with_language_1 = subjectWithLanguage + "_" + "video2.mp4";
                            break;
                        case 3:
                            Constants.select_with_language_1 = subjectWithLanguage + "_" + "video3.mp4";
                            break;
                        case 4:
                            Constants.select_with_language_1 = subjectWithLanguage + "_" + "video4.mp4";
                            break;

                    }
                    iv_download_chapter.setVisibility(View.INVISIBLE);
//                    Constants.downloadClickedPosition = 3;
                    downloadVideo(pbar_singledownload, videoUrl.get(Constants.selected_chapter-1), Constants.select_with_language_1);
                }
            }
        });

        rl_session_1_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Constants.isNetworkAvailable(DetailsActivity.this)) {
                    if(!tv_offline_1.getText().toString().equalsIgnoreCase(getString(R.string.downloaded))) {
                        iv_download_1.setVisibility(View.INVISIBLE);
                        Constants.chapter_downloadig = 1;
                        Constants.downloadClickedPosition = 1;
                        Constants.video_url_1 = videoUrl.get(0);
                        Constants.video_url_2 = videoUrl.get(1);
                        if(offlinePath3 ==null && offlinePath4 ==null) {
                            downloadVideo(progressbar_1, videoUrl.get(0), subjectWithLanguage + "_" + "video1.mp4");
                            downloadVideo(progressbar_1, videoUrl.get(1), subjectWithLanguage + "_" + "video2.mp4");
                        }
                        else {
                            Constants.downloadCount=1;
                            if (offlinePath1 == null)
                                downloadVideo(progressbar_1, videoUrl.get(0), subjectWithLanguage + "_" + "video1.mp4");
                            if (offlinePath2 == null)
                                downloadVideo(progressbar_1, videoUrl.get(1), subjectWithLanguage + "_" + "video2.mp4");
                        }
                    }
                } else {
                    Toast.makeText(DetailsActivity.this, "Please turn on internet to download.", Toast.LENGTH_LONG).show();
                }
            }
        });

        rl_session_2_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Constants.isNetworkAvailable(DetailsActivity.this)) {
                    if(!tv_offline_2.getText().toString().equalsIgnoreCase(getString(R.string.downloaded))) {
                        iv_download_2.setVisibility(View.INVISIBLE);
                        Constants.downloadClickedPosition = 2;
                        Constants.chapter_downloadig = 2;
                        Constants.video_url_1 = videoUrl.get(2);
                        Constants.video_url_2 = videoUrl.get(3);
                        if(offlinePath3 ==null && offlinePath4 ==null) {
                            downloadVideo(progressbar_2, videoUrl.get(2), subjectWithLanguage + "_" + "video3.mp4");
                            downloadVideo(progressbar_2, videoUrl.get(3), subjectWithLanguage + "_" + "video4.mp4");
                        }
                        else {
                            Constants.downloadCount=1;
                            if (offlinePath3 == null) {
                                downloadVideo(progressbar_2, videoUrl.get(2), subjectWithLanguage + "_" + "video3.mp4");
                            }
                            if (offlinePath4 == null) {
                                downloadVideo(progressbar_2, videoUrl.get(3), subjectWithLanguage + "_" + "video4.mp4");
                            }
                        }
                    }
                } else {
                    Toast.makeText(DetailsActivity.this, "Please turn on internet to download.", Toast.LENGTH_LONG).show();
                }
            }
        });
        if (offlinePath1 != null && offlinePath2 != null) {
            Glide.with(this).load(R.drawable.tick).centerCrop().into(iv_download_1);
            tv_offline_1.setText(getString(R.string.downloaded));
        } else {
            if (Constants.downloadClickedPosition == 1) {
                progressbar_1.setVisibility(View.VISIBLE);
                iv_download_1.setVisibility(View.INVISIBLE);
            }
        }

        if (offlinePath3 != null && offlinePath4 != null) {
            Glide.with(this).load(R.drawable.tick).centerCrop().into(iv_download_2);
            tv_offline_2.setText(getString(R.string.downloaded));
        } else {
            if (Constants.chapter_downloadig == 2) {
                progressbar_2.setVisibility(View.VISIBLE);
                iv_download_2.setVisibility(View.INVISIBLE);
            }
        }


        rl_chapter_1_subchapter_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String offlinePath = checkIfVideoisOffline("video1");
                    if (offlinePath != null) {
                        Constants.selected_chapter=1;
                        rl_offline_chapterdownload.setVisibility(View.VISIBLE);
                        tv_chapter_1.setTextColor(getResources().getColor(R.color.purple_200));
                        tv_chapter_2.setTextColor(getResources().getColor(R.color.black));
                        tv_chapter_1_subchapter_1.setTextColor(getResources().getColor(R.color.purple_200));
                        tv_chapter_1_subchapter_2.setTextColor(getResources().getColor(R.color.grey));
                        tv_chapter_2_subchapter_1.setTextColor(getResources().getColor(R.color.grey));
                        tv_chapter_2_subchapter_2.setTextColor(getResources().getColor(R.color.grey));
                        Glide.with(DetailsActivity.this).load(R.drawable.tick).centerCrop().into(iv_download_chapter);
                        tv_offline.setText(getString(R.string.downloaded));
                        initializePlayer(offlinePath);
                    } else {
                        if (Constants.isNetworkAvailable(DetailsActivity.this)) {
                            Constants.selected_chapter=1;
                            rl_offline_chapterdownload.setVisibility(View.VISIBLE);
                            tv_chapter_1.setTextColor(getResources().getColor(R.color.purple_200));
                            tv_chapter_2.setTextColor(getResources().getColor(R.color.black));
                            tv_chapter_1_subchapter_1.setTextColor(getResources().getColor(R.color.purple_200));
                            tv_chapter_1_subchapter_2.setTextColor(getResources().getColor(R.color.grey));
                            tv_chapter_2_subchapter_1.setTextColor(getResources().getColor(R.color.grey));
                            tv_chapter_2_subchapter_2.setTextColor(getResources().getColor(R.color.grey));
                            Glide.with(DetailsActivity.this).load(R.drawable.download).centerCrop().into(iv_download_chapter);
                            tv_offline.setText(getString(R.string.download_chapter));
                            initializePlayer(baseurl + videoUrl.get(0));
                        } else {
                            Toast.makeText(DetailsActivity.this, "Please turn on internet to play video.", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        });
        rl_chapter_1_subchapter_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String offlinePath = checkIfVideoisOffline("video2");
                    if (offlinePath != null) {
                        Constants.selected_chapter=2;
                        rl_offline_chapterdownload.setVisibility(View.VISIBLE);
                        tv_chapter_1.setTextColor(getResources().getColor(R.color.purple_200));
                        tv_chapter_2.setTextColor(getResources().getColor(R.color.black));
                        tv_chapter_1_subchapter_1.setTextColor(getResources().getColor(R.color.grey));
                        tv_chapter_1_subchapter_2.setTextColor(getResources().getColor(R.color.purple_200));
                        tv_chapter_2_subchapter_1.setTextColor(getResources().getColor(R.color.grey));
                        tv_chapter_2_subchapter_2.setTextColor(getResources().getColor(R.color.grey));
                        Glide.with(DetailsActivity.this).load(R.drawable.tick).centerCrop().into(iv_download_chapter);
                        tv_offline.setText(getString(R.string.downloaded));
                        initializePlayer(offlinePath);
                    } else {
                        if (Constants.isNetworkAvailable(DetailsActivity.this)) {
                            Constants.selected_chapter=2;
                            rl_offline_chapterdownload.setVisibility(View.VISIBLE);
                            tv_chapter_1.setTextColor(getResources().getColor(R.color.purple_200));
                            tv_chapter_2.setTextColor(getResources().getColor(R.color.black));
                            tv_chapter_1_subchapter_1.setTextColor(getResources().getColor(R.color.grey));
                            tv_chapter_1_subchapter_2.setTextColor(getResources().getColor(R.color.purple_200));
                            tv_chapter_2_subchapter_1.setTextColor(getResources().getColor(R.color.grey));
                            tv_chapter_2_subchapter_2.setTextColor(getResources().getColor(R.color.grey));
                            Glide.with(DetailsActivity.this).load(R.drawable.download).centerCrop().into(iv_download_chapter);
                            tv_offline.setText(getString(R.string.download_chapter));
                            initializePlayer(baseurl + videoUrl.get(1));
                        } else {
                            Toast.makeText(DetailsActivity.this, "Please turn on internet to play video.", Toast.LENGTH_LONG).show();
                        }
                    }
            }
        });

        rl_chapter_2_subchapter_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String offlinePath = checkIfVideoisOffline("video3");
                    if (offlinePath != null) {
                        Constants.selected_chapter=3;
                        rl_offline_chapterdownload.setVisibility(View.VISIBLE);
                        tv_chapter_1.setTextColor(getResources().getColor(R.color.black));
                        tv_chapter_2.setTextColor(getResources().getColor(R.color.purple_200));
                        tv_chapter_1_subchapter_1.setTextColor(getResources().getColor(R.color.grey));
                        tv_chapter_1_subchapter_2.setTextColor(getResources().getColor(R.color.grey));
                        tv_chapter_2_subchapter_1.setTextColor(getResources().getColor(R.color.purple_200));
                        tv_chapter_2_subchapter_2.setTextColor(getResources().getColor(R.color.grey));
                        Glide.with(DetailsActivity.this).load(R.drawable.tick).centerCrop().into(iv_download_chapter);
                        tv_offline.setText(getString(R.string.downloaded));
                        initializePlayer(offlinePath);
                    } else {
                        if (Constants.isNetworkAvailable(DetailsActivity.this)) {
                            Constants.selected_chapter=3;
                            rl_offline_chapterdownload.setVisibility(View.VISIBLE);
                            tv_chapter_1.setTextColor(getResources().getColor(R.color.black));
                            tv_chapter_2.setTextColor(getResources().getColor(R.color.purple_200));
                            tv_chapter_1_subchapter_1.setTextColor(getResources().getColor(R.color.grey));
                            tv_chapter_1_subchapter_2.setTextColor(getResources().getColor(R.color.grey));
                            tv_chapter_2_subchapter_1.setTextColor(getResources().getColor(R.color.purple_200));
                            tv_chapter_2_subchapter_2.setTextColor(getResources().getColor(R.color.grey));
                            Glide.with(DetailsActivity.this).load(R.drawable.download).centerCrop().into(iv_download_chapter);
                            tv_offline.setText(getString(R.string.download_chapter));
                            initializePlayer(baseurl + videoUrl.get(2));
                        } else {
                            Toast.makeText(DetailsActivity.this, "Please turn on internet to play video.", Toast.LENGTH_LONG).show();

                        }
                    }
            }
        });

        rl_chapter_2_subchapter_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String offlinePath = checkIfVideoisOffline("video4");
                    if (offlinePath != null) {
                        Constants.selected_chapter=4;
                        rl_offline_chapterdownload.setVisibility(View.VISIBLE);
                        tv_chapter_1.setTextColor(getResources().getColor(R.color.black));
                        tv_chapter_2.setTextColor(getResources().getColor(R.color.purple_200));
                        tv_chapter_1_subchapter_1.setTextColor(getResources().getColor(R.color.grey));
                        tv_chapter_1_subchapter_2.setTextColor(getResources().getColor(R.color.grey));
                        tv_chapter_2_subchapter_1.setTextColor(getResources().getColor(R.color.grey));
                        tv_chapter_2_subchapter_2.setTextColor(getResources().getColor(R.color.purple_200));
                        Glide.with(DetailsActivity.this).load(R.drawable.tick).centerCrop().into(iv_download_chapter);
                        tv_offline.setText(R.string.downloaded);
                        initializePlayer(offlinePath);
                    } else {
                        if (Constants.isNetworkAvailable(DetailsActivity.this)) {
                            Constants.selected_chapter=4;
                            rl_offline_chapterdownload.setVisibility(View.VISIBLE);
                            tv_chapter_1.setTextColor(getResources().getColor(R.color.black));
                            tv_chapter_2.setTextColor(getResources().getColor(R.color.purple_200));
                            tv_chapter_1_subchapter_1.setTextColor(getResources().getColor(R.color.grey));
                            tv_chapter_1_subchapter_2.setTextColor(getResources().getColor(R.color.grey));
                            tv_chapter_2_subchapter_1.setTextColor(getResources().getColor(R.color.grey));
                            tv_chapter_2_subchapter_2.setTextColor(getResources().getColor(R.color.purple_200));
                            Glide.with(DetailsActivity.this).load(R.drawable.download).centerCrop().into(iv_download_chapter);
                            tv_offline.setText(getString(R.string.download_chapter));
                            initializePlayer(baseurl + videoUrl.get(3));
                        } else {
                            Toast.makeText(DetailsActivity.this, "Please turn on internet to play video.", Toast.LENGTH_LONG).show();
                        }
                    }
            }
        });

        tv_viewmore_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv_viewmore_1.getText().toString().equalsIgnoreCase(getString(R.string.view))) {
                    tv_viewmore_1.setText(getString(R.string.hide));
                    ll_chapter_1.setVisibility(View.VISIBLE);
                } else {
                    tv_viewmore_1.setText(getString(R.string.view));
                    ll_chapter_1.setVisibility(View.GONE);
                }
            }
        });
        tv_viewmore_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv_viewmore_2.getText().toString().equalsIgnoreCase(getString(R.string.view))) {
                    tv_viewmore_2.setText(getString(R.string.hide));
                    ll_chapter_2.setVisibility(View.VISIBLE);
                } else {
                    tv_viewmore_2.setText(getString(R.string.view));
                    ll_chapter_2.setVisibility(View.GONE);
                }
            }
        });
//        tv_viewmore_3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (tv_viewmore_3.getText().toString().equalsIgnoreCase(getString(R.string.view))) {
//                    tv_viewmore_3.setText(getString(R.string.hide));
//                    ll_chapter_3.setVisibility(View.VISIBLE);
//                } else {
//                    tv_viewmore_3.setText(getString(R.string.view));
//                    ll_chapter_3.setVisibility(View.GONE);
//                }
//            }
//        });
    }

    public String checkIfVideoisOffline(String video_numbering) {
        if (sharedPreferences.getString("videolist_key", null) != null) {
            String videoGsonText = sharedPreferences.getString("videolist_key", null);
            List<String> videoList = gson.fromJson(videoGsonText, List.class);
            for (int i = 0; i < videoList.size(); i++) {
                String path = videoList.get(i);
                if (path.contains(subjectWithLanguage + "_" + video_numbering)) {
                    return path;
                }
            }
        }
        return null;
    }

    public void StartWorkManager() {
        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(MyWorkerClass.class).build();
        WorkManager.getInstance(this).enqueue(oneTimeWorkRequest);
    }


    public void downloadVideo(ProgressBar progressbar, String dynamicurl, String fileName) {
        progressbar.setVisibility(View.VISIBLE);
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .readTimeout(400, TimeUnit.SECONDS)
                .writeTimeout(400, TimeUnit.SECONDS)
                .build();

        final Gson gson = new GsonBuilder()
                .setLenient()
                .serializeNulls()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseurl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ApiServiceClass apiServiceClass = retrofit.create(ApiServiceClass.class);
        Call<ResponseBody> interfaceObject = apiServiceClass.getData(dynamicurl);

        interfaceObject.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                new AsyncTask<Void, Void, Boolean>() {
                    @Override
                    protected Boolean doInBackground(Void... voids) {
                        boolean writtenToDisk = writeResponseBodyToDisk(response.body(), fileName);
                        return writtenToDisk;
                    }

                    @Override
                    protected void onPostExecute(Boolean aBoolean) {
                        super.onPostExecute(aBoolean);
                        if (aBoolean != null) {
                            if (aBoolean) {
                                //  tv_offline.setVisibility(View.GONE);
                                saveVideopathInSharedpreference(fileName);
                                updateUiAfterSingleDownload();

                            } else {
                                Constants.selected_chapter=0;
                                Constants.downloadClickedPosition=0;
                                if (DetailsActivity.this != null)
                                    Toast.makeText(DetailsActivity.this, "Dowload failed", Toast.LENGTH_LONG).show();

                                if (Constants.chapter_downloadig == 1) {
                                    Constants.chapter_downloadig = 0;
                                    if (progressbar_1 != null && tv_offline_1 != null) {
                                        progressbar_1.setVisibility(View.GONE);
                                        tv_offline_1.setVisibility(View.GONE);
                                    }
                                }
                                if (Constants.chapter_downloadig == 2) {
                                    Constants.chapter_downloadig = 0;
                                    if (progressbar_2 != null && tv_offline_2 != null) {
                                        progressbar_2.setVisibility(View.GONE);
                                        tv_offline_2.setVisibility(View.GONE);
                                    }
                                }
                                //  tv_offline.setText(R.string.available_offline);
                            }
                        }
                    }
                }.execute();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Constants.selected_chapter=0;
                Constants.downloadClickedPosition=0;
                if (!isDestroyed())
                    Toast.makeText(DetailsActivity.this, "Dowload failed", Toast.LENGTH_LONG).show();

            }
        });
    }

    private boolean writeResponseBodyToDisk(ResponseBody body, String fileName) {
        try {
            boolean success = true;
            String storagePath = Environment.getExternalStorageDirectory().toString();
            File downloadedVideo = new File(storagePath, fileName);

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(downloadedVideo);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;
                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }

    private void initializePlayer(String videoUrl) {

        Release_Privious_Player();
        String playerInfo = Util.getUserAgent(this, "OnlineClasses");
        DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(
                this, playerInfo
        );


        SimpleExoPlayer player = new SimpleExoPlayer.Builder(this)
                .setMediaSourceFactory(
                        new DefaultMediaSourceFactory(dataSourceFactory))
                .build();
        MediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory).setExtractorsFactory(new DefaultExtractorsFactory())
                .createMediaSource(Uri.parse(videoUrl));
        player.prepare(mediaSource);
        player.addListener(this);
        playerview.setPlayer(player);
        player.setPlayWhenReady(true);
        privious_player = player;


    }

    public void saveVideopathInSharedpreference(String fileName) {
        String storagePath = Environment.getExternalStorageDirectory().toString();
        File downloadedVideo = new File(storagePath, fileName);
        String videoUrl = String.valueOf(Uri.fromFile(downloadedVideo));

        if (sharedPreferences.getString("videolist_key", null) != null) {
            String videoGsonText = sharedPreferences.getString("videolist_key", null);
            List<String> videoList = gson.fromJson(videoGsonText, List.class);
            videoList.add(videoUrl);
            String newvideoGsonText = gson.toJson(videoList);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("videolist_key", newvideoGsonText);
            editor.apply();
        } else {
            List<String> videoList = new ArrayList<>();
            videoList.add(videoUrl);
            String videoGsonText = gson.toJson(videoList);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("videolist_key", videoGsonText);
            editor.apply();
        }
    }

    public void Release_Privious_Player() {
        try {
//            Toast.makeText(getActivity(), "Crash 21 Line 1", Toast.LENGTH_SHORT).show();
            if (privious_player != null) {
//                privious_player.removeListener(this);
                privious_player.release();
            }
        } catch (Exception e) {
        }
    }

    public void updateUiAfterSingleDownload()
    {
        if(progressbar_1 !=null && progressbar_2 !=null && pbar_singledownload !=null && iv_download_1 != null && iv_download_2 !=null && iv_download_chapter !=null
         && tv_offline_1 !=null && tv_offline_2 !=null && tv_offline !=null)
        {
            Constants.downloadCount = Constants.downloadCount + 1;
            if (Constants.downloadClickedPosition == 1 && Constants.downloadCount == 2) {
                Constants.downloadClickedPosition =0;
                progressbar_1.setVisibility(View.GONE);
                iv_download_1.setVisibility(View.VISIBLE);
                Glide.with(context).load(R.drawable.tick).centerCrop().into(iv_download_1);
                tv_offline_1.setText(getString(R.string.downloaded));
                Constants.chapter_downloadig = 0;
                Log.d("sfcsjfjs","first time download");
                if (DetailsActivity.this != null)
                    Toast.makeText(DetailsActivity.this, "Dowloaded successfully", Toast.LENGTH_LONG).show();
                if (Constants.selected_chapter == 1 || Constants.selected_chapter == 2) {
                    iv_download_chapter.setVisibility(View.VISIBLE);
                    Glide.with(context).load(R.drawable.tick).centerCrop().into(iv_download_chapter);
                    pbar_singledownload.setVisibility(View.GONE);
                    tv_offline.setText(getString(R.string.downloaded));
                    Constants.selected_chapter=0;
                }
                Constants.downloadCount = 0;
            } else if (Constants.downloadClickedPosition == 2 && Constants.downloadCount == 2) {
                Constants.downloadClickedPosition =0;
                Constants.downloadCount = 0;
                progressbar_2.setVisibility(View.GONE);
                iv_download_2.setVisibility(View.VISIBLE);
                Glide.with(context).load(R.drawable.tick).centerCrop().into(iv_download_2);
                tv_offline_2.setText(getString(R.string.downloaded));
                Constants.chapter_downloadig = 0;
                Log.d("sfcsjfjs","second time download");
                if (DetailsActivity.this != null)
                    Toast.makeText(DetailsActivity.this, "Dowloaded successfully", Toast.LENGTH_LONG).show();
                if (Constants.selected_chapter == 3 || Constants.selected_chapter == 4) {
                    tv_offline.setText(getString(R.string.downloaded));
                    iv_download_chapter.setVisibility(View.VISIBLE);
                    Glide.with(context).load(R.drawable.tick).centerCrop().into(iv_download_chapter);
                    Constants.selected_chapter=0;
                }
            } else if (Constants.selected_chapter != 0 && Constants.downloadClickedPosition==0) {

                String offlinePath1 = checkIfVideoisOffline("video1");
                String offlinePath2 = checkIfVideoisOffline("video2");
                String offlinePath3 = checkIfVideoisOffline("video3");
                String offlinePath4 = checkIfVideoisOffline("video4");
                if (offlinePath1 != null && offlinePath2 != null) {
                    Glide.with(context).load(R.drawable.tick).centerCrop().into(iv_download_1);
                    tv_offline_1.setText(getString(R.string.downloaded));
                }

                if (offlinePath3 != null && offlinePath4 != null) {
                    Glide.with(context).load(R.drawable.tick).centerCrop().into(iv_download_2);
                    tv_offline_2.setText(getString(R.string.downloaded));
                }
                Constants.selected_chapter = 0;
                pbar_singledownload.setVisibility(View.GONE);
                Constants.chapter_downloadig = 0;
                tv_offline.setText(getString(R.string.downloaded));
                iv_download_chapter.setVisibility(View.VISIBLE);
                Glide.with(context).load(R.drawable.tick).centerCrop().into(iv_download_chapter);
                Log.d("sfcsjfjs","third time download");
                if (DetailsActivity.this != null)
                    Toast.makeText(DetailsActivity.this, "Dowloaded successfully", Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Constants.chapter_downloadig = 0;
            Constants.downloadClickedPosition =0;
            Constants.selected_chapter=0;
            Constants.downloadCount=0;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        progressbar_1.setVisibility(View.GONE);
        Release_Privious_Player();
    }

    @Override
    protected void onResume() {
        super.onResume();
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
        Release_Privious_Player();
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
        recreate();
    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        if (playbackState == Player.STATE_READY) {
            if (progress_buffering != null)
                progress_buffering.setVisibility(View.GONE);
        }
        if(playbackState == Player.STATE_BUFFERING && progress_buffering !=null)
        {
            progress_buffering.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onRepeatModeChanged(int repeatMode) {
    }

    @Override
    public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {
    }

    @Override
    public void onPositionDiscontinuity(int reason) {

    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
    }

    @Override
    public void onSeekProcessed() {

    }
}