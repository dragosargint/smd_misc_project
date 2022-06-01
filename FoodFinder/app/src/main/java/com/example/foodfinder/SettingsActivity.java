package com.example.foodfinder;

import static com.example.foodfinder.Constants.DARK_MODE_KEY;
import static com.example.foodfinder.Constants.SHARED_PREFERENCES;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import com.example.foodfinder.databinding.ActivityMainBinding;
import com.example.foodfinder.databinding.ActivitySettingsBinding;

public class SettingsActivity extends AppCompatActivity {

    private ActivitySettingsBinding binding;
    private SharedPreferences sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        updateTheme();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        updateSwitch();
        listenerOnHomeButton();

        binding.darkModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    SharedPreferences.Editor editor = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE).edit();
                    editor.putBoolean(DARK_MODE_KEY, true);
                    editor.commit();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    setTheme(R.style.DarkTheme);
                    reset();
                } else {
                    SharedPreferences.Editor editor = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE).edit();
                    editor.putBoolean(DARK_MODE_KEY, false);
                    editor.commit();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    setTheme(R.style.AppTheme);
                    binding.darkModeSwitch.setChecked(false);
                    reset();
                }
            }

            private void reset() {
                Intent intent = new Intent(SettingsActivity.this, SettingsActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }


    private void listenerOnHomeButton() {
        binding.home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void updateSwitch() {
        if (sharedPref.getBoolean(DARK_MODE_KEY, false)) {
            binding.darkModeSwitch.setChecked(true);
        } else {
            binding.darkModeSwitch.setChecked(false);
        }
    }

    private void updateTheme() {
        if (sharedPref.getBoolean(DARK_MODE_KEY, false)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            setTheme(R.style.DarkTheme);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            setTheme(R.style.AppTheme);
        }
    }
}