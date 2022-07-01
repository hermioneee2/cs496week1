package com.example.cs496week1.ui.main;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NameAdapter extends RecyclerView.Adapter<NameAdapter.ViewHolder> {
    private ArrayList<String> mNames;
    private LayoutInflater mInflater;

    NameAdapter(Context context, ArrayList<String> names) {
        this.mInflater = LayoutInflater.from(context);
        this.mNames = names;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder();

    public class ViewHolder extends RecyclerView.ViewHolder implements View.
}