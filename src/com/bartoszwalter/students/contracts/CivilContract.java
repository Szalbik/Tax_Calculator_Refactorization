package com.bartoszwalter.students.contracts;

import com.bartoszwalter.students.taxes.TaxCalculator;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class CivilContract extends Contract {
    public static double kwotaZmniejszajacaPodatek = 0;
    private double salary;

    public CivilContract(double salary) {
        this.salary = salary;
        calculatedContract = new LinkedHashMap<>();
        calculateContract();
    }

    public void calculateContract(){
        przychod = getTaxBase(salary, calculateSocialFee(salary), getTaxDeductibleCost());
        calculateInsuarances(salary);
        kosztyUzyskaniaPrzychodu = getKosztyUzyskaniaPrzychodu();
        podstawaOpodatkowania = obliczonaPodstawa - kosztyUzyskaniaPrzychodu;
        wyliczonaPodstawaOpodatkowania = roundToWholeNumber(podstawaOpodatkowania);
        obliczPodatek(wyliczonaPodstawaOpodatkowania);
        podatekPotracony = zaliczkaNaPodatekDochodowy;
        obliczZaliczkeDoUS();
        wynagrodzenie = przychod
                - ((skladka_emerytalna + skladka_rentowa + skladka_chorobowa) + skladkaZdrowotna + zaokraglonaUS);

        calculatedContract.put("Typ umowy: ", "UMOWA-ZLECENIE");
        calculatedContract.put("Podstawa wymiaru składek: ", Double.toString(salary));
        calculatedContract.put("Składka na ubezpieczenie emerytalne: ", Double.toString(skladka_emerytalna));
        calculatedContract.put("Składka na ubezpieczenie rentowe: ", Double.toString(skladka_rentowa));
        calculatedContract.put("Składka na ubezpieczenie chorobowe: ", Double.toString(skladka_chorobowa));
        calculatedContract.put("Podstawa wymiaru składki na ubezpieczenie zdrowotne: ", Double.toString(przychod));
        calculatedContract.put("Składka na ubezpieczenie zdrowotne: 9% = ", Double.toString(skladkaZdrowotna));
        calculatedContract.put("Składka na ubezpieczenie zdrowotne: 7,75% = ", Double.toString(skladkaZdrowotnaPomniejszajacaPodatek));
        calculatedContract.put("Koszty uzyskania przychodu (stałe): ", Double.toString(kosztyUzyskaniaPrzychodu));
        calculatedContract.put("Podstawa opodatkowania: ", Double.toString(przychod));
        calculatedContract.put("Podstawa opodatkowania zaokrąglona: ", Double.toString(roundToWholeNumber(przychod)));
        calculatedContract.put("Zaliczka na podatek dochodowy 18 % = ", Double.toString(calculateTax(salary)));
        calculatedContract.put("Podatek potrącony = ", Double.toString(zaliczkaNaPodatekDochodowy - kwotaZmniejszajacaPodatek));
        calculatedContract.put("Zaliczka do urzędu skarbowego = ", Double.toString(obliczZaliczkeDoUS()));
        calculatedContract.put("Zaliczka do urzędu skarbowego po zaokrągleniu = ", Double.toString(roundToWholeNumber(zaliczkaWplaconaDoUS)));
        calculatedContract.put("Pracownik otrzyma wynagrodzenie netto w wysokości = ", Double.toString(wynagrodzenie));
    }

    public double getKosztyUzyskaniaPrzychodu() {
        return (obliczonaPodstawa * 20) / 100;
    }


}
