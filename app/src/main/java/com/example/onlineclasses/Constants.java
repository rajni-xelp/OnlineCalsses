package com.example.onlineclasses;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.ProgressBar;

public class Constants {
    public static String video_url_1 = "";
    public static String video_url_2 = "";
    public static int chapter_downloadig = 0;
    public static int downloadClickedPosition = 0;
    public static boolean singleChapterDownloaded = false;

    public static int selected_chapter = 0;
    ;

    public static ProgressBar staticProgressbar_1;
//   public static ProgressBar staticProgressbar_2;

    public static String select_with_language_1 = "";
    public static String select_with_language_2 = "";

    public static String subject;
    public static int downloadCount = 0;
    public static String languageSelected = "";
    public static String chapterSelected = "";
    public static String androidvideo_1 = "codingwithmitch/media/VideoPlayerRecyclerView/Rest+api+teaser+video.mp4";
    public static String androidvideo_2 = "codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.mp4";
    public static String androidvideo_3 = "codingwithmitch/media/VideoPlayerRecyclerView/REST+API+Retrofit+MVVM+Course+Summary.mp4";

    public static String androidvideo_hindi = "pencil-backend/test-vids/androidhindi.mp4";

//    public static String mathvideo_1 = "pencil-backend/test-vids/English_Maths_1.mp4";
//    public static String mathvideo_2 = "pencil-backend/test-vids/English_Maths_2.mp4";
//
//    public static String mathvideo_hindi_1 = "pencil-backend/test-vids/Hindi_Maths_1.mp4";
//    public static String mathvideo_hindi_2 = "pencil-backend/test-vids/Hindi_Maths_2.mp4";

    public static String mathvideo_1 = "pencil-backend/test-vids/englis_math_addition_3.mp4";
    public static String mathvideo_2 = "pencil-backend/test-vids/english_math_subtraction_4.mp4";

    public static String mathvideo_hindi_1 = "pencil-backend/test-vids/hindi_math_addition_3.mp4";
    public static String mathvideo_hindi_2 = "pencil-backend/test-vids/hindi_math_subtraction_4.mp4";


    public static String mathvideo_kannada_1 = "pencil-backend/test-vids/Kaanada_Maths_1.mp4";
    public static String mathvideo_kannada_2 = "pencil-backend/test-vids/kannada_math_addition_2.mp4";


    public static String science_1 = "pencil-backend/test-vids/English_Science_1.mp4";
    public static String science_2 = "pencil-backend/test-vids/English_Science_2.mp4";

    public static String science_Hindi_1 = "pencil-backend/test-vids/Hindi_Science_1.mp4";

    public static String kannada_science_1 = "pencil-backend/test-vids/kannada_science_1.mp4";


    public static boolean isNetworkAvailable(Context _context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
