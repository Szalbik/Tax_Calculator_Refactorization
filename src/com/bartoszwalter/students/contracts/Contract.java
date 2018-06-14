package com.bartoszwalter.students.contracts;


import java.text.DecimalFormat;
import java.util.Map;

public abstract class Contract {



    public double calculateTax(double salary){
        return (salary * 18) / 100;
    }

    public double calculateRetirementFee(double salary)
    {
        return (salary * 9.76) / 100;
    }

    public double calculateRentFee(double salary)
    {
        return (salary * 1.5) / 100;
    }

    protected double calculateHealthInsurance(double salary) {
        return (salary * 2.45) / 1;

    }

    //Sum of fee to Social Insurance Institution
    public double calculateSocialFee(double retirementFee, double rentFee, double healthInsurance){
        return retirementFee + rentFee + healthInsurance;

    }

    //pol. Koszt uzyskania przychodu
    public double getTaxDeductibleCost(){
        return 111.25;
    }

    //pol. Podstawa opodatkowania
    public double getTaxBase(double salary, double socialFee, double taxDeductibleCost){
        return salary - socialFee - taxDeductibleCost;
    }

    //pol. Zaliczka na podatek
    public double getTaxAdvance(){
       return 1.0; //do poprawy
    }

    public double roundToWholeNumber(double number)
    {
        DecimalFormat decimalFormat = new DecimalFormat("#");
        return Double.parseDouble(decimalFormat.format(number));
    }

    protected double roundToTwoDigitalsAfterComa(double number)
    {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        return Double.parseDouble(decimalFormat.format(number));
    }







}
