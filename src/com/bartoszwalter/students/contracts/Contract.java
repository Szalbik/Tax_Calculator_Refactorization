package com.bartoszwalter.students.contracts;


import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public abstract class Contract {
    public static HashMap<String, String> calculatedContract;

    public static double przychod = 0;
    public static char typ_umowy = ' ';
    // składki na ubezpieczenia społeczne
    public static double skladka_emerytalna = 0; // 9,76% podstawyy
    public static double skladka_rentowa = 0; // 1,5% podstawy
    public static double skladka_chorobowa = 0; // 2,45% podstawy
    // składki na ubezpieczenia zdrowotne
    public static double kosztyUzyskaniaPrzychodu = 111.25;
    public static double skladkaZdrowotna = 0; // od podstawy wymiaru 9%
    public static double skladkaZdrowotnaPomniejszajacaPodatek = 0; // od podstawy wymiaru 7,75 %

    public static double zaliczkaNaPodatekDochodowy = 0; // zaliczka na podatek dochodowy 18%
    public static double zaliczkaWplaconaDoUS = 0;
    public static double zaokraglonaUS = 0;

    public static double obliczonaPodstawa;
    public static double podstawaOpodatkowania;
    public static double wyliczonaPodstawaOpodatkowania;
    public static double podatekPotracony;
    public static double wynagrodzenie;


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
