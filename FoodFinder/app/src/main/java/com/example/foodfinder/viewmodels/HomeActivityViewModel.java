package com.example.foodfinder.viewmodels;

import static com.example.foodfinder.Constants.DEFAULT_NO_RECIPES;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.foodfinder.R;
import com.example.foodfinder.interfaces.RandomRecipeListener;
import com.example.foodfinder.spoonacularAPI.RequestHandler;
import com.example.foodfinder.spoonacularAPI.responseformat.RandomRecipes;
import com.example.foodfinder.spoonacularAPI.responseformat.Recipe;
import com.example.foodfinder.threads.SortingThread;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class HomeActivityViewModel extends AndroidViewModel {

    private MutableLiveData<List<Recipe>> recipeList;
    private RequestHandler requestHandler;
    Random rand;


    public HomeActivityViewModel(@NonNull Application application) {
        super(application);
        recipeList = new MutableLiveData<List<Recipe>>();
        requestHandler = RequestHandler.getInstance();
        rand = new Random();
    }

    public LiveData<List<Recipe>> getRecipesLiveData() {
        return recipeList;
    }

    public List<Recipe> getRecipesAsList() {
        return  recipeList.getValue();
    }

    public  List<Recipe> getRecipesAsListRange(int idxMin, int idxMax) {
        List<Recipe> list = recipeList.getValue();
        if (list == null)
            return  null;

        if (idxMin < 0 || idxMin >= list.size())
            return  null;

        if (idxMax < 0 || idxMax >= list.size())
            return null;

        if (idxMin > idxMax)
            return null;

        List<Recipe> new_list = new ArrayList<Recipe>();
        for (int idx = idxMin; idx < idxMax; idx++) {
            new_list.add(list.get(idx));
        }

        return new_list;
    }


    public void getRandomRecipes()
    {
        requestHandler.getRandomRecipes(new RandomRecipeListener() {
            @Override
            public void onResponse(RandomRecipes response, String message) {
                recipeList.postValue(response.recipes);
            }

            @Override
            public void onError(String message) {
            }
        }, DEFAULT_NO_RECIPES);
    }

    public void sort(int sortType) {
        SortingThread sortingThreadRunnable = new SortingThread(recipeList, sortType);
        Thread t = new Thread(sortingThreadRunnable);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        List<Recipe> list = recipeList.getValue();
//        if(list != null) {
//        String str = "";
//        for (int i = 0; i< list.size(); i++) {
//            str += list.get(i).title + ";;;;";
//        }
//        Log.e("In view model", str);}
        synchronized (recipeList) {
            recipeList.notify();
        }
    }


    public Recipe getRandomRecipe()
    {
        if (recipeList != null) {
            List<Recipe> list = recipeList.getValue();
            if (list != null && list.size() > 0) {
                int randomIdx = rand.nextInt(list.size());
                return list.get(randomIdx);
            }
        }

        return null;
    }


}
