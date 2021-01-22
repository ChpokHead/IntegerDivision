package com.chpok.main;

import com.chpok.division.DivisionMathProvider;
import com.chpok.division.DivisionResult;
import com.chpok.formating.DivisionViewProvider;
import com.chpok.validation.ValidationProvider;

public class IntegerDivisionCalculator {
    private final ValidationProvider validationProvider;
    private final DivisionViewProvider viewProvider;
    private final DivisionMathProvider mathProvider;
    
    public IntegerDivisionCalculator(ValidationProvider validationProvider, DivisionViewProvider viewProvider,
            DivisionMathProvider mathProvider) {
        this.validationProvider = validationProvider;
        this.viewProvider = viewProvider;
        this.mathProvider = mathProvider;
    }
    
    public String calculate(int divident, int divisor) {
        validationProvider.validate(divisor);
        
        DivisionResult result = mathProvider.provideMathCalculation(divident, divisor);
        
        return viewProvider.provideView(result);
    }
}
