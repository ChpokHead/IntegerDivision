package com.chpok.division.formatter;

import com.chpok.division.domain.DivisionResult;

public interface DivisionViewProvider {
    String provideView(DivisionResult result);
}
