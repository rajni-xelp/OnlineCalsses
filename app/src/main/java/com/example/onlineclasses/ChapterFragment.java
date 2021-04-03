package com.example.onlineclasses;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ChapterFragment extends Fragment {

    public ChapterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chapter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tv_session_1=view.findViewById(R.id.tv_session_1);
        TextView tv_session_2=view.findViewById(R.id.tv_session_2);
        TextView tv_subchapter_1_1=view.findViewById(R.id.tv_subchapter_1_1);
        TextView tv_subchapter_1_2=view.findViewById(R.id.tv_subchapter_1_2);
        TextView tv_subchapter_2_1=view.findViewById(R.id.tv_subchapter_2_1);
        TextView tv_subchapter_2_2=view.findViewById(R.id.tv_subchapter_2_2);
        TextView tv_viewmore_1=view.findViewById(R.id.tv_viewmore_1);
        LinearLayout ll_chapter_1=view.findViewById(R.id.ll_chapter_1);

        TextView tv_viewmore_2=view.findViewById(R.id.tv_viewmore_2);
        LinearLayout ll_chapter_2=view.findViewById(R.id.ll_chapter_2);


        switch (Constants.subject)
        {
            case "Android" :
                tv_session_1.setText(getString(R.string._1_android_basic));
                tv_session_2.setText(getString(R.string._2_activity_lifecycle));
//                tv_session_3.setText(getString(R.string.fragment));
                tv_subchapter_1_1.setText(getString(R.string._1_1_androidbasic_chapter_1));
                tv_subchapter_1_2.setText(getString(R.string._1_2_androidbasic_chapter_2));
                tv_subchapter_2_1.setText(getString(R.string._2_1_activitylifecycle_chapter_1));
                tv_subchapter_2_2.setText(getString(R.string._2_2_activitylifecycle_chapter_2));
//                tv_subchapter_3_1.setText(getString(R.string._3_1_fragment_chapter_1));
//                tv_subchapter_3_2.setText(getString(R.string._3_2_fragment_chapter_2));

                break;

            case "Math" :
                tv_session_1.setText(getString(R.string._1_integrations));
                tv_session_2.setText(getString(R.string._2_differentiation));
//                tv_session_3.setText(getString(R.string.algebra));
                tv_subchapter_1_1.setText(getString(R.string._1_1_Integration_chapter_1));
                tv_subchapter_1_2.setText(getString(R.string._1_2_Integration_chapter_2));
                tv_subchapter_2_1.setText(getString(R.string._2_1_differentation_chapter_1));
                tv_subchapter_2_2.setText(getString(R.string._2_2_differentation_chapter_2));
//                tv_subchapter_3_1.setText(getString(R.string._3_1_Algebra_chapter_1));
//                tv_subchapter_3_2.setText(getString(R.string._3_2_Algebra_chapter_2));
                break;

            case "History" :
                tv_session_1.setText(getString(R.string._1_ancient));
                tv_session_2.setText(getString(R.string._2_medieval));
//                tv_session_3.setText(getString(R.string.modern));
                tv_subchapter_1_1.setText(getString(R.string._1_1_Ancient_chapter_1));
                tv_subchapter_1_2.setText(getString(R.string._1_2_Ancient_chapter_2));
                tv_subchapter_2_1.setText(getString(R.string._2_1_medieval_chapter_1));
                tv_subchapter_2_2.setText(getString(R.string._2_2_medieval_chapter_2));
//                tv_subchapter_3_1.setText(getString(R.string._3_1_modern_chapter_1));
//                tv_subchapter_3_2.setText(getString(R.string._3_2_modern_chapter_2));
                break;

            case "Science" :
                tv_session_1.setText(getString(R.string._1_science_basic));
                tv_session_2.setText(getString(R.string._2_waves));
//                tv_session_3.setText(getString(R.string.thermodynamics));
                tv_subchapter_1_1.setText(getString(R.string._1_1_sciencebasic_chapter_1));
                tv_subchapter_1_2.setText(getString(R.string._1_2_sciencebasic_chapter_2));
                tv_subchapter_2_1.setText(getString(R.string._2_1_waves_chapter_1));
                tv_subchapter_2_2.setText(getString(R.string._2_2_waves_chapter_2));
//                tv_subchapter_3_1.setText(getString(R.string._3_1_thermodynamics_chapter_1));
//                tv_subchapter_3_2.setText(getString(R.string._3_2_thermodynamics_chapter_2));
                break;
        }

        tv_viewmore_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(tv_viewmore_1.getText().toString().equalsIgnoreCase(getString(R.string.view)))
               {
                   tv_viewmore_1.setText(getString(R.string.hide));
                   ll_chapter_1.setVisibility(View.VISIBLE);
               }
               else
               {
                   tv_viewmore_1.setText(getString(R.string.view));
                   ll_chapter_1.setVisibility(View.GONE);
               }
            }
        });

        tv_viewmore_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tv_viewmore_2.getText().toString().equalsIgnoreCase(getString(R.string.view)))
                {
                    tv_viewmore_2.setText(getString(R.string.hide));
                    ll_chapter_2.setVisibility(View.VISIBLE);
                }
                else
                {
                    tv_viewmore_2.setText(getString(R.string.view));
                    ll_chapter_2.setVisibility(View.GONE);
                }
            }
        });

//        tv_viewmore_3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(tv_viewmore_3.getText().toString().equalsIgnoreCase(getString(R.string.view)))
//                {
//                    tv_viewmore_3.setText(getString(R.string.hide));
//                    ll_chapter_3.setVisibility(View.VISIBLE);
//                }
//                else
//                {
//                    tv_viewmore_3.setText(getString(R.string.view));
//                    ll_chapter_3.setVisibility(View.GONE);
//                }
//            }
//        });
    }
}