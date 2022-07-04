package com.example.cs496week1.ui.main;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.cs496week1.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

import link.fls.swipestack.SwipeStack;
import com.example.cs496week1.ui.main.widget.WheelView;

public class Fragment_Third extends Fragment {
    ArrayList<People> peopleArrayList;
    ArrayList<People> selectedArrayList;
    private RecyclerView nameRV;
    private RecyclerView univRV;
    private RecyclerView sidRV;
    private NameRVAdapter nameRVAdapter;
    private UnivRVAdapter univRVAdapter;
    private SidRVAdapter sidRVAdapter;

    //Slider
    private SnapHelper nameHelper;
    private SnapHelper univHelper;
    private SnapHelper sidHelper;
    private LinearLayoutManager nameRVLayoutManager;
    private LinearLayoutManager univRVLayoutManager;
    private LinearLayoutManager sidRVLayoutManager;

    //CARD DECK
    private SwipeStack cardStack;
    private CardsAdapter cardsAdapter;
    private ArrayList<CardItem> cardItems;
    private View btnCancel;
    private View btnLove;
    // position of currently displayed item
    private int currentPosition;

    //WHEELVIEW
//    private WheelView wv_name, wv_univ, wv_name;
    private WheelView wv_name, wv_univ, wv_sid;
//    private CityAdapter cityAdapter;
//    private CountyAdapter countyAdapter;
    private NameAdapter nameAdapter;
    private UnivAdapter univAdapter;
    private SidAdapter sidAdapter;
//    private TextView tv_name, tv_univ, tv_number;
    private TextView tv_name, tv_univ, tv_sid;
    private WheelView wv_number;

    private String curName;
    private String curUniv;
    private String curSid;

    ArrayList<String> selectedUniqueName;
    ArrayList<String> selectedUniqueUniv;
    ArrayList<String> selectedUniqueSid;

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

//        // TODO: completed here, apply to tab 2 like this
////        // use string to easily find view by ID
////        String mViewName = "imageView3";
////        int imageViewID = res.getIdentifier(mViewName , "id", getActivity().getPackageName());
////        ImageView iv= (ImageView) view.findViewById(imageViewID);
//
//        // above three lines are equivalent to:
//        ImageView iv = (ImageView) view.findViewById(R.id.imageView3);
//
//        // use string to easily call image from drawable
////        String mDrawableName = "photo1";
//        String mDrawableName = "photo" + curDispId;
//        int imageID = res.getIdentifier(mDrawableName , "drawable", getActivity().getPackageName());
//        Drawable drawable = res.getDrawable(imageID);
//
//        iv.setImageDrawable(drawable);

        // People Info
        // Todo: should this be elsewhere?
        jsonParsing(getJsonString());

        ArrayList<Integer> idList = new ArrayList<>(Arrays.asList(1, 2, 4));

        // IMPORTANT NOTE: both idList and peopleArrayList should be in increasing order of id
        //                 in order for this to properly function
        selectedArrayList = new ArrayList<People>();
        int j = 0;
        for (int i = 0; i < idList.size(); i++) {
//            int j = 0;
            while (peopleArrayList.get(j).getId() != idList.get(i)) {
                j++;
            }
            selectedArrayList.add(peopleArrayList.get(j));
            j++;
        }

        //CARD DECK
        cardStack = (SwipeStack) view.findViewById(R.id.container);
        setCardStackAdapter(view);
        currentPosition = 0;

        cardStack.setListener(new SwipeStack.SwipeStackListener() {
            @Override
            public void onViewSwipedToLeft(int position) {
                  currentPosition = position + 1;
            }

            @Override
            public void onViewSwipedToRight(int position) {
                  currentPosition = position + 1;
            }

            @Override
            public void onStackEmpty() {
                // Todo: what should we do on stack empty?
            }
        });

        TreeSet<String> selectedNameSet = new TreeSet<>();
        TreeSet<String> selectedUnivSet = new TreeSet<>();
        TreeSet<String> selectedSidSet = new TreeSet<>();

        for (int i = 0; i < selectedArrayList.size(); i++) {
            selectedNameSet.add(selectedArrayList.get(i).getName());
            selectedUnivSet.add(selectedArrayList.get(i).getUniversity());
            selectedSidSet.add(selectedArrayList.get(i).getSt_number());
        }

        selectedUniqueName = new ArrayList<>(selectedNameSet);
        selectedUniqueUniv = new ArrayList<>(selectedUnivSet);
        selectedUniqueSid = new ArrayList<>(selectedSidSet);

        //WheelView
        wv_name = (WheelView) view.findViewById(R.id.wv_name);
        wv_univ = (WheelView) view.findViewById(R.id.wv_univ);
        wv_sid = (WheelView) view.findViewById(R.id.wv_sid);
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        tv_univ = (TextView) view.findViewById(R.id.tv_univ);
        tv_sid = (TextView) view.findViewById(R.id.tv_sid);

        /* 市滑轮控件 */
        nameAdapter = new NameAdapter();
        wv_name.setAdapter(nameAdapter);
        wv_name.setOnItemSelectedListener(new WheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelView wv, int index) {
                curName = nameAdapter.getItem(index);
                tv_name.setText("市: "+nameAdapter.getItem(index));
                if (checkCorrect()) {
                    // Todo: add functionality here
                    // This part handles the case of full match
                    currentPosition++;
                    if (currentPosition % 2 == 0) {
                        cardStack.swipeTopViewToRight();
                    } else {
                        cardStack.swipeTopViewToLeft();
                    }
                }
            }
        });

        /* 区滑轮控件 */
        univAdapter = new UnivAdapter();
        wv_univ.setAdapter(univAdapter);
        wv_univ.setOnItemSelectedListener(new WheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelView wv, int index) {
                curUniv = univAdapter.getItem(index);
                tv_univ.setText("县: "+univAdapter.getItem(index));
                if (checkCorrect()) {
                    // Todo: add functionality here
                    // This part handles the case of full match
                    currentPosition++;
                    if (currentPosition % 2 == 0) {
                        cardStack.swipeTopViewToRight();
                    } else {
                        cardStack.swipeTopViewToLeft();
                    }
                }
            }
        });

        sidAdapter = new SidAdapter();
        wv_sid.setAdapter(sidAdapter);
        wv_sid.setOnItemSelectedListener(new WheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelView wv, int index) {
                curSid = sidAdapter.getItem(index);
                tv_sid.setText("县: "+sidAdapter.getItem(index));
                if (checkCorrect()) {
                    // Todo: add functionality here
                    // This part handles the case of full match
                    currentPosition++;
                    if (currentPosition % 2 == 0) {
                        cardStack.swipeTopViewToRight();
                    } else {
                        cardStack.swipeTopViewToLeft();
                    }
                }
            }
        });

        curName = nameAdapter.getItem(0);
        curUniv = univAdapter.getItem(0);
        curSid = sidAdapter.getItem(0);
        tv_name.setText(curName);
        tv_univ.setText(curUniv);
        tv_sid.setText(curSid);

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
        catch (IOException ex) {
            ex.printStackTrace();
        }

        return json;
    }

    private void jsonParsing(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);

            JSONArray peopleArray = jsonObject.getJSONArray("People");
            peopleArrayList = new ArrayList<People>();

            for(int i = 0; i < peopleArray.length(); i++) {
                JSONObject peopleObject = peopleArray.getJSONObject(i);

                People person = new People();

                person.setId(peopleObject.getInt("id"));
                person.setName(peopleObject.getString("name"));
                person.setUniversity(peopleObject.getString("university"));
                person.setSt_number(peopleObject.getString("st_number"));
                person.setPic_src(peopleObject.getString("pic_src"));

                peopleArrayList.add(person);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
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

    //SLIDER
    public class RVScrollListener extends RecyclerView.OnScrollListener {
        public RVScrollListener() {
        }

        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            switch (newState) {
                case RecyclerView.SCROLL_STATE_SETTLING:
                    if (checkCorrect()) {
                        // Todo: add functionality here
                        // This part handles the case of full match
                        currentPosition++;
                        if (currentPosition % 2 == 0) {
                            cardStack.swipeTopViewToRight();
                        } else {
                            cardStack.swipeTopViewToLeft();
                        }
                    }
                case RecyclerView.SCROLL_STATE_DRAGGING:
                case RecyclerView.SCROLL_STATE_IDLE:
                    break;
            }
        }
    }

    public boolean checkCorrect() {
        return (curName.equals(selectedArrayList.get(currentPosition).getName()) &
                curUniv.equals(selectedArrayList.get(currentPosition).getUniversity()) &
                curSid.toString().equals(selectedArrayList.get(currentPosition).getSt_number()));
    }

    //CARD DECK
    private void setCardStackAdapter(View view) {
        cardItems = new ArrayList<>();

        Resources resources = getResources();

        for (int i = 0; i < selectedArrayList.size(); i++) {
            String mDrawableName = "photo" + (i + 1);
            int imageID = resources.getIdentifier(mDrawableName, "drawable", getActivity().getPackageName());

            cardItems.add(new CardItem(imageID));
        }

        cardsAdapter = new CardsAdapter(getActivity(), cardItems);
        cardStack.setAdapter(cardsAdapter);
    }

    private class NameAdapter extends WheelView.WheelAdapter {
        @Override
        protected int getItemCount() {
            return selectedUniqueName.size();
        }

        @Override
        protected String getItem(int index) {
            return selectedUniqueName.get(index);
        }
    }

    private class UnivAdapter extends WheelView.WheelAdapter {
        @Override
        protected int getItemCount() {
            return selectedUniqueUniv.size();
        }

        @Override
        protected String getItem(int index) {
            return selectedUniqueUniv.get(index);
        }
    }

    private class SidAdapter extends WheelView.WheelAdapter {
        @Override
        protected int getItemCount() {
            return selectedUniqueSid.size();
        }

        @Override
        protected String getItem(int index) {
            return selectedUniqueSid.get(index);
        }
    }
}