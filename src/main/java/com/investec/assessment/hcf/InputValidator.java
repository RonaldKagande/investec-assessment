package com.investec.assessment.hcf;

import com.investec.assessment.hcf.exceptions.HighestCommonFactorException;

public class InputValidator {
    public static void validateInput(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new HighestCommonFactorException("Input array is empty or null.");
        }
    }
}
