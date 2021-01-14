package com.chpok.validation;

public class Validator implements ValidationProvider{

    @Override
    public void validate(int dividend, int divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException("Divisor is zero!");
        }
        
        if (dividend < 0) {
            throw new IllegalArgumentException("Dividend is negative! Dividend value: " + dividend);
        }
        
        if (divisor < 0) {
            throw new IllegalArgumentException("Divisor is negative! Divisor value: " + divisor);
        }
    }

}
