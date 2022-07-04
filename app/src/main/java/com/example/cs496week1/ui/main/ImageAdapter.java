package com.example.cs496week1.ui.main;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs496week1.Commons;
import com.example.cs496week1.R;
import com.wajahatkarim3.easyflipview.EasyFlipView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater layoutInflater;

    // Constructor
    public ImageAdapter(Context context){
        mContext = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // Todo: are the following three properly defined?
    @Override
    public int getCount() {
        return Commons.peopleArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return Commons.peopleArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        if (itemView == null){
            itemView = layoutInflater.inflate(R.layout.image_item, parent, false);
        }

        ImageView imageView;
        TextView textNameView, textUnivSidView;

        imageView = itemView.findViewById(R.id.imageFront);
        textNameView = itemView.findViewById(R.id.imageName);
        textUnivSidView = itemView.findViewById(R.id.imageUnivSid);

        Resources resources = mContext.getResources();
        String mDrawableName = "photo" + (position + 1);
        int imageID = resources.getIdentifier(mDrawableName , "drawable", mContext.getPackageName());
        Drawable drawable = resources.getDrawable(imageID);

        imageView.setImageDrawable(drawable);
        textNameView.setText(Commons.peopleArrayList.get(position).getName());
        textUnivSidView.setText(Commons.peopleArrayList.get(position).getUniversity() + " " +
                Commons.peopleArrayList.get(position).getSt_number());

        int widthPixels = mContext.getResources().getDisplayMetrics().widthPixels;
        itemView.setLayoutParams(new GridView.LayoutParams(widthPixels / 3, widthPixels / 3));

        return itemView;
    }

}
