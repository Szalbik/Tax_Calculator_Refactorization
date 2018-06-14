package com.bartoszwalter.students.taxes;

import com.bartoszwalter.students.Utils.ResultWriter;
import com.bartoszwalter.students.contractFactory.Factory;
import com.bartoszwalter.students.contracts.Contract;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class TaxCalculator {
    private static Double salary;
    private static Character typ_umowy;

	public static void main(String[] args) {
		try {
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);

			System.out.print("Podaj kwotę dochodu: ");
            salary = Double.parseDouble(br.readLine());

			System.out.print("Typ umowy: (P)raca, (Z)lecenie: ");

			typ_umowy = br.readLine().charAt(0);

		} catch (Exception ex) {
			System.out.println("Błędna kwota");
			System.err.println(ex);
			return;
		}

//		DecimalFormat formatPrzecinkowy = new DecimalFormat("#.00");
//		DecimalFormat formatCalkowity = new DecimalFormat("#");

        Factory.createContract(salary, typ_umowy);
        ResultWriter.printResult();
	}

}
