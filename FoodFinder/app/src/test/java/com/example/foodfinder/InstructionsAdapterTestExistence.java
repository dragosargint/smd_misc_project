package com.example.foodfinder;

import static org.junit.Assert.assertEquals;

import com.example.foodfinder.adapters.InstructionsAdapter;
import com.example.foodfinder.spoonacularAPI.responseformat.Instructions;
import com.example.foodfinder.spoonacularAPI.responseformat.Step;

import org.junit.Test;

import java.util.ArrayList;

public class InstructionsAdapterTestExistence {

    @Test
    /* Test if getItemCount returns 0 */
    public void getItemCountZero() {
        InstructionsAdapter instructionsAdapter = new InstructionsAdapter(null, null); // give null instructions
        int actual = instructionsAdapter.getItemCount();
        assertEquals(0, actual);
    }
}