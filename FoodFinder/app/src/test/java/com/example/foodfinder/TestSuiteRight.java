package com.example.foodfinder;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({FileRWTestErrorConditions.class,
        FileRWTestRight.class,
        HomeActivityViewModelTestRightWithFake.class,
        InstructionsAdapterTestRight.class,
        RandomRecipeAdapterTestRight.class,
        StringCheckerTestRight.class
})
public class TestSuiteRight {

}
