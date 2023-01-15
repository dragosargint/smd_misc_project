package com.example.foodfinder;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({FileRWTestErrorConditions.class,
        FileRWTestRight.class,
        HomeActivityViewModelTestBoundaryWithFake.class,
        HomeActivityViewModelTestErrorConditionsWithStub.class,
        HomeActivityViewModelTestRightWithFake.class,
        InstructionsAdapterTestExistence.class,
        InstructionsAdapterTestRight.class,
        RandomRecipeAdapterTestRight.class,
        RecipeSearcherTestBoundary.class,
        RecipeSearcherTestCrossCheck.class,
        RecipeSearcherTestErrorConditions.class,
        RecipeSearcherTestOrdering.class,
        RecipeSearcherTestPerformance.class,
        RequestHandlerTestConformance.class,
        RequestHandlerTestCrossCheck.class,
        RequestHandlerTestErrorConditions.class,
        RequestHandlerTestPerformance.class,
        StringCheckerTestErrorConditions.class,
        StringCheckerTestRight.class
})
public class TestSuiteAll {

}
