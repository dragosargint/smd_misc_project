package com.example.foodfinder;

import static com.example.foodfinder.Constants.DARK_MODE_KEY;
import static com.example.foodfinder.Constants.IMAGE_KEY;
import static com.example.foodfinder.Constants.RECIPE_ID_KEY;
import static com.example.foodfinder.Constants.SHARED_PREFERENCES;
import static com.example.foodfinder.Constants.TITLE_KEY;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.foodfinder.adapters.InstructionsAdapter;
import com.example.foodfinder.adapters.RandomRecipeAdapter;
import com.example.foodfinder.databinding.ActivityMainBinding;
import com.example.foodfinder.databinding.ActivityRecipeBinding;
import com.example.foodfinder.spoonacularAPI.responseformat.Instructions;
import com.example.foodfinder.spoonacularAPI.responseformat.Recipe;
import com.example.foodfinder.viewmodels.HomeActivityViewModel;
import com.example.foodfinder.viewmodels.RecipeActivityViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecipeActivity extends AppCompatActivity {

    private ActivityRecipeBinding binding;
    private InstructionsAdapter instructionsAdapter;
    private RecipeActivityViewModel recipeActivityViewModel;
    private int id;
    private String image;
    private String title;
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);

        updateTheme();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        id = getIntent().getIntExtra(RECIPE_ID_KEY, 0);
        image = getIntent().getStringExtra(IMAGE_KEY);
        title = getIntent().getStringExtra(TITLE_KEY);

        binding = ActivityRecipeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        instructionsAdapter = new InstructionsAdapter(this, new Instructions());
        binding.instructionsRecycler.setHasFixedSize(true);
        binding.instructionsRecycler.setLayoutManager(new GridLayoutManager(RecipeActivity.this, 1));
        binding.instructionsRecycler.setAdapter(instructionsAdapter);

        Picasso.get().load(image).into(binding.image);
        binding.title.setText(title);

        initViewModel();
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

    private void initViewModel() {
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        recipeActivityViewModel = viewModelProvider.get(RecipeActivityViewModel.class);
        recipeActivityViewModel.setID(id);
        recipeActivityViewModel.getInstructionsLiveData().observe(this, instructions -> {
            if(instructions != null) {
                instructionsAdapter.replaceAll(instructions);
            }
        });
        recipeActivityViewModel.getInstructions();
    }
}