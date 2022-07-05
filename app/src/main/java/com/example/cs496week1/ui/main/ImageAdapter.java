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
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.cs496week1.Commons;
import com.example.cs496week1.R;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater layoutInflater;

    // Constructor
    public ImageAdapter(Context context){
        mContext = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

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
//        RelativeLayout relativeLayout;

        imageView = itemView.findViewById(R.id.imageFront);
        textNameView = itemView.findViewById(R.id.imageName);
        textUnivSidView = itemView.findViewById(R.id.imageUnivSid);
//        relativeLayout = itemView.findViewById(R.id.imageRV);

        Resources resources = mContext.getResources();
        String mDrawableName = "photo" + (position + 1);
        int imageID = resources.getIdentifier(mDrawableName , "drawable", mContext.getPackageName());
        Drawable drawable = ResourcesCompat.getDrawable(resources, imageID, resources.newTheme());

        imageView.setImageDrawable(drawable);
        textNameView.setText(Commons.peopleArrayList.get(position).getName());
        textUnivSidView.setText(Commons.peopleArrayList.get(position).getUniv() + " " +
                Commons.peopleArrayList.get(position).getSid());

//        ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
//        // generate random color
//        int color = generator.getRandomColor();
//
//        // below text drawable is a circular.
//        TextDrawable drawable2 = new TextDrawable.Builder()
////                .width(100) // width in px
////                .height(100) // height in px
//                .setColor(color)
//                // as we are building a circular drawable
//                // we are calling a build round method.
//                // in that method we are passing our text and color.
//                .build();
//
//        relativeLayout.setBackground(drawable2);

        int widthPixels = mContext.getResources().getDisplayMetrics().widthPixels;
        itemView.setLayoutParams(new GridView.LayoutParams(widthPixels / 3, widthPixels / 3));

        return itemView;
    }

}
