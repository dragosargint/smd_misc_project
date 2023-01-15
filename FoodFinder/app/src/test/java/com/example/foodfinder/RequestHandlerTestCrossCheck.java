package com.example.foodfinder;

import static org.junit.Assert.*;

import com.example.foodfinder.adapters.RandomRecipeAdapter;
import com.example.foodfinder.interfaces.RandomRecipeListener;
import com.example.foodfinder.spoonacularAPI.RequestHandler;
import com.example.foodfinder.spoonacularAPI.responseformat.RandomRecipes;
import com.example.foodfinder.spoonacularAPI.responseformat.Recipe;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RequestHandlerTestCrossCheck {

    List<Recipe> recipeListFromSync;
    List<Recipe> recipeListFromAsync;

    @Before
    public void SetUp(){
        RequestHandler requestHandler = RequestHandler.getInstance();
        recipeListFromSync = requestHandler.getRandomRecipesSynchronously(5);
        RandomRecipeListener randomRecipeListener = new RandomRecipeListener() {
            @Override
            public void onResponse(RandomRecipes response, String message) {
                recipeListFromAsync = new ArrayList<Recipe>();
                recipeListFromAsync.addAll(response.recipes);
            }

            @Override
            public void onError(String message) {

            }
        };

        requestHandler.getRandomRecipes(randomRecipeListener, 5);
        synchronized (randomRecipeListener) {
            while (recipeListFromAsync == null) {
                try {
                    randomRecipeListener.wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void getRandomRecipesCrossCheck()
    {
        assertEquals(recipeListFromAsync.size(), recipeListFromSync.size());
    }

}