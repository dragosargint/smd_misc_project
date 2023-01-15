package com.example.foodfinder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.foodfinder.interfaces.RandomRecipeListener;
import com.example.foodfinder.spoonacularAPI.RequestHandler;
import com.example.foodfinder.spoonacularAPI.responseformat.RandomRecipes;
import com.example.foodfinder.spoonacularAPI.responseformat.Recipe;
import com.example.foodfinder.viewmodels.HomeActivityViewModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class RequestHandlerTestConformance {

    @Test
    public void checkRecipeImageContainsHttps() {
        String islinkRegex = "https://.*";
        RequestHandler requestHandler = RequestHandler.getInstance();
        List<Recipe> recipeList = requestHandler.getRandomRecipesSynchronously(1);
        boolean expected = true;
        boolean actual = StringChecker.check(recipeList.get(0).image, islinkRegex);
        assertEquals(expected, actual);
    }

    @Test
    public void checkRecipeImageMalformed() {
        String islinkRegex = "https://.*";
        Recipe recipe = new Recipe();
        recipe.image = "ABCD";
        boolean expected = false;
        boolean actual = StringChecker.check(recipe.image, islinkRegex);
        assertEquals(expected, actual);
    }

}