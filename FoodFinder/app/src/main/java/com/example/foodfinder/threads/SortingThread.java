package com.example.foodfinder.threads;

import static com.example.foodfinder.Constants.SORT_A;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.foodfinder.spoonacularAPI.responseformat.Recipe;

import java.util.Comparator;
import java.util.List;

public class SortingThread implements Runnable{

    private MutableLiveData<List<Recipe>> recipeList;
    private int sortType;

    public SortingThread(MutableLiveData<List<Recipe>> recipeList, int sortType) {
        this.recipeList = recipeList;
        this.sortType = sortType;
    }

    @Override
    public void run() {
        if (recipeList != null && recipeList.getValue() != null) {
            if (sortType == SORT_A)
                sortA();
            else
                sortZ();
        }

    }

    private void sortA() {
        List<Recipe> list = recipeList.getValue();
        list.sort(new Comparator<Recipe>() {
            @Override
            public int compare(Recipe recipe, Recipe t1) {
                return recipe.title.compareTo(t1.title);
            }
        });
        recipeList.postValue(list);
    }

    private void sortZ() {
        List<Recipe> list = recipeList.getValue();
        list.sort(new Comparator<Recipe>() {
            @Override
            public int compare(Recipe recipe, Recipe t1) {
                return - recipe.title.compareTo(t1.title);
            }
        });
        recipeList.postValue(list);
    }

}
