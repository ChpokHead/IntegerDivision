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
    void provideViewShouldReturnCorrectViewIfDividentIsMoreThanDivisor() {
        final int divident = 1432;
        final int divisor = 12;
        List<DivisionStep> steps = new ArrayList<DivisionStep>();
        
        steps.add(DivisionStep.builder()
                              .withDivident(14)
                              .withRemainder(14, divisor)
                              .withResult(14, divisor)
                              .withSubtract(divisor)
                              .withNumOfLeadingZeros(0)
                              .build());
        steps.add(DivisionStep.builder()
                              .withDivident(23)
                              .withRemainder(23, divisor)
                              .withResult(23, divisor)
                              .withSubtract(divisor)
                              .withNumOfLeadingZeros(0)
                              .build());
        
        steps.add(DivisionStep.builder()
                              .withDivident(112)
                              .withRemainder(112, divisor)
                              .withResult(112, divisor)
                              .withSubtract(divisor)
                              .withNumOfLeadingZeros(0)
                              .build());
 
        final DivisionResult actual = divider.provideMathCalculation(divident, divisor);
        final DivisionResult expected = DivisionResult.builder()
                                                           .withDivident(divident)
                                                           .withDivisor(divisor)
                                                           .withResult(divident, divisor)
                                                           .withDivisionSteps(steps)
                                                           .build();        
        assertThat(actual, is(equalTo(expected)));
    }
    
    @Test
    void divideShouldReturnCorrectDivisionResultIfDividentIsMoreThanDivisorAndStepHasZeroRemainder() {
        final int divident = 1432;
        final int divisor = 14;
        List<DivisionStep> steps = new ArrayList<DivisionStep>();
        
        steps.add(DivisionStep.builder()
                              .withDivident(14)
                              .withRemainder(14, divisor)
                              .withResult(14, divisor)
                              .withSubtract(divisor)
                              .withNumOfLeadingZeros(0)
                              .build());
        steps.add(DivisionStep.builder()
                              .withDivident(32)
                              .withRemainder(32, divisor)
                              .withResult(32, divisor)
                              .withSubtract(divisor)
                              .withNumOfLeadingZeros(0)
                              .build());
        
        final DivisionResult expected = DivisionResult.builder()
                                                      .withDivident(divident)
                                                      .withDivisor(divisor)
                                                      .withResult(divident, divisor)
                                                      .withDivisionSteps(steps)
                                                      .build();
        final DivisionResult actual = divider.provideMathCalculation(divident, divisor);
        
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    void divideShouldReturnCorrectDivisionResultIfDividentIsLessThanDivisor() {
        final int divident = 15;
        final int divisor = 3539;
        List<DivisionStep> steps = new ArrayList<DivisionStep>();
        
        steps.add(DivisionStep.builder()
                              .withDivident(15)
                              .withRemainder(15, divisor)
                              .withSubtract(divisor)
                              .withResult(15, divisor)
                              .withNumOfLeadingZeros(0)
                              .build());
        
        final DivisionResult expected = DivisionResult.builder()
                                                      .withDivident(divident)
                                                      .withDivisor(divisor)
                                                      .withResult(divident, divisor)
                                                      .withDivisionSteps(steps)
                                                      .build();
        final DivisionResult actual = divider.provideMathCalculation(divident, divisor);
        
        assertThat(actual, is(equalTo(expected)));
    }
    
    @Test
    void divideShouldReturnCorrectDivisionResultIfDividentIsZero() {
        final int divident = 0;
        final int divisor = 20;
        List<DivisionStep> steps = new ArrayList<DivisionStep>();
        
        steps.add(DivisionStep.builder()
                              .withDivident(0)
                              .withRemainder(0, divisor)
                              .withSubtract(divisor)
                              .withResult(0, divisor)
                              .withNumOfLeadingZeros(0)
                              .build());
        
        final DivisionResult expected = DivisionResult.builder()
                                                      .withDivident(divident)
                                                      .withDivisor(divisor)
                                                      .withResult(divident, divisor)
                                                      .withDivisionSteps(steps)
                                                      .build();
        final DivisionResult actual = divider.provideMathCalculation(divident, divisor);
        
        assertThat(actual, is(equalTo(expected)));
    }
    
    @Test
    void divideShouldReturnCorrectDivisionResultIfDividentHasZeros() {
        final int divident = 405022500;
        final int divisor = 45;
        List<DivisionStep> steps = new ArrayList<DivisionStep>();
        
        steps.add(DivisionStep.builder()
                              .withDivident(405)
                              .withRemainder(405, divisor)
                              .withResult(405, divisor)
                              .withSubtract(divisor)
                              .withNumOfLeadingZeros(0)
                              .build());
        steps.add(DivisionStep.builder()
                              .withDivident(225)
                              .withRemainder(225, divisor)
                              .withResult(225, divisor)
                              .withSubtract(divisor)
                              .withNumOfLeadingZeros(1)
                              .build());
        
        final DivisionResult expected = DivisionResult.builder()
                                                      .withDivident(divident)
                                                      .withDivisor(divisor)
                                                      .withResult(divident, divisor)
                                                      .withDivisionSteps(steps)
                                                      .build();
        final DivisionResult actual = divider.provideMathCalculation(divident, divisor);
        
        assertThat(actual, is(equalTo(expected)));
    }
    
}
