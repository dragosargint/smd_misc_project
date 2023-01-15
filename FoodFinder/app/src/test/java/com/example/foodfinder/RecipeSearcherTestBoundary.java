package com.example.foodfinder;

import static org.junit.Assert.*;

import com.example.foodfinder.spoonacularAPI.RequestHandler;
import com.example.foodfinder.spoonacularAPI.responseformat.Recipe;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class RecipeSearcherTestBoundary {

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
     * Check if recipeSearcher returns the correct recipe based on the title
     * using the normalSearch method
     */
    public void normalSearch() {
        int last_elem = recipeList.size() - 1;
        Recipe expectedRecipe = recipeList.get(last_elem);
        Recipe actualRecipe = recipeSearcher.normalSearch(expectedRecipe.title);

        assertEquals(expectedRecipe.id, actualRecipe.id);
    }

    @Test
    /**
     * Check if recipeSearcher returns the correct recipe based on the title
     * using the binarySearch method
     */
    public void binarySearch() {
        int last_elem = recipeList.size() - 1;
        Recipe expectedRecipe = recipeList.get(last_elem);
        Recipe actualRecipe = recipeSearcher.binarySearch(expectedRecipe.title);

        assertEquals(expectedRecipe.id, actualRecipe.id);
    }
}