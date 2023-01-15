package com.example.foodfinder.spoonacularAPI.responseformat;

import java.io.Serializable;
import java.util.ArrayList;

public class Instructions implements Serializable {
    public String name;
    public ArrayList<Step> steps;
}
