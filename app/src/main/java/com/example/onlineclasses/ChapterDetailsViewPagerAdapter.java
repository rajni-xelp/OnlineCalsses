package com.example.onlineclasses;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ChapterDetailsViewPagerAdapter extends FragmentPagerAdapter {
    List<String> chapterDetailsList;
    public ChapterDetailsViewPagerAdapter(@NonNull FragmentManager fm, int behavior,List<String> chapterDetailsList) {
        super(fm, behavior);
        this.chapterDetailsList=chapterDetailsList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        if(position==0)
        {
            Bundle bundle=new Bundle();
            bundle.putString("key",chapterDetailsList.get(0));
            ChapterDetailsSubFragment chapterDetailsSubFragment=new ChapterDetailsSubFragment();
            chapterDetailsSubFragment.setArguments(bundle);
            return chapterDetailsSubFragment;
        }
        else if(position==1)
        {
            Bundle bundle=new Bundle();
            bundle.putString("key",chapterDetailsList.get(1));
            ChapterDetailsSubFragment chapterDetailsSubFragment=new ChapterDetailsSubFragment();
            chapterDetailsSubFragment.setArguments(bundle);
            return chapterDetailsSubFragment;
        }
        else
        {
            Bundle bundle=new Bundle();
            bundle.putString("key",chapterDetailsList.get(2));
            ChapterDetailsSubFragment chapterDetailsSubFragment=new ChapterDetailsSubFragment();
            chapterDetailsSubFragment.setArguments(bundle);
            return chapterDetailsSubFragment;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
