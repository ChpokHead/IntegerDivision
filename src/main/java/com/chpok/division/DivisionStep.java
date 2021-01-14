package com.chpok.division;

public class DivisionStep {
    private int divident;
    private int divisor;
    private int result;
    private int remainder;
    
    public DivisionStep(int divident, int divisor, int result, int reminder) {
        this.divident = divident;
        this.divisor = divisor;
        this.result = result;
        this.remainder = reminder;
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
    public int getRemainder() {
        return remainder;
    }   
    
}
