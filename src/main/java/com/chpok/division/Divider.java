package com.chpok.division;

import java.util.ArrayList;
import java.util.List;

public class Divider {
    public DivisionResult divide(int divident, int divisor) {
        validate(divisor);
        
        DivisionResult result = new DivisionResult(divident, divisor);
        List<DivisionStep> divisionSteps = new ArrayList<>();
        int stepCount = 0;
        int numOfLeadingZeros = 0;

        if (divident < 0) {
            divident = -divident;
        }
        
        if (divisor < 0) {
            divisor = -divisor;
        }
        
        int subDivident = pickDividentFromNumber(divident, divisor);
        int dividentLength = getNumberLength(divident);
    
        divisionSteps.add(new DivisionStep(subDivident, divisor, numOfLeadingZeros));
        
        int i = getNumberLength(subDivident);
        
        while (i < dividentLength) {
            stepCount++;

            numOfLeadingZeros = getNumOfLeadingZeros(i, divident);
            
            if (numOfLeadingZeros > 0) {
                
                String dividentAsString = Integer.toString(divident).substring(i);
                
                if (divisionSteps.get(stepCount - 1).getRemainder() != 0) {
                    numOfLeadingZeros = 0;
                    dividentAsString = divisionSteps.get(stepCount - 1).getRemainder() + dividentAsString;
                    subDivident = Integer.parseInt(dividentAsString);
                    subDivident = pickDividentFromNumber(subDivident, divisor);
                    
                    i += getNumberLength(subDivident) - getNumberLength(divisionSteps.get(stepCount - 1).getRemainder());
                } else {
                    if (numOfLeadingZeros + i == dividentLength) {
                        break;
                    }
                    
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
            
            divisionSteps.add(new DivisionStep(subDivident, divisor, numOfLeadingZeros));
        }
        
        for (DivisionStep step : divisionSteps) {
            System.out.println(step);
        }
        
        result.setDivisionSteps(divisionSteps);
        return result;
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
        
        while (pos < dividentAsString.length() && dividentAsString.charAt(pos) == '0') {
            count++;
            pos++;
        }
        
        return count;
    }
    
}
