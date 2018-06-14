package com.bartoszwalter.students.contracts;

import com.bartoszwalter.students.taxes.TaxCalculator;

import java.util.HashMap;

public class CivilContract extends Contract {

private double salary;
public static HashMap<String, Double> civilContract;

    public CivilContract(double salary) {
        this.salary = salary;
        civilContract = new HashMap<>();
        calculateCivilContract();
    }

    public void calculateCivilContract(){
        civilContract.put("Obliczona podstawa: ", TaxCalculator.obliczonaPodstawa(this.salary));
        civilContract.put("Obliczone ubezpieczenie: ", TaxCalculator.obliczUbezpieczenia(TaxCalculator.obliczonaPodstawa(this.salary)));
    }


}
