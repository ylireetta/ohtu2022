
package ohtu.intjoukkosovellus;

import java.util.Arrays;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] ljono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm = 0;    // Tyhjässä joukossa alkioiden_määrä on nolla; alkutilanne = 0

    public IntJoukko() {
        ljono = new int[KAPASITEETTI];
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            ljono = new int[KAPASITEETTI];
        } else {
            ljono = new int[kapasiteetti];
        }
        
        this.kasvatuskoko = OLETUSKASVATUS;
    }
    
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0 || kasvatuskoko < 0) {
            ljono = new int[KAPASITEETTI];
            this.kasvatuskoko = OLETUSKASVATUS;
        } else {
            ljono = new int[kapasiteetti];
            this.kasvatuskoko = kasvatuskoko;
        }
        
    }

    public boolean lisaa(int luku) {
        if (alkioidenLkm == 0) {
            ljono[0] = luku;
            alkioidenLkm++;
            return true;
        }
        
        if (!kuuluu(luku)) {
            ljono[alkioidenLkm] = luku;
            alkioidenLkm++;
            
            // Jos taulukko on lisäyksen jälkeen täynnä, luodaan siitä kopio ja kasvatetaan kokoa
            if (alkioidenLkm == ljono.length) {
                ljono = Arrays.copyOf(ljono, alkioidenLkm + kasvatuskoko);
            }
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        int loytymisindeksi = -1;
        
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                loytymisindeksi = i;
                break;
            }
        }
        
        // jos poistettava luku löytyi, siirretään sen oikealla puolella olevia alkioita yksi pykälä vasemmalle
        if (loytymisindeksi != -1) {
            for (int j = loytymisindeksi; j < alkioidenLkm - 1; j++) {
                ljono[j] = ljono[j + 1];
            }
            alkioidenLkm--;
            return true;
        }
        return false;
    }
    
    public int mahtavuus() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        switch (alkioidenLkm) {
            case 0:
                return "{}";
            default:
                String tuotos = "{";
                int viimeisenIndeksi = alkioidenLkm - 1;
                for (int i = 0; i < viimeisenIndeksi; i++) {
                    tuotos += ljono[i];
                    tuotos += ", ";
                }
                tuotos += ljono[viimeisenIndeksi];
                tuotos += "}";
                return tuotos;
        }
    }

    public int[] toIntArray() {
        // Muodostetaan alkuperäisestä taulusta kopio, josta jätetään "tyhjät" nollat pois
        int[] taulu = Arrays.copyOf(ljono, alkioidenLkm);
        
        return taulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        // yhdiste = palautettavaan joukkoon otetaan mukaan kaikki a:sta ja b:stä löytyvät alkiot
        IntJoukko yhdiste = new IntJoukko(a.mahtavuus() + b.mahtavuus());
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        
        for (int i = 0; i < aTaulu.length; i++) {
            yhdiste.lisaa(aTaulu[i]);
        }
        
        for (int i = 0; i < bTaulu.length; i++) {
            yhdiste.lisaa(bTaulu[i]);
        }
        
        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        // leikkaus = alkiot kuuluvat sekä joukkoon a että b
        IntJoukko leikkaus = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        
        for (int i = 0; i < aTaulu.length; i++) {
            if (b.kuuluu(aTaulu[i])) {
                leikkaus.lisaa(aTaulu[i]);
            }
        }
        
        return leikkaus;

    }
    
    public static IntJoukko erotus (IntJoukko a, IntJoukko b) {
        // erotus = joukkoon a kuuluu ainoastaan sellaisia alkioita, joita ei löydy joukosta b
        IntJoukko erotus = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        
        for (int i = 0; i < aTaulu.length; i++) {
            if (!b.kuuluu(aTaulu[i])) {
                erotus.lisaa(aTaulu[i]);
            }
        }
         
        return erotus;
    }
        
}
