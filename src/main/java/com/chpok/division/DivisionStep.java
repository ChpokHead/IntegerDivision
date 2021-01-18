package com.chpok.division;

public class DivisionStep {
    private int divident;
    private int subtract;
    private int result;
    private int remainder;
    private int numOfLeadingZeros;
    
    public void setDivident(int divident) {
        this.divident = divident;
    }
    
    public void setSubtract(int subtract) {
        this.subtract = subtract;
    }
    
    public void setResult(int result) {
        this.result = result;
    }
    
    public void setRemainder(int remainder) {
        this.remainder = remainder;
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

    public void setNumOfLeadingZeros(int numOfLeadingZeros) {
        this.numOfLeadingZeros = numOfLeadingZeros;
    }
    
    @Override
    public String toString() {
        return getDivident() + " " + getSubtract() + " " + getRemainder() + " " + getResult(); 
    }
    
}
