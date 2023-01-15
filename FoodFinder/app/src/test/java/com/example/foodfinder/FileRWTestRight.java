package com.example.foodfinder;

import static org.junit.Assert.*;

import com.example.foodfinder.spoonacularAPI.RequestHandler;
import com.example.foodfinder.spoonacularAPI.responseformat.Recipe;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FileRWTestRight {
    List<Recipe> recipeList;
    FileRW<Recipe> recipeFileRW;
    String PATH_SINGLE = "serialized_recipe";
    String PATH_LIST = "serialized_list_of_recipe";


    @Before
    public void setUp() {
        /**
         * Obtain a list of random recipes from the API, serialize them and write it to a file.
         * Afterwards test the read and readList methods of the FileRW
         */
        RequestHandler requestHandler = RequestHandler.getInstance();
        recipeList = requestHandler.getRandomRecipesSynchronously(5);
        recipeFileRW = new FileRW<>(Recipe.class);
        recipeFileRW.write(PATH_SINGLE, recipeList.get(0));
        recipeFileRW.write(PATH_LIST, recipeList);
    }

    @Test
    public void read() {
        Recipe expectedRecipe = recipeList.get(0);
        Recipe actualRecipe = recipeFileRW.read(PATH_SINGLE);
        assertEquals(expectedRecipe.id, actualRecipe.id);
    }

    @Test
    public void readListCompareSizes() {
        List<Recipe> actualRecipeList = recipeFileRW.readList(PATH_LIST);
        assertNotEquals(null, actualRecipeList);
        assertEquals(recipeList.size(), actualRecipeList.size());
    }

    @Test
    public void readListCompareObjects() {
        List<Recipe> actualRecipeList = recipeFileRW.readList((PATH_LIST));
        assertNotEquals(null, actualRecipeList);
        for (int i = 0; i < actualRecipeList.size(); i++) {
            assertEquals(recipeList.get(i).id, actualRecipeList.get(i).id);
        }
    }
}