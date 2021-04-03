package com.example.onlineclasses;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SummaryFragment extends Fragment {


    public SummaryFragment() {
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
        return inflater.inflate(R.layout.fragment_summary, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tv_whatwill_i_learn_details=view.findViewById(R.id.tv_whatwill_i_learn_details);
        TextView tv_skill_1=view.findViewById(R.id.tv_skill_1);
        TextView tv_skill_2=view.findViewById(R.id.tv_skill_2);
        TextView tv_skill_3=view.findViewById(R.id.tv_skill_3);
        switch (Constants.subject)
        {
            case "Android" :
                tv_whatwill_i_learn_details.setText(getString(R.string.what_will_i_learn_android_details));
                tv_skill_1.setText(getString(R.string._1_programming));
                tv_skill_2.setText(getString(R.string._2_r_programming_and_its_packages));
                tv_skill_3.setText(getString(R.string._3_algorithm));
                break;

            case "Math" :
                tv_whatwill_i_learn_details.setText(getString(R.string.what_will_i_learn_math_details));
                tv_skill_1.setText(getString(R.string._1_calculus));
                tv_skill_2.setText(getString(R.string._2_r_integration));
                tv_skill_3.setText(getString(R.string._3_Probability));
                break;

            case "History" :
                tv_whatwill_i_learn_details.setText(getString(R.string.what_will_i_learn_history_details));
                tv_skill_1.setText(getString(R.string._1_medival));
                tv_skill_2.setText(getString(R.string._2_ancient));
                tv_skill_3.setText(getString(R.string._3_modern));
                break;

            case "Science" :
                tv_whatwill_i_learn_details.setText(getString(R.string.what_will_i_learn_science_details));
                tv_skill_1.setText(getString(R.string._1_practice_roblems));
                tv_skill_2.setText(getString(R.string._2_theory));
                tv_skill_3.setText(getString(R.string._3_basic));
                break;
        }

    }
}