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


public class HomeActivityViewModelTestErrorConditionsWithStub {
    List<Recipe> recipeList;
    MutableLiveData stubbedMutableLiveData;
    HomeActivityViewModel homeActivityViewModel;
    RandomRecipeListener randomRecipeListener;

    @Before
    public void setUp()
    {
        recipeList = new ArrayList<Recipe>();
        stubbedMutableLiveData = Mockito.mock(MutableLiveData.class); // Create a mocked Mutablelivedata which is used in HomeActivityViewModel
        Mockito.when(stubbedMutableLiveData.getValue()).thenReturn(new ArrayList<Recipe>()); // When using the getValue method return the list
        homeActivityViewModel = new HomeActivityViewModel(new Application(), stubbedMutableLiveData); // Create HomeActivityViewModel and give it the mocked MutableData
    }

    @Test
    public void getRecipeAsListRangeBoundaryExceedSuperior() {
        List<Recipe> expected = null;
        List<Recipe> actual = homeActivityViewModel.getRecipesAsListRange(0, 20); // only 10 available but wants from 0 to 20
        assertEquals(expected, actual);
    }

    @Test
    public void getRecipeAsListRangeBoundaryExceedInferior() {
        List<Recipe> expected = null;
        List<Recipe> actual = homeActivityViewModel.getRecipesAsListRange(-10, 10); // only 10 available but wants from -10 to 10
        assertEquals(expected, actual);
    }

    @Test
    public void getRecipeAsListRangeBoundaryNotOrderd() {
        List<Recipe> expected = null;
        List<Recipe> actual = homeActivityViewModel.getRecipesAsListRange(10, 0); // idxMin > idxMax
        assertEquals(expected, actual);
    }

    @Test
    public void getRecipeAsListRangeBoundaryNegativeBoundaries() {
        List<Recipe> expected = null;
        List<Recipe> actual = homeActivityViewModel.getRecipesAsListRange(-10, -1); // idxMin < idxMax but negative
        assertEquals(expected, actual);
    }
}