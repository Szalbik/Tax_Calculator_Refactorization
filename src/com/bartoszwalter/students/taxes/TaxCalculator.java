package com.bartoszwalter.students.taxes;

import com.bartoszwalter.students.Utils.ResultWriter;
import com.bartoszwalter.students.contractFactory.Factory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TaxCalculator {
    private static Double salary;1
    private static Character contractType;

	public static void main(String[] args) {
		try {
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);

			System.out.print("Podaj kwotę dochodu: ");
            salary = Double.parseDouble(br.readLine());

			System.out.print("Typ umowy: (P)raca, (Z)lecenie: ");

			contractType = br.readLine().charAt(0);

		} catch (Exception ex) {
			System.out.println("Błędna kwota");
			System.err.println(ex);
			return;
		}
        Factory.createContract(salary, contractType);
        ResultWriter.printResult();
	}

}
