package com.example.foodfinder.interfaces;

import com.example.foodfinder.spoonacularAPI.responseformat.RandomRecipes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RandomRecipesCallInterface {
    @GET("recipes/random")
    Call<RandomRecipes> getRandomRecipes(
            @Query("apiKey") String apiKey,
            @Query("number") String number
    );
}
