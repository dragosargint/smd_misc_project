package com.example.foodfinder;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({RecipeSearcherTestPerformance.class,
        RequestHandlerTestPerformance.class,
})
public class TestSuitePerformance {

}
