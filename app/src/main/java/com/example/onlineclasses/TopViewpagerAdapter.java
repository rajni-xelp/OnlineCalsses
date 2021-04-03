package com.example.onlineclasses;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

public class TopViewpagerAdapter extends FragmentPagerAdapter {
    public TopViewpagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position==0)
        {
            Bundle bundle=new Bundle();
            bundle.putInt("position",0);
            TopPageFragment topPageFragment=new TopPageFragment();
            topPageFragment.setArguments(bundle);
            return topPageFragment;
        }
        else if(position==1)
        {
            Bundle bundle=new Bundle();
            bundle.putInt("position",1);
            TopPageFragment topPageFragment=new TopPageFragment();
            topPageFragment.setArguments(bundle);
            return topPageFragment;
        }
        else
        {
            Bundle bundle=new Bundle();
            bundle.putInt("position",2);
            TopPageFragment topPageFragment=new TopPageFragment();
            topPageFragment.setArguments(bundle);
            return topPageFragment;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
