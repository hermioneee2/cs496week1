package com.example.cs496week1.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs496week1.R;

import java.util.ArrayList;

public class NameRVAdapter extends RecyclerView.Adapter<NameRVAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private ArrayList<String> nameList;

    NameRVAdapter(Context context, ArrayList<String> nameList) {
        this.mInflater = LayoutInflater.from(context);
        this.nameList = nameList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NameRVAdapter.ViewHolder(mInflater.inflate(R.layout.names_rv_item, parent, false));
    };

    @Override
    public void onBindViewHolder(@NonNull NameRVAdapter.ViewHolder holder, int position) {
        String name = nameList.get(position % nameList.size());
        holder.nameTV.setText(name);
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.idTVName);
        }
    }
}