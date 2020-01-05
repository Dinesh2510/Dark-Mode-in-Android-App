package com.demo.darkmode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;


import android.content.Intent;

import android.view.View;
import android.widget.Button;

import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.LightTheme);
        }

        setContentView(R.layout.activity_main);

        Button btnChangeTheme = findViewById(R.id.ChangeTheme);
        btnChangeTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    FancyToast.makeText(getApplicationContext(), "Light Mode !", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    FancyToast.makeText(getApplicationContext(), "Dark Mode !", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                }

                finish();
                startActivity(new Intent(MainActivity.this, MainActivity.this.getClass()));
            }
        });


    }
}

