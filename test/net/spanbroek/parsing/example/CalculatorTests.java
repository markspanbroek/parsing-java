package net.spanbroek.parsing.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTests {

    Calculator calculator = new Calculator();

    @Test
    public void shouldEvaluateSingleDigitInteger() {
        for (int i=0; i<10; i++) {
            assertEquals(i, calculator.evaluate("" + i));
        }
    }

    @Test
    public void shouldEvaluateAddition() {
        assertEquals(2, calculator.evaluate("1+1"));
    }

    @Test
    public void shouldEvaluateSubtraction() {
        assertEquals(1, calculator.evaluate("2-1"));
    }

    @Test
    public void shouldAllowCombinationOfSubtractionAndAddition() {
        assertEquals(2, calculator.evaluate("2-1+1"));
    }

    @Test
    public void shouldAllowBraces() {
        assertEquals(0, calculator.evaluate("2-(1+1)"));
    }

    @Test
    public void shouldHandleLargeExpressions() {
        String expression = "1";
        for (int i=0; i<10000; i++) {
            expression = expression + "+0";
        }

        assertEquals(1, calculator.evaluate(expression));
    }
}
