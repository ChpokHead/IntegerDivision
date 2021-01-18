package com.chpok.main;

import com.chpok.division.Divider;
import com.chpok.formating.Formattor;

public class IntegerDivisionConsoleApplication {

    public static void main(String[] args) {
        Divider divider = new Divider();
        Formattor formattor = new Formattor();
        
        formattor.makeDivisionView(divider.divide(-1000100, 12));
    }

}
