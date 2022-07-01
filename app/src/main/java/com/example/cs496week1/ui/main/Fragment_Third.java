package com.example.cs496week1.ui.main;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.cs496week1.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Fragment_Third extends Fragment {
    ViewPager viewPager;
    ArrayList<People> peopleList;

    public Fragment_Third() {
        super(R.layout.fragment_third);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_third, container, false);

        TextView tv= (TextView) view.findViewById(R.id.textView3);
//        tv.setText("test"); //



        // id that is currently displayed
        int curDispId = 4;

        // People Image
        Resources res = getResources();

        // TODO: completed here, apply to tab 2 like this
//        // use string to easily find view by ID
//        String mViewName = "imageView3";
//        int imageViewID = res.getIdentifier(mViewName , "id", getActivity().getPackageName());
//        ImageView iv= (ImageView) view.findViewById(imageViewID);

        // above three lines are equivalent to:
        ImageView iv = (ImageView) view.findViewById(R.id.imageView3);

        // use string to easily call image from drawable
//        String mDrawableName = "photo1";
        String mDrawableName = "photo" + curDispId;
        int imageID = res.getIdentifier(mDrawableName , "drawable", getActivity().getPackageName());
        Drawable drawable = res.getDrawable(imageID);

        iv.setImageDrawable(drawable);

        // People Info
        jsonParsing(getJsonString());
        String test1 = peopleList.get(curDispId-1).getName();
        tv.setText(test1);

        return view;
    }

    //read json and return string
    private String getJsonString() {
        String json = "";

        try {
            InputStream is = getActivity().getAssets().open("People.json");
            int fileSize = is.available();

            byte[] buffer = new byte[fileSize];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

        return json;
    }

    // class to contain people data from json after reading
    public class People{
        private Integer id;
        private String name;
        private String university;
        private String st_number;
        private String pic_src;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUniversity() {
            return university;
        }

        public void setUniversity(String university) {
            this.university = university;
        }

        public String getSt_number() {
            return st_number;
        }

        public void setSt_number(String st_number) {
            this.st_number = st_number;
        }

        public String getPic_src() {
            return pic_src;
        }

        public void setPic_src(String pic_src) {
            this.pic_src = pic_src;
        }
    }

    private void jsonParsing(String json)
    {
        try{
            JSONObject jsonObject = new JSONObject(json);

            JSONArray peopleArray = jsonObject.getJSONArray("People");
            peopleList = new ArrayList<People>();

            for(int i=0; i<peopleArray.length(); i++)
            {
                JSONObject peopleObject = peopleArray.getJSONObject(i);

                People person = new People();

                person.setId(peopleObject.getInt("id"));
                person.setName(peopleObject.getString("name"));
                person.setUniversity(peopleObject.getString("university"));
                person.setSt_number(peopleObject.getString("st_number"));
                person.setPic_src(peopleObject.getString("pic_src"));

                peopleList.add(person);
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }



}