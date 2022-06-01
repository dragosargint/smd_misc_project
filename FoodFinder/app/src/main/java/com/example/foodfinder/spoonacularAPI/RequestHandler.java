package com.example.foodfinder.spoonacularAPI;

import static com.example.foodfinder.Constants.API_KEY;
import static com.example.foodfinder.Constants.BASE_URL;

import android.util.Log;

import com.example.foodfinder.interfaces.InstructionsListener;
import com.example.foodfinder.interfaces.RandomRecipeListener;
import com.example.foodfinder.interfaces.RandomRecipesCallInterface;
import com.example.foodfinder.interfaces.RecipeInstructionsCallInterface;
import com.example.foodfinder.spoonacularAPI.responseformat.Instructions;
import com.example.foodfinder.spoonacularAPI.responseformat.RandomRecipes;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestHandler {

    private static RequestHandler instance;
    private static Retrofit retrofit;

    public RequestHandler() {}

    public static RequestHandler getInstance() {
        if (instance == null && retrofit == null) {
            instance = new RequestHandler();
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return instance;
    }

    public void getRandomRecipes(RandomRecipeListener listener, int number_of_recipes) {
        RandomRecipesCallInterface randomRecipesCallClass = retrofit.create(RandomRecipesCallInterface.class);
        Call<RandomRecipes> randomRecipesCall = randomRecipesCallClass.getRandomRecipes(API_KEY, String.valueOf(number_of_recipes));
        randomRecipesCall.enqueue(new Callback<RandomRecipes>() {
            @Override
            public void onResponse(Call<RandomRecipes> call, Response<RandomRecipes> response) {
                if (response != null && response.body() != null) {
                    listener.onResponse(response.body(), response.message());
                } else {
                    listener.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<RandomRecipes> call, Throwable t) {
                listener.onError(t.getMessage());
            }
        });
    }

    public void getInstructions(InstructionsListener listener, int id){
        RecipeInstructionsCallInterface recipeInstructionsCallClass = retrofit.create(RecipeInstructionsCallInterface.class);
        Call<List<Instructions>> instructionsCall = recipeInstructionsCallClass.getInstructions(id, API_KEY);
        instructionsCall.enqueue(new Callback<List<Instructions>>() {
            @Override
            public void onResponse(Call<List<Instructions>> call, Response<List<Instructions>> response) {
                if (response != null && response.body() != null && response.body().size() > 0)
                {
                    listener.onResponse(response.body().get(0), response.message());
                } else {
                    listener.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Instructions>> call, Throwable t) {
                listener.onError(t.getMessage());
            }
        });
    }

}
