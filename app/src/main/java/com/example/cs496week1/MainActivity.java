package com.example.cs496week1;

import android.content.Context;
import android.os.Bundle;

import com.example.cs496week1.databinding.ActivityMainBinding;
import com.example.cs496week1.ui.main.SectionsPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.cs496week1.ui.main.SectionsPagerAdapter;
import com.example.cs496week1.databinding.ActivityMainBinding;

import com.example.cs496week1.ui.main.Fragment_First;
import com.example.cs496week1.ui.main.Fragment_Second;
import com.example.cs496week1.ui.main.Fragment_Third;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

    public static Context contextOfApplication;

    public static Context getContextOfApplication()
    {
        return contextOfApplication;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        contextOfApplication = getApplicationContext();
    }

    //added for easier tab change
    public void setupViewPager(ViewPager viewPager) {
        sectionsPagerAdapter.addFragment(new Fragment_First(), "Tab 1");
        sectionsPagerAdapter.addFragment(new Fragment_Second(), "Tab 2");
        sectionsPagerAdapter.addFragment(new Fragment_Third(), "Tab 3");
        viewPager.setAdapter(sectionsPagerAdapter);
    }
}