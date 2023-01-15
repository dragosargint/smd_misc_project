package com.example.foodfinder;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
/**
 * Just a subset of some test
 * Conformance V
 * Ordering V
 * Range => RangeList from HomeActiviryViewModel in the error conditions test
 * Reference => preconditions using fake ->
 * Existence V
 * Cardinality X
 * Time V
 */
@Suite.SuiteClasses({
        RequestHandlerTestConformance.class,
        RecipeSearcherTestOrdering.class,
        HomeActivityViewModelTestErrorConditionsWithStub.class,
        HomeActivityViewModelTestBoundaryWithFake.class,
        InstructionsAdapterTestExistence.class,
        RecipeSearcherTestPerformance.class,
})
public class TestSuiteCORRECT {

}
