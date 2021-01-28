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
        final int divident = 1;
        final int divisor = 0;
        final Exception exception = assertThrows(IllegalArgumentException.class, () -> validation.validate(divident, divisor));
        
        final String expected = "Divisor can't be zero!";
        final String actual = exception.getMessage();
        
        assertThat(actual, is(equalTo(expected)));
    }
    
    @Test
    void validateShouldThrowIllegalArgumentExceptionIfDividentIsNegative() {
        final int divident = -5;
        final int divisor = 10;
        final Exception exception = assertThrows(IllegalArgumentException.class, () -> validation.validate(divident, divisor));
        
        final String expected = "Divident and divisor must be positive!";
        final String actual = exception.getMessage();
        
        assertThat(actual, is(equalTo(expected)));
    }
    
    @Test
    void validateShouldThrowIllegalArgumentExceptionIfDivisorIsNegative() {
        final int divident = 50;
        final int divisor = -10;
        final Exception exception = assertThrows(IllegalArgumentException.class, () -> validation.validate(divident, divisor));
        
        final String expected = "Divident and divisor must be positive!";
        final String actual = exception.getMessage();
        
        assertThat(actual, is(equalTo(expected)));
    }
    
    @Test
    void validateShouldThrowIllegalArgumentExceptionIfDividentAndDivisorAreNegative() {
        final int divident = -50;
        final int divisor = -10;
        final Exception exception = assertThrows(IllegalArgumentException.class, () -> validation.validate(divident, divisor));
        
        final String expected = "Divident and divisor must be positive!";
        final String actual = exception.getMessage();
        
        assertThat(actual, is(equalTo(expected)));
    }
    
    @Test
    void validateShouldNotThrowExceptionWhenDivisorIsNotZeroAndPositiveWithDivident() {
        final int divident = 100;
        final int divisor = 20;
        
        assertDoesNotThrow(() -> validation.validate(divident, divisor));
    }

}
