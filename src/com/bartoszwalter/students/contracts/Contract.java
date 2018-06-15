package com.bartoszwalter.students.contracts;


import java.text.DecimalFormat;
import java.util.LinkedHashMap;


public abstract class Contract {
    public static double amountReduceTax = 46.33;
    public static LinkedHashMap<String, String> calculatedContract;

    public static double income = 0;
    public static double retireContribution = 0;
    public static double rentContribution = 0;
    public static double sicknessContribution = 0;
    public static double shortTermCosts = 111.25;
    public static double healthCareContribution = 0;
    public static double healthCareContribReduceTax = 0;
    public static double advancePayToIncomeTax = 0;
    public static double advancePayToUS = 0;
    public static double roundUS = 0;
    public static double calculatedBase;
    public static double taxBase;
    public static double calculatedTaxBase;
    public static double withholdingTax;
    public static double pay;


    public double calculateTax(double salary){
        return advancePayToIncomeTax = (salary * 18) / 100;
    }

    public double calculateRetirementFee(double salary)
    {
        retireContribution = (salary * 9.76) / 100;
        return retireContribution;
    }

    public double calculateRentFee(double salary)
    {
        rentContribution = (salary * 1.5) / 100;
        return rentContribution;
    }

    public static void obliczPodatek(double pay) {
        advancePayToIncomeTax = (pay * 18) / 100;
    }

    protected double calculateHealthInsurance(double salary) {
        healthCareContribution = (salary * 2.45) / 1;
        return healthCareContribution;
    }

    public static double calculateAdvanceToUS() {
        advancePayToUS = advancePayToIncomeTax - healthCareContribReduceTax - amountReduceTax;
        return advancePayToUS;
    }

    public double calculateSocialFee(double base){
         retireContribution = base * 0.0976;
         rentContribution = base * 0.015;
         sicknessContribution = base * 0.0245;
         return retireContribution + rentContribution + sicknessContribution;
    }

    public double getTaxDeductibleCost(){
        return 111.25;
    }

    public double getTaxBase(double salary, double socialFee, double taxDeductibleCost){
        return salary - socialFee - taxDeductibleCost;
    }

    public double getTaxAdvance(){
       return 1.0; //do poprawy
    }

    public static double calculateInsuarances(double income) {
        healthCareContribution = (income * 9) / 100;
        healthCareContribReduceTax = (income * 7.75) / 100;
        return healthCareContribution + healthCareContribReduceTax;
    }

    public double roundToWholeNumber(double number)
    {
        DecimalFormat decimalFormat = new DecimalFormat("#");
        return Double.parseDouble(decimalFormat.format(number));
    }

    public double roundToTwoDigitalsAfterComa(double number)
    {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        return Double.parseDouble(decimalFormat.format(number));
    }
}
