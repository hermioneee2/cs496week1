package com.example.cs496week1.ui.main;

import android.content.Context;
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

    // Keep all Images in array
    public Integer[] mThumbIds = {
            R.drawable.photo1, R.drawable.photo2,
            R.drawable.photo3, R.drawable.photo4,
            R.drawable.photo5, R.drawable.photo6,
            R.drawable.photo7, R.drawable.photo8,
            R.drawable.photo9, R.drawable.photo10,
            R.drawable.photo11, R.drawable.photo12,
            R.drawable.photo13, R.drawable.photo14,
            R.drawable.photo15, R.drawable.photo16,
            R.drawable.photo17, R.drawable.photo18,
            R.drawable.photo19, R.drawable.photo20
    };

    // Constructor
    public ImageAdapter(Context context){
        mContext = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ImageView imageView = new ImageView(mContext);
//        imageView.setImageResource(mThumbIds[position]);
//        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        // Todo: update this to take into account getResources().getDisplayMetrics()
//        int widthPixels = mContext.getResources().getDisplayMetrics().widthPixels;
//        imageView.setLayoutParams(new GridView.LayoutParams(widthPixels / 3, widthPixels / 3));
//        return imageView;
//    }

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

        imageView.setImageResource(mThumbIds[position]);
        textNameView.setText(Commons.peopleArrayList.get(position).getName());
        textUnivSidView.setText(Commons.peopleArrayList.get(position).getUniversity() + " " +
                Commons.peopleArrayList.get(position).getSt_number());

        int widthPixels = mContext.getResources().getDisplayMetrics().widthPixels;
        itemView.setLayoutParams(new GridView.LayoutParams(widthPixels / 3, widthPixels / 3));

        return itemView;
    }

}
