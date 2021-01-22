package com.chpok.division;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

class DivisionMathProviderTest {
    
    private final DivisionMathProviderImpl divider = new DivisionMathProviderImpl();
    
    @Test
    void divideShouldReturnCorrectDivisionResultIfDividentIsMoreThanDivisor() {
        final int divident = 1432;
        final int divisor = 12;
        List<DivisionStep> steps = new ArrayList<DivisionStep>();
        
        steps.add(new DivisionStep(14, 12, 0));
        steps.add(new DivisionStep(23, 12, 0));
        steps.add(new DivisionStep(112, 12, 0));
        
        final DivisionResult expected = new DivisionResult(divident, divisor, steps);
        final DivisionResult actual = divider.provideMathCalculation(divident, divisor);
        
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    void divideShouldReturnCorrectDivisionResultIfDividentIsLessThanDivisor() {
        final int divident = 15;
        final int divisor = 3539;
        List<DivisionStep> steps = new ArrayList<DivisionStep>();
        
        steps.add(new DivisionStep(15, 3539, 0));
        
        final DivisionResult expected = new DivisionResult(divident, divisor, steps);
        final DivisionResult actual = divider.provideMathCalculation(divident, divisor);
        
        assertThat(actual, is(equalTo(expected)));
    }
    
    @Test
    void divideShouldReturnCorrectDivisionResultIfDividentIsZero() {
        final int divident = 0;
        final int divisor = 20;
        List<DivisionStep> steps = new ArrayList<DivisionStep>();
        
        steps.add(new DivisionStep(0, 20, 0));
        
        final DivisionResult expected = new DivisionResult(divident, divisor, steps);
        final DivisionResult actual = divider.provideMathCalculation(divident, divisor);
        
        assertThat(actual, is(equalTo(expected)));
    }
    
    @Test
    void divideShouldReturnCorrectDivisionResultIfDividentIsNegative() {
        final int divident = -44;
        final int divisor = 3;
        List<DivisionStep> steps = new ArrayList<DivisionStep>();
        
        steps.add(new DivisionStep(4, 3, 0));
        steps.add(new DivisionStep(14, 3, 0));
        
        final DivisionResult expected = new DivisionResult(divident, divisor, steps);
        final DivisionResult actual = divider.provideMathCalculation(divident, divisor);
        
        assertThat(actual, is(equalTo(expected)));
    }
    
    @Test
    void divideShouldReturnCorrectDivisionResultIfDivisorIsNegative() {
        final int divident = 51;
        final int divisor = -13;
        List<DivisionStep> steps = new ArrayList<DivisionStep>();
        
        steps.add(new DivisionStep(51, 13, 0));
        
        final DivisionResult expected = new DivisionResult(divident, divisor, steps);
        final DivisionResult actual = divider.provideMathCalculation(divident, divisor);
        
        assertThat(actual, is(equalTo(expected)));
    }
    
    @Test
    void divideShouldReturnCorrectDivisionResultIfDivisorAndDividentAreNegative() {
        final int divident = -4354;
        final int divisor = -5;
        List<DivisionStep> steps = new ArrayList<DivisionStep>();
        
        steps.add(new DivisionStep(43, 5, 0));
        steps.add(new DivisionStep(35, 5, 0));
        steps.add(new DivisionStep(4, 5, 0));
        
        final DivisionResult expected = new DivisionResult(divident, divisor, steps);
        final DivisionResult actual = divider.provideMathCalculation(divident, divisor);
        
        assertThat(actual, is(equalTo(expected)));
    }
    
    @Test
    void divideShouldReturnCorrectDivisionResultIfDividentHasZeros() {
        final int divident = 405022500;
        final int divisor = 45;
        List<DivisionStep> steps = new ArrayList<DivisionStep>();
        
        steps.add(new DivisionStep(405, 45, 0));
        steps.add(new DivisionStep(225, 45, 1));
        
        final DivisionResult expected = new DivisionResult(divident, divisor, steps);
        final DivisionResult actual = divider.provideMathCalculation(divident, divisor);
        
        assertThat(actual, is(equalTo(expected)));
    }
    
}
