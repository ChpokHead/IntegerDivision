package com.chpok.division;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.chpok.division.domain.DivisionResult;
import com.chpok.division.domain.DivisionStep;
import com.chpok.division.formatter.DivisionViewProvider;
import com.chpok.division.provider.DivisionMathProvider;
import com.chpok.validation.ValidationProvider;

@ExtendWith(MockitoExtension.class)
class IntegerDivisionCalculatorTest {
    @Mock
    private ValidationProvider validationProvider;
    @Mock
    private DivisionMathProvider mathProvider;
    @Mock
    private DivisionViewProvider viewProvider;
    @InjectMocks
    private IntegerDivisionCalculator calculator;
    
    @Test
    void calculateShouldReturnCorrectDivisionIfDividendAndDivisorArePositive() {
        final int dividend = 1432;
        final int divisor = 12;
        final List<DivisionStep> steps = new ArrayList<DivisionStep>();
        
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
 
        final DivisionResult divResult = DivisionResult.builder()
                                                           .withDividend(dividend)
                                                           .withDivisor(divisor)
                                                           .withResult(dividend, divisor)
                                                           .withDivisionSteps(steps)
                                                           .build();
        
        when(mathProvider.provideMathCalculation(dividend, divisor)).thenReturn(divResult);
        
        when(viewProvider.provideView(divResult)).thenReturn("_1432|12\n 12  |---\n --  |119\n _23\n  12\n  --\n _112\n  108\n  ---\n    4");
        
        final String expected = "_1432|12\n 12  |---\n --  |119\n _23\n  12\n  --\n _112\n  108\n  ---\n    4";
        final String actual = calculator.calculate(dividend, divisor);
        
        assertThat(actual, is(equalTo(expected)));
    }
    
    @Test
    void calculateShouldThrowIllegalArgumentExceptionWhenArgumentsAreIncorrect() {
        final int dividend = -5;
        final int divisor = 12;
        final String expected = "Dividend is negative! Dividend value: -5";

        doThrow(new IllegalArgumentException(expected)).when(validationProvider).validate(dividend, divisor);
        
        final Exception exception = assertThrows(IllegalArgumentException.class, () -> calculator.calculate(dividend, divisor));
        final String actual = exception.getMessage();
        
        assertThat(actual, is(equalTo(expected)));
    }
    
}
