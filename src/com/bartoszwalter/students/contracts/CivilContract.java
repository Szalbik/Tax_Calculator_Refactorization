package com.bartoszwalter.students.contracts;

import java.util.LinkedHashMap;

public class CivilContract extends Contract {
    public static double amountReduceTax = 0;
    private double salary;

    public CivilContract(double salary) {
        this.salary = salary;
        calculatedContract = new LinkedHashMap<>();
        calculateContract();
    }

    public void calculateContract(){
        income = getTaxBase(salary, calculateSocialFee(salary), getTaxDeductibleCost());
        calculateInsuarances(salary);
        shortTermCosts = getShortTermCosts();
        taxBase = calculatedBase - shortTermCosts;
        calculatedTaxBase = roundToWholeNumber(taxBase);
        obliczPodatek(calculatedTaxBase);
        withholdingTax = advancePayToIncomeTax;
        calculateAdvanceToUS();
        pay = income
                - ((retireContribution + rentContribution + sicknessContribution) + healthCareContribution + roundUS);

        calculatedContract.put("Typ umowy: ", "UMOWA-ZLECENIE");
        calculatedContract.put("Podstawa wymiaru składek: ", Double.toString(salary));
        calculatedContract.put("Składka na ubezpieczenie emerytalne: ", Double.toString(retireContribution));
        calculatedContract.put("Składka na ubezpieczenie rentowe: ", Double.toString(rentContribution));
        calculatedContract.put("Składka na ubezpieczenie chorobowe: ", Double.toString(sicknessContribution));
        calculatedContract.put("Podstawa wymiaru składki na ubezpieczenie zdrowotne: ", Double.toString(income));
        calculatedContract.put("Składka na ubezpieczenie zdrowotne: 9% = ", Double.toString(healthCareContribution));
        calculatedContract.put("Składka na ubezpieczenie zdrowotne: 7,75% = ", Double.toString(healthCareContribReduceTax));
        calculatedContract.put("Koszty uzyskania przychodu (stałe): ", Double.toString(shortTermCosts));
        calculatedContract.put("Podstawa opodatkowania: ", Double.toString(income));
        calculatedContract.put("Podstawa opodatkowania zaokrąglona: ", Double.toString(roundToWholeNumber(income)));
        calculatedContract.put("Zaliczka na podatek dochodowy 18 % = ", Double.toString(calculateTax(salary)));
        calculatedContract.put("Podatek potrącony = ", Double.toString(advancePayToIncomeTax - amountReduceTax));
        calculatedContract.put("Zaliczka do urzędu skarbowego = ", Double.toString(calculateAdvanceToUS()));
        calculatedContract.put("Zaliczka do urzędu skarbowego po zaokrągleniu = ", Double.toString(roundToWholeNumber(advancePayToUS)));
        calculatedContract.put("Pracownik otrzyma pay netto w wysokości = ", Double.toString(pay));
    }

    public double getShortTermCosts() {
        return (calculatedBase * 20) / 100;
    }


}
