package com.chpok.validation;

public class ValidationPorviderImpl implements ValidationProvider{

    @Override
    public void validate(int divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException("Divisor can't be zero!");
        }
    }

}
