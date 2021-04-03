package com.example.onlineclasses;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

public class QuizRadioSubFragment extends Fragment {

    public QuizRadioSubFragment() {
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
        return inflater.inflate(R.layout.fragment_quiz_sub_fragment, container, false);
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
        RadioButton rbt_1=view.findViewById(R.id.rbt_1);
        RadioButton rbt_2=view.findViewById(R.id.rbt_2);
        RadioButton rbt_3=view.findViewById(R.id.rbt_3);
        RadioButton rbt_4=view.findViewById(R.id.rbt_4);
        tv_questions.setText(question);
        rbt_1.setText(answer1);
        rbt_2.setText(answer2);
        rbt_3.setText(answer3);
        rbt_4.setText(answer4);
    }
}