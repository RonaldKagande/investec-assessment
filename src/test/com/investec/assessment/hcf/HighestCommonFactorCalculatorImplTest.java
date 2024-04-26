package com.investec.assessment.hcf;

import com.investec.assessment.hcf.exceptions.HighestCommonFactorException;
import com.investec.assessment.hcf.impl.HighestCommonFactorCalculatorImpl;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class HighestCommonFactorCalculatorImplTest {

    private HighestCommonFactorCalculatorImpl calculator;

    @Before
    public void setUp() {
        calculator = new HighestCommonFactorCalculatorImpl();
    }

    @Test
    public void testHighestCommonFactorValidInput() {
        int[] numbers = { 24, 36, 48, 52 };
        int hcf = calculator.highestCommonFactor(numbers);
        assertEquals(4, hcf); // Expected HCF is 4
    }

    @Test
    public void testSingleNumberInput() {
        int[] numbers = { 5 };
        int hcf = calculator.highestCommonFactor(numbers);
        assertEquals(5, hcf); // HCF of a single number is the number itself
    }

    @Test
    public void testEmptyInput() {
        int[] numbers = {};
        assertThrows(HighestCommonFactorException.class, () -> calculator.highestCommonFactor(numbers));
    }

    @Test
    public void testNullInput() {
        assertThrows(HighestCommonFactorException.class, () -> calculator.highestCommonFactor(null));
    }

    // Test private method calculateGreatestCommonDivisor using reflection
    @Test
    public void testCalculateGreatestCommonDivisorUsingReflection() throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException {
        Method method = HighestCommonFactorCalculatorImpl.class.getDeclaredMethod("calculateGreatestCommonDivisor",
                int.class, int.class);
        method.setAccessible(true); // Allow access to private method

        int result = (int) method.invoke(calculator, 24, 36);
        assertEquals(12, result); // Expected GCD using reflection is 12
    }


    @Test
    public void testCalculateGreatestCommonDivisorWithZeroDivisor() {
        int a = 24;
        int b = 0;

        // Calling the public method, which indirectly uses calculateGreatestCommonDivisor
        int gcd = calculator.highestCommonFactor(new int[]{a, b});
        assertEquals(a, gcd); // GCD with 0 divisor should return the non-zero number
    }


    @Test
    public void testCalculateGreatestCommonDivisorWithNegativeNumbers() {
        int a = -24;
        int b = 36;

        // Calling the public method, which indirectly uses calculateGreatestCommonDivisor
        int gcd = calculator.highestCommonFactor(new int[]{a, b});
        assertEquals(12, gcd);
    }
}
