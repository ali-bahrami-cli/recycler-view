package com.example.myapplication;

public interface DialogInterfaces {
    interface OnAboutClickListener {
        void onAboutClick(String name, String caption);
    }

    interface OnDateSelectedListener {
        void onDateSelected(int year, int month, int day, int position);
    }

    interface OnTimeSelectedListener {
        void onTimeSelected(int hour, int minute, int position);
    }

    interface OnProgressDialogListener {
        void onProgressDialogShow(int position);
    }

    interface OnAvatarClickListener {
        void onAvatarClick(String name, int position);
    }
}