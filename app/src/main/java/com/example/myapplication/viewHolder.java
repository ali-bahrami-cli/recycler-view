package com.example.myapplication;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class viewHolder extends RecyclerView.ViewHolder {
    private ImageView imageview_item;
    private TextView textview_item;
    private TextView about;
    private TextView btnDatePicker;
    private TextView btnTimePicker;
    private TextView btnProgressDialog;

    public viewHolder(@NonNull View itemView) {
        super(itemView);
        imageview_item = itemView.findViewById(R.id.imageview_item);
        textview_item = itemView.findViewById(R.id.textview_item);
        about = itemView.findViewById(R.id.about);
        btnDatePicker = itemView.findViewById(R.id.btn_date_picker);
        btnTimePicker = itemView.findViewById(R.id.btn_time_picker);
        btnProgressDialog = itemView.findViewById(R.id.btn_progress_dialog);
    }

    public void setItems(String name, int imageId, String aboutText, int position,
                         DialogInterfaces.OnAboutClickListener aboutListener,
                         DialogInterfaces.OnDateSelectedListener dateListener,
                         DialogInterfaces.OnTimeSelectedListener timeListener,
                         DialogInterfaces.OnProgressDialogListener progressListener,
                         DialogInterfaces.OnAvatarClickListener avatarListener) {
        textview_item.setText(name);
        imageview_item.setImageResource(imageId);
        about.setText("درباره");
        btnDatePicker.setText("تاریخ");
        btnTimePicker.setText("زمان");
        btnProgressDialog.setText("لودینگ");

        about.setOnClickListener(v -> {
            if (aboutListener != null) {
                aboutListener.onAboutClick(name, aboutText);
            }
        });

        btnDatePicker.setOnClickListener(v -> {
            if (dateListener != null) {
                dateListener.onDateSelected(0, 0, 0, position);
            }
        });

        btnTimePicker.setOnClickListener(v -> {
            if (timeListener != null) {
                timeListener.onTimeSelected(0, 0, position);
            }
        });

        btnProgressDialog.setOnClickListener(v -> {
            if (progressListener != null) {
                progressListener.onProgressDialogShow(position);
            }
        });

        imageview_item.setOnClickListener(v -> {
            if (avatarListener != null) {
                avatarListener.onAvatarClick(name, position);
            }
        });
    }
}