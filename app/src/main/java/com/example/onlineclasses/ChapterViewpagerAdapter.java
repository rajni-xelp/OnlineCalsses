package com.example.onlineclasses;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ChapterViewpagerAdapter extends FragmentPagerAdapter {
    List<String> titleList=new ArrayList<>();
    public ChapterViewpagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        titleList.add("Summary");
        titleList.add("Session");
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position==0)
        {
         return new SummaryFragment();
        }
        else
        {
            return new ChapterFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
