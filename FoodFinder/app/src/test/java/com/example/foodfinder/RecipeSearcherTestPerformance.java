package com.example.foodfinder;

import static org.junit.Assert.assertEquals;

import com.example.foodfinder.interfaces.RandomRecipeListener;
import com.example.foodfinder.spoonacularAPI.RequestHandler;
import com.example.foodfinder.spoonacularAPI.responseformat.Recipe;

import org.junit.Before;
import org.junit.Test;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecipeSearcherTestPerformance {

    List<Recipe> recipeList;
    RecipeSearcher recipeSearcher;
    @Before
    public void setUp() {
        /**
         * Obtain a list of 10 random Recipes
         */
        recipeList = new ArrayList<Recipe>();
        Random random = new Random();
        for (int i = 0; i < 1000000; i++) {
            byte[] array = new byte[100]; // length is bounded by 7
            random.nextBytes(array);
            String generatedString = new String(array, Charset.forName("UTF-8"));
            Recipe recipe = new Recipe();
            recipe.title = generatedString;
            recipeList.add(recipe);
        }
        recipeSearcher = new RecipeSearcher(recipeList);
    }

    @Test(timeout = 4000)
    /**
     * The creation of a RecipeSearcher object is costly since it will copy and sort the list
     */
    public void sortingInRecipeSearcher() {
        RecipeSearcher recipeSearcher = new RecipeSearcher(recipeList);
    }

    @Test(timeout = 100)
    public void normalSearch() {
        int last_elem = recipeList.size() - 1;
        Recipe expectedRecipe = recipeList.get(last_elem);
        recipeSearcher.normalSearch(expectedRecipe.title);
    }

    @Test(timeout = 10)
    public void binarySearch() {
        int last_elem = recipeList.size() - 1;
        Recipe expectedRecipe = recipeList.get(last_elem);
        recipeSearcher.binarySearch(expectedRecipe.title);
    }
}