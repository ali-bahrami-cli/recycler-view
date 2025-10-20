package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText input1, input2, input3;
    Button send, del;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input1 = findViewById(R.id.input1);
        input2 = findViewById(R.id.input2);
        input3 = findViewById(R.id.input3);
        send = findViewById(R.id.send);
        del = findViewById(R.id.del);

        sharedPref = getApplicationContext().getSharedPreferences("send", Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        del.setOnClickListener(v -> {
            input1.setText("");
            input2.setText("");
            input3.setText("");
        });

        send.setOnClickListener(v -> {
            String name = input1.getText().toString().trim();
            String family = input2.getText().toString().trim();
            String study = input3.getText().toString().trim();

            if (name.isEmpty() && family.isEmpty() && study.isEmpty()) return;

            String oldData = sharedPref.getString("contacts", "");

            String newData = name + " " + family + " - " + study;

            if (!oldData.isEmpty()) {
                oldData += ",";
            }
            oldData += newData;

            editor.putString("contacts", oldData);
            editor.apply();

            startActivity(new Intent(MainActivity.this, secondActivity.class));
        });
    }
}
