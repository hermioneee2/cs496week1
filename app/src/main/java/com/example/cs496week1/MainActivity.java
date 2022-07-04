package com.example.cs496week1;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.cs496week1.databinding.ActivityMainBinding;
import com.example.cs496week1.ui.main.Fragment_First;
import com.example.cs496week1.ui.main.Fragment_Second;
import com.example.cs496week1.ui.main.Fragment_Third;
import com.example.cs496week1.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

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

        jsonParsing(getJsonString());

        Commons.selectedArrayList = new ArrayList<>();

        ArrayList<Integer> idList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                11, 12, 13, 14, 15, 16, 17, 18, 19, 20));

        // IMPORTANT NOTE: both idList and peopleArrayList should be in increasing order of id
        //                 in order for this to properly function
        int j = 0;
        for (int i = 0; i < idList.size(); i++) {
            while (Commons.peopleArrayList.get(j).getId() != idList.get(i)) {
                j++;
            }
            Commons.selectedArrayList.add(Commons.peopleArrayList.get(j));
            j++;
        }
    }

    //added for easier tab change
    public void setupViewPager(ViewPager viewPager) {
        sectionsPagerAdapter.addFragment(new Fragment_First(), getString(R.string.tab_text_1));
        sectionsPagerAdapter.addFragment(new Fragment_Second(), getString(R.string.tab_text_2));
        sectionsPagerAdapter.addFragment(new Fragment_Third(), getString(R.string.tab_text_3));
        viewPager.setAdapter(sectionsPagerAdapter);
    }

    //read json and return string
    private String getJsonString() {
        String json = "";

        try {
            InputStream is = this.getAssets().open("People.json");
            int fileSize = is.available();

            byte[] buffer = new byte[fileSize];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        return json;
    }

    private void jsonParsing(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);

            JSONArray peopleArray = jsonObject.getJSONArray("People");
            Commons.peopleArrayList = new ArrayList<>();

            for(int i = 0; i < peopleArray.length(); i++) {
                JSONObject peopleObject = peopleArray.getJSONObject(i);

                Commons.People person = new Commons.People();

                person.setId(peopleObject.getInt("id"));
                person.setName(peopleObject.getString("name"));
                person.setUniv(peopleObject.getString("univ"));
                person.setSid(peopleObject.getString("sid"));
                person.setNumb(peopleObject.getString("numb"));
                person.setPSrc(peopleObject.getString("pSrc"));

                Commons.peopleArrayList.add(person);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}