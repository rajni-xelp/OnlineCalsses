package com.example.onlineclasses;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import static androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class ChapterDetailsFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chapter_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tv_heading=view.findViewById(R.id.tv_heading);
        ViewPager chapter_details_viewpager=view.findViewById(R.id.chapter_details_viewpager);
        List<String> chapterDetailsList=new ArrayList<>();
        TabLayout tb_layout=view.findViewById(R.id.tb_layout);
            switch (Constants.chapterSelected)
            {
                case "Android_basic":
                    tv_heading.setText("Android Basic");
                    chapterDetailsList.add("This section describes how to build a simple Android app. First, you learn how to create a \"Hello, World!\" project with Android Studio and run it. Then, you create a new interface for the app that takes user input and switches to a new screen in the app to display it.");
                    chapterDetailsList.add("Android apps are built as a combination of components that can be invoked individually. For example, an activity is a type of app component that provides a user interface (UI).");
                    chapterDetailsList.add("Android allows you to provide different resources for different devices. For example, you can create different layouts for different screen sizes. The system determines which layout to use based on the screen size of the current device.");
                    break;

                case "Activity_lifecycle":
                    tv_heading.setText("Activity Lifecycle");
                    chapterDetailsList.add("The Activity class is a crucial component of an Android app, and the way activities are launched and put together is a fundamental part of the platform's application model. Unlike programming paradigms in which apps are launched with a main() method");
                    chapterDetailsList.add("As the user begins to leave the activity, the system calls methods to dismantle the activity. In some cases, this dismantlement is only partial; the activity still resides in memory (such as when the user switches to another app), and can still come back to the foreground.");
                    chapterDetailsList.add("This document explains the activity lifecycle in detail. The document begins by describing the lifecycle paradigm. Next, it explains each of the callbacks: what happens internally while they execute, and what you should implement during them.");
                    break;

                case "math_integration":
                    tv_heading.setText("Integration");
                    chapterDetailsList.add("The integral sign ∫ represents integration. (In modern Arabic mathematical notation, a reflected integral symbol ArabicIntegralSign.svg is used.[17]) The symbol dx, called the differential of the variable x, indicates that the variable of integration is x. The function f(x) is called the integrand");
                    chapterDetailsList.add("After the Integral Symbol we put the function we want to find the integral of (called the Integrand),\n" +
                            "\n" +
                            "and then finish with dx to mean the slices go in the x direction (and approach zero in width).\n" +
                            "\n" +
                            "And here is how we write the answer:");
                    chapterDetailsList.add("The integral sign ∫ represents integration. (In modern Arabic mathematical notation, a reflected integral symbol ArabicIntegralSign.svg is used.[17]) The symbol dx, called the differential of the variable x, indicates that the variable of integration is x. The function f(x) is called the integrand");
                    break;

                case "math_differentiation":
                    tv_heading.setText("Differentiation");
                    chapterDetailsList.add("The integral sign ∫ represents integration. (In modern Arabic mathematical notation, a reflected integral symbol ArabicIntegralSign.svg is used.[17]) The symbol dx, called the differential of the variable x, indicates that the variable of integration is x. The function f(x) is called the integrand");
                    chapterDetailsList.add("After the Integral Symbol we put the function we want to find the integral of (called the Integrand),\n" +
                            "\n" +
                            "and then finish with dx to mean the slices go in the x direction (and approach zero in width).\n" +
                            "\n" +
                            "And here is how we write the answer:");
                    chapterDetailsList.add("The integral sign ∫ represents integration. (In modern Arabic mathematical notation, a reflected integral symbol ArabicIntegralSign.svg is used.[17]) The symbol dx, called the differential of the variable x, indicates that the variable of integration is x. The function f(x) is called the integrand");
                    break;

            }
        ChapterDetailsViewPagerAdapter chapterDetailsViewPagerAdapter=new ChapterDetailsViewPagerAdapter(getChildFragmentManager(),BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,chapterDetailsList);
        chapter_details_viewpager.setAdapter(chapterDetailsViewPagerAdapter);
        tb_layout.setupWithViewPager(chapter_details_viewpager);
    }
}