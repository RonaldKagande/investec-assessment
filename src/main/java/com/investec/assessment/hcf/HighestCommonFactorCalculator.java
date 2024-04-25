package com.investec.assessment.hcf;

import com.investec.assessment.hcf.exceptions.HighestCommonFactorException;

public class HighestCommonFactorCalculator{
    public int highestCommonFactor(int[] numbers) {
        validateInput(numbers);

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

    private void validateInput(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new HighestCommonFactorException("Input array is empty or null.");
        }
    }

}
