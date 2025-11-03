package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NavigationRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<viewHolder> {

    String[] names = {};
    int[] ids = {};

    public Adapter() {
        listItem listItem = new listItem();
        names = listItem.getName();
        ids = listItem.getAvatar();
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.r_view_layout, parent, false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.setItems(names[position], ids[position]);
    }

    @Override
    public int getItemCount() {
        return names.length;
    }


}