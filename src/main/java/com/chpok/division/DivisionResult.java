package com.chpok.division;

import java.util.List;

public class DivisionResult {
    private int divident;
    private int divisor;
    private int result;
    
    private List<DivisionStep> divisionSteps;
    
    public DivisionResult(int divident, int divisor, List<DivisionStep> divisionSteps, boolean isResultNegative) {
        this.divident = divident;
        this.divisor = divisor;
        this.result = divident / divisor;
        
        if (isResultNegative) {
            this.result = -this.result;
        }
        
        this.divisionSteps = divisionSteps;
    }

    public int getDivident() {
        return divident;
    }

    public int getDivisor() {
        return divisor;
    }
    
    public int getResult() {
        return result;
    }

    public List<DivisionStep> getDivisionSteps() {
        return divisionSteps;
    } 
    
}
