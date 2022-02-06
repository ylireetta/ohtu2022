/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author ylireett
 */
public class KauppaTest {
    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    Kauppa k;
    
    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);
        k = new Kauppa(varasto, pankki, viite);
        
        // Lisätään varastoon maitoa
        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
    }
    
    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // määritellään että viitegeneraattori palauttaa viitteen 42
        when(viite.uusi()).thenReturn(42);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    
    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
        // määritellään että viitegeneraattori palauttaa viitteen 42
        when(viite.uusi()).thenReturn(42);

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        // Tilisiirron parametrit: asiakkaan nimi, viitenumero, asiakkaan tilinumero, kaupan tilinumero, siirrettävä summa
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(), eq(5));
    }
    
    @Test
    public void koriinKaksiEriTuotettaJoitaVarastossaTilisiirtoaKutsutaan() {
        // määritellään että viitegeneraattori palauttaa viitteen 400
        when(viite.uusi()).thenReturn(400);
        
        // määritellään että tuote numero 2 on riisi jonka hinta on 3 ja saldo 5
        when(varasto.saldo(2)).thenReturn(5); 
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "riisi", 3));
        
        // ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1); // maito
        k.lisaaKoriin(2); // riisi
        k.tilimaksu("reetta", "54321");
        
        verify(pankki).tilisiirto(eq("reetta"), eq(400), eq("54321"), anyString(), eq(8));
    }
    
    @Test
    public void koriinKaksiSamaaTuotettaJotaVarastossaTilisiirtoaKutsutaan() {
        // määritellään että viitegeneraattori palauttaa viitteen 400
        when(viite.uusi()).thenReturn(400);
        
        // ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1); // maito
        k.lisaaKoriin(1); // maito
        k.tilimaksu("reetta", "54321");
        
        verify(pankki).tilisiirto(eq("reetta"), eq(400), eq("54321"), anyString(), eq(10));
        
    }
    
    @Test
    public void koriinTuoteJotaVarastossaJaTuoteJotaEiOleTilisiirtoaKutsutaan() {
        // määritellään että viitegeneraattori palauttaa viitteen 400
        when(viite.uusi()).thenReturn(400);
                
        // määritellään että tuote numero 2 on riisi jonka hinta on 3 ja saldo 0
        when(varasto.saldo(2)).thenReturn(0); 
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "riisi", 3));
        
        // ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1); // maito
        k.lisaaKoriin(2); // riisi
        k.tilimaksu("reetta", "54321");
        
        // Maksetaan vain maidosta 5 €, sillä riisiä ei ollut varastossa
        verify(pankki).tilisiirto(eq("reetta"), eq(400), eq("54321"), anyString(), eq(5));
        
    }
    
    @Test
    public void uusiAsiointiNollaaEdellisetOstokset() {
        k.aloitaAsiointi();
        // Ostetaan kaksi maitoa
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu("reetta", "12345");
        
        
        verify(pankki).tilisiirto(eq("reetta"), anyInt(), eq("12345"), anyString(), eq(10));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("liisa", "54321");
        
        
        verify(pankki).tilisiirto(eq("liisa"), anyInt(), eq("54321"), anyString(), eq(5));
        
    }
    
    @Test
    public void uusiViitenumeroJokaiselleMaksutapahtumalle() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1); // maito
        k.tilimaksu("reetta", "333");
        
        verify(viite, times(1)).uusi(); // uutta viitenumeroa on kutsuttu kerran
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1); // maito
        k.tilimaksu("ole", "0000");
        
        verify(viite, times(2)).uusi(); // uutta viitenumeroa on kutsuttu kaksi kertaa
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1); // maito
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu("laura", "5678");
        
        verify(viite, times(3)).uusi(); // uutta viitenumeroa on kutsuttu kolme kertaa
    }
    
    @Test
    public void tuotteenPoistaminenKoristaPalauttaaVarastoon() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1); // maito
        k.poistaKorista(1);
        
        verify(varasto).palautaVarastoon(new Tuote(1, "maito", 5));
    }
}
