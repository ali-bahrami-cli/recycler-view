package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements
        DialogInterfaces.OnAboutClickListener,
        DialogInterfaces.OnDateSelectedListener,
        DialogInterfaces.OnTimeSelectedListener,
        DialogInterfaces.OnProgressDialogListener,
        DialogInterfaces.OnAvatarClickListener {

    private ProgressDialog progressDialog;
    private Adapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        RecyclerView recyclerview_items = findViewById(R.id.recyclerview_items);
        adapter = new Adapter();
        adapter.setOnAboutClickListener(this);
        adapter.setOnDateSelectedListener(this);
        adapter.setOnTimeSelectedListener(this);
        adapter.setOnProgressDialogListener(this);
        adapter.setOnAvatarClickListener(this);

        recyclerview_items.setLayoutManager(new LinearLayoutManager(this));
        recyclerview_items.setAdapter(adapter);
    }

    @Override
    public void onAboutClick(String name, String caption) {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);

        View dialogView = getLayoutInflater().inflate(R.layout.dialog_about, null);

        TextView title = dialogView.findViewById(R.id.title);
        TextView message = dialogView.findViewById(R.id.message);
        Button btnClose = dialogView.findViewById(R.id.btn_close);

        title.setText(name);
        message.setText(caption);

        builder.setView(dialogView);

        androidx.appcompat.app.AlertDialog dialog = builder.create();

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }

        btnClose.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    @Override
    public void onDateSelected(int year, int month, int day, int position) {
        showDatePickerDialog(position);
    }

    @Override
    public void onTimeSelected(int hour, int minute, int position) {
        showTimePickerDialog(position);
    }

    @Override
    public void onProgressDialogShow(int position) {
        showProgressDialog(position);
    }

    @Override
    public void onAvatarClick(String name, int position) {
        Toast.makeText(this, "کاربر: " + name, Toast.LENGTH_SHORT).show();
    }

    private void showDatePickerDialog(int position) {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePicker = new DatePickerDialog(
                this,
                (view, year, month, day) -> handleDateSelection(year, month, day, position),
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePicker.setTitle("تاریخ تولد کاربر را انتخاب کنید");
        datePicker.show();
    }

    private void showTimePickerDialog(int position) {
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog timePicker = new TimePickerDialog(
                this,
                (view, hour, minute) -> handleTimeSelection(hour, minute, position),
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
        );
        timePicker.setTitle("زمان قرار ملاقات را انتخاب کنید");
        timePicker.show();
    }

    private void showProgressDialog(int position) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("در حال ذخیره اطلاعات کاربر...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        new Handler().postDelayed(() -> {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
                Toast.makeText(this, "اطلاعات کاربر ذخیره شد", Toast.LENGTH_SHORT).show();
            }
        }, 2000);
    }

    private void handleDateSelection(int year, int month, int day, int position) {
        String[] months = {"فروردین", "اردیبهشت", "خرداد", "تیر", "مرداد", "شهریور",
                "مهر", "آبان", "آذر", "دی", "بهمن", "اسفند"};
        String selectedDate = year + "/" + (month + 1) + "/" + day;
        String monthName = months[month];

        Toast.makeText(this, "تاریخ تولد کاربر انتخاب شد: " + selectedDate + " (" + monthName + ")", Toast.LENGTH_LONG).show();
    }

    private void handleTimeSelection(int hour, int minute, int position) {
        String selectedTime = String.format("%02d:%02d", hour, minute);
        Toast.makeText(this, "زمان قرار ملاقات تنظیم شد: " + selectedTime, Toast.LENGTH_LONG).show();
    }
}