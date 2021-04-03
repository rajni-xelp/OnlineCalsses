package com.example.onlineclasses;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import static androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class QuizCheckboxSubFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_checkbox_sub, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String question= getArguments().getString("question");
        String answer1=getArguments().getString("answer1");
        String answer2=getArguments().getString("answer2");
        String answer3=getArguments().getString("answer3");
        String answer4=getArguments().getString("answer4");
        TextView tv_questions=view.findViewById(R.id.tv_questions);
        CheckBox cb_1=view.findViewById(R.id.cb_1);
        CheckBox cb_2=view.findViewById(R.id.cb_2);
        CheckBox cb_3=view.findViewById(R.id.cb_3);
        CheckBox cb_4=view.findViewById(R.id.cb_4);

        tv_questions.setText(question);
        cb_1.setText(answer1);
        cb_2.setText(answer2);
        cb_3.setText(answer3);
        cb_4.setText(answer4);

    }
}