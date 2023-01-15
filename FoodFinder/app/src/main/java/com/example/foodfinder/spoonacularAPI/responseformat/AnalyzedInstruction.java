package com.example.foodfinder.spoonacularAPI.responseformat;

import java.io.Serializable;
import java.util.ArrayList;

public class AnalyzedInstruction implements Serializable {
    public String name;
    public ArrayList<Step> steps;
}
