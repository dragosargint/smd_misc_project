package com.example.foodfinder;

import static org.junit.Assert.assertEquals;

import com.example.foodfinder.interfaces.RandomRecipeListener;
import com.example.foodfinder.spoonacularAPI.RequestHandler;
import com.example.foodfinder.spoonacularAPI.responseformat.RandomRecipes;
import com.example.foodfinder.spoonacularAPI.responseformat.Recipe;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RequestHandlerTestPerformance {

    List<Recipe> recipeListFromSync;
    List<Recipe> recipeListFromAsync;
    RequestHandler requestHandler;

    @Before
    public void SetUp(){
        requestHandler = RequestHandler.getInstance();
    }

    @Test(timeout = 2500)
    public void getRandomRecipesSync()
    {
        recipeListFromSync = requestHandler.getRandomRecipesSynchronously(100);
    }

    @Test(timeout = 2500)
    public void getRandomRecipesAsync()
    {
        RandomRecipeListener randomRecipeListener = new RandomRecipeListener() {
            @Override
            public void onResponse(RandomRecipes response, String message) {
                recipeListFromAsync = new ArrayList<>();
                recipeListFromAsync.addAll(response.recipes);
            }

            @Override
            public void onError(String message) {

            }
        };

        requestHandler.getRandomRecipes(randomRecipeListener, 100);
        synchronized (randomRecipeListener) {
            while (recipeListFromAsync == null) {
                try {
                    randomRecipeListener.wait(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}