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
        
        final DivisionResult divisionResult = new DivisionResult.Builder()
                                            .setDivident(divident)
                                            .setDivisor(divisor)
                                            .setResult(divident, divisor)
                                            .setDivisionSteps(steps)
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
        
        steps.add(new DivisionStep.Builder()
                      .setDivident(15)
                      .setRemainder(15, divisor)
                      .setSubtract(divisor)
                      .setResult(15, divisor)
                      .setNumOfLeadingZeros(0)
                      .build());
        
        final DivisionResult divisionResult = new DivisionResult.Builder()
                                            .setDivident(divident)
                                            .setDivisor(divisor)
                                            .setResult(divident, divisor)
                                            .setDivisionSteps(steps)
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
        
        steps.add(new DivisionStep.Builder()
                      .setDivident(0)
                      .setRemainder(0, divisor)
                      .setSubtract(divisor)
                      .setResult(0, divisor)
                      .setNumOfLeadingZeros(0)
                      .build());
        
        final DivisionResult divisionResult = new DivisionResult.Builder()
                                            .setDivident(divident)
                                            .setDivisor(divisor)
                                            .setResult(divident, divisor)
                                            .setDivisionSteps(steps)
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
        
        final DivisionResult divisionResult = new DivisionResult.Builder()
                                            .setDivident(divident)
                                            .setDivisor(divisor)
                                            .setResult(divident, divisor)
                                            .setDivisionSteps(steps)
                                            .build();
        final String actual = viewProvider.provideView(divisionResult);
        final String expected = "_405022500|45\n 405      |-------\n ---      |9000500\n   _0225\n     225\n    ----\n       0";
        
        assertThat(actual, is(equalTo(expected)));
    }

}
