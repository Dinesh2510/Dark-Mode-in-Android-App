package com.demo.darkmode;

import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.widget.CompoundButton;
import android.widget.Switch;


public class MainActivity extends AppCompatActivity {

    private static final String NIGHT_MODE = "night_mode";
    private SharedPreferences mSharedPref;
    Switch switch1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // init shared preferences
        mSharedPref = getPreferences(Context.MODE_PRIVATE);

        if (isNightModeEnabled()) {
            setAppTheme(R.style.DarkTheme);
        } else {
            setAppTheme(R.style.LightTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switch1 = findViewById(R.id.switch1);
        // Get state from preferences
        if (isNightModeEnabled()) {
            switch1.setChecked(true);
        } else {
            switch1.setChecked(false);
        }
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (isNightModeEnabled()) {
                    setIsNightModeEnabled(false);
                    setAppTheme(R.style.LightTheme);
                } else {
                    setIsNightModeEnabled(true);
                    setAppTheme(R.style.DarkTheme);
                }

                // Recreate activity
                recreate();
            }

        });

    }


    private void setAppTheme(@StyleRes int style) {
        setTheme(style);
    }

    private boolean isNightModeEnabled() {
        return mSharedPref.getBoolean(NIGHT_MODE, false);
    }

    private void setIsNightModeEnabled(boolean state) {
        SharedPreferences.Editor mEditor = mSharedPref.edit();
        mEditor.putBoolean(NIGHT_MODE, state);
        mEditor.apply();
    }

}

