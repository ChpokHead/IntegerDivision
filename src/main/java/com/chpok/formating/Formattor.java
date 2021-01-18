package com.chpok.formating;

import java.util.List;
import com.chpok.division.DivisionResult;
import com.chpok.division.DivisionStep;

public class Formattor {
    
    private int indentsCount;
    
    public void makeDivisionView(DivisionResult result) {
        indentsCount = 0;
        
        drawHeader(result);
        drawRestLines(result);
    }
    
    private void drawHeader(DivisionResult result) {
        drawFirstLine(result);
        drawSecondLine(result);
        drawThirdLine(result);
    }
    
    private void drawFirstLine(DivisionResult result) {        
        System.out.println("_" + result.getDivident() + "|" + result.getDivisor());
    }
    
    private void drawSecondLine(DivisionResult result) {
        List<DivisionStep> divisionSteps = result.getDivisionSteps();
        int dividentLength = getNumberLength(result.getDivident());
        int resultLength = getNumberLength(result.getResult());
        int subtractLength = getNumberLength(divisionSteps.get(0).getSubtract());
        
        System.out.print(" " + divisionSteps.get(0).getSubtract());
        
        for (int i = 0; i <  dividentLength - subtractLength; i++) {
            System.out.print(" ");
        }
        
        System.out.print("|");
        
        for (int i = 0; i < resultLength; i++) {
            System.out.print("-");
        }
        
        System.out.println();
    }
    
    private void drawThirdLine(DivisionResult result) {
        int dividentLength = getNumberLength(result.getDivisionSteps().get(0).getDivident());
        int subtract = result.getDivisionSteps().get(0).getSubtract();
        
        System.out.print(" ");
        for(int i = 0; i < dividentLength; i++) {
            System.out.print("-");
        }
        
        for (int i = getNumberLength(subtract); i < getNumberLength(result.getDivident()); i++) {
            System.out.print(" ");
        }
        
        System.out.print("|");
        
        System.out.print(result.getResult());
        
        System.out.println();
    }
    
    private void drawRestLines(DivisionResult result) {
        List<DivisionStep> divisionSteps = result.getDivisionSteps();
        
        for (int i = 1; i < divisionSteps.size(); i++) {
            drawStep(divisionSteps.get(i), divisionSteps.get(i-1));
        }
        
        drawRemainder(result);
    }
    
    private void drawRemainder(DivisionResult result) {
        List<DivisionStep> divisionSteps = result.getDivisionSteps();
        DivisionStep lastStep = divisionSteps.get(divisionSteps.size() - 1);
        
        drawIndents(indentsCount + getNumberLength(lastStep.getDivident()) - getNumberLength(lastStep.getRemainder()));        
        
        System.out.println(" " + lastStep.getRemainder());
    }
    
    private void drawStep(DivisionStep currentStep, DivisionStep prevStep) {
        if (prevStep.getRemainder() != 0) {
            indentsCount += getNumberLength(prevStep.getDivident()) - getNumberLength(prevStep.getRemainder());
        } else {
            indentsCount += getNumberLength(prevStep.getDivident());
        }
        
        if (currentStep.getSubtract() == 0) {
            return;
        }
 
        drawDivident(currentStep);
        
        drawSubtract(currentStep);
       
        drawDashes(currentStep);
        
        indentsCount += currentStep.getNumOfLeadingZeros();
    }
    
    private void drawIndents(int indentsCount) {
        while (indentsCount != 0) {
            System.out.print(" ");
            indentsCount--;
        }
    }
    
    private void drawLeadingZeros(int numOfZeros) {
        for (int i = 0; i < numOfZeros; i++) {
            System.out.print('0');
        }
    }
    
    private void drawDivident(DivisionStep step) {
        drawIndents(indentsCount);
        System.out.print("_");
        drawLeadingZeros(step.getNumOfLeadingZeros());
        System.out.println(step.getDivident());
    }
    
    private void drawSubtract(DivisionStep step) {
        drawIndents(indentsCount);
        for (int i = 0; i < step.getNumOfLeadingZeros(); i++) {
            System.out.print(" ");
        }
        
        int indent = getNumberLength(step.getDivident()) - getNumberLength(step.getSubtract());
        for (int i = 0; i < indent; i++) {
            System.out.print(" ");
        }
        
        System.out.println(" " + step.getSubtract());
    }
    
    private void drawDashes(DivisionStep step) {
        drawIndents(indentsCount);
        System.out.print(" ");
        
        for (int i = 0; i < getNumberLength(step.getDivident()) + step.getNumOfLeadingZeros(); i++) {
            System.out.print("-");
        }
        System.out.println();
    }
    
    private int getNumberLength(int number) {
        return Integer.toString(number).length();
    }
    
}
