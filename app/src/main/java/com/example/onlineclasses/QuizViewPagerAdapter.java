package com.example.onlineclasses;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class QuizViewPagerAdapter extends FragmentPagerAdapter {
    List<ModelForQuestion> modelForQuestionList;
    public QuizViewPagerAdapter(@NonNull FragmentManager fm, int behavior, List<ModelForQuestion> modelForQuestionList) {
        super(fm, behavior);
        this.modelForQuestionList=modelForQuestionList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position==0)
        {
            Bundle bundle=new Bundle();
            bundle.putString("question",modelForQuestionList.get(0).question);
            bundle.putString("answer1",modelForQuestionList.get(0).answer_1);
            bundle.putString("answer2",modelForQuestionList.get(0).answer_2);
            bundle.putString("answer3",modelForQuestionList.get(0).answer_3);
            bundle.putString("answer4",modelForQuestionList.get(0).answer_4);
            QuizRadioSubFragment quizRadioSubFragment=new QuizRadioSubFragment();
            quizRadioSubFragment.setArguments(bundle);

            return quizRadioSubFragment;
        }
        else
        {
            Bundle bundle=new Bundle();
            bundle.putString("question",modelForQuestionList.get(1).question);
            bundle.putString("answer1",modelForQuestionList.get(1).answer_1);
            bundle.putString("answer2",modelForQuestionList.get(1).answer_2);
            bundle.putString("answer3",modelForQuestionList.get(1).answer_3);
            bundle.putString("answer4",modelForQuestionList.get(1).answer_4);
            QuizCheckboxSubFragment quizCheckboxSubFragment=new QuizCheckboxSubFragment();
            quizCheckboxSubFragment.setArguments(bundle);
            return quizCheckboxSubFragment;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
