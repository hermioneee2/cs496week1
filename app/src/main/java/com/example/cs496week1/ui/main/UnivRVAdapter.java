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
    private ArrayList<Fragment_Third.People> peopleArrayList;

    UnivRVAdapter(Context context, ArrayList<Fragment_Third.People> peopleArrayList) {
        this.mInflater = LayoutInflater.from(context);
        this.peopleArrayList = peopleArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UnivRVAdapter.ViewHolder(mInflater.inflate(R.layout.univs_rv_item, parent, false));
    };

    @Override
    public void onBindViewHolder(@NonNull UnivRVAdapter.ViewHolder holder, int position) {
        Fragment_Third.People people = peopleArrayList.get(position % peopleArrayList.size());
        holder.univTV.setText(people.getUniversity());
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