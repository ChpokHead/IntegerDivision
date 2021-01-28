package com.chpok.division.provider;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

import com.chpok.division.domain.DivisionResult;
import com.chpok.division.domain.DivisionStep;

class DivisionMathProviderTest {
    
    private final DivisionMathProviderImpl divider = new DivisionMathProviderImpl();
    
    @Test
    void divideShouldReturnCorrectDivisionResultIfDividentIsMoreThanDivisor() {
        final int divident = 1432;
        final int divisor = 12;
        List<DivisionStep> steps = new ArrayList<DivisionStep>();
        
        steps.add(new DivisionStep.Builder()
                      .setDivident(14)
                      .setRemainder(14, divisor)
                      .setResult(14, divisor)
                      .setSubtract(divisor)
                      .setNumOfLeadingZeros(0)
                      .build());
        steps.add(new DivisionStep.Builder()
                      .setDivident(23)
                      .setRemainder(23, divisor)
                      .setResult(23, divisor)
                      .setSubtract(divisor)
                      .setNumOfLeadingZeros(0)
                      .build());
        steps.add(new DivisionStep.Builder()
                      .setDivident(112)
                      .setRemainder(112, divisor)
                      .setResult(112, divisor)
                      .setSubtract(divisor)
                      .setNumOfLeadingZeros(0)
                      .build());
        
        final DivisionResult expected = new DivisionResult.Builder()
                                            .setDivident(divident)
                                            .setDivisor(divisor)
                                            .setResult(divident, divisor)
                                            .setDivisionSteps(steps)
                                            .build();
        final DivisionResult actual = divider.provideMathCalculation(divident, divisor);
        
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    void divideShouldReturnCorrectDivisionResultIfDividentIsLessThanDivisor() {
        final int divident = 15;
        final int divisor = 3539;
        List<DivisionStep> steps = new ArrayList<DivisionStep>();
        
        steps.add(new DivisionStep.Builder()
                      .setDivident(15)
                      .setRemainder(15, divisor)
                      .setSubtract(divisor)
                      .setResult(15, divisor)
                      .setNumOfLeadingZeros(0)
                      .build());
        
        final DivisionResult expected = new DivisionResult.Builder()
                                            .setDivident(divident)
                                            .setDivisor(divisor)
                                            .setResult(divident, divisor)
                                            .setDivisionSteps(steps)
                                            .build();
        final DivisionResult actual = divider.provideMathCalculation(divident, divisor);
        
        assertThat(actual, is(equalTo(expected)));
    }
    
    @Test
    void divideShouldReturnCorrectDivisionResultIfDividentIsZero() {
        final int divident = 0;
        final int divisor = 20;
        List<DivisionStep> steps = new ArrayList<DivisionStep>();
        
        steps.add(new DivisionStep.Builder()
                      .setDivident(0)
                      .setRemainder(0, divisor)
                      .setSubtract(divisor)
                      .setResult(0, divisor)
                      .setNumOfLeadingZeros(0)
                      .build());
        
        final DivisionResult expected = new DivisionResult.Builder()
                                            .setDivident(divident)
                                            .setDivisor(divisor)
                                            .setResult(divident, divisor)
                                            .setDivisionSteps(steps)
                                            .build();
        final DivisionResult actual = divider.provideMathCalculation(divident, divisor);
        
        assertThat(actual, is(equalTo(expected)));
    }
    
    @Test
    void divideShouldReturnCorrectDivisionResultIfDividentHasZeros() {
        final int divident = 405022500;
        final int divisor = 45;
        List<DivisionStep> steps = new ArrayList<DivisionStep>();
        
        steps.add(new DivisionStep.Builder()
                      .setDivident(405)
                      .setRemainder(405, divisor)
                      .setResult(405, divisor)
                      .setSubtract(divisor)
                      .setNumOfLeadingZeros(0)
                      .build());
        steps.add(new DivisionStep.Builder()
                      .setDivident(225)
                      .setRemainder(225, divisor)
                      .setResult(225, divisor)
                      .setSubtract(divisor)
                      .setNumOfLeadingZeros(1)
                      .build());
        
        final DivisionResult expected = new DivisionResult.Builder()
                                            .setDivident(divident)
                                            .setDivisor(divisor)
                                            .setResult(divident, divisor)
                                            .setDivisionSteps(steps)
                                            .build();
        final DivisionResult actual = divider.provideMathCalculation(divident, divisor);
        
        assertThat(actual, is(equalTo(expected)));
    }
    
}
