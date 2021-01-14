package com.chpok.division.provider;

import java.util.ArrayList;
import java.util.List;

import com.chpok.division.domain.DivisionResult;
import com.chpok.division.domain.DivisionStep;

public class DivisionMathProviderImpl implements DivisionMathProvider {
    private static final char ZERO = '0';
    
    private class CalcUnit {
        int subDividend;
        int stepCount;
        int subDividendPos;
        int numOfLeadingZeros;        
    }
    
    @Override
    public DivisionResult provideMathCalculation(int dividend, int divisor) {
        CalcUnit calcUnit = new CalcUnit();
        List<DivisionStep> divisionSteps = new ArrayList<>();
        DivisionResult result = DivisionResult.builder()
                                              .withDividend(dividend)
                                              .withDivisor(divisor)
                                              .withResult(dividend, divisor)
                                              .withDivisionSteps(divisionSteps)
                                              .build();
        calcUnit.subDividend = pickDividendFromNumber(dividend, divisor);
    
        addDivisionStep(calcUnit, result);
        
        calcUnit.subDividendPos = getNumberLength(calcUnit.subDividend);
        
        populateDivisionSteps(calcUnit, result);
                
        return result;
    }
    
    private void populateDivisionSteps(CalcUnit calcUnit, DivisionResult result) {
        while (!isPosReachedDividendSize(calcUnit.subDividendPos, result.getDividend())) {
            calcUnit.stepCount++;
            calcUnit.numOfLeadingZeros = getNumOfLeadingZeros(calcUnit.subDividendPos, result.getDividend());
            
            if (calcUnit.numOfLeadingZeros > 0) {
                if (isPosReachedDividendSize(calcUnit.numOfLeadingZeros + calcUnit.subDividendPos, result.getDividend())) {
                    break;
                }
                
                updateCalcUnitIfHasLeadingZeros(calcUnit, result);
                
            } else {
                updateCalcUnitWithoutLeadingZeros(calcUnit, result);
            }
            
            addDivisionStep(calcUnit, result);
        }
    }
    
    private void addDivisionStep(CalcUnit calcUnit, DivisionResult result) {
        result.getDivisionSteps().add(DivisionStep.builder()
                .withDividend(calcUnit.subDividend)
                .withResult(calcUnit.subDividend, result.getDivisor())
                .withSubtract(result.getDivisor())
                .withRemainder(calcUnit.subDividend, result.getDivisor())
                .withNumOfLeadingZeros(calcUnit.numOfLeadingZeros)
                .build());
    }
    
    private int getNumberLength(int number) {        
        return Integer.toString(number).length();
    }
    
    private boolean isPosReachedDividendSize(int numSize, int dividend) {
        return numSize < getNumberLength(dividend) ? false : true;
    }
    
    private int pickDividendFromNumber(int number, int divisor) {
        int digitPos = 0;
        int dividend = getDigitFromNumber(digitPos, number);
        
        while (dividend < divisor && digitPos != getNumberLength(number) - 1) {
            digitPos++;
            dividend = joinDigits(dividend, getDigitFromNumber(digitPos, number));
        }
        
        return dividend;
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
    
    private int getNumOfLeadingZeros(int pos, int dividend) {
        String dividendAsString = Integer.toString(dividend);
        int count = 0;
        
        while (pos < dividendAsString.length() && dividendAsString.charAt(pos) == ZERO) {
            count++;
            pos++;
        }
        
        return count;
    }
    
    private int getSubDivIfDivHasLeadingZeros(int subDivPos, int dividend, int divisor) {
        int subDividend = trimNumberFromPos(subDivPos, dividend);
        
        return pickDividendFromNumber(subDividend, divisor);
    }
    
    private boolean isLastRemainderWasZero(int stepCount, List<DivisionStep> divisionSteps) {
        return divisionSteps.get(stepCount - 1).getRemainder() == 0;
    }
    
    private int getNewSubDividend(int subDiv, int stepCount, List<DivisionStep> steps, int divisor) {
        if (!isLastRemainderWasZero(stepCount, steps)) {
            subDiv = joinDigits(steps.get(stepCount - 1).getRemainder(), subDiv);
        }

        return pickDividendFromNumber(subDiv, divisor);
    }
    
    private int updateSubDivPos(int subDiv, int stepCount, List<DivisionStep> steps) {
        return isLastRemainderWasZero(stepCount, steps) ? getNumberLength(subDiv) : getNumberLength(subDiv) - getNumberLength(steps.get(stepCount - 1).getRemainder());
    }
    
    private void updateCalcUnitIfHasLeadingZeros(CalcUnit calcUnit, DivisionResult result) {
        calcUnit.subDividend = getSubDivIfDivHasLeadingZeros(calcUnit.subDividendPos, result.getDividend(), result.getDivisor());
        calcUnit.subDividendPos += getNumberLength(calcUnit.subDividend) + calcUnit.numOfLeadingZeros;
    }
    
    private void updateCalcUnitWithoutLeadingZeros(CalcUnit calcUnit, DivisionResult result) {
        calcUnit.subDividend = trimNumberFromPos(calcUnit.subDividendPos, result.getDividend());
        calcUnit.subDividend = getNewSubDividend(calcUnit.subDividend, calcUnit.stepCount, result.getDivisionSteps(), result.getDivisor());    
        calcUnit.subDividendPos += updateSubDivPos(calcUnit.subDividend, calcUnit.stepCount, result.getDivisionSteps());
    }
}

