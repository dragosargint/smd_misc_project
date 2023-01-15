package com.example.foodfinder;

import static org.junit.Assert.assertEquals;

import com.example.foodfinder.spoonacularAPI.RequestHandler;
import com.example.foodfinder.spoonacularAPI.responseformat.Recipe;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class RecipeSearcherTestCrossCheck {

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
     * Cross check binarySerach and normalSearch
     */
    public void binarySearchNormalSearchCross() {
        int last_item = recipeList.size() - 1;
        Recipe r = recipeList.get(last_item);
        Recipe expectedRecipe = recipeSearcher.normalSearch(r.title);
        Recipe actualRecipe = recipeSearcher.binarySearch(r.title);

        assertEquals(expectedRecipe.id, actualRecipe.id);
    }

}