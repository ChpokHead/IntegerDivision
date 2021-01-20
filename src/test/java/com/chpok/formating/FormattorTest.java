package com.chpok.formating;

import static org.junit.jupiter.api.Assertions.*;

import java.io.PrintStream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.chpok.division.Divider;

class FormattorTest {

    private final Formattor formattor = new Formattor();
    private final Divider divider = new Divider();
    private final PrintStream out = System.out;
    
    @BeforeAll
    void setStream() {
        System.setOut(out);
    }
    
    @Test
    void makeDivisionViewShouldReturnCorrectViewIfDividentIsMoreThanDivisor() {
        final int divident = 78945;
        final int divisor = 4;
        
        System.out.println();
    }

}
