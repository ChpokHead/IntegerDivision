package com.chpok.division.domain;

import java.util.List;
import java.util.Objects;

public class DivisionResult {
    private final int divident;
    private final int divisor;
    private final int result;
    private final List<DivisionStep> divisionSteps;
    
    private DivisionResult(Builder builder) {
        divident = builder.divident;
        divisor = builder.divisor;
        result = builder.result;
        divisionSteps = builder.divisionSteps;
    }
    
    public static Builder builder() {
        return new Builder();
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
        private int divident;
        private int divisor;
        private int result;
        private List<DivisionStep> divisionSteps;
        
        private Builder() {}
        
        public Builder withDivident(int divident) {
            this.divident = divident;
            return this;
        }
        
        public Builder withDivisor(int divisor) {
            this.divisor = divisor;
            return this;
        }
        
        public Builder withResult(int divident, int divisor) {
            this.result = divident / divisor;
            return this;
        }
        
        public Builder withDivisionSteps(List <DivisionStep> steps) {
            this.divisionSteps = steps;
            return this;
        }
        
        public DivisionResult build() {
            return new DivisionResult(this);
        }
    }

}
