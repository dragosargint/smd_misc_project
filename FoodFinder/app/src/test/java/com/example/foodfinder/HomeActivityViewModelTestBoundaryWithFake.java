package com.example.foodfinder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.foodfinder.interfaces.RandomRecipeListener;
import com.example.foodfinder.spoonacularAPI.responseformat.RandomRecipes;
import com.example.foodfinder.spoonacularAPI.responseformat.Recipe;
import com.example.foodfinder.viewmodels.HomeActivityViewModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;


public class HomeActivityViewModelTestBoundaryWithFake {
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
     * Test if getRecipeAsListRange returns the correct number in range => 5
     */
    public void getRecipeAsListRangeSize() {
        int expected = 5;
        int actual = homeActivityViewModel.getRecipesAsListRange(0, 5).size();
        assertEquals(expected, actual);
    }

    @Test
    /**
     * Test if recipes are the same in the 2 ranges
     */
    public void getRecipesAsListRange() {
        int idxMin = 4;
        int idxMax = 9;
        List<Recipe> actualRangeList = homeActivityViewModel.getRecipesAsListRange(idxMin, idxMax);
        assertNotEquals(null, actualRangeList);
        for (int i = idxMin; i < idxMax; i++) {
            assertEquals(recipeList.get(i).id, actualRangeList.get(i - idxMin).id);
        }
    }
}