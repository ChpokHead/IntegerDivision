package com.chpok.division;

import java.util.List;

public class DivisionResult {
    private int divident;
    private int divisor;
    private int result;
    
    private List<DivisionStep> divisionSteps;
    
    public DivisionResult(int divident, int divisor) {
        this.divident = divident;
        this.divisor = divisor;
        this.result = divident / divisor;
    }

    public DivisionResult(int divident, int divisor, List<DivisionStep> divisionSteps) {
        this.divident = divident;
        this.divisor = divisor;
        this.result = divident / divisor;
        this.divisionSteps = divisionSteps;
    }

    public int getDivident() {
        return divident;
    }

    public int getDivisor() {
        return divisor;
    }
    
    public int getResult() {
        return result;
    }

    public List<DivisionStep> getDivisionSteps() {
        return divisionSteps;
    }
    
    public void setDivisionSteps(List<DivisionStep> divisionSteps) {
        this.divisionSteps = divisionSteps;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + divident;
        result = prime * result + ((divisionSteps == null) ? 0 : divisionSteps.hashCode());
        result = prime * result + divisor;
        result = prime * result + this.result;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DivisionResult other = (DivisionResult) obj;
        if (divident != other.divident)
            return false;
        if (divisionSteps == null) {
            if (other.divisionSteps != null)
                return false;
        } else if (!divisionSteps.equals(other.divisionSteps))
            return false;
        if (divisor != other.divisor)
            return false;
        if (result != other.result)
            return false;
        return true;
    } 

}
