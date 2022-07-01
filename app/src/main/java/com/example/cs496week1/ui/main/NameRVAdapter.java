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
    private ArrayList<Fragment_Third.People> peopleArrayList;

    NameRVAdapter(Context context, ArrayList<Fragment_Third.People> peopleArrayList) {
        this.mInflater = LayoutInflater.from(context);
        this.peopleArrayList = peopleArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NameRVAdapter.ViewHolder(mInflater.inflate(R.layout.names_rv_item, parent, false));
    };

    @Override
    public void onBindViewHolder(@NonNull NameRVAdapter.ViewHolder holder, int position) {
        Fragment_Third.People people = peopleArrayList.get(position);
        holder.nameTV.setText(people.getName());
    }

    @Override
    public int getItemCount() {
        return peopleArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.idTVName);
        }
    }
}