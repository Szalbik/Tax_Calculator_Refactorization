package com.bartoszwalter.students.taxes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class TaxCalculator {
	
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
	public static double kwotaZmniejszajacaPodatek = 46.33; // kwota zmienjszająca podatek 46,33 PLN
	public static double zaliczkaWplaconaDoUS = 0;
	public static double zaokraglonaUS = 0;

	public static double obliczonaPodstawa;
	public static double podstawaOpodatkowania;
    public static double wyliczonaPodstawaOpodatkowania;
    public static double podatekPotracony;
    public static double wynagrodzenie;

	public static void main(String[] args) {
		try {
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);

			System.out.print("Podaj kwotę dochodu: ");
			przychod = Double.parseDouble(br.readLine());

			System.out.print("Typ umowy: (P)raca, (Z)lecenie: ");
			typ_umowy = br.readLine().charAt(0);

		} catch (Exception ex) {
			System.out.println("Błędna kwota");
			System.err.println(ex);
			return;
		}

		DecimalFormat formatPrzecinkowy = new DecimalFormat("#.00");
		DecimalFormat formatCalkowity = new DecimalFormat("#");

		if (typ_umowy == 'P') {
            wykonajUmowaoPrace(formatPrzecinkowy, formatCalkowity);
        } else if (typ_umowy == 'Z') {
            wykonajUmowaZlecenie(formatPrzecinkowy, formatCalkowity);
		} else {
			System.out.println("Nieznany typ umowy!");
		}
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

	public static void obliczUbezpieczenia(double przychod) {
		skladkaZdrowotna = (przychod * 9) / 100;
		skladkaZdrowotnaPomniejszajacaPodatek = (przychod * 7.75) / 100;
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
