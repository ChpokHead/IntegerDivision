package com.chpok.validation;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

class ValidationProviderTest {
    
    private final ValidationProvider validation = new ValidationPorviderImpl(); 

    @Test
    void validateShouldThrowIllegalArgumentExceptionIfDivisorIsZero() {
        final Exception exception = assertThrows(IllegalArgumentException.class, () -> validation.validate(0));
        
        final String expected = "Divisor can't be zero!";
        final String actual = exception.getMessage();
        
        assertThat(actual, is(equalTo(expected)));
    }

}
