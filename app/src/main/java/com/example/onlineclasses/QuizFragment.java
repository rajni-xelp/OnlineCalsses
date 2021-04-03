package com.example.onlineclasses;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import static androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class QuizFragment extends Fragment {
    public QuizFragment() {
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
        return inflater.inflate(R.layout.fragment_quiz, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager chapter_details_viewpager=view.findViewById(R.id.chapter_quiz_viewpager);
        List<ModelForQuestion> modelForQuestionList=new ArrayList<>();
        TabLayout tb_layout=view.findViewById(R.id.tb_layout);
        switch (Constants.chapterSelected)
        {
            case "Android_basic":
                ModelForQuestion modelForQuestion1=new ModelForQuestion();
                modelForQuestion1.question="Which of these cann't be called using intent ?";
                modelForQuestion1.answer_1="Activity";
                modelForQuestion1.answer_2="Broadcast receiver";
                modelForQuestion1.answer_3="Content provider";
                modelForQuestion1.answer_4="Services";

                ModelForQuestion modelForQuestion2=new ModelForQuestion();
                modelForQuestion2.question="Which are the life cycle methods below belongs to fragment ?";
                modelForQuestion2.answer_1="on Create";
                modelForQuestion2.answer_2="on Destroy";
                modelForQuestion2.answer_3="on Heavy";
                modelForQuestion2.answer_4="on Loose";

                modelForQuestionList.add(modelForQuestion1);
                modelForQuestionList.add(modelForQuestion2);
                break;

            case "Activity_lifecycle":
                ModelForQuestion modelForQuestion3=new ModelForQuestion();
                modelForQuestion3.question="Which overrided method of activity called first after launch ?";
                modelForQuestion3.answer_1="on Create";
                modelForQuestion3.answer_2="on Start";
                modelForQuestion3.answer_3="on Pause";
                modelForQuestion3.answer_4="on Resume";

                ModelForQuestion modelForQuestion4=new ModelForQuestion();
                modelForQuestion4.question="In how many ways can an activity be destroyed ?";
                modelForQuestion4.answer_1="One";
                modelForQuestion4.answer_2="Two";
                modelForQuestion4.answer_3="Three";
                modelForQuestion4.answer_4="Four";

                modelForQuestionList.add(modelForQuestion3);
                modelForQuestionList.add(modelForQuestion4);
                break;

            case "math_integration":
                ModelForQuestion modelForQuestion5=new ModelForQuestion();
                modelForQuestion5.question="What would be answer for 2 + 3  ?";
                modelForQuestion5.answer_1="Five";
                modelForQuestion5.answer_2="Six";
                modelForQuestion5.answer_3="Seven";
                modelForQuestion5.answer_4="Eight";

                ModelForQuestion modelForQuestion6=new ModelForQuestion();
                modelForQuestion6.question="We can calculate the integration in how many ways ?";
                modelForQuestion6.answer_1="One";
                modelForQuestion6.answer_2="Two";
                modelForQuestion6.answer_3="Three";
                modelForQuestion6.answer_4="Four";

                modelForQuestionList.add(modelForQuestion5);
                modelForQuestionList.add(modelForQuestion6);
                break;

        }

        QuizViewPagerAdapter quizViewPagerAdapter=new QuizViewPagerAdapter(getChildFragmentManager(),BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,modelForQuestionList);
        chapter_details_viewpager.setAdapter(quizViewPagerAdapter);
        tb_layout.setupWithViewPager(chapter_details_viewpager);
    }
}