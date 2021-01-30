package com.chpok.division.formatter;

import java.util.List;

import com.chpok.division.domain.DivisionResult;
import com.chpok.division.domain.DivisionStep;

public class DivisionViewProviderImpl implements DivisionViewProvider{
    
    private static final String UNDERSCORE = "_";
    private static final String PIPE = "|";
    private static final String DASH = "-";
        
    @Override
    public String provideView(DivisionResult divisionResult) {     
        return drawHeader(divisionResult) + drawRestLines(divisionResult); 
    }
    
    private String drawHeader(DivisionResult divisionResult) {
        return drawFirstLine(divisionResult) + drawSecondLine(divisionResult) + drawThirdLine(divisionResult);
    }
    
    private String drawFirstLine(DivisionResult divisionResult) {
        return UNDERSCORE + divisionResult.getDivident() + PIPE 
                + divisionResult.getDivisor() + "\n";
    }
    
    private String drawSecondLine(DivisionResult divisionResult) {
        List<DivisionStep> divisionSteps = divisionResult.getDivisionSteps();
        int dividentLength = getNumberLength(divisionResult.getDivident());
        int subDividentLength = getNumberLength(divisionSteps.get(0).getDivident());
        int resultLength = getNumberLength(divisionResult.getResult());
        int subtractLength = getNumberLength(divisionSteps.get(0).getSubtract());
        StringBuilder result = new StringBuilder(" ");
                
        result.append(drawSymbolNumberTimes(' ', subDividentLength - subtractLength));
        
        result.append(divisionSteps.get(0).getSubtract());
        
        result.append(drawSymbolNumberTimes(' ', dividentLength - subDividentLength));
        
        result.append(PIPE);
        
        result.append(drawSymbolNumberTimes(DASH, resultLength));
        
        
        if (divisionResult.getResult() < 0) {
            result.append(DASH);
        }
        
        result.append("\n");
        
        return result.toString();
    }
    
    private String drawThirdLine(DivisionResult divisionResult) {
        int dividentLength = getNumberLength(divisionResult.getDivident());
        int subDividentLength = getNumberLength(divisionResult.getDivisionSteps().get(0).getDivident());
        StringBuilder result = new StringBuilder(" ");

        result.append(drawSymbolNumberTimes(DASH, subDividentLength));
        
        result.append(drawSymbolNumberTimes(' ', dividentLength - subDividentLength));
        
        result.append(PIPE + divisionResult.getResult() + "\n");
        
        return result.toString();
    }
    
    private String drawRestLines(DivisionResult divisionResult) {
        List<DivisionStep> divisionSteps = divisionResult.getDivisionSteps();
        StringBuilder result = new StringBuilder();
        
        int[] indents = countIndents(divisionSteps);
        
        for (int i = 1; i < divisionSteps.size(); i++) {
            result.append(drawStep(indents[i], divisionSteps.get(i)));
        }
        
        result.append(drawRemainder(indents[divisionSteps.size()], divisionResult));
        
        return result.toString();
    }
    
    private String drawRemainder(int indent, DivisionResult divisionResult) {
        List<DivisionStep> divisionSteps = divisionResult.getDivisionSteps();
        DivisionStep lastStep = divisionSteps.get(divisionSteps.size() - 1);
                
        return drawSymbolNumberTimes(' ', indent + getNumberLength(lastStep.getDivident()) 
                - getNumberLength(lastStep.getRemainder())) + " " + lastStep.getRemainder();
    }
    
    private String drawStep(int indent, DivisionStep currentStep) {
        if (currentStep.getSubtract() == 0) {
            return "";
        }
                 
        return drawDivident(indent, currentStep) + drawSubtract(indent, currentStep) 
                + drawDashes(indent, currentStep);
    }
    
    private String drawSymbolNumberTimes(char sym, int num) {
        StringBuilder result = new StringBuilder();
        
        while (num != 0) {
            result.append(sym);
            num--;
        }
        
        return result.toString();
    }
    
    private String drawSymbolNumberTimes(String sym, int num) {
        StringBuilder result = new StringBuilder();
        
        while (num != 0) {
            result.append(sym);
            num--;
        }
        
        return result.toString();
    }
        
    private String drawLeadingZeros(int numOfZeros) {
        return drawSymbolNumberTimes(Integer.toString(0), numOfZeros);
    }
    
    private String drawDivident(int indent, DivisionStep step) {
        return drawSymbolNumberTimes(' ', indent) + UNDERSCORE + drawLeadingZeros(step.getNumOfLeadingZeros()) 
                + step.getDivident() + "\n";
    }
    
    private String drawSubtract(int indent, DivisionStep step) {
        StringBuilder result = new StringBuilder();
        
        result.append(drawSymbolNumberTimes(' ', indent));
        
        result.append(drawSymbolNumberTimes(' ', step.getNumOfLeadingZeros()));
                
        result.append(drawSymbolNumberTimes(' ', getNumberLength(step.getDivident()) - getNumberLength(step.getSubtract())));

        result.append(" " + step.getSubtract() + "\n");
        
        return result.toString();
    }
    
    private String drawDashes(int indent, DivisionStep step) {
        StringBuilder result = new StringBuilder();
        
        result.append(drawSymbolNumberTimes(' ', indent) + " ");
        
        result.append(drawSymbolNumberTimes(DASH, getNumberLength(step.getDivident()) + step.getNumOfLeadingZeros()));
        
        result.append("\n");
        
        return result.toString();
    }
    
    private int getNumberLength(int number) {        
        return Integer.toString(number).length();
    }
    
    private int[] countIndents(List<DivisionStep> steps) {
        int[] indents = new int[steps.size() + 1];
        for (int i = 1; i < steps.size(); i++) {    
            if (steps.get(i - 1).getRemainder() != 0) {
                indents[i] = indents[i - 1] + getNumberLength(steps.get(i - 1).getDivident()) 
                        - getNumberLength(steps.get(i - 1).getRemainder());
            } else {
                indents[i] = indents[i - 1] + getNumberLength(steps.get(i - 1).getDivident());
            }
            
            indents[i + 1] = indents[i] + steps.get(i).getNumOfLeadingZeros();
        }
        
        return indents;
    }
    
}
