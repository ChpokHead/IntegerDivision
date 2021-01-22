package com.chpok.formating;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;
import com.chpok.division.DivisionMathProviderImpl;

class DivisionVIewProviderTest {

    private final DivisionViewProviderImpl viewProvider = new DivisionViewProviderImpl();
    private final DivisionMathProviderImpl mathProvider = new DivisionMathProviderImpl();
    
    @Test
    void provideViewShouldReturnCorrectViewIfDividentIsMoreThanDivisor() {
        final int divident = 785;
        final int divisor = 4;
        final String actual = viewProvider.provideView(mathProvider.provideMathCalculation(divident, divisor));
        final String expected = "_785|4\n 4  |---\n -  |196\n_38\n 36\n --\n _25\n  24\n  --\n   1";
        
        assertThat(actual, is(equalTo(expected)));
    }
    
    @Test
    void provideViewShouldReturnCorrectViewIfDividentIsLessThanDivisor() {
        final int divident = 15;
        final int divisor = 100;
        final String actual = viewProvider.provideView(mathProvider.provideMathCalculation(divident, divisor));
        final String expected = "_15|100\n  0|-\n --|0\n 15";
        
        assertThat(actual, is(equalTo(expected)));
    }
    
    @Test
    void provideViewShouldReturnCorrectViewIfDividentIsZero() {
        final int divident = 0;
        final int divisor = 15342;
        final String actual = viewProvider.provideView(mathProvider.provideMathCalculation(divident, divisor));
        final String expected = "_0|15342\n 0|-\n -|0\n 0";
        
        assertThat(actual, is(equalTo(expected)));
    }
    
    @Test
    void provideViewShouldReturnCorrectViewIfDividentIsNegative() {
        final int divident = -5321;
        final int divisor = 167;
        final String actual = viewProvider.provideView(mathProvider.provideMathCalculation(divident, divisor));
        final String expected = "_5321|167\n 501 |---\n --- |-31\n _311\n  167\n  ---\n  144";
        
        assertThat(actual, is(equalTo(expected)));
    }
    
    @Test
    void provideViewShouldReturnCorrectViewIfDivisorIsNegative() {
        final int divident = 643;
        final int divisor = -135;
        final String actual = viewProvider.provideView(mathProvider.provideMathCalculation(divident, divisor));
        final String expected = "_643|135\n 540|--\n ---|-4\n 103";
        
        assertThat(actual, is(equalTo(expected)));
    }
    
    @Test
    void provideViewShouldReturnCorrectViewIfDividentAndDivisorAreNegative() {
        final int divident = -4252;
        final int divisor = -1345;
        final String actual = viewProvider.provideView(mathProvider.provideMathCalculation(divident, divisor));
        final String expected = "_4252|1345\n 4035|-\n ----|3\n  217";
        
        assertThat(actual, is(equalTo(expected)));
    }
    
    @Test
    void provideViewShouldReturnCorrectViewIfDividentHasZeros() {
        final int divident = 405022500;
        final int divisor = 45;
        final String actual = viewProvider.provideView(mathProvider.provideMathCalculation(divident, divisor));
        final String expected = "_405022500|45\n 405      |-------\n ---      |9000500\n   _0225\n     225\n    ----\n       0";
        
        assertThat(actual, is(equalTo(expected)));
    }

}
