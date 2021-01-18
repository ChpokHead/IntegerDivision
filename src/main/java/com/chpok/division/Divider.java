package com.chpok.division;

import java.util.ArrayList;
import java.util.List;

public class Divider {
    public DivisionResult divide(int divident, int divisor) {
        validate(divisor);
        
        List<DivisionStep> divisionSteps = new ArrayList<DivisionStep>();
        int stepCount = 0;
        int numOfLeadingZeros = 0;
        boolean isResultNegative = false;

        if (divident < 0) {
            divident = -divident;
            isResultNegative = true;
        }
        
        if (divisor < 0) {
            divisor = -divisor;
            isResultNegative = true;
        }
        
        int subDivident = pickDividentFromNumber(divident, divisor);
        int dividentLength = getNumberLength(divident);
    
        
        divisionSteps.add(populateDivisionStep(subDivident, divisor, numOfLeadingZeros));
        
        int i = getNumberLength(subDivident);
        
        while (i < dividentLength) {
            numOfLeadingZeros = getNumOfLeadingZeros(i, divident);
            
            stepCount++;
            
            if (numOfLeadingZeros > 0) {
                String dividentAsString = Integer.toString(divident).substring(i);
                
                if (divisionSteps.get(stepCount - 1).getRemainder() != 0) {
                    numOfLeadingZeros = 0;
                    dividentAsString = divisionSteps.get(stepCount - 1).getRemainder() + dividentAsString;
                    subDivident = Integer.parseInt(dividentAsString);
                    subDivident = pickDividentFromNumber(subDivident, divisor);
                    
                    i += getNumberLength(subDivident) - getNumberLength(divisionSteps.get(stepCount - 1).getRemainder());
                } else {
                    subDivident = Integer.parseInt(dividentAsString);
                    subDivident = pickDividentFromNumber(subDivident, divisor);
                    
                    i += getNumberLength(subDivident) + numOfLeadingZeros;
                }        
            } else {
                subDivident = trimNumberFromPos(i, divident);
            
                if (divisionSteps.get(stepCount - 1).getRemainder() != 0) {
                    subDivident = joinDigits(divisionSteps.get(stepCount - 1).getRemainder(), subDivident);
                    subDivident = pickDividentFromNumber(subDivident, divisor);
                
                    i += getNumberLength(subDivident) - getNumberLength(divisionSteps.get(stepCount - 1).getRemainder());
                } else {
                    subDivident = pickDividentFromNumber(subDivident, divisor);
                
                    i += getNumberLength(subDivident);
                }   
            }
            
            divisionSteps.add(populateDivisionStep(subDivident, divisor, numOfLeadingZeros));
        }
        
        return new DivisionResult(divident, divisor, divisionSteps, isResultNegative);
    }
    
    private void validate(int divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException("Divisor cannot be zero!");
        }
    }
    
    private int getNumberLength(int number) {        
        return Integer.toString(number).length();
    }
    
    private int pickDividentFromNumber(int number, int divisor) {
        int digitPos = 0;
        int divident = getDigitFromNumber(digitPos, number);
        
        while (divident < divisor && digitPos != getNumberLength(number) - 1) {
            digitPos++;
            divident = joinDigits(divident, getDigitFromNumber(digitPos, number));
        }
        
        return divident;
    }
    
    private int getDigitFromNumber(int digitPos, int number) {
        return Integer.parseInt(Integer.toString(number).substring(digitPos, digitPos + 1));
    }
    
    private int joinDigits(int firstDigit, int secondDigit) {        
        return Integer.parseInt(Integer.toString(firstDigit) + Integer.toString(secondDigit));
    }
    
    private int trimNumberFromPos(int pos, int number) {        
        return Integer.parseInt(Integer.toString(number).substring(pos));
    }
    
    private int getNumOfLeadingZeros(int pos, int divident) {
        String dividentAsString = Integer.toString(divident);
        int count = 0;
        
        while (dividentAsString.charAt(pos) == '0' && pos < dividentAsString.length() - 1) {
            count++;
            pos++;
        }
        
        return count;
    }
    
    private DivisionStep populateDivisionStep(int divident, int divisor, int numOfLeadingZeros) {
        DivisionStep step = new DivisionStep();
        
        step.setDivident(divident);
        step.setResult(divident / divisor);
        step.setSubtract(divisor * step.getResult());
        step.setRemainder(divident % divisor);
        step.setNumOfLeadingZeros(numOfLeadingZeros);
        return step;
    }
    
}
