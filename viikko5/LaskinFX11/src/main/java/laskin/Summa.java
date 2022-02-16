/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author ylireett
 */
public class Summa extends Komento {
    private int palautettavaTulos;
    private int edellinenSyote;
    
    public Summa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        // kutsutaan Komento-luokan konstruktoria, jolloin luokan oliomuuttujat saavat arvonsa
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }
    
    @Override
    public void suorita() {
        int arvo = 0;
        int edellinenTulos = 0;
        
        try {
            arvo = Integer.parseInt(syote.getText());
            edellinenTulos = Integer.parseInt(tulos.getText());
        } catch (Exception e) {
            
        }
        
        // otetaan talteen tuloskentän edellinen arvo undo-operaatiota varten
        // edellinen syöte talteen, jotta voidaan suorittaa korjaava laskutoimitus undon yhteydessä
        palautettavaTulos = edellinenTulos;
        edellinenSyote = arvo;
        
        sov.plus(arvo);
        int tulosInt = sov.tulos();
        tulos.setText("" + tulosInt);
        syote.setText("");
        
        // jos laskun tulokseksi tuli nolla, nollaa-nappia ei voi painaa
        // jos muu kuin nolla, nollausnamikka on painettavissa
        if (tulosInt == 0) {
            nollaa.disableProperty().set(true);
        } else {
            nollaa.disableProperty().set(false);
            undo.disableProperty().set(false);
        }
    }
    
    @Override
    public void peru() {
        tulos.setText("" + palautettavaTulos);
        sov.miinus(edellinenSyote); // korjataan konepellin alla oleva konkreettinen tulos
    }
}
