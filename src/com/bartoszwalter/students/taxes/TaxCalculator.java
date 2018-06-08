package com.bartoszwalter.students.taxes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class TaxCalculator {
	
	public static double przychod = 0;
	public static char umowa = ' ';
	// składki na ubezpieczenia społeczne
	public static double skladka_emerytalna = 0; // 9,76% podstawy
	public static double skladka_rentowa = 0; // 1,5% podstawy
	public static double ubezpieczenie_chorobowe = 0; // 2,45% podstawy
	// składki na ubezpieczenia zdrowotne
	public static double koszty_uzyskania = 111.25;
	public static double skladka_zdrowotna1 = 0; // od podstawy wymiaru 9%
	public static double skladka_zdrowotna2 = 0; // od podstawy wymiaru 7,75 %
	public static double zaliczka_podatek_dochodowy = 0; // zaliczka na podatek dochodowy 18%
	public static double kwota_zmniejszajaca_podatek = 46.33; // kwota zmienjszająca podatek 46,33 PLN
	public static double zaliczka_urzad_skarbowy = 0;
	public static double zaliczka_urzad_skarbowy_podstawa = 0;
	public static double wynagrodzenie = 0;

	public static void main(String[] args) {
		try {
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			
			System.out.print("Podaj kwotę dochodu: ");	
			przychod = Double.parseDouble(br.readLine());
			
			System.out.print("Typ umowy: (P)raca, (Z)lecenie: ");
			umowa = br.readLine().charAt(0);
			
		} catch (Exception ex) {
			System.out.println("Błędna kwota");
			System.err.println(ex);
			return;
		}

		DecimalFormat df00 = new DecimalFormat("#.00");
		DecimalFormat df = new DecimalFormat("#");
		
		if (umowa == 'P') {
			wyliczDlaUmowyOPrace(df00, df);
		} else if (umowa == 'Z') {

		} else {
			System.out.println("Nieznany typ umowy!");
		}
	}

	public static void wyliczDlaUmowyOPrace(DecimalFormat df00, DecimalFormat df){
		System.out.println("UMOWA O PRACĘ");
		System.out.println("Podstawa wymiaru składek " + przychod);
		double oPodstawa = obliczonaPodstawa(przychod);
		System.out.println("Składka na ubezpieczenie emerytalne "
				+ df00.format(skladka_emerytalna));
		System.out.println("Składka na ubezpieczenie rentowe    "
				+ df00.format(skladka_rentowa));
		System.out.println("Składka na ubezpieczenie chorobowe  "
				+ df00.format(ubezpieczenie_chorobowe));
		System.out
				.println("Podstawa wymiaru składki na ubezpieczenie zdrowotne: "
						+ oPodstawa);
		obliczUbezpieczenia(oPodstawa);
		System.out.println("Składka na ubezpieczenie zdrowotne: 9% = "
				+ df00.format(skladka_zdrowotna1) + " 7,75% = " + df00.format(skladka_zdrowotna2));
		System.out.println("Koszty uzyskania przychodu w stałej wysokości "
				+ koszty_uzyskania);
		double podstawaOpodat = oPodstawa - koszty_uzyskania;
		double podstawaOpodat0 = Double
				.parseDouble(df.format(podstawaOpodat));
		System.out.println("Podstawa opodatkowania " + podstawaOpodat
				+ " zaokrąglona " + df.format(podstawaOpodat0));
		obliczPodatek(podstawaOpodat0);
		System.out.println("Zaliczka na podatek dochodowy 18 % = "
				+ zaliczka_podatek_dochodowy);
		System.out.println("Kwota wolna od podatku = " + kwota_zmniejszajaca_podatek);
		double podatekPotracony = zaliczka_podatek_dochodowy - kwota_zmniejszajaca_podatek;
		System.out.println("Podatek potrącony = "
				+ df00.format(podatekPotracony));
		obliczZaliczke();
		zaliczka_urzad_skarbowy_podstawa = Double.parseDouble(df.format(zaliczka_urzad_skarbowy));
		System.out.println("Zaliczka do urzędu skarbowego = "
				+ df00.format(zaliczka_urzad_skarbowy) + " po zaokrągleniu = "
				+ df.format(zaliczka_urzad_skarbowy_podstawa));
		obliczWynagrodzenie();
		System.out.println();
		System.out
				.println("Pracownik otrzyma wynagrodzenie netto w wysokości = "
						+ df00.format(wynagrodzenie));
	}

	public static void WyliczDlaUmowyZlecenie(DecimalFormat df00, DecimalFormat df){
		System.out.println("UMOWA-ZLECENIE");
		System.out.println("Podstawa wymiaru składek " + przychod);
		double oPodstawa = obliczonaPodstawa(przychod);
		System.out.println("Składka na ubezpieczenie emerytalne "
				+ df00.format(skladka_emerytalna));
		System.out.println("Składka na ubezpieczenie rentowe    "
				+ df00.format(skladka_rentowa));
		System.out.println("Składka na ubezpieczenie chorobowe  "
				+ df00.format(ubezpieczenie_chorobowe));
		System.out
				.println("Podstawa wymiaru składki na ubezpieczenie zdrowotne: "
						+ oPodstawa);
		obliczUbezpieczenia(oPodstawa);
		System.out.println("Składka na ubezpieczenie zdrowotne: 9% = "
				+ df00.format(skladka_zdrowotna1) + " 7,75% = " + df00.format(skladka_zdrowotna2));
		kwota_zmniejszajaca_podatek = 0;
		koszty_uzyskania = (oPodstawa * 20) / 100;
		System.out.println("Koszty uzyskania przychodu (stałe) "
				+ koszty_uzyskania);
		double podstawaOpodat = oPodstawa - koszty_uzyskania;
		double podstawaOpodat0 = Double.parseDouble(df.format(podstawaOpodat));
		System.out.println("Podstawa opodatkowania " + podstawaOpodat
				+ " zaokrąglona " + df.format(podstawaOpodat0));
		obliczPodatek(podstawaOpodat0);
		System.out.println("Zaliczka na podatek dochodowy 18 % = "
				+ zaliczka_podatek_dochodowy);
		double podatekPotracony = zaliczka_podatek_dochodowy;
		System.out.println("Podatek potrącony = "
				+ df00.format(podatekPotracony));
		obliczZaliczke();
		zaliczka_urzad_skarbowy_podstawa = Double.parseDouble(df.format(zaliczka_urzad_skarbowy));
		System.out.println("Zaliczka do urzędu skarbowego = "
				+ df00.format(zaliczka_urzad_skarbowy) + " po zaokrągleniu = "
				+ df.format(zaliczka_urzad_skarbowy_podstawa));
		obliczWynagrodzenie();
		System.out.println();
		System.out
				.println("Pracownik otrzyma wynagrodzenie netto w wysokości = "
						+ df00.format(wynagrodzenie));
	}

	public static void obliczZaliczke() {
		zaliczka_urzad_skarbowy = zaliczka_podatek_dochodowy - skladka_zdrowotna2 - kwota_zmniejszajaca_podatek;
	}

	public static void obliczPodatek(double przychod) {
		zaliczka_podatek_dochodowy = (przychod * 18) / 100;
	}

	public static double obliczonaPodstawa(double przychod) {
		skladka_emerytalna = (przychod * 9.76) / 100;
		skladka_rentowa = (przychod * 1.5) / 100;
		ubezpieczenie_chorobowe = (przychod * 2.45) / 100;
		return (przychod - skladka_emerytalna - skladka_rentowa - ubezpieczenie_chorobowe);
	}

	public static void obliczUbezpieczenia(double przychod) {
		skladka_zdrowotna1 = (przychod * 9) / 100;
		skladka_zdrowotna2 = (przychod * 7.75) / 100;
	}
	public static void  obliczWynagrodzenie(){
		wynagrodzenie = przychod - ((skladka_emerytalna + skladka_rentowa + ubezpieczenie_chorobowe) + skladka_zdrowotna1 + zaliczka_urzad_skarbowy_podstawa);
	}



}
