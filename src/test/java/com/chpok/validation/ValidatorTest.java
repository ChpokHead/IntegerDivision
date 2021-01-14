package com.chpok.validation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

class ValidatorTest {
    
    private final ValidationProvider validation = new Validator(); 

    @Test
    void validateShouldThrowIllegalArgumentExceptionIfDivisorIsZero() {
        final int dividend = 1;
        final int divisor = 0;
        final Exception exception = assertThrows(IllegalArgumentException.class, () -> validation.validate(dividend, divisor));
        
        final String expected = "Divisor is zero!";
        final String actual = exception.getMessage();
        
        assertThat(actual, is(equalTo(expected)));
    }
    
    @Test
    void validateShouldThrowIllegalArgumentExceptionIfDividentIsNegative() {
        final int dividend = -5;
        final int divisor = 10;
        final Exception exception = assertThrows(IllegalArgumentException.class, () -> validation.validate(dividend, divisor));
        
        final String expected = "Dividend is negative! Dividend value: " + dividend;
        final String actual = exception.getMessage();
        
        assertThat(actual, is(equalTo(expected)));
    }
    
    @Test
    void validateShouldThrowIllegalArgumentExceptionIfDivisorIsNegative() {
        final int dividend = 50;
        final int divisor = -10;
        final Exception exception = assertThrows(IllegalArgumentException.class, () -> validation.validate(dividend, divisor));
        
        final String expected = "Divisor is negative! Divisor value: " + divisor;
        final String actual = exception.getMessage();
        
        assertThat(actual, is(equalTo(expected)));
    }
    
    @Test
    void validateShouldNotThrowExceptionWhenDivisorIsNotZeroAndPositiveWithDivident() {
        final int dividend = 100;
        final int divisor = 20;
        
        assertDoesNotThrow(() -> validation.validate(dividend, divisor));
    }

}
