package com.chpok.division;

import com.chpok.division.domain.DivisionResult;
import com.chpok.division.formatter.DivisionViewProvider;
import com.chpok.division.provider.DivisionMathProvider;
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
    
    public String calculate(int dividend, int divisor) {
        validationProvider.validate(dividend, divisor);
        
        DivisionResult result = mathProvider.provideMathCalculation(dividend, divisor);
        
        return viewProvider.provideView(result);
    }
}
