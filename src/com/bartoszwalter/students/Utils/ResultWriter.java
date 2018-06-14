package com.bartoszwalter.students.Utils;

import java.text.DecimalFormat;

public class ResultWriter {
}





    private static void wykonajUmowaZlecenie(DecimalFormat formatPrzecinkowy, DecimalFormat formatCalkowity) {
//        System.out.println("UMOWA-ZLECENIE");
//        System.out.println("Podstawa wymiaru składek " + przychod);
        double obliczonaPodstawa = obliczonaPodstawa(przychod);
//        System.out.println("Składka na ubezpieczenie emerytalne "
//                + formatPrzecinkowy.format(skladka_emerytalna));
//        System.out.println("Składka na ubezpieczenie rentowe    "
//                + formatPrzecinkowy.format(skladka_rentowa));
//        System.out.println("Składka na ubezpieczenie chorobowe  "
//                + formatPrzecinkowy.format(skladka_chorobowa));
//        System.out
//                .println("Podstawa wymiaru składki na ubezpieczenie zdrowotne: "
//                        + obliczonaPodstawa);
        obliczUbezpieczenia(obliczonaPodstawa);
//        System.out.println("Składka na ubezpieczenie zdrowotne: 9% = "
//                + formatPrzecinkowy.format(skladkaZdrowotna) + " 7,75% = " + formatPrzecinkowy.format(skladkaZdrowotnaPomniejszajacaPodatek));
        kwotaZmniejszajacaPodatek = 0;
        kosztyUzyskaniaPrzychodu = (obliczonaPodstawa * 20) / 100;
//        System.out.println("Koszty uzyskania przychodu (stałe) "
//                + kosztyUzyskaniaPrzychodu);
        podstawaOpodatkowania = obliczonaPodstawa - kosztyUzyskaniaPrzychodu;
        wyliczonaPodstawaOpodatkowania = Double.parseDouble(formatCalkowity.format(podstawaOpodatkowania));
//        System.out.println("Podstawa opodatkowania " + podstawaOpodatkowania
//                + " zaokrąglona " + formatCalkowity.format(wyliczonaPodstawaOpodatkowania));

//      =============================
//      Obliczanie podatku

        obliczPodatek(wyliczonaPodstawaOpodatkowania);

//        System.out.println("Zaliczka na podatek dochodowy 18 % = "
//                + zaliczkaNaPodatekDochodowy);
        podatekPotracony = zaliczkaNaPodatekDochodowy;
//        System.out.println("Podatek potrącony = "
//                + formatPrzecinkowy.format(podatekPotracony));
        obliczZaliczkeDoUS();
        zaokraglonaUS = Double.parseDouble(formatCalkowity.format(zaliczkaWplaconaDoUS));
//        System.out.println("Zaliczka do urzędu skarbowego = "
//                + formatPrzecinkowy.format(zaliczkaWplaconaDoUS) + " po zaokrągleniu = "
//                + formatCalkowity.format(zaokraglonaUS));
        wynagrodzenie = przychod
                - ((skladka_emerytalna + skladka_rentowa + skladka_chorobowa) + skladkaZdrowotna + zaokraglonaUS);
//        System.out.println();
//        System.out
//                .println("Pracownik otrzyma wynagrodzenie netto w wysokości = "
//                        + formatPrzecinkowy.format(wynagrodzenie));
    }