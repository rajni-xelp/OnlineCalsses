package com.example.onlineclasses;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.ObjectKey;

import java.util.Calendar;

public class TopPageFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AppCompatImageView iv_topfragment=view.findViewById(R.id.iv_topfragment);
        int position = getArguments().getInt("position");
        switch (position)
        {
            case  0 :
                Glide.with(this).load(R.drawable.science).centerCrop().into(iv_topfragment);
                break;

            case  1 :
                Glide.with(this).load(R.drawable.android).centerCrop().into(iv_topfragment);
                break;

            case  2 :
                Glide.with(this).load(R.drawable.history).centerCrop().into(iv_topfragment);
                break;


        }
    }
}