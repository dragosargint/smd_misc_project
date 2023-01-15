package com.example.foodfinder;

import static org.junit.Assert.assertEquals;

import com.example.foodfinder.spoonacularAPI.RequestHandler;
import com.example.foodfinder.spoonacularAPI.responseformat.Recipe;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class RecipeSearcherTestErrorConditions {

    List<Recipe> recipeList;
    RecipeSearcher recipeSearcher;
    @Before
    public void setUp() {
        /**
         * Obtain a list of 10 random Recipes
         */
        recipeList = RequestHandler.getInstance().getRandomRecipesSynchronously(10);
        recipeSearcher = new RecipeSearcher(recipeList);
    }

    @Test
    /**
     * Check if recipeSearcher returns null if the recipe is not found
     */
    public void normalSearch() {
        Recipe expected = null;
        Recipe actualRecipe = recipeSearcher.normalSearch("wrongTitle");

        assertEquals(expected, actualRecipe);
    }

    @Test
    /**
     * Check if recipeSearcher returns the correct recipe based on the title
     * using the binarySearch method
     */
    public void binarySearch() {
        Recipe expected = null;
        Recipe actualRecipe = recipeSearcher.binarySearch("wrongTitle");

        assertEquals(expected, actualRecipe);
    }
}