package com.chpok.formating;

import java.util.List;
import com.chpok.division.DivisionResult;
import com.chpok.division.DivisionStep;

public class DivisionViewProviderImpl implements DivisionViewProvider{
    
    private int indentsCount;
    
    @Override
    public String provideView(DivisionResult divisionResult) {
        indentsCount = 0;
        
        return drawHeader(divisionResult) + drawRestLines(divisionResult); 
    }
    
    private String drawHeader(DivisionResult divisionResult) {
        return drawFirstLine(divisionResult) + drawSecondLine(divisionResult) + drawThirdLine(divisionResult);
    }
    
    private String drawFirstLine(DivisionResult divisionResult) {
        return "_" + makeNumberPositive(divisionResult.getDivident()) + "|" 
                + makeNumberPositive(divisionResult.getDivisor()) + "\n";
    }
    
    private String drawSecondLine(DivisionResult divisionResult) {
        List<DivisionStep> divisionSteps = divisionResult.getDivisionSteps();
        int dividentLength = getNumberLength(divisionResult.getDivident());
        int subDividentLength = getNumberLength(divisionSteps.get(0).getDivident());
        int resultLength = getNumberLength(divisionResult.getResult());
        int subtractLength = getNumberLength(divisionSteps.get(0).getSubtract());
        StringBuilder result = new StringBuilder(" ");
                
        for (int i = 0; i < subDividentLength - subtractLength; i++) {
            result.append(" ");
        }
        
        result.append(divisionSteps.get(0).getSubtract());
        
        for (int i = 0; i <  dividentLength - subDividentLength; i++) {
           result.append(" ");
        }
        
        result.append("|");
        
        for (int i = 0; i < resultLength; i++) {
            result.append("-");
        }
        
        if(divisionResult.getResult() < 0) {
            result.append("-");
        }
        
        result.append("\n");
        
        return result.toString();
    }
    
    private String drawThirdLine(DivisionResult divisionResult) {
        int dividentLength = getNumberLength(divisionResult.getDivident());
        int subDividentLength = getNumberLength(divisionResult.getDivisionSteps().get(0).getDivident());
        StringBuilder result = new StringBuilder(" ");
        
        for(int i = 0; i < subDividentLength; i++) {
            result.append("-");
        }
        
        for (int i = 0; i < dividentLength - subDividentLength; i++) {
            result.append(" ");
        }
        
        result.append("|" + divisionResult.getResult() + "\n");
        
        return result.toString();
    }
    
    private String drawRestLines(DivisionResult divisionResult) {
        List<DivisionStep> divisionSteps = divisionResult.getDivisionSteps();
        StringBuilder result = new StringBuilder();
        
        for (int i = 1; i < divisionSteps.size(); i++) {
            result.append(drawStep(divisionSteps.get(i), divisionSteps.get(i-1)));
        }
        
        result.append(drawRemainder(divisionResult));
        
        return result.toString();
    }
    
    private String drawRemainder(DivisionResult divisionResult) {
        List<DivisionStep> divisionSteps = divisionResult.getDivisionSteps();
        DivisionStep lastStep = divisionSteps.get(divisionSteps.size() - 1);
                
        return drawIndents(indentsCount + getNumberLength(lastStep.getDivident()) 
                - getNumberLength(lastStep.getRemainder())) + " " + lastStep.getRemainder();
    }
    
    private String drawStep(DivisionStep currentStep, DivisionStep prevStep) {
        if (prevStep.getRemainder() != 0) {
            indentsCount += getNumberLength(prevStep.getDivident()) - getNumberLength(prevStep.getRemainder());
        } else {
            indentsCount += getNumberLength(prevStep.getDivident());
        }
        
        if (currentStep.getSubtract() == 0) {
            return "";
        }
 
        String result = drawDivident(currentStep) + drawSubtract(currentStep) + drawDashes(currentStep);
        
        indentsCount += currentStep.getNumOfLeadingZeros();
        
        return result;
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
    
    private String drawDivident(DivisionStep step) {
        return drawIndents(indentsCount) + "_" + drawLeadingZeros(step.getNumOfLeadingZeros()) + step.getDivident() + "\n";
    }
    
    private String drawSubtract(DivisionStep step) {
        StringBuilder result = new StringBuilder();
        
        result.append(drawIndents(indentsCount));
        
        for (int i = 0; i < step.getNumOfLeadingZeros(); i++) {
            result.append(" ");
        }
        
        int indent = getNumberLength(step.getDivident()) - getNumberLength(step.getSubtract());
        
        for (int i = 0; i < indent; i++) {
            result.append(" ");
        }
        
        result.append(" " + step.getSubtract() + "\n");
        
        return result.toString();
    }
    
    private String drawDashes(DivisionStep step) {
        StringBuilder result = new StringBuilder();
        
        result.append(drawIndents(indentsCount) + " ");
        
        for (int i = 0; i < getNumberLength(step.getDivident()) + step.getNumOfLeadingZeros(); i++) {
            result.append("-");
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
    
}
