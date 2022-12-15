package com.example.foodfinder;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.foodfinder.spoonacularAPI.RequestHandler;
import com.example.foodfinder.spoonacularAPI.responseformat.Recipe;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

class Person implements Serializable {
    String name;
    int age;
    public Person(String name, int age)
    {
        this.name = name;
        this.age = age;
    }
}

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void check() {
        RequestHandler requestHandler = RequestHandler.getInstance();
        List<Recipe> l = requestHandler.getRandomRecipesSynchronously(10);

        l.sort(new Comparator<Recipe>() {
            @Override
            public int compare(Recipe recipe, Recipe t1) {
                return recipe.title.compareTo(t1.title);
            }
        });

        Recipe recipe = l.get(l.size()-1);
        System.out.println("\n\n\n\n");
        System.out.println(recipe.title);
        RecipeSearcher rs = new RecipeSearcher(l);
        long start1 = System.nanoTime();
        Recipe recipeF1 = rs.normalSearch(recipe.title);
        long end1 = System.nanoTime();
        System.out.println("Elapsed Time in nano seconds: "+ (end1-start1));
        long start2 = System.nanoTime();
        Recipe recipeF2 = rs.normalSearch(recipe.title);
        long end2 = System.nanoTime();
        System.out.println("Elapsed Time in nano seconds: "+ (end2-start2));


        System.out.println(recipeF1.title);
        System.out.println(recipeF2.title);
        System.out.println("\n\n\n\n\n\n\n");



        assertEquals(1,1);
    }
}