package com.example.foodfinder;

import static com.example.foodfinder.Constants.API_KEY;
import static org.junit.Assert.assertEquals;

import com.example.foodfinder.interfaces.RandomRecipeListener;
import com.example.foodfinder.spoonacularAPI.RequestHandler;
import com.example.foodfinder.spoonacularAPI.responseformat.RandomRecipes;
import com.example.foodfinder.spoonacularAPI.responseformat.Recipe;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RequestHandlerTestErrorConditions {

    @Test(expected = NullPointerException.class)
    public void getRandomSyncRawInvalidApiKey() throws NullPointerException, IOException {
        String INVALID_API_KEY = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
        RequestHandler.getInstance().getRandomRecipesSynchronouslyRaw(INVALID_API_KEY, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getRandomSyncRawInvalidNrOfRecipes() throws IOException {
        List<Recipe> l = RequestHandler.getInstance().getRandomRecipesSynchronouslyRaw(API_KEY, -1);
    }

    @Test
    public void getRandomAsyncInvalidApiKey(){
        String INVALID_API_KEY = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
        int expected = 1;
        final int[] error = {0};
        RandomRecipeListener listener = new RandomRecipeListener() {
            @Override
            public void onResponse(RandomRecipes response, String message) {
            }

            @Override
            public void onError(String message){
                error[0] = 1;
            }
        };
        RequestHandler.getInstance().getRandomRecipes(listener, INVALID_API_KEY,10);
        synchronized (listener) {
            try {
                listener.wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        assertEquals(expected,error[0]);
    }

    @Test
    public void getRandomAsyncInvalidNrOfRecipes(){
        int expected = 1;
        final int[] error = {0};
        RandomRecipeListener listener = new RandomRecipeListener() {
            @Override
            public void onResponse(RandomRecipes response, String message) {
            }

            @Override
            public void onError(String message){
                error[0] = 1;
            }
        };
        RequestHandler.getInstance().getRandomRecipes(listener, API_KEY,-1);
        synchronized (listener) {
            try {
                listener.wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        assertEquals(expected,error[0]);
    }


}