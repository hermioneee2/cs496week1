package com.example.cs496week1.ui.main.widget;

import android.content.Context;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by 77991 on 2020/6/4.
 */

public abstract class WheelItemClickListener extends RecyclerView.SimpleOnItemTouchListener {

    private GestureDetectorCompat gestureDetector;

    public WheelItemClickListener(Context context) {
        GestureDetector.SimpleOnGestureListener listener = new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        };
        gestureDetector = new GestureDetectorCompat(context, listener);
        gestureDetector.setIsLongpressEnabled(false);
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View childView = rv.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && gestureDetector.onTouchEvent(e)) {
            onItemClick(rv.getChildAdapterPosition(childView));
            return true;
        }
        return false;
    }

    abstract void onItemClick(int position);
}