package com.chpok.division;

import com.chpok.division.formatter.DivisionViewProvider;
import com.chpok.division.formatter.DivisionViewProviderImpl;
import com.chpok.division.provider.DivisionMathProvider;
import com.chpok.division.provider.DivisionMathProviderImpl;
import com.chpok.validation.Validator;
import com.chpok.validation.ValidationProvider;

public class IntegerDivisionConsoleApplication {

    public static void main(String[] args) {
        ValidationProvider validationProvider = new Validator();
        DivisionViewProvider viewProvider = new DivisionViewProviderImpl();
        DivisionMathProvider mathProvider = new DivisionMathProviderImpl();
        
        IntegerDivisionCalculator calculator = new IntegerDivisionCalculator(validationProvider, viewProvider, mathProvider);
        
        System.out.println(calculator.calculate(0, 20));
    }

}
