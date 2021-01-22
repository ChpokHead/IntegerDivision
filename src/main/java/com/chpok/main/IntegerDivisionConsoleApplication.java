package com.chpok.main;

import com.chpok.division.DivisionMathProvider;
import com.chpok.division.DivisionMathProviderImpl;
import com.chpok.formating.DivisionViewProvider;
import com.chpok.formating.DivisionViewProviderImpl;
import com.chpok.validation.ValidationPorviderImpl;
import com.chpok.validation.ValidationProvider;

public class IntegerDivisionConsoleApplication {

    public static void main(String[] args) {
        ValidationProvider validationProvider = new ValidationPorviderImpl();
        DivisionViewProvider viewProvider = new DivisionViewProviderImpl();
        DivisionMathProvider mathProvider = new DivisionMathProviderImpl();
        
        IntegerDivisionCalculator calculator = new IntegerDivisionCalculator(validationProvider, viewProvider, mathProvider);
        
        System.out.println(calculator.calculate(1432, 12));
    }

}
