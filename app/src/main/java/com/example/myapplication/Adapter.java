package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<viewHolder> {
    private String[] names = {};
    private int[] ids = {};
    private String[] captions = {};
    private DialogInterfaces.OnAboutClickListener onAboutClickListener;
    private DialogInterfaces.OnDateSelectedListener onDateSelectedListener;
    private DialogInterfaces.OnTimeSelectedListener onTimeSelectedListener;
    private DialogInterfaces.OnProgressDialogListener onProgressDialogListener;
    private DialogInterfaces.OnAvatarClickListener onAvatarClickListener;

    public Adapter() {
        listItem listItem = new listItem();
        names = listItem.getName();
        ids = listItem.getAvatar();
        captions = listItem.getCaption();
    }

    public void setOnAboutClickListener(DialogInterfaces.OnAboutClickListener listener) {
        this.onAboutClickListener = listener;
    }

    public void setOnDateSelectedListener(DialogInterfaces.OnDateSelectedListener listener) {
        this.onDateSelectedListener = listener;
    }

    public void setOnTimeSelectedListener(DialogInterfaces.OnTimeSelectedListener listener) {
        this.onTimeSelectedListener = listener;
    }

    public void setOnProgressDialogListener(DialogInterfaces.OnProgressDialogListener listener) {
        this.onProgressDialogListener = listener;
    }

    public void setOnAvatarClickListener(DialogInterfaces.OnAvatarClickListener listener) {
        this.onAvatarClickListener = listener;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.r_view_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.setItems(names[position], ids[position], captions[position], position,
                onAboutClickListener, onDateSelectedListener, onTimeSelectedListener,
                onProgressDialogListener, onAvatarClickListener);
    }

    @Override
    public int getItemCount() {
        return names.length;
    }
}