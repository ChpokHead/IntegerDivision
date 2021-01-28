package com.chpok.division.domain;

import java.util.List;
import java.util.Objects;

public class DivisionResult {
    private int divident;
    private int divisor;
    private int result;
    private List<DivisionStep> divisionSteps;

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

    @Override
    public int hashCode() {
        return Objects.hash(divident, divident, result, divisionSteps);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DivisionResult divisionResult = (DivisionResult) o;
        return Objects.equals(divident, divisionResult.divident) &&
               Objects.equals(divisor, divisionResult.divisor) &&
               Objects.equals(result, divisionResult.result) &&
               Objects.equals(divisionSteps, divisionResult.divisionSteps);
    } 
    
    public static class Builder {
        private DivisionResult divRes;
        
        public Builder() {
            divRes = new DivisionResult();
        }
        
        public Builder setDivident(int divident) {
            divRes.divident = divident;
            return this;
        }
        
        public Builder setDivisor(int divisor) {
            divRes.divisor = divisor;
            return this;
        }
        
        public Builder setResult(int divident, int divisor) {
            divRes.result = divident / divisor;
            return this;
        }
        
        public Builder setDivisionSteps(List <DivisionStep> steps) {
            divRes.divisionSteps = steps;
            return this;
        }
        
        public DivisionResult build() {
            return divRes;
        }
    }

}
