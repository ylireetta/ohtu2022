package ohtu;

import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class OstoskoriTest {

    Ostoskori kori;

    @Before
    public void setUp() {
        kori = new Ostoskori();
    }

    // step 1
    @Test
    public void ostoskorinHintaJaTavaroidenMaaraAlussa() { 
        assertEquals(0, kori.hinta());
        assertEquals(0, kori.tavaroitaKorissa());
    }
    
    // step 2
    @Test
    public void yhdenTuotteenLisaamisenJalkeenKorissaYksiTuote() {
        Tuote maito = new Tuote("maito", 3);
 
        kori.lisaaTuote(maito);
 
        assertEquals(1, kori.tavaroitaKorissa());
    }
    
    // step 3
    @Test
    public void yhdenTuotteenLisaamisenJalkeenKorinHintaOnTuotteenHinta() {
        Tuote maito = new Tuote("maito", 3);
 
        kori.lisaaTuote(maito);
 
        assertEquals(3, kori.hinta());
    }
    
    // step 4
    @Test
    public void kahdenTuotteenLisaamisenJalkeenKorissaKaksiTuotetta() {
        Tuote maito = new Tuote("maito", 3);
        kori.lisaaTuote(maito);
        
        Tuote munat = new Tuote("munat", 2);
        kori.lisaaTuote(munat);
 
        assertEquals(2, kori.tavaroitaKorissa());
    }
    
    // step 5
    @Test
    public void kahdenTuotteenLisaamisenJalkeenKorinHintaOnTuotteidenSumma() {
        Tuote maito = new Tuote("maito", 3);
        kori.lisaaTuote(maito);
        
        Tuote munat = new Tuote("munat", 2);
        kori.lisaaTuote(munat);
 
        assertEquals(5, kori.hinta());
    }
    
    // step 6
    public void kahdenSamanTuotteenLisaamisenJalkeenKorissaOnKaksiTavaraa() {
        Tuote maito = new Tuote("maito", 3);
        kori.lisaaTuote(maito);
        
        kori.lisaaTuote(maito);
        
        assertEquals(2, kori.tavaroitaKorissa());
    }
    
    // step 7
    public void kahdenSamanTuotteenLisaamisenJalkeenKorinHintaOnKaksiKertaaTuotteenHinta() {
        Tuote maito = new Tuote("maito", 3);
        kori.lisaaTuote(maito);
        kori.lisaaTuote(maito);
        
        assertEquals((maito.getHinta() * 2), kori.hinta());
    }
    
    // step 8
    @Test
    public void yhdenTuotteenLisaamisenJalkeenKorissaYksiOstosOlio() {
        Tuote tuote1 = new Tuote("vessapaperi", 7);
        kori.lisaaTuote(tuote1);
 
        List<Ostos> ostokset = kori.ostokset();
 
        // testaa ett?? metodin palauttamin listan pituus 1
        assertEquals(1, ostokset.size());
    }
    
    // step 9
    @Test
    public void yhdenTuotteenLisaamisenJalkeenKorissaYksiOstosOlioJollaOikeaTuotteenNimiJaMaara() {
        Tuote maito = new Tuote("maito", 3);
        kori.lisaaTuote(maito);
 
        Ostos ostos = kori.ostokset().get(0);
 
        // testaa t????ll??, ett?? palautetun listan ensimm??inen ostos on halutunkaltainen.
        assertEquals("maito", ostos.tuotteenNimi());
        assertEquals(1, ostos.lukumaara());
    }
    
    // step 10
    @Test
    public void kahdenTuotteenLisaamisenJalkeenKorissaKaksiOstosta() {
        Tuote maito = new Tuote("maito", 3);
        kori.lisaaTuote(maito);
        
        Tuote porkkana = new Tuote("porkkana", 1);
        kori.lisaaTuote(porkkana);
 
        List<Ostos> ostokset = kori.ostokset();
 
        assertEquals(2, ostokset.size());
    }
    
    // step 11
    @Test
    public void kahdenSamanTuotteenLisaamisenJalkeenKorissaYksiOstos() {
        Tuote maito = new Tuote("maito", 3);
        kori.lisaaTuote(maito);
        kori.lisaaTuote(maito);
        
        List<Ostos> ostokset = kori.ostokset();
        
        assertEquals(1, ostokset.size());
        assertEquals(2, kori.tavaroitaKorissa());
    }
    
    // step 12
    @Test
    public void kahdenSamanTuotteenLisaamisenJalkeenOstoksenNimiJaLukumaaraOikein() {
        Tuote maito = new Tuote("maito", 3);
        kori.lisaaTuote(maito);
        kori.lisaaTuote(maito);
        
        List<Ostos> ostokset = kori.ostokset();
        Ostos ekaOstos = ostokset.get(0);
        
        assertEquals(1, ostokset.size()); // yksi ostos
        assertEquals(2, kori.tavaroitaKorissa()); // lkm
        assertEquals("maito", ekaOstos.tuotteenNimi()); // ostoksen nimi
    }
    
    // step 13
    @Test
    public void kaksiSamaaTuotettaKorissaToinenPoistetaanOstokseenJaaYksiKplTuotetta() {
        Tuote maito = new Tuote("maito", 3);
        kori.lisaaTuote(maito);
        kori.lisaaTuote(maito);
        kori.poista(maito);
        
        List<Ostos> ostokset = kori.ostokset();
        Ostos ekaOstos = ostokset.get(0);
        
        assertEquals(1, ekaOstos.lukumaara());
    }
    
    // step 14
    @Test
    public void lisattyTuotePoistetaanKoriOnTyhja() {
        Tuote maito = new Tuote("maito", 3);
        kori.lisaaTuote(maito);
        kori.poista(maito);
        
        List<Ostos> ostokset = kori.ostokset();
        
        assertEquals(0, ostokset.size());
        assertEquals(0, kori.hinta());
        assertEquals(0, kori.tavaroitaKorissa());
    }
    
    // step 15
    @Test
    public void tyhjennaTyhjentaaKorin() {
        Tuote maito = new Tuote("maito", 3);
        kori.lisaaTuote(maito);
        
        kori.tyhjenna();
        
        List<Ostos> ostokset = kori.ostokset();
        
        assertEquals(0, ostokset.size());
        assertEquals(0, kori.hinta());
        assertEquals(0, kori.tavaroitaKorissa());
    }

}
