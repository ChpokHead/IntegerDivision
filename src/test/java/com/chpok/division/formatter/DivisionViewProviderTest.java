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
  
        final DivisionResult divisionResult = DivisionResult.builder()
                                                .withDivident(divident)
                                                .withDivisor(divisor)
                                                .withResult(divident, divisor)
                                                .withDivisionSteps(steps)
                                                .build();
        final String actual = viewProvider.provideView(divisionResult);
        final String expected = "_1432|12\n 12  |---\n --  |119\n _23\n  12\n  --\n _112\n  108\n  ---\n    4";
        
        assertThat(actual, is(equalTo(expected)));
    }
    
    @Test
    void provideViewShouldReturnCorrectViewIfDividentIsLessThanDivisor() {
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

        final DivisionResult divisionResult = DivisionResult.builder()
                                                      .withDivident(divident)
                                                      .withDivisor(divisor)
                                                      .withResult(divident, divisor)
                                                      .withDivisionSteps(steps)
                                                      .build();
        final String actual = viewProvider.provideView(divisionResult);
        final String expected = "_15|3539\n  0|-\n --|0\n 15";
        
        assertThat(actual, is(equalTo(expected)));
    }
    
    @Test
    void provideViewShouldReturnCorrectViewIfDividentIsZero() {
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

        final DivisionResult divisionResult = DivisionResult.builder()
                                                      .withDivident(divident)
                                                      .withDivisor(divisor)
                                                      .withResult(divident, divisor)
                                                      .withDivisionSteps(steps)
                                                      .build();
        final String actual = viewProvider.provideView(divisionResult);
        final String expected = "_0|20\n 0|-\n -|0\n 0";
        
        assertThat(actual, is(equalTo(expected)));
    }
    
    @Test
    void provideViewShouldReturnCorrectViewIfDividentHasZeros() {
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

        final DivisionResult divisionResult = DivisionResult.builder()
                                                      .withDivident(divident)
                                                      .withDivisor(divisor)
                                                      .withResult(divident, divisor)
                                                      .withDivisionSteps(steps)
                                                      .build();
        final String actual = viewProvider.provideView(divisionResult);
        final String expected = "_405022500|45\n 405      |-------\n ---      |9000500\n   _0225\n     225\n    ----\n       0";
        
        assertThat(actual, is(equalTo(expected)));
    }

}
