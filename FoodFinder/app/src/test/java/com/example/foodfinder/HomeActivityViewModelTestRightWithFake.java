package com.example.foodfinder;

import static org.junit.Assert.*;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.foodfinder.interfaces.RandomRecipeListener;
import com.example.foodfinder.spoonacularAPI.responseformat.RandomRecipes;
import com.example.foodfinder.spoonacularAPI.responseformat.Recipe;
import com.example.foodfinder.viewmodels.HomeActivityViewModel;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import org.mockito.Mock;
import org.mockito.Mockito;


public class HomeActivityViewModelTestRightWithFake {
    List<Recipe> recipeList;
    MutableLiveData fakeMutableLiveData;
    HomeActivityViewModel homeActivityViewModel;
    RandomRecipeListener randomRecipeListener;

    @Before
    public void setUp()
    {
        recipeList = new ArrayList<Recipe>();
        fakeMutableLiveData = Mockito.mock(MutableLiveData.class); // Create a mocked Mutablelivedata which is used in HomeActivityViewModel
        Mockito.when(fakeMutableLiveData.getValue()).thenReturn(recipeList); // When using the getValue method return the list
        homeActivityViewModel = new HomeActivityViewModel(new Application(), fakeMutableLiveData); // Create HomeActivityViewModel and give it the mocked MutableData
        randomRecipeListener = new RandomRecipeListener() {
            @Override
            public void onResponse(RandomRecipes response, String message) {
                recipeList.addAll(response.recipes);
            }

            @Override
            public void onError(String message) {

            }
        };

        // get random recipes and wait for them to be available
        homeActivityViewModel.getRandomRecipes(randomRecipeListener, 10);
        while (recipeList.size() == 0) {
            synchronized (randomRecipeListener) {
                try {
                    randomRecipeListener.wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    /**
     * Test if getRecipeAsList returns the correct number of recipes => 10
     */
    public void getRecipesAsListSize() {
        int expected = 10;
        int actual = homeActivityViewModel.getRecipesAsList().size();
        assertEquals(expected, actual);
    }

    @Test
    /**
     * Test if recipes are the same in the 2 list
     */
    public void getRecipesAsList() {
        List<Recipe> actualList = homeActivityViewModel.getRecipesAsList();
        assertNotEquals(null, actualList);
        for (int i = 0; i < actualList.size(); i++) {
            assertEquals(recipeList.get(i).id, actualList.get(i).id);
        }
    }
}