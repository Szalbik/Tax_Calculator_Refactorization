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

		DecimalFormat formatPrzecinkowy = new DecimalFormat("#.00");
		DecimalFormat formatCalkowity = new DecimalFormat("#");

        Factory.createContract(salary, typ_umowy);
        ResultWriter.printResult();
	}

    private static void wykonajUmowaZlecenie(DecimalFormat formatPrzecinkowy, DecimalFormat formatCalkowity) {
	    obliczonaPodstawa = obliczonaPodstawa(przychod);
        obliczUbezpieczenia(obliczonaPodstawa);
        kwotaZmniejszajacaPodatek = 0;
        kosztyUzyskaniaPrzychodu = (obliczonaPodstawa * 20) / 100;
        podstawaOpodatkowania = obliczonaPodstawa - kosztyUzyskaniaPrzychodu;
        wyliczonaPodstawaOpodatkowania = Double.parseDouble(formatCalkowity.format(podstawaOpodatkowania));
        obliczPodatek(wyliczonaPodstawaOpodatkowania);
        podatekPotracony = zaliczkaNaPodatekDochodowy;
        obliczZaliczkeDoUS();
        zaokraglonaUS = Double.parseDouble(formatCalkowity.format(zaliczkaWplaconaDoUS));
        wynagrodzenie = przychod
                - ((skladka_emerytalna + skladka_rentowa + skladka_chorobowa) + skladkaZdrowotna + zaokraglonaUS);
    }

    private static void wykonajUmowaoPrace(DecimalFormat formatPrzecinkowy, DecimalFormat formatCalkowity) {
        obliczonaPodstawa = obliczonaPodstawa(przychod);
        obliczUbezpieczenia(obliczonaPodstawa);
        podstawaOpodatkowania = obliczonaPodstawa - kosztyUzyskaniaPrzychodu;
        wyliczonaPodstawaOpodatkowania = Double.parseDouble(formatCalkowity.format(podstawaOpodatkowania));
        obliczPodatek(wyliczonaPodstawaOpodatkowania);
        podatekPotracony = zaliczkaNaPodatekDochodowy - kwotaZmniejszajacaPodatek;
        obliczZaliczkeDoUS();
        zaokraglonaUS = Double.parseDouble(formatCalkowity.format(zaliczkaWplaconaDoUS));
        wynagrodzenie = przychod
                - ((skladka_emerytalna + skladka_rentowa + skladka_chorobowa) + skladkaZdrowotna + zaokraglonaUS);
    }


    public static void obliczZaliczkeDoUS() {
		zaliczkaWplaconaDoUS = zaliczkaNaPodatekDochodowy - skladkaZdrowotnaPomniejszajacaPodatek - kwotaZmniejszajacaPodatek;
	}

	public static void obliczPodatek(double przychod) {
		zaliczkaNaPodatekDochodowy = (przychod * 18) / 100;
	}

	public static double obliczonaPodstawa(double przychod) {
		skladka_emerytalna = wyliczSkladkeEmerytalna(przychod);
		skladka_rentowa = wyliczSkladkeRentowa(przychod);
		skladka_chorobowa = wyliczSkladkeChorobowa(przychod);
		return (przychod - skladka_emerytalna - skladka_rentowa - skladka_chorobowa);
	}

	public static double obliczUbezpieczenia(double przychod) {
		skladkaZdrowotna = (przychod * 9) / 100;
		skladkaZdrowotnaPomniejszajacaPodatek = (przychod * 7.75) / 100;
		return skladkaZdrowotna + skladkaZdrowotnaPomniejszajacaPodatek;
	}

	public static double wyliczSkladkeEmerytalna(double przychod){
	    return (przychod * 9.76) / 100;
    }

    public static double wyliczSkladkeRentowa(double przychod){
        return (przychod * 1.5) / 100;
    }

    public static double wyliczSkladkeChorobowa(double przychod){
        return (przychod * 2.45) / 100;
    }

}
