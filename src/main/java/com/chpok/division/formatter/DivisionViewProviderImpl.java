package com.chpok.division.formatter;

import java.util.List;

import com.chpok.division.domain.DivisionResult;
import com.chpok.division.domain.DivisionStep;

public class DivisionViewProviderImpl implements DivisionViewProvider{
    
    private static final String UNDERSCORE = "_";
    private static final String PIPE = "|";
    private static final String DASH = "-";
    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";
        
    @Override
    public String provideView(DivisionResult divisionResult) {     
        return drawHeader(divisionResult) + drawRestLines(divisionResult); 
    }
    
    private String drawHeader(DivisionResult divisionResult) {
        return drawFirstLine(divisionResult) + drawSecondLine(divisionResult) + drawThirdLine(divisionResult);
    }
    
    private String drawFirstLine(DivisionResult divisionResult) {
        return UNDERSCORE + divisionResult.getDividend() + PIPE 
                + divisionResult.getDivisor() + NEW_LINE;
    }
    
    private String drawSecondLine(DivisionResult divisionResult) {
        List<DivisionStep> divisionSteps = divisionResult.getDivisionSteps();
        int dividentLength = getNumberLength(divisionResult.getDividend());
        int subDividentLength = getNumberLength(divisionSteps.get(0).getDividend());
        int subtractLength = getNumberLength(divisionSteps.get(0).getSubtract());
        
        return new StringBuilder(SPACE).append(drawSymbolNumberTimes(SPACE, subDividentLength - subtractLength))
                                       .append(divisionSteps.get(0).getSubtract())
                                       .append(drawSymbolNumberTimes(SPACE, dividentLength - subDividentLength))
                                       .append(PIPE).append(drawSymbolNumberTimes(DASH, getNumberLength(divisionResult.getResult())))
                                       .append(NEW_LINE)
                                       .toString();
    }
    
    private String drawThirdLine(DivisionResult divisionResult) {
        int dividentLength = getNumberLength(divisionResult.getDividend());
        int subDividentLength = getNumberLength(divisionResult.getDivisionSteps().get(0).getDividend());
        
        return new StringBuilder(SPACE).append(drawSymbolNumberTimes(DASH, subDividentLength))
                                     .append(drawSymbolNumberTimes(SPACE, dividentLength - subDividentLength))
                                     .append(PIPE + divisionResult.getResult() + NEW_LINE)
                                     .toString();
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
                
        return drawSymbolNumberTimes(SPACE, indent + getNumberLength(lastStep.getDividend()) 
                - getNumberLength(lastStep.getRemainder())) + SPACE + lastStep.getRemainder();
    }
    
    private String drawStep(int indent, DivisionStep currentStep) {
        return drawDivident(indent, currentStep) + drawSubtract(indent, currentStep) 
                + drawDashes(indent, currentStep);
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
        return drawSymbolNumberTimes(SPACE, indent) + UNDERSCORE + drawLeadingZeros(step.getNumOfLeadingZeros()) 
                + step.getDividend() + NEW_LINE;
    }
    
    private String drawSubtract(int indent, DivisionStep step) {
        return new StringBuilder().append(drawSymbolNumberTimes(SPACE, indent))
                                  .append(drawSymbolNumberTimes(SPACE, step.getNumOfLeadingZeros()))
                                  .append(drawSymbolNumberTimes(SPACE, getNumberLength(step.getDividend()) - getNumberLength(step.getSubtract())))
                                  .append(SPACE + step.getSubtract() + NEW_LINE)
                                  .toString();
    }
    
    private String drawDashes(int indent, DivisionStep step) {       
        return new StringBuilder().append(drawSymbolNumberTimes(SPACE, indent) + SPACE)
                                  .append(drawSymbolNumberTimes(DASH, getNumberLength(step.getDividend()) + step.getNumOfLeadingZeros()))
                                  .append(NEW_LINE)
                                  .toString();
    }
    
    private int getNumberLength(int number) {        
        return Integer.toString(number).length();
    }
    
    private int[] countIndents(List<DivisionStep> steps) {
        int[] indents = new int[steps.size() + 1];
        for (int i = 1; i < steps.size(); i++) {    
            if (steps.get(i - 1).getRemainder() != 0) {
                indents[i] = indents[i - 1] + getNumberLength(steps.get(i - 1).getDividend()) 
                        - getNumberLength(steps.get(i - 1).getRemainder());
            } else {
                indents[i] = indents[i - 1] + getNumberLength(steps.get(i - 1).getDividend());
            }
            
            indents[i + 1] = indents[i] + steps.get(i).getNumOfLeadingZeros();
        }
        
        return indents;
    }
    
}
