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
public abstract class Komento {
    // protected = aliluokat pääsevät näihin muuttujiin käsiksi, mutta sovelluksen muut osat eivät
    // voidaan siis käyttää esim. tekstikenttiä summa-luokan puolelta ilman että kyseiselle luokalle listataan nämä samat kentät
    protected TextField syote;
    protected TextField tulos;
    protected Sovelluslogiikka sov;
    protected Button undo;
    protected Button nollaa;
    
    public Komento(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        this.syote = syotekentta;
        this.tulos = tuloskentta;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sov = sovellus;
    }
    
    public abstract void suorita();
    
    public abstract void peru();
}
