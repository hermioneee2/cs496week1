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

public class UnivRVAdapter extends RecyclerView.Adapter<UnivRVAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private ArrayList<String> univList;

    UnivRVAdapter(Context context, ArrayList<String> univList) {
        this.mInflater = LayoutInflater.from(context);
        this.univList = univList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UnivRVAdapter.ViewHolder(mInflater.inflate(R.layout.univs_rv_item, parent, false));
    };

    @Override
    public void onBindViewHolder(@NonNull UnivRVAdapter.ViewHolder holder, int position) {
        String univ = univList.get(position % univList.size());
        holder.univTV.setText(univ);
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView univTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            univTV = itemView.findViewById(R.id.idTVUniv);
        }
    }
}