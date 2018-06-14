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
//      =============================
//      Obliczanie podatku
        obliczPodatek(wyliczonaPodstawaOpodatkowania);
        podatekPotracony = zaliczkaNaPodatekDochodowy;
        obliczZaliczkeDoUS();
        zaokraglonaUS = Double.parseDouble(formatCalkowity.format(zaliczkaWplaconaDoUS));
        wynagrodzenie = przychod
                - ((skladka_emerytalna + skladka_rentowa + skladka_chorobowa) + skladkaZdrowotna + zaokraglonaUS);
    }

    private static void wykonajUmowaoPrace(DecimalFormat formatPrzecinkowy, DecimalFormat formatCalkowity) {
        System.out.println("UMOWA O PRACĘ");
        System.out.println("Podstawa wymiaru składek " + przychod);
        obliczonaPodstawa = obliczonaPodstawa(przychod);
        System.out.println("Składka na ubezpieczenie emerytalne "
                + formatPrzecinkowy.format(skladka_emerytalna));
        System.out.println("Składka na ubezpieczenie rentowe    "
                + formatPrzecinkowy.format(skladka_rentowa));
        System.out.println("Składka na ubezpieczenie chorobowe  "
                + formatPrzecinkowy.format(skladka_chorobowa));
        System.out
                .println("Podstawa wymiaru składki na ubezpieczenie zdrowotne: "
                        + obliczonaPodstawa);
        obliczUbezpieczenia(obliczonaPodstawa);
        System.out.println("Składka na ubezpieczenie zdrowotne: 9% = "
                + formatPrzecinkowy.format(skladkaZdrowotna) + " 7,75% = " + formatPrzecinkowy.format(skladkaZdrowotnaPomniejszajacaPodatek));
        System.out.println("Koszty uzyskania przychodu w stałej wysokości "
                + kosztyUzyskaniaPrzychodu);
        podstawaOpodatkowania = obliczonaPodstawa - kosztyUzyskaniaPrzychodu;
        wyliczonaPodstawaOpodatkowania = Double
                .parseDouble(formatCalkowity.format(podstawaOpodatkowania));
        System.out.println("Podstawa opodatkowania " + podstawaOpodatkowania
                + " zaokrąglona " + formatCalkowity.format(wyliczonaPodstawaOpodatkowania));
        
//      ================================  
//      Obliczanie podatku
        
        obliczPodatek(wyliczonaPodstawaOpodatkowania);
        
        System.out.println("Zaliczka na podatek dochodowy 18 % = "
                + zaliczkaNaPodatekDochodowy);
        System.out.println("Kwota wolna od podatku = " + kwotaZmniejszajacaPodatek);
        podatekPotracony = zaliczkaNaPodatekDochodowy - kwotaZmniejszajacaPodatek;
        System.out.println("Podatek potrącony = "
                + formatPrzecinkowy.format(podatekPotracony));
        obliczZaliczkeDoUS();
        zaokraglonaUS = Double.parseDouble(formatCalkowity.format(zaliczkaWplaconaDoUS));
        System.out.println("Zaliczka do urzędu skarbowego = "
                + formatPrzecinkowy.format(zaliczkaWplaconaDoUS) + " po zaokrągleniu = "
                + formatCalkowity.format(zaokraglonaUS));
        wynagrodzenie = przychod
                - ((skladka_emerytalna + skladka_rentowa + skladka_chorobowa) + skladkaZdrowotna + zaokraglonaUS);
        System.out.println();
        System.out
                .println("Pracownik otrzyma wynagrodzenie netto w wysokości = "
                        + formatPrzecinkowy.format(wynagrodzenie));
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
