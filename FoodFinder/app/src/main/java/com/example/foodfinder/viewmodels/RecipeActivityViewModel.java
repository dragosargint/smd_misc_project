package com.example.foodfinder.viewmodels;

import static com.example.foodfinder.Constants.DEFAULT_NO_RECIPES;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.foodfinder.interfaces.InstructionsListener;
import com.example.foodfinder.interfaces.RandomRecipeListener;
import com.example.foodfinder.spoonacularAPI.RequestHandler;
import com.example.foodfinder.spoonacularAPI.responseformat.Instructions;
import com.example.foodfinder.spoonacularAPI.responseformat.RandomRecipes;
import com.example.foodfinder.spoonacularAPI.responseformat.Recipe;

import java.util.List;

public class RecipeActivityViewModel extends AndroidViewModel {
    private final RequestHandler requestHandler;
    private MutableLiveData<Instructions> instructions;
    private int id;

    public RecipeActivityViewModel(@NonNull Application application) {
        super(application);
        instructions = new MutableLiveData<Instructions>();
        requestHandler = RequestHandler.getInstance();
    }

    public void setID(int id) {
        this.id = id;
    }

    public LiveData<Instructions> getInstructionsLiveData() {
        return instructions;
    }

    public void getInstructions()
    {
        requestHandler.getInstructions(new InstructionsListener() {
            @Override
            public void onResponse(Instructions response, String message) {
                instructions.postValue(response);
            }

            @Override
            public void onError(String message) {
                Log.e("getInstructions", message);
            }
        }, id);
    }


}
