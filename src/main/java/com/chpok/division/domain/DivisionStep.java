package com.chpok.division.domain;

import java.util.Objects;

public class DivisionStep {
    private int divident;
    private int subtract;
    private int result;
    private int remainder;
    private int numOfLeadingZeros;
    
    public int getDivident() {
        return divident;
    }
    
    public int getSubtract() {
        return subtract;
    }
    
    public int getResult() {
        return result;
    }
    
    public int getRemainder() {
        return remainder;
    }   
    
    public int getNumOfLeadingZeros() {
        return numOfLeadingZeros;
    }

    @Override
    public String toString() {
        return getDivident() + " " + getSubtract() + " " + getRemainder() + " " + getResult(); 
    }

    @Override
    public int hashCode() {
        return Objects.hash(divident, subtract, result, remainder, numOfLeadingZeros);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DivisionStep step = (DivisionStep) o;
        return Objects.equals(divident, step.divident) &&
               Objects.equals(subtract, step.subtract) &&
               Objects.equals(result, step.result) &&
               Objects.equals(remainder, step.remainder) &&
               Objects.equals(numOfLeadingZeros, step.numOfLeadingZeros);
    }
    
    public static class Builder {
        private DivisionStep step;
        
        public Builder() {
            step = new DivisionStep();
        }
        
        public Builder setDivident(int divident) {
            step.divident = divident;
            return this;
        }
        
        public Builder setSubtract(int divisor) {
            step.subtract = divisor * step.result;        
            return this;
        }
        
        public Builder setResult(int divident, int divisor) {
            step.result = divident / divisor;
            return this;
        }
        
        public Builder setRemainder(int divident, int divisor) {
            step.remainder = divident % divisor;
            return this;
        }
        
        public Builder setNumOfLeadingZeros(int numOfLeadingZeros) {
            step.numOfLeadingZeros = numOfLeadingZeros;
            return this;
        }
        
        public DivisionStep build() {
            return step;
        }
    }
    
}
