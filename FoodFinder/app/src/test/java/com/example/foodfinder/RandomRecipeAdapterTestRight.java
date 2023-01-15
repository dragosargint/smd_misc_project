package com.example.foodfinder;

import static org.junit.Assert.*;

import com.example.foodfinder.adapters.RandomRecipeAdapter;
import com.example.foodfinder.spoonacularAPI.responseformat.Recipe;

import org.junit.Test;

import java.util.ArrayList;

public class RandomRecipeAdapterTestRight {

    @Test
    /* Test if getItemCount returns 0 */
    public void getItemCountZero() {
        RandomRecipeAdapter randomRecipeAdapter = new RandomRecipeAdapter(null, new ArrayList<Recipe>(), null);
        int actual = randomRecipeAdapter.getItemCount();
        assertEquals(0, actual);
    }

    @Test
    /* Test if getItemCount returns 10 */
    public void getItemCountNotZero() {
        ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
        int expected = 10;
        for (int i = 0; i < expected; i++) {
            Recipe recipe = new Recipe();
            recipeList.add(recipe);
        }
        RandomRecipeAdapter randomRecipeAdapter = new RandomRecipeAdapter(null, recipeList, null);
        int actual = randomRecipeAdapter.getItemCount();
        assertEquals(expected, actual);
    }
}