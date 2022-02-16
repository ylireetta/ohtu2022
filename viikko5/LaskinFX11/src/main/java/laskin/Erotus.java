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
public class Erotus extends Komento {
    private int palautettavaTulos;
    private int edellinenSyote;
    
    public Erotus(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
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
        
        palautettavaTulos = edellinenTulos;
        edellinenSyote = arvo;
        
        sov.miinus(arvo);
        int tulosInt = sov.tulos();
        tulos.setText("" + tulosInt);
        syote.setText("");
        
        // jos laskun tulokseksi tuli nolla, nollaa-nappia ei voi painaa
        // jos muu kuin nolla, nollausnamikka on painettavissa
        if (tulosInt == 0) {
            nollaa.disableProperty().set(true);
        } else {
            nollaa.disableProperty().set(false);
        }
    }
    
    @Override
    public void peru() {
        tulos.setText("" + palautettavaTulos);
        sov.plus(edellinenSyote);
    }
}
