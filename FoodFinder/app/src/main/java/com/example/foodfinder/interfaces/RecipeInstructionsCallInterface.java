package com.example.foodfinder.interfaces;

import com.example.foodfinder.spoonacularAPI.responseformat.Instructions;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RecipeInstructionsCallInterface {
    @GET("recipes/{id}/analyzedInstructions")
    Call<List<Instructions>> getInstructions(
            @Path("id") int id,
            @Query("apiKey") String apiKey
    );
}

