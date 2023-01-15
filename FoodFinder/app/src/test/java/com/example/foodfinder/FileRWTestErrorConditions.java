package com.example.foodfinder;

import static org.junit.Assert.assertEquals;

import com.example.foodfinder.spoonacularAPI.RequestHandler;
import com.example.foodfinder.spoonacularAPI.responseformat.Recipe;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.nio.charset.StandardCharsets;
import java.util.List;

class TestObject implements Serializable {
    int a;
    int b;
    int c;
};

public class FileRWTestErrorConditions {

    FileRW<Recipe> recipeFileRW;
    final String INVALID_PATH = "/invalid/path/cannot_read_write_here";
    final String VALID_PATH_WITH_GARBAGE = "valid_path_with_garbage";
    final String VALID_PATH_WITH_DIF_OBJECT = "valid_path_with_diffrent_object";
    final String VALID_PATH_WITH_ONE_RECIPE = "valid_path_with_one_recipe";
    final String GarbageString = "GarbageString";

    @Before
    public void setUp() {
        recipeFileRW = new FileRW<>(Recipe.class);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(VALID_PATH_WITH_GARBAGE);
            fileOutputStream.write(GarbageString.getBytes(StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileRW<TestObject> testObjectFileRW = new FileRW<>(TestObject.class);
        testObjectFileRW.write(VALID_PATH_WITH_DIF_OBJECT, new TestObject());
        recipeFileRW.write(VALID_PATH_WITH_ONE_RECIPE, new Recipe());
    }

    @Test(expected = IOException.class)
    /**
     * Obtain a random recipe from the API, serialize it and write it to a file.
     * Afterwards test the read method of the FileRW by checking the IDs of the recipes
     */
    public void writeRawInvalidPath() throws IOException {
        recipeFileRW.writeRaw(INVALID_PATH, new Recipe());
    }

    @Test
    public void writeInvalidPath() {
        boolean expected = false;
        boolean actual = recipeFileRW.write(INVALID_PATH, new Recipe());
        assertEquals(expected, actual);
    }

    @Test(expected = IOException.class)
    public void readRawInvalidPath() throws Exception {
        Recipe actualRecipe = recipeFileRW.readRaw(INVALID_PATH);
    }

    @Test
    public void readInvalidPath() throws Exception {
        Recipe expectedRecipe = null;
        Recipe actualRecipe = recipeFileRW.read(INVALID_PATH);
        assertEquals(expectedRecipe, actualRecipe);
    }

    @Test(expected = IOException.class)
    public void readListRawInvalidPath() throws Exception {
        List<Recipe> actualRecipe = recipeFileRW.readListRaw(INVALID_PATH);
    }

    @Test
    public void readListInvalidPath() {
        List<Recipe> expectedList = null;
        List<Recipe> actualList = recipeFileRW.readList(INVALID_PATH);
        assertEquals(expectedList, actualList);
    }

    @Test(expected = ClassCastException.class)
    public void readRawClassCastException() throws Exception {
        Recipe actualRecipe = recipeFileRW.readRaw(VALID_PATH_WITH_DIF_OBJECT);
    }

    @Test
    public void readClassCastException() {
        Recipe expectedRecipe = null;
        Recipe actualRecipe = recipeFileRW.read(VALID_PATH_WITH_DIF_OBJECT);
        assertEquals(expectedRecipe, actualRecipe);
    }

    @Test(expected = ClassCastException.class)
    public void readListRawRecipesFromDifObj() throws Exception {
        List<Recipe> list = recipeFileRW.readListRaw(VALID_PATH_WITH_DIF_OBJECT);
    }

    @Test
    public void readListRecipesFromDifObj() {
        List<Recipe> expectedList = null;
        List<Recipe> actulaList = recipeFileRW.readList(VALID_PATH_WITH_DIF_OBJECT);
        assertEquals(expectedList, actulaList);
    }

    @Test(expected = ClassCastException.class)
    public void readListRawRecipesFromOneRecipe() throws Exception {
        List<Recipe> list = recipeFileRW.readListRaw(VALID_PATH_WITH_ONE_RECIPE);
    }

    @Test
    public void readListRecipesFromOneRecipe() {
        List<Recipe> expectedList = null;
        List<Recipe> actualList = recipeFileRW.readList(VALID_PATH_WITH_ONE_RECIPE);
        assertEquals(expectedList, actualList);
    }
}