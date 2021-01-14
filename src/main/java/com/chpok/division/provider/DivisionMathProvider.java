package com.chpok.division.provider;

import com.chpok.division.domain.DivisionResult;

public interface DivisionMathProvider {
    public DivisionResult provideMathCalculation(int dividend, int divisor);
}
