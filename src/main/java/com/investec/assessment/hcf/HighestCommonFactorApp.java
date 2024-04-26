package com.investec.assessment.hcf;

import com.investec.assessment.hcf.exceptions.HighestCommonFactorException;
import com.investec.assessment.hcf.impl.HighestCommonFactorCalculatorImpl;

public class HighestCommonFactorApp {
    public static void main(String[] args) {

        IHighestCommonFactorCalculator calculator = new HighestCommonFactorCalculatorImpl();
        int[] numbers = { 24, 36, 48, 52 };
        try {
            int hcf = calculator.highestCommonFactor(numbers);
            System.out.println("Highest Common Factor: " + hcf);
        } catch (HighestCommonFactorException e) {
            System.err.println("Error calculating Highest Common Factor: " + e.getMessage());
        }
    }
}
