package com.bartoszwalter.students.taxes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class TaxCalculator {
	
	public static double podstawa_skadek = 0;
	public static char typ_umowy = ' ';
	// składki na ubezpieczenia społeczne
	public static double skladka_emerytalna = 0; // 9,76% podstawyy
	public static double skladka_rentowa = 0; // 1,5% podstawy
	public static double skladka_chorobowa = 0; // 2,45% podstawy
	// składki na ubezpieczenia zdrowotne
	public static double kosztyUzyskaniaPrzychodu = 111.25;
	public static double s_zdrow1 = 0; // od podstawy wymiaru 9%
	public static double s_zdrow2 = 0; // od podstawy wymiaru 7,75 %
	public static double zalicznaNaPodDoch = 0; // zaliczka na podatek dochodowy 18%
	public static double kwotaZmiejsz = 46.33; // kwota zmienjszająca podatek 46,33 PLN
	public static double zaliczkaUS = 0;
	public static double zaliczkaUS0 = 0;

	public static void main(String[] args) {
		try {
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);

			System.out.print("Podaj kwotę dochodu: ");
			podstawa_skadek = Double.parseDouble(br.readLine());

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
        System.out.println("UMOWA-ZLECENIE");
        System.out.println("Podstawa wymiaru składek " + podstawa_skadek);
        double obliczonaPodstawa = obliczonaPodstawa(podstawa_skadek);
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
                + formatPrzecinkowy.format(s_zdrow1) + " 7,75% = " + formatPrzecinkowy.format(s_zdrow2));
        kwotaZmiejsz = 0;
        kosztyUzyskaniaPrzychodu = (obliczonaPodstawa * 20) / 100;
        System.out.println("Koszty uzyskania przychodu (stałe) "
                + kosztyUzyskaniaPrzychodu);
        double podstawaOpodat = obliczonaPodstawa - kosztyUzyskaniaPrzychodu;
        double wyliczonaPodstawaOpodat = Double.parseDouble(formatCalkowity.format(podstawaOpodat));
        System.out.println("Podstawa opodatkowania " + podstawaOpodat
                + " zaokrąglona " + formatCalkowity.format(wyliczonaPodstawaOpodat));

//      ================================  
//      Obliczanie podatku
        
        obliczPodatek(wyliczonaPodstawaOpodat);
        
        System.out.println("Zaliczka na podatek dochodowy 18 % = "
                + zalicznaNaPodDoch);
        double podatekPotracony = zalicznaNaPodDoch;
        System.out.println("Podatek potrącony = "
                + formatPrzecinkowy.format(podatekPotracony));
        obliczZaliczke();
        zaliczkaUS0 = Double.parseDouble(formatCalkowity.format(zaliczkaUS));
        System.out.println("Zaliczka do urzędu skarbowego = "
                + formatPrzecinkowy.format(zaliczkaUS) + " po zaokrągleniu = "
                + formatCalkowity.format(zaliczkaUS0));
        double wynagrodzenie = podstawa_skadek
                - ((skladka_emerytalna + skladka_rentowa + skladka_chorobowa) + s_zdrow1 + zaliczkaUS0);
        System.out.println();
        System.out
                .println("Pracownik otrzyma wynagrodzenie netto w wysokości = "
                        + formatPrzecinkowy.format(wynagrodzenie));
    }

    private static void wykonajUmowaoPrace(DecimalFormat formatPrzecinkowy, DecimalFormat formatCalkowity) {
        System.out.println("UMOWA O PRACĘ");
        System.out.println("Podstawa wymiaru składek " + podstawa_skadek);
        double obliczonaPodstawa = obliczonaPodstawa(podstawa_skadek);
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
                + formatPrzecinkowy.format(s_zdrow1) + " 7,75% = " + formatPrzecinkowy.format(s_zdrow2));
        System.out.println("Koszty uzyskania przychodu w stałej wysokości "
                + kosztyUzyskaniaPrzychodu);
        double podstawaOpodat = obliczonaPodstawa - kosztyUzyskaniaPrzychodu;
        double wyliczonaPodstawaOpodat = Double
                .parseDouble(formatCalkowity.format(podstawaOpodat));
        System.out.println("Podstawa opodatkowania " + podstawaOpodat
                + " zaokrąglona " + formatCalkowity.format(wyliczonaPodstawaOpodat));
        
//      ================================  
//      Obliczanie podatku
        
        obliczPodatek(wyliczonaPodstawaOpodat);
        
        System.out.println("Zaliczka na podatek dochodowy 18 % = "
                + zalicznaNaPodDoch);
        System.out.println("Kwota wolna od podatku = " + kwotaZmiejsz);
        double podatekPotracony = zalicznaNaPodDoch - kwotaZmiejsz;
        System.out.println("Podatek potrącony = "
                + formatPrzecinkowy.format(podatekPotracony));
        obliczZaliczke();
        zaliczkaUS0 = Double.parseDouble(formatCalkowity.format(zaliczkaUS));
        System.out.println("Zaliczka do urzędu skarbowego = "
                + formatPrzecinkowy.format(zaliczkaUS) + " po zaokrągleniu = "
                + formatCalkowity.format(zaliczkaUS0));
        double wynagrodzenie = podstawa_skadek
                - ((skladka_emerytalna + skladka_rentowa + skladka_chorobowa) + s_zdrow1 + zaliczkaUS0);
        System.out.println();
        System.out
                .println("Pracownik otrzyma wynagrodzenie netto w wysokości = "
                        + formatPrzecinkowy.format(wynagrodzenie));
    }


    public static void obliczZaliczke() {
		zaliczkaUS = zalicznaNaPodDoch - s_zdrow2 - kwotaZmiejsz;
	}

	public static void obliczPodatek(double podstawa_skadek) {
		zalicznaNaPodDoch = (podstawa_skadek * 18) / 100;
	}

	public static double obliczonaPodstawa(double podstawa_skadek) {
		skladka_emerytalna = (podstawa_skadek * 9.76) / 100;
		skladka_rentowa = (podstawa_skadek * 1.5) / 100;
		skladka_chorobowa = (podstawa_skadek * 2.45) / 100;
		return (podstawa_skadek - skladka_emerytalna - skladka_rentowa - skladka_chorobowa);
	}

	public static void obliczUbezpieczenia(double podstawa_skadek) {
		s_zdrow1 = (podstawa_skadek * 9) / 100;
		s_zdrow2 = (podstawa_skadek * 7.75) / 100;
	}
}
