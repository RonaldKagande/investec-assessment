package com.investec.assessment.hcf.impl;

import com.investec.assessment.hcf.IHighestCommonFactorCalculator;
import com.investec.assessment.hcf.InputValidator;

public class HighestCommonFactorCalculatorImpl implements IHighestCommonFactorCalculator {
    @Override
    public int highestCommonFactor(int[] numbers) {

        InputValidator.validateInput(numbers);

        int result = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            result = calculateGreatestCommonDivisor(result, numbers[i]);
        }
        return result;
    }

    // Method to calculate the Greatest Common Divisor using the Euclidean algorithm
    private int calculateGreatestCommonDivisor(int a, int b) {
        // Iterate until b becomes 0 (Euclidean algorithm)
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

}
