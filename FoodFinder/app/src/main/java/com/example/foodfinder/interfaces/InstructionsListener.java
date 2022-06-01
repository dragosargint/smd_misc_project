package com.example.foodfinder.interfaces;

import com.example.foodfinder.spoonacularAPI.responseformat.Instructions;
import com.example.foodfinder.spoonacularAPI.responseformat.RandomRecipes;

public interface InstructionsListener {
    void onResponse(Instructions response, String message);
    void onError(String message);
}
