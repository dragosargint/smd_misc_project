package com.example.foodfinder;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({HomeActivityViewModelTestBoundaryWithFake.class,
        RecipeSearcherTestBoundary.class
})
public class TestSuiteBoundary {

}
