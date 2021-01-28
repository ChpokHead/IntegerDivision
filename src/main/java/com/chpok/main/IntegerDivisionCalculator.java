package com.chpok.main;

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
    
    public String calculate(int divident, int divisor) {
        validationProvider.validate(divident, divisor);
        
        DivisionResult result = mathProvider.provideMathCalculation(divident, divisor);
        
        return viewProvider.provideView(result);
    }
}
