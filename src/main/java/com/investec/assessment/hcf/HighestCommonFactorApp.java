package com.investec.assessment.hcf;

public class HighestCommonFactorApp {
    public static void main(String[] args) {
        HighestCommonFactorCalculator calculator = new HighestCommonFactorCalculator();
        int[] numbers = { 24, 36, 48, 52 };
        int hcf = calculator.highestCommonFactor(numbers);
        System.out.println("Highest Common Factor: " + hcf);
    }
}
