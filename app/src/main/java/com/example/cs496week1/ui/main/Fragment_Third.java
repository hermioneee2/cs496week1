package com.example.cs496week1.ui.main;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
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
import java.util.HashSet;
import java.util.List;
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
//    private WheelView wv_city, wv_county, wv_name;
    private WheelView wv_city, wv_county, wv_sid;
    private CityAdapter cityAdapter;
    private CountyAdapter countyAdapter;
    private NameAdapter nameAdapter;
    private UnivAdapter univAdapter;
    private SidAdapter sidAdapter;
//    private TextView tv_city, tv_county, tv_number;
    private TextView tv_city, tv_county, tv_sid;
    private WheelView wv_number;

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
        // People Image
//        Resources res = getResources();

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
//                  currentPosition = position + 1;
            }

            @Override
            public void onViewSwipedToRight(int position) {
//                  currentPosition = position + 1;
            }

            @Override
            public void onStackEmpty() {
                // Todo: what should we do on stack empty?
            }
        });

//        /* 水平滑轮控件 */
//        wv_number.setAdapter(new WheelView.WheelAdapter() {
//            @Override
//            protected int getItemCount() {
//                return 100;
//            }
//
//            @Override
//            protected String getItem(int index) {
//                return String.valueOf(index);
//            }
//        });
//        wv_number.setOnItemSelectedListener(new WheelView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(WheelView wv, int index) {
//                tv_number.setText("水平布局"+index);
//            }
//        });
//        wv_number.setCurrentItem(88);

//
//        nameRV = view.findViewById(R.id.idRVNames);
//        univRV = view.findViewById(R.id.idRVUnivs);
//        sidRV = view.findViewById(R.id.idRVSids);
//
//        nameRVLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
//        univRVLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
//        sidRVLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
//
//        nameRV.setLayoutManager(nameRVLayoutManager);
//        univRV.setLayoutManager(univRVLayoutManager);
//        sidRV.setLayoutManager(sidRVLayoutManager);
//
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
//
//        nameRVAdapter = new NameRVAdapter(getActivity(), selectedUniqueName);
//        univRVAdapter = new UnivRVAdapter(getActivity(), selectedUniqueUniv);
//        sidRVAdapter = new SidRVAdapter(getActivity(), selectedUniqueSid);
//
//        nameRV.setAdapter(nameRVAdapter);
//        univRV.setAdapter(univRVAdapter);
//        sidRV.setAdapter(sidRVAdapter);
//
//        // Todo: snapping on boot?
//        nameHelper = new PagerSnapHelper();
//        univHelper = new PagerSnapHelper();
//        sidHelper = new PagerSnapHelper();
//
//        nameHelper.attachToRecyclerView(nameRV);
//        univHelper.attachToRecyclerView(univRV);
//        sidHelper.attachToRecyclerView(sidRV);
//
//        nameRV.addOnScrollListener(new RVScrollListener());
//        univRV.addOnScrollListener(new RVScrollListener());
//        sidRV.addOnScrollListener(new RVScrollListener());
//
//        nameRVLayoutManager.scrollToPosition(Integer.MAX_VALUE / 2);
//        univRVLayoutManager.scrollToPosition(Integer.MAX_VALUE / 2);
//        sidRVLayoutManager.scrollToPosition(Integer.MAX_VALUE / 2);

        //WheelView
//        getActivity().setContentView(R.layout.activity_main);
        getActivity().setTitle("游小陈的博客");

        wv_city = (WheelView) view.findViewById(R.id.wv_city);
        wv_county = (WheelView) view.findViewById(R.id.wv_county);
//        wv_number = (WheelView) view.findViewById(R.id.wv_number);
//        wv_name = (WheelView) view.findViewById(R.id.wv_name);
        wv_sid = (WheelView) view.findViewById(R.id.wv_name);
        tv_city = (TextView) view.findViewById(R.id.tv_city);
        tv_county = (TextView) view.findViewById(R.id.tv_county);
        tv_sid = (TextView) view.findViewById(R.id.tv_sid);
//        tv_number = (TextView) view.findViewById(R.id.tv_number);

//        /* 市滑轮控件 */
//        cityAdapter = new CityAdapter();
//        wv_city.setAdapter(cityAdapter);
//        wv_city.setOnItemSelectedListener(new WheelView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(WheelView wv, int index) {
//                tv_city.setText("市: "+cityAdapter.getItem(index));
//                List<String> strs = Arrays.asList(TestDatas.AREAS[index]);
////                List<String> strs = Arrays.asList(selectedUniqueName.get(index));
//                countyAdapter.strs.clear();
//                countyAdapter.strs.addAll(strs);
//                countyAdapter.notifyDataSetChanged();
//                wv_county.setCurrentItem(0);
//                tv_county.setText("县: "+countyAdapter.getItem(0));
//            }
//        });

        /* 市滑轮控件 */
        nameAdapter = new NameAdapter();
        wv_city.setAdapter(nameAdapter);
        wv_city.setOnItemSelectedListener(new WheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelView wv, int index) {
                tv_city.setText("市: "+nameAdapter.getItem(index));
            }
        });


        /* 区滑轮控件 */
        univAdapter = new UnivAdapter();
        wv_county.setAdapter(univAdapter);
        wv_county.setOnItemSelectedListener(new WheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelView wv, int index) {
                tv_county.setText("县: "+univAdapter.getItem(index));
            }
        });

        sidAdapter = new SidAdapter();
        wv_sid.setAdapter(sidAdapter);
        wv_sid.setOnItemSelectedListener(new WheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelView wv, int index) {
                tv_sid.setText("县: "+sidAdapter.getItem(index));
            }
        });

//        countyAdapter = new CountyAdapter();
//        wv_county.setAdapter(countyAdapter);


//        /*  名字适配  */
//        wv_name.setAdapter(new WheelView.WheelAdapter() {
//            @Override
//            protected int getItemCount() {
//                return 20;
//            }
//
//            @Override
//            protected String getItem(int index) {
//                return "游小陈";
//            }
//        });

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
        TextView nameSnapView = (TextView) nameHelper.findSnapView(nameRVLayoutManager);
        TextView univSnapView = (TextView) univHelper.findSnapView(univRVLayoutManager);
        TextView sidSnapView = (TextView) sidHelper.findSnapView(sidRVLayoutManager);

        return (nameSnapView.getText().toString().equals(selectedArrayList.get(currentPosition).getName()) &
                univSnapView.getText().toString().equals(selectedArrayList.get(currentPosition).getUniversity()) &
                sidSnapView.getText().toString().equals(selectedArrayList.get(currentPosition).getSt_number()));
    }

    //CARD DECK
    private void setCardStackAdapter(View view) {
        cardItems = new ArrayList<>();

        Resources resources = getResources();

        for (int i = 0; i < selectedArrayList.size(); i++) {
//            ImageView imageView = (ImageView) view.findViewById(R.id.avatar);

            String mDrawableName = "photo" + (i + 1);
            int imageID = resources.getIdentifier(mDrawableName, "drawable", getActivity().getPackageName());
//            Drawable drawable = resources.getDrawable(imageID);

//            imageView.setImageDrawable(drawable);

            cardItems.add(new CardItem(imageID));
        }

        cardsAdapter = new CardsAdapter(getActivity(), cardItems);
        cardStack.setAdapter(cardsAdapter);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == R.id.reset) {
//            cardStack.resetStack();
//        }
//        return super.onOptionsItemSelected(item);
//    }

    //WHEELVIEW
    private class CityAdapter extends WheelView.WheelAdapter {
        @Override
        protected int getItemCount() {
            return TestDatas.NAMES.length;
        }

        @Override
        protected String getItem(int index) {
            return TestDatas.NAMES[index];
        }
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

    private class CountyAdapter extends WheelView.WheelAdapter {
        private List<String> strs;

        CountyAdapter() {
            strs = new ArrayList<>();
        }

        @Override
        protected int getItemCount() {
            return strs.size();
        }

        @Override
        protected String getItem(int index) {
            return strs.get(index);
        }
    }



}