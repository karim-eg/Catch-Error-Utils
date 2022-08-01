package com.EnceptCode.CatchErrorUtils;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_crash = findViewById(R.id.Button_crash);

        button_crash.setOnClickListener(view -> {

        Toast.makeText(MainActivity.this, "You Crashed The App, wait for error details...", Toast.LENGTH_LONG).show();
        throw new RuntimeException("You Crashed The App.."); // crash App

        });

    }
}