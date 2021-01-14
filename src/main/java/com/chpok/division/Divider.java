package com.chpok.division;

import java.util.ArrayList;
import java.util.List;

public class Divider {
    public void divide(int divident, int divisor) {
        validate(divisor);
        
        List<DivisionStep> divisionSteps = new ArrayList<DivisionStep>();
    }
    
    private void validate(int divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException("Divisor cannot be zero!");
        }
    }
    
}
