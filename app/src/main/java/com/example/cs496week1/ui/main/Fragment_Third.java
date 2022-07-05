package com.example.cs496week1.ui.main;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.cs496week1.Commons;
import com.example.cs496week1.R;

import java.util.ArrayList;
import java.util.TreeSet;

import link.fls.swipestack.SwipeStack;
import com.example.cs496week1.ui.main.widget.WheelView;
import com.yashovardhan99.timeit.Stopwatch;

public class Fragment_Third extends Fragment {
//    ArrayList<People> peopleArrayList;
//    ArrayList<People> selectedArrayList;
    // position of currently displayed item
    private int currentPosition;

    //Slider
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

    //CARD DECK
    private SwipeStack cardStack;
    private CardsAdapter cardsAdapter;
    private ArrayList<CardItem> cardItems;
    private View btnCancel;
    private View btnLove;

    //WHEELVIEW
//    private WheelView wv_name, wv_univ, wv_name;
    private WheelView wv_name, wv_univ, wv_sid;
//    private CityAdapter cityAdapter;
//    private CountyAdapter countyAdapter;
    private NameAdapter nameAdapter;
    private UnivAdapter univAdapter;
    private SidAdapter sidAdapter;
//    private TextView tv_name, tv_univ, tv_number;
//    private TextView tv_name, tv_univ, tv_sid;
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
                cardStack.resetStack();
                currentPosition = 0;
            }
        });

        TreeSet<String> selectedNameSet = new TreeSet<>();
        TreeSet<String> selectedUnivSet = new TreeSet<>();
        TreeSet<String> selectedSidSet = new TreeSet<>();

        for (int i = 0; i < Commons.selectedArrayList.size(); i++) {
            selectedNameSet.add(Commons.selectedArrayList.get(i).getName());
            selectedUnivSet.add(Commons.selectedArrayList.get(i).getUniv());
            selectedSidSet.add(Commons.selectedArrayList.get(i).getSid());
        }

        selectedUniqueName = new ArrayList<>(selectedNameSet);
        selectedUniqueUniv = new ArrayList<>(selectedUnivSet);
        selectedUniqueSid = new ArrayList<>(selectedSidSet);

        //WheelView
        wv_name = (WheelView) view.findViewById(R.id.wv_name);
        wv_univ = (WheelView) view.findViewById(R.id.wv_univ);
        wv_sid = (WheelView) view.findViewById(R.id.wv_sid);
//        tv_name = (TextView) view.findViewById(R.id.tv_name);
//        tv_univ = (TextView) view.findViewById(R.id.tv_univ);
//        tv_sid = (TextView) view.findViewById(R.id.tv_sid);

        nameAdapter = new NameAdapter();
        wv_name.setAdapter(nameAdapter);
        wv_name.setOnItemSelectedListener(new WheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelView wv, int index) {
                curName = nameAdapter.getItem(index);
                if (checkCorrect()) {
                    // This part handles the case of full match
//                    currentPosition++;
                    if (currentPosition % 2 == 0) {
                        cardStack.swipeTopViewToRight();
                    } else {
                        cardStack.swipeTopViewToLeft();
                    }
                }
            }
        });

        univAdapter = new UnivAdapter();
        wv_univ.setAdapter(univAdapter);
        wv_univ.setOnItemSelectedListener(new WheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelView wv, int index) {
                curUniv = univAdapter.getItem(index);
//                tv_univ.setText("县: "+univAdapter.getItem(index));
                if (checkCorrect()) {
                    // This part handles the case of full match
//                    currentPosition++;
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
                if (checkCorrect()) {
                    // This part handles the case of full match
//                    currentPosition++;
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

        //TIMER
        Stopwatch stopwatch = new Stopwatch();
        stopwatch.setTextView(view.findViewById(R.id.timeNow));
        stopwatch.start();

        Button stopButton = (Button) view.findViewById(R.id.stopButton) ;

        stopButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopwatch.stop();
                stopwatch.start();
            }
        });

        return view;
    }

    public boolean checkCorrect() {
        return (curName.equals(Commons.selectedArrayList.get(currentPosition).getName()) &
                curUniv.equals(Commons.selectedArrayList.get(currentPosition).getUniv()) &
                curSid.equals(Commons.selectedArrayList.get(currentPosition).getSid()));
    }

    //CARD DECK
    private void setCardStackAdapter(View view) {
        cardItems = new ArrayList<>();

        Resources resources = getResources();

        for (int i = 0; i < Commons.selectedArrayList.size(); i++) {
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