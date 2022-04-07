/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset;

import java.util.Scanner;

/**
 *
 * @author ylireett
 */
public abstract class KiviPaperiSakset {
    private static final Scanner scanner = new Scanner(System.in);
    private String eka = "";
    private String toka = "";
    
    public void pelaa() {
        System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
        System.out.println();
        Tuomari tuomari = new Tuomari();
        
        if (peliVoiAlkaa()) {
            while (onkoOkSiirto(eka) && onkoOkSiirto(toka)) {
                System.out.println();
                tuomari.kirjaaSiirto(eka, toka);
                System.out.println(tuomari);
                System.out.println();

                System.out.print("Ensimmäisen pelaajan siirto: ");
                eka = scanner.nextLine();
                
                if (!onkoOkSiirto(eka)) break;

                toka = toisenSiirto();
                
                if (!onkoOkSiirto(toka)) break;
            }
            
            lopputulostus(tuomari);
            
        } else {
            lopputulostus(tuomari);
        }
    }
    
    private String ensimmaisenSiirto() {
        System.out.print("Ensimmäisen pelaajan siirto: ");
        return scanner.nextLine();
    }

    // tämä on abstrakti metodi sillä sen toteutus vaihtelee eri pelityypeissä
    abstract protected String toisenSiirto();

    protected static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
    
    private boolean peliVoiAlkaa() {
        String ekanSiirto = ensimmaisenSiirto();
        if (!onkoOkSiirto(ekanSiirto)) {
            return false;
        }
        
        eka = ekanSiirto;
        
        String tokanSiirto = toisenSiirto();
        if (!onkoOkSiirto(tokanSiirto)) {
            return false;
        }
        
        toka = tokanSiirto;
        
        return true;
    }
    
    private void lopputulostus(Tuomari tuomari) {
        System.out.println();
        System.out.println("Kiitos!");
        System.out.println(tuomari);
    }
    
}
