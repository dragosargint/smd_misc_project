package com.example.foodfinder;

import static com.example.foodfinder.Constants.DARK_MODE_KEY;
import static com.example.foodfinder.Constants.IMAGE_KEY;
import static com.example.foodfinder.Constants.RECIPE_ID_KEY;
import static com.example.foodfinder.Constants.SHARED_PREFERENCES;
import static com.example.foodfinder.Constants.TITLE_KEY;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.foodfinder.adapters.RandomRecipeAdapter;
import com.example.foodfinder.broadcastreciever.NetworkStateReceiver;
import com.example.foodfinder.databinding.ActivityMainBinding;
import com.example.foodfinder.interfaces.ImageClickListener;
import com.example.foodfinder.interfaces.NetworkAccessListener;
import com.example.foodfinder.interfaces.TimerListener;
import com.example.foodfinder.spoonacularAPI.responseformat.Recipe;
import com.example.foodfinder.threads.TimerThread;
import com.example.foodfinder.viewmodels.HomeActivityViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private RandomRecipeAdapter randomRecipeAdapter;
    private HomeActivityViewModel homeActivityViewModel;
    private ImageClickListener imageClickListener;
    private BroadcastReceiver broadcastReceiver = null;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);

        updateTheme();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initImageClickListener();
        randomRecipeAdapter = new RandomRecipeAdapter(this, new ArrayList<Recipe>(), imageClickListener);
        binding.reciclerView.setHasFixedSize(true);
        binding.reciclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 1));
        binding.reciclerView.setAdapter(randomRecipeAdapter);

        initViewModel();
        refreshListenerOnHomeButton();
        setListenerOnSettingsButton();
        setListenerOnSpinner();

        setNotificationTimer();

    }

    private void setListenerOnSpinner() {
        binding.sortTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                homeActivityViewModel.sort(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setNotificationTimer() {
        TimerThread timerRunnable = new TimerThread(30000, 10, new TimerListener() {
            @Override
            public void onFinish() {
                Recipe recipe = homeActivityViewModel.getRandomRecipe();
                if (recipe != null) {
                    Toast.makeText(MainActivity.this, "You should check out this recipe: " + recipe.title, Toast.LENGTH_LONG).show();
                }
            }
        });
        Thread t = new Thread(timerRunnable);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
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

    @Override
    protected void onResume() {
        super.onResume();
        register_reciever();

    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }

    private void initImageClickListener() {
        imageClickListener = new ImageClickListener() {
            @Override
            public void onClick(int id, String image, String title) {
                Intent intent = new Intent(MainActivity.this, RecipeActivity.class);
                intent.putExtra(RECIPE_ID_KEY, id);
                intent.putExtra(IMAGE_KEY, image);
                intent.putExtra(TITLE_KEY, title);
                startActivity(intent);
            }
        };
    }

    private void initViewModel() {
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        homeActivityViewModel = viewModelProvider.get(HomeActivityViewModel.class);
        homeActivityViewModel.getRecipesLiveData().observe(this, recipeList -> {
            if(recipeList != null) {
                randomRecipeAdapter.replaceAll(recipeList);
            }
        });
        homeActivityViewModel.getRandomRecipes();
    }

    private void register_reciever() {
        broadcastReceiver = new NetworkStateReceiver(new NetworkAccessListener() {
            @Override
            public void onConnected() {
                binding.noInternet.setVisibility(View.INVISIBLE);
                binding.reciclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onDisconnected() {
                binding.noInternet.setVisibility(View.VISIBLE);
                binding.reciclerView.setVisibility(View.INVISIBLE);
            }
        });
        registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    private void refreshListenerOnHomeButton() {
        binding.home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeActivityViewModel.getRandomRecipes();
            }
        });
    }


    private void setListenerOnSettingsButton() {
        binding.settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

}