package com.example.cs496week1.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.example.cs496week1.R;

public class Fragment_Second extends Fragment {
    ViewPager viewPager;

    public Fragment_Second(){
        super(R.layout.fragment_second);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_second, container, false);

        GridView gridView = (GridView) rootView.findViewById(R.id.fragment_second);

        // Instance of ImageAdapter Class
        gridView.setAdapter(new ImageAdapter(getActivity()));
        return rootView;
    }

}