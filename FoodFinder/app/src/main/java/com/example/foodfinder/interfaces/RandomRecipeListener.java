package com.example.foodfinder.interfaces;

import com.example.foodfinder.spoonacularAPI.responseformat.RandomRecipes;

public interface RandomRecipeListener {
    void onResponse(RandomRecipes response, String message);
    void onError(String message);
}
