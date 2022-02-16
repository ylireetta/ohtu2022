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
public class Nollaa extends Komento {
    private int palautettavaTulos;
    
    public Nollaa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }
    
    @Override
    public void suorita() {
        int entinenTulos = 0;
        
        try {
            entinenTulos = Integer.parseInt(tulos.getText());
        } catch (Exception e) {
            
        }
        
        palautettavaTulos = entinenTulos;
        
        sov.nollaa();
        int tulosInt = sov.tulos();
        
        tulos.setText("" + tulosInt);
    }
    
    @Override
    public void peru() {
        tulos.setText("" + palautettavaTulos);
        sov.plus(palautettavaTulos); // korjataan konepellin alle konkreettinen tulos, jotta pystytään jatkamaan edellisestä pisteestä
    }
    
}
