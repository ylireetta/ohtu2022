package ohtu.kivipaperisakset;

import java.util.Scanner;

public class Paaohjelma {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\nValitse pelataanko"
                    + "\n (a) ihmistä vastaan "
                    + "\n (b) tekoälyä vastaan"
                    + "\n (c) parannettua tekoälyä vastaan"
                    + "\nmuilla valinnoilla lopetetaan");

            String vastaus = scanner.nextLine();
            if (vastaus.endsWith("a")) {
                KiviPaperiSakset kaksinpeli = new KPSPelaajaVsPelaaja();
                kaksinpeli.pelaa();
            } else if (vastaus.endsWith("b")) {
                KiviPaperiSakset yksinpeli = new KPSTekoaly();
                yksinpeli.pelaa();
            } else if (vastaus.endsWith("c")) {
                KiviPaperiSakset pahaYksinpeli = new KPSParempiTekoaly();
                pahaYksinpeli.pelaa();
            } else {
                break;
            }

        }

    }
}
