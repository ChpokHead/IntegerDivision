package com.chpok.division;

import java.util.ArrayList;
import java.util.List;

public class DivisionMathProviderImpl implements DivisionMathProvider {
    
    @Override
    public DivisionResult provideMathCalculation(int divident, int divisor) {
        
        DivisionResult result = new DivisionResult(divident, divisor);
        List<DivisionStep> divisionSteps = new ArrayList<>();
        int stepCount = 0;
        int numOfLeadingZeros = 0;

        divident = reverseNumberIfNegative(divident);
        divisor = reverseNumberIfNegative(divisor);
        
        int subDivident = pickDividentFromNumber(divident, divisor);
        int dividentLength = getNumberLength(divident);
    
        divisionSteps.add(new DivisionStep(subDivident, divisor, numOfLeadingZeros));
        
        int subDivPos = getNumberLength(subDivident);
        
        while (subDivPos < dividentLength) {
            stepCount++;

            numOfLeadingZeros = getNumOfLeadingZeros(subDivPos, divident);
            
            if (numOfLeadingZeros > 0) {
                if (numOfLeadingZeros + subDivPos == dividentLength) {
                    break;
                }
                
                subDivident = getSubDivIfDivHasLeadingZeros(subDivPos, divident, divisor);
                subDivPos += getNumberLength(subDivident) + numOfLeadingZeros;
            } else {
                subDivident = trimNumberFromPos(subDivPos, divident);
                subDivident = getNewSubDivident(subDivident, stepCount, divisionSteps, divisor);    
                subDivPos += updateSubDivPos(subDivident, stepCount, divisionSteps);
            }
            
            divisionSteps.add(new DivisionStep(subDivident, divisor, numOfLeadingZeros));
        }
        
        result.setDivisionSteps(divisionSteps);
        
        return result;
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
    
    private int reverseNumberIfNegative(int number) {
        return number < 0 ? -number : number;
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
    
    private int getSubDivIfDivHasLeadingZeros(int subDivPos, int divident, int divisor) {
        int subDivident = trimNumberFromPos(subDivPos, divident);
        
        return pickDividentFromNumber(subDivident, divisor);
    }
    
    private boolean isLastRemainderWasZero(int stepCount, List<DivisionStep> divisionSteps) {
        return divisionSteps.get(stepCount - 1).getRemainder() == 0;
    }
    
    private int getNewSubDivident(int subDiv, int stepCount, List<DivisionStep> steps, int divisor) {
        if (!isLastRemainderWasZero(stepCount, steps)) {
            subDiv = joinDigits(steps.get(stepCount - 1).getRemainder(), subDiv);
        }

        return pickDividentFromNumber(subDiv, divisor);
    }
    
    private int updateSubDivPos(int subDiv, int stepCount, List<DivisionStep> steps) {
        if (!isLastRemainderWasZero(stepCount, steps)) {
            return getNumberLength(subDiv) - getNumberLength(steps.get(stepCount - 1).getRemainder());
        } else {
            return getNumberLength(subDiv);
        }
    }
    
}

