package com.chpok.division.formatter;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.chpok.division.domain.DivisionResult;
import com.chpok.division.domain.DivisionStep;

class DivisionViewProviderTest {
    private final DivisionViewProviderImpl viewProvider = new DivisionViewProviderImpl();
    
    @Test
    void provideViewShouldReturnCorrectViewIfDividentIsMoreThanDivisor() {
        final int dividend = 1432;
        final int divisor = 12;
        List<DivisionStep> steps = new ArrayList<DivisionStep>();
        
        steps.add(DivisionStep.builder()
                              .withDividend(14)
                              .withRemainder(14, divisor)
                              .withResult(14, divisor)
                              .withSubtract(divisor)
                              .withNumOfLeadingZeros(0)
                              .build());
        steps.add(DivisionStep.builder()
                              .withDividend(23)
                              .withRemainder(23, divisor)
                              .withResult(23, divisor)
                              .withSubtract(divisor)
                              .withNumOfLeadingZeros(0)
                              .build());
        
        steps.add(DivisionStep.builder()
                              .withDividend(112)
                              .withRemainder(112, divisor)
                              .withResult(112, divisor)
                              .withSubtract(divisor)
                              .withNumOfLeadingZeros(0)
                              .build());
 
        final DivisionResult divisionResult = DivisionResult.builder()
                                                           .withDividend(dividend)
                                                           .withDivisor(divisor)
                                                           .withResult(dividend, divisor)
                                                           .withDivisionSteps(steps)
                                                           .build();
        final String actual = viewProvider.provideView(divisionResult);
        final String expected = "_1432|12\n 12  |---\n --  |119\n _23\n  12\n  --\n _112\n  108\n  ---\n    4";
        
        assertThat(actual, is(equalTo(expected)));
    }
    
    @Test
    void provideViewShouldReturnCorrectViewIfDividentIsMoreThanDivisorAndStepHasZeroRemainder() {
        final int dividend = 1432;
        final int divisor = 14;
        List<DivisionStep> steps = new ArrayList<DivisionStep>();
        
        steps.add(DivisionStep.builder()
                              .withDividend(14)
                              .withRemainder(14, divisor)
                              .withResult(14, divisor)
                              .withSubtract(divisor)
                              .withNumOfLeadingZeros(0)
                              .build());
        steps.add(DivisionStep.builder()
                              .withDividend(32)
                              .withRemainder(32, divisor)
                              .withResult(32, divisor)
                              .withSubtract(divisor)
                              .withNumOfLeadingZeros(0)
                              .build());
  
        final DivisionResult divisionResult = DivisionResult.builder()
                                                            .withDividend(dividend)
                                                            .withDivisor(divisor)
                                                            .withResult(dividend, divisor)
                                                            .withDivisionSteps(steps)
                                                            .build();
        final String actual = viewProvider.provideView(divisionResult);
        final String expected = "_1432|14\n 14  |---\n --  |102\n  _32\n   28\n   --\n    4";
        
        
        assertThat(actual, is(equalTo(expected)));
    }
    
    @Test
    void provideViewShouldReturnCorrectViewIfDividentIsLessThanDivisor() {
        final int dividend = 15;
        final int divisor = 3539;
        List<DivisionStep> steps = new ArrayList<DivisionStep>();
        
        steps.add(DivisionStep.builder()
                              .withDividend(15)
                              .withRemainder(15, divisor)
                              .withSubtract(divisor)
                              .withResult(15, divisor)
                              .withNumOfLeadingZeros(0)
                              .build());

        final DivisionResult divisionResult = DivisionResult.builder()
                                                            .withDividend(dividend)
                                                            .withDivisor(divisor)
                                                            .withResult(dividend, divisor)
                                                            .withDivisionSteps(steps)
                                                            .build();
        final String actual = viewProvider.provideView(divisionResult);
        final String expected = "_15|3539\n  0|-\n --|0\n 15";
        
        assertThat(actual, is(equalTo(expected)));
    }
    
    @Test
    void provideViewShouldReturnCorrectViewIfDividentIsZero() {
        final int dividend = 0;
        final int divisor = 20;
        List<DivisionStep> steps = new ArrayList<DivisionStep>();
        
        steps.add(DivisionStep.builder()
                              .withDividend(0)
                              .withRemainder(0, divisor)
                              .withSubtract(divisor)
                              .withResult(0, divisor)
                              .withNumOfLeadingZeros(0)
                              .build());

        final DivisionResult divisionResult = DivisionResult.builder()
                                                            .withDividend(dividend)
                                                            .withDivisor(divisor)
                                                            .withResult(dividend, divisor)
                                                            .withDivisionSteps(steps)
                                                            .build();
        final String actual = viewProvider.provideView(divisionResult);
        final String expected = "_0|20\n 0|-\n -|0\n 0";
        
        assertThat(actual, is(equalTo(expected)));
    }
    
    @Test
    void provideViewShouldReturnCorrectViewIfDividentHasZeros() {
        final int dividend = 405022500;
        final int divisor = 45;
        List<DivisionStep> steps = new ArrayList<DivisionStep>();
        
        steps.add(DivisionStep.builder()
                              .withDividend(405)
                              .withRemainder(405, divisor)
                              .withResult(405, divisor)
                              .withSubtract(divisor)
                              .withNumOfLeadingZeros(0)
                              .build());
        steps.add(DivisionStep.builder()
                              .withDividend(225)
                              .withRemainder(225, divisor)
                              .withResult(225, divisor)
                              .withSubtract(divisor)
                              .withNumOfLeadingZeros(1)
                              .build());

        final DivisionResult divisionResult = DivisionResult.builder()
                                                            .withDividend(dividend)
                                                            .withDivisor(divisor)
                                                            .withResult(dividend, divisor)
                                                            .withDivisionSteps(steps)
                                                            .build();
        final String actual = viewProvider.provideView(divisionResult);
        final String expected = "_405022500|45\n 405      |-------\n ---      |9000500\n   _0225\n     225\n    ----\n       0";
        
        assertThat(actual, is(equalTo(expected)));
    }

}
