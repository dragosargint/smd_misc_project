package com.example.foodfinder;

import static org.junit.Assert.*;

import com.example.foodfinder.adapters.InstructionsAdapter;
import com.example.foodfinder.spoonacularAPI.responseformat.Instructions;
import com.example.foodfinder.spoonacularAPI.responseformat.Step;

import org.junit.Test;

import java.util.ArrayList;

public class InstructionsAdapterTestRight {

    @Test
    /* Test if getItemCount returns 0 */
    public void getItemCountZero() {
        InstructionsAdapter instructionsAdapter = new InstructionsAdapter(null, new Instructions());
        int actual = instructionsAdapter.getItemCount();
        assertEquals(0, actual);
    }

    @Test
    /* Test if getItemCount returns 10 */
    public void getItemCountNonZero() {
        Instructions instructions = new Instructions();
        instructions.steps = new ArrayList<Step>();
        int expected = 10;
        for (int i = 0; i < expected; i++) {
            instructions.steps.add(new Step());
        }
        InstructionsAdapter instructionsAdapter = new InstructionsAdapter(null, instructions);
        int actual = instructionsAdapter.getItemCount();
        assertEquals(expected, actual);
    }
}