package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class secondActivity extends AppCompatActivity {

    Button back, del;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        sharedPref = getApplicationContext().getSharedPreferences("send", Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        back = findViewById(R.id.back);
        del = findViewById(R.id.del);
        LinearLayout main = findViewById(R.id.main);

        String prefText = sharedPref.getString("contacts", "");

        if (!prefText.isEmpty()) {
            String[] prefArr = prefText.split(",");
            boolean flag = true;

            for (String contact : prefArr) {
                TextView textView = new TextView(this);
                textView.setText(contact);
                textView.setTextSize(18);
                textView.setHeight(100);
                textView.setGravity(Gravity.CENTER);

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(10, 10, 10, 10);
                textView.setLayoutParams(params);

                if (flag) {
                    textView.setBackgroundColor(Color.parseColor("#16404D"));
                    textView.setTextColor(Color.parseColor("#EFEEEA"));
                    flag = false;
                } else {
                    textView.setBackgroundColor(Color.parseColor("#DDA853"));
                    textView.setTextColor(Color.parseColor("#000000"));
                    flag = true;
                }

                main.addView(textView);
            }
        }

        back.setOnClickListener(v -> finish());

        del.setOnClickListener(v -> {
            editor.clear();
            editor.apply();
            main.removeAllViews();
        });
    }
}
