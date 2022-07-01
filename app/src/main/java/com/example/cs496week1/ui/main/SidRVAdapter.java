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

public class SidRVAdapter extends RecyclerView.Adapter<SidRVAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private ArrayList<Fragment_Third.People> peopleArrayList;

    SidRVAdapter(Context context, ArrayList<Fragment_Third.People> peopleArrayList) {
        this.mInflater = LayoutInflater.from(context);
        this.peopleArrayList = peopleArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SidRVAdapter.ViewHolder(mInflater.inflate(R.layout.sids_rv_item, parent, false));
    };

    @Override
    public void onBindViewHolder(@NonNull SidRVAdapter.ViewHolder holder, int position) {
        Fragment_Third.People people = peopleArrayList.get(position);
        holder.sidTV.setText(people.getSt_number());
    }

    @Override
    public int getItemCount() {
        return peopleArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView sidTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sidTV = itemView.findViewById(R.id.idTVSid);
        }
    }
}