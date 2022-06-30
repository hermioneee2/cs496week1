package com.example.cs496week1.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.cs496week1.R;

public class Fragment_Third extends Fragment {
    ViewPager viewPager;

    public Fragment_Third(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        return view;

    }

}