package com.example.cs496week1.ui.main;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.cs496week1.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public SectionsPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }
//
////    public SectionsPagerAdapter(Context context, FragmentManager fm) {
////        super(fm);
////        mContext = context;
////    }

    public void addFragment(Fragment fragment, String title){
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
    //    public SectionPageAdapter(FragmentManager fm){
//        super(fm);
//    }
    @Override
    public Fragment getItem(int position) { return mFragmentList.get(position); }

    @Override
    public int getCount() {
        return mFragmentList.size() ;
    }
}