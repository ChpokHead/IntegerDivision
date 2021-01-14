package com.chpok.division.domain;

import java.util.Objects;

public class DivisionStep {
    private final int dividend;
    private final int subtract;
    private final int result;
    private final int remainder;
    private final int numOfLeadingZeros;
    
    private DivisionStep(Builder builder) {
        dividend = builder.dividend;
        subtract = builder.subtract;
        result = builder.result;
        remainder = builder.remainder;
        numOfLeadingZeros = builder.numOfLeadingZeros;
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    public int getDividend() {
        return dividend;
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
        return getDividend() + " " + getSubtract() + " " + getRemainder() + " " + getResult(); 
    }

    @Override
    public int hashCode() {
        return Objects.hash(dividend, subtract, result, remainder, numOfLeadingZeros);
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
        return Objects.equals(dividend, step.dividend) &&
               Objects.equals(subtract, step.subtract) &&
               Objects.equals(result, step.result) &&
               Objects.equals(remainder, step.remainder) &&
               Objects.equals(numOfLeadingZeros, step.numOfLeadingZeros);
    }
    
    public static class Builder {
        private int dividend;
        private int subtract;
        private int result;
        private int remainder;
        private int numOfLeadingZeros;
        
        private Builder() {}
        
        public Builder withDividend(int dividend) {
            this.dividend = dividend;
            return this;
        }
        
        public Builder withSubtract(int divisor) {
            this.subtract = divisor * this.result;        
            return this;
        }
        
        public Builder withResult(int dividend, int divisor) {
            this.result = dividend / divisor;
            return this;
        }
        
        public Builder withRemainder(int dividend, int divisor) {
            this.remainder = dividend % divisor;
            return this;
        }
        
        public Builder withNumOfLeadingZeros(int numOfLeadingZeros) {
            this.numOfLeadingZeros = numOfLeadingZeros;
            return this;
        }
        
        public DivisionStep build() {
            return new DivisionStep(this);
        }
    }
    
}
