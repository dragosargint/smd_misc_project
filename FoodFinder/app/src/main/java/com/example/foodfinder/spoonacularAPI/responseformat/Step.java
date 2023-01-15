package com.example.foodfinder.spoonacularAPI.responseformat;

import java.io.Serializable;
import java.util.ArrayList;

public class Step implements Serializable {
    public int number;
    public String step;
    public ArrayList<Ingredient> ingredients;
    public ArrayList<Equipment> equipment;
    public Length length;
}
