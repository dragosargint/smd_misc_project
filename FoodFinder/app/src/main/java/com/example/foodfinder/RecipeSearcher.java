package com.example.foodfinder;

import com.example.foodfinder.spoonacularAPI.responseformat.Recipe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RecipeSearcher {
    List<Recipe> list;
    Comparator<Recipe> comparator;

    public RecipeSearcher (List<Recipe> list) {
        this.list = new ArrayList<Recipe>();
        this.list.addAll(list);
        comparator = new Comparator<Recipe>() {
            @Override
            public int compare(Recipe recipe, Recipe t1) {
                return recipe.title.compareTo(t1.title);
            }
        };

        this.list.sort(comparator);
    }

    public Recipe normalSearch(String title) {
        for (Recipe recipe : list) {
            if (recipe.title.compareTo(title) == 0)
                return recipe;
        }
        return null;
    }

    public Recipe binarySearch(String title) {

        Recipe targetRecipe = new Recipe();
        targetRecipe.title = title;
        int idx = Collections.binarySearch(list, targetRecipe, comparator);
        if (idx < 0)
            return null;
        return list.get(idx);
    }

}
