package com.chpok.division.formatter;

import java.util.List;

import com.chpok.division.domain.DivisionResult;
import com.chpok.division.domain.DivisionStep;

public class DivisionViewProviderImpl implements DivisionViewProvider{
    
    private static final String UNDERSCORE = "_";
    private static final String PIPE = "|";
    private static final String DASH = "-";
    
    private int indents[];
    
    @Override
    public String provideView(DivisionResult divisionResult) {     
        indents = new int[divisionResult.getDivisionSteps().size() + 1];
        
        return drawHeader(divisionResult) + drawRestLines(divisionResult); 
    }
    
    private String drawHeader(DivisionResult divisionResult) {
        return drawFirstLine(divisionResult) + drawSecondLine(divisionResult) + drawThirdLine(divisionResult);
    }
    
    private String drawFirstLine(DivisionResult divisionResult) {
        return UNDERSCORE + makeNumberPositive(divisionResult.getDivident()) + PIPE 
                + makeNumberPositive(divisionResult.getDivisor()) + "\n";
    }
    
    private String drawSecondLine(DivisionResult divisionResult) {
        List<DivisionStep> divisionSteps = divisionResult.getDivisionSteps();
        int dividentLength = getNumberLength(divisionResult.getDivident());
        int subDividentLength = getNumberLength(divisionSteps.get(0).getDivident());
        int resultLength = getNumberLength(divisionResult.getResult());
        int subtractLength = getNumberLength(divisionSteps.get(0).getSubtract());
        StringBuilder result = new StringBuilder(" ");
                
        result.append(drawIndents(subDividentLength - subtractLength));
        
        result.append(divisionSteps.get(0).getSubtract());
        
        result.append(drawIndents(dividentLength - subDividentLength));
        
        result.append(PIPE);
        
        for (int i = 0; i < resultLength; i++) {
            result.append(DASH);
        }
        
        if(divisionResult.getResult() < 0) {
            result.append(DASH);
        }
        
        result.append("\n");
        
        return result.toString();
    }
    
    private String drawThirdLine(DivisionResult divisionResult) {
        int dividentLength = getNumberLength(divisionResult.getDivident());
        int subDividentLength = getNumberLength(divisionResult.getDivisionSteps().get(0).getDivident());
        StringBuilder result = new StringBuilder(" ");
        
        for(int i = 0; i < subDividentLength; i++) {
            result.append(DASH);
        }
        
        result.append(drawIndents(dividentLength - subDividentLength));
        
        result.append(PIPE + divisionResult.getResult() + "\n");
        
        return result.toString();
    }
    
    private String drawRestLines(DivisionResult divisionResult) {
        List<DivisionStep> divisionSteps = divisionResult.getDivisionSteps();
        StringBuilder result = new StringBuilder();
        
        countIndents(divisionSteps);
        
        for (int i = 1; i < divisionSteps.size(); i++) {
            result.append(drawStep(i, divisionSteps.get(i)));
        }
        
        result.append(drawRemainder(divisionResult));
        
        return result.toString();
    }
    
    private String drawRemainder(DivisionResult divisionResult) {
        List<DivisionStep> divisionSteps = divisionResult.getDivisionSteps();
        DivisionStep lastStep = divisionSteps.get(divisionSteps.size() - 1);
                
        return drawIndents(indents[divisionSteps.size()] + getNumberLength(lastStep.getDivident()) 
                - getNumberLength(lastStep.getRemainder())) + " " + lastStep.getRemainder();
    }
    
    private String drawStep(int stepPos, DivisionStep currentStep) {
        if (currentStep.getSubtract() == 0) {
            return "";
        }
                 
        return drawDivident(stepPos, currentStep) + drawSubtract(stepPos, currentStep) 
                + drawDashes(stepPos, currentStep);
    }
    
    private String drawIndents(int indentsCount) {
        StringBuilder result = new StringBuilder();
        
        while (indentsCount != 0) {
            result.append(" ");
            indentsCount--;
        }
        
        return result.toString();
    }
        
    private String drawLeadingZeros(int numOfZeros) {
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < numOfZeros; i++) {
            result.append(0);
        }
        
        return result.toString();
    }
    
    private String drawDivident(int stepPos, DivisionStep step) {
        return drawIndents(indents[stepPos]) + UNDERSCORE + drawLeadingZeros(step.getNumOfLeadingZeros()) 
                + step.getDivident() + "\n";
    }
    
    private String drawSubtract(int stepPos, DivisionStep step) {
        StringBuilder result = new StringBuilder();
        
        result.append(drawIndents(indents[stepPos]));
        
        result.append(drawIndents(step.getNumOfLeadingZeros()));
        
        int indent = getNumberLength(step.getDivident()) - getNumberLength(step.getSubtract());
        
        result.append(drawIndents(indent));

        result.append(" " + step.getSubtract() + "\n");
        
        return result.toString();
    }
    
    private String drawDashes(int stepPos, DivisionStep step) {
        StringBuilder result = new StringBuilder();
        
        result.append(drawIndents(indents[stepPos]) + " ");
        
        for (int i = 0; i < getNumberLength(step.getDivident()) + step.getNumOfLeadingZeros(); i++) {
            result.append(DASH);
        }

        result.append("\n");
        
        return result.toString();
    }
    
    private int makeNumberPositive(int number) {
        if (number < 0) {
            number = -number;
        }
        
        return number;
    }
    
    private int getNumberLength(int number) {
        number = makeNumberPositive(number);
        
        return Integer.toString(number).length();
    }
    
    private void countIndents(List<DivisionStep> steps) {
        for (int i = 1; i < steps.size(); i++) {    
            if (steps.get(i - 1).getRemainder() != 0) {
                indents[i] = indents[i - 1] + getNumberLength(steps.get(i - 1).getDivident()) 
                        - getNumberLength(steps.get(i - 1).getRemainder());
            } else {
                indents[i] = indents[i - 1] + getNumberLength(steps.get(i - 1).getDivident());
            }
            
            indents[i + 1] = indents[i] + steps.get(i).getNumOfLeadingZeros();
        }
    }
    
}