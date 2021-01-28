package com.chpok.validation;

public class Validator implements ValidationProvider{

    @Override
    public void validate(int divident, int divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException("Divisor can't be zero!");
        }
        if (divident < 0 || divisor < 0) {
            throw new IllegalArgumentException("Divident and divisor must be positive!");
        }
    }

}
