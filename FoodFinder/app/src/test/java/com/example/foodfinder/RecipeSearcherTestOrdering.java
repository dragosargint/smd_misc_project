package com.example.foodfinder;

import static org.junit.Assert.assertEquals;

import com.example.foodfinder.spoonacularAPI.RequestHandler;
import com.example.foodfinder.spoonacularAPI.responseformat.Recipe;

import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;

public class RecipeSearcherTestOrdering {

    List<Recipe> recipeList;
    RecipeSearcher recipeSearcher;
    @Before
    public void setUp() {
        /**
         * Obtain a list of 10 random Recipes
         */
        recipeList = RequestHandler.getInstance().getRandomRecipesSynchronously(10);
        recipeSearcher = new RecipeSearcher(recipeList);
        recipeList.sort(new Comparator<Recipe>() {
            @Override
            public int compare(Recipe recipe, Recipe t1) {
                return recipe.title.compareTo(t1.title);
            }
        });
    }

    @Test
    public void checkOrdering() {
        List<Recipe> actualList = recipeSearcher.getTitleOrderedList();
        for (int i = 0; i < actualList.size(); i++) {
            assertEquals(recipeList.get(i), actualList.get(i));
        }
    }
}