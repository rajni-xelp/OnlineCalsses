package com.example.onlineclasses;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class MyWorkerClass extends Worker {
    String baseurl;
    public MyWorkerClass(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        if (Constants.subject.equalsIgnoreCase("Android") && Constants.languageSelected.equalsIgnoreCase("english")) {
            baseurl = "https://s3.ca-central-1.amazonaws.com/";
        } else {
            baseurl = "http://13.235.209.150/";
        }
        return null;
    }


    public void downloadVideo(ProgressBar progressbar, AppCompatImageView iv_download, String dynamicurl, String fileName) {
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
                        boolean writtenToDisk = writeResponseBodyToDisk(response.body(),fileName);
                        return writtenToDisk;
                    }

                    @Override
                    protected void onPostExecute(Boolean aBoolean) {
                        super.onPostExecute(aBoolean);
                        if (aBoolean != null) {
                            if (aBoolean) {
                              //  tv_offline.setVisibility(View.GONE);
                                saveVideopathInSharedpreference(fileName);
                                Constants.downloadCount=Constants.downloadCount+1;
                                if(Constants.downloadCount==2) {
                                 //   progressbar_1.setVisibility(View.GONE);
                                    Constants.downloadCount=0;
                                    Toast.makeText(MyApplication.getAppContext(), "Dowload successfully", Toast.LENGTH_LONG).show();
                                }

                            } else {
                               // iv_download.setVisibility(View.VISIBLE);
                              //  progressbar_1.setVisibility(View.VISIBLE);
                                Toast.makeText(MyApplication.getAppContext(), "Dowload failed", Toast.LENGTH_LONG).show();
                              //  tv_offline.setText(R.string.available_offline);
                            }
                        }
                    }
                }.execute();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
    private boolean writeResponseBodyToDisk(ResponseBody body,String fileName) {
        try {
            boolean success = true;
//            String fileName = "FILE.mp4";
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

    public void saveVideopathInSharedpreference(String fileName) {
        SharedPreferences sharedPreferences = MyApplication.getAppContext().getSharedPreferences("onlineClassesVideoList", MODE_PRIVATE);
        SharedPreferences.Editor readPercentageditor = sharedPreferences.edit();
       Gson gson = new Gson();
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
}
