package com.example.foodfinder;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({FileRWTestErrorConditions.class,
        HomeActivityViewModelTestErrorConditionsWithStub.class,
        RecipeSearcherTestErrorConditions.class,
        RequestHandlerTestErrorConditions.class,
        StringCheckerTestErrorConditions.class
})
public class TestSuiteErrorConditions {

}
