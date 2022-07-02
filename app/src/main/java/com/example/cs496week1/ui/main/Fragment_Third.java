package com.example.cs496week1.ui.main;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.cs496week1.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Fragment_Third extends Fragment {
    ArrayList<People> peopleArrayList;
    private RecyclerView nameRV;
    private RecyclerView univRV;
    private RecyclerView sidRV;
    private NameRVAdapter nameRVAdapter;
    private UnivRVAdapter univRVAdapter;
    private SidRVAdapter sidRVAdapter;
    private SnapHelper nameHelper;
    private SnapHelper univHelper;
    private SnapHelper sidHelper;
    private LinearLayoutManager nameRVLayoutManager;
    private LinearLayoutManager univRVLayoutManager;
    private LinearLayoutManager sidRVLayoutManager;

    public Fragment_Third() {
        super(R.layout.fragment_third);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_third, container, false);

        // id that is currently displayed
        int curDispId = 4;

        // People Image
        Resources res = getResources();

        // TODO: completed here, apply to tab 2 like this
//        // use string to easily find view by ID
//        String mViewName = "imageView3";
//        int imageViewID = res.getIdentifier(mViewName, "id", getActivity().getPackageName());
//        ImageView iv = (ImageView) view.findViewById(imageViewID);

        // above three lines are equivalent to:
        ImageView iv = (ImageView) view.findViewById(R.id.imageView3);

        // use string to easily call image from drawable
//        String mDrawableName = "photo1";
        String mDrawableName = "photo" + curDispId;
        int imageID = res.getIdentifier(mDrawableName , "drawable", getActivity().getPackageName());
        Drawable drawable = res.getDrawable(imageID);

        iv.setImageDrawable(drawable);

        // People Info
        // Todo: should this be elsewhere?
        jsonParsing(getJsonString());

        nameRV = view.findViewById(R.id.idRVNames);
        univRV = view.findViewById(R.id.idRVUnivs);
        sidRV = view.findViewById(R.id.idRVSids);

        nameRVLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        univRVLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        sidRVLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        nameRV.setLayoutManager(nameRVLayoutManager);
        univRV.setLayoutManager(univRVLayoutManager);
        sidRV.setLayoutManager(sidRVLayoutManager);

        nameRVAdapter = new NameRVAdapter(getActivity(), peopleArrayList);
        univRVAdapter = new UnivRVAdapter(getActivity(), peopleArrayList);
        sidRVAdapter = new SidRVAdapter(getActivity(), peopleArrayList);

        nameRV.setAdapter(nameRVAdapter);
        univRV.setAdapter(univRVAdapter);
        sidRV.setAdapter(sidRVAdapter);


        // Todo: snapping on boot?
        nameHelper = new PagerSnapHelper();
        univHelper = new PagerSnapHelper();
        sidHelper = new PagerSnapHelper();

        nameHelper.attachToRecyclerView(nameRV);
        univHelper.attachToRecyclerView(univRV);
        sidHelper.attachToRecyclerView(sidRV);

        nameRV.addOnScrollListener(new RVScrollListener());
        univRV.addOnScrollListener(new RVScrollListener());
        sidRV.addOnScrollListener(new RVScrollListener());

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
            peopleArrayList = new ArrayList<People>();

            for(int i=0; i<peopleArray.length(); i++)
            {
                JSONObject peopleObject = peopleArray.getJSONObject(i);

                People person = new People();

                person.setId(peopleObject.getInt("id"));
                person.setName(peopleObject.getString("name"));
                person.setUniversity(peopleObject.getString("university"));
                person.setSt_number(peopleObject.getString("st_number"));
                person.setPic_src(peopleObject.getString("pic_src"));

                peopleArrayList.add(person);
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public class RVScrollListener extends RecyclerView.OnScrollListener {
        public RVScrollListener() {
        }

        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            switch (newState) {
                case RecyclerView.SCROLL_STATE_SETTLING:
                    if (checkCorrect()) {
                        // Todo: add functionality here
                        // This part handles the case of full match
                        Toast myToast = Toast.makeText(getContext(), "Successful match", Toast.LENGTH_SHORT);
                        myToast.show();
                    }
                case RecyclerView.SCROLL_STATE_DRAGGING:
                case RecyclerView.SCROLL_STATE_IDLE:
                    break;
            }
        }
    }

    public boolean checkCorrect() {
        // Todo: the type casting aborts app (presumably due to error)
        TextView nameSnapView = (TextView) nameHelper.findSnapView(nameRVLayoutManager);
        TextView univSnapView = (TextView) univHelper.findSnapView(univRVLayoutManager);
        TextView sidSnapView = (TextView) sidHelper.findSnapView(sidRVLayoutManager);

        return (nameSnapView.getText().toString() == peopleArrayList.get(2).getName() &
                univSnapView.getText().toString() == peopleArrayList.get(2).getUniversity() &
                sidSnapView.getText().toString() == peopleArrayList.get(2).getSt_number());
//        return true;
    }
}