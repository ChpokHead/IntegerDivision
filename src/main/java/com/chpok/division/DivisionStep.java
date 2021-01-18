package com.chpok.division;

public class DivisionStep {
    private int divident;
    private int subtract;
    private int result;
    private int remainder;
    private int numOfLeadingZeros;
    
    public DivisionStep(int divident, int divisor, int numOfLeadingZeros) {
        this.divident = divident;
        this.result = divident / divisor;
        this.subtract = divisor * result;
        this.remainder = divident % divisor;
        this.numOfLeadingZeros = numOfLeadingZeros;
    }
    
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
        final int prime = 31;
        int result = 1;
        result = prime * result + divident;
        result = prime * result + numOfLeadingZeros;
        result = prime * result + remainder;
        result = prime * result + this.result;
        result = prime * result + subtract;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DivisionStep other = (DivisionStep) obj;
        if (divident != other.divident)
            return false;
        if (numOfLeadingZeros != other.numOfLeadingZeros)
            return false;
        if (remainder != other.remainder)
            return false;
        if (result != other.result)
            return false;
        if (subtract != other.subtract)
            return false;
        return true;
    }
    
}
