package ohtu;

import java.util.ArrayList;
import java.util.List;

public class Ostoskori {
    private int tavaroidenLkm;
    private int hintaYht;
    private List<Ostos> ostokset;
    
    public Ostoskori() {
        this.tavaroidenLkm = 0;
        this.hintaYht = 0;
        this.ostokset = new ArrayList<>();
    }
 
    public int tavaroitaKorissa() {
        // kertoo korissa olevien tavaroiden lukumäärän
        // eli jos koriin lisätty 2 kpl tuotetta "maito", 
        //   tulee metodin palauttaa 2 
        // jos korissa on 1 kpl tuotetta "maito" ja 1 kpl tuotetta "juusto", 
        //   tulee metodin palauttaa 2   

        return tavaroidenLkm;
    }
 
    public int hinta() {
        // kertoo korissa olevien tuotteiden yhteenlasketun hinnan
 
        return hintaYht;
    }
 
    public void lisaaTuote(Tuote lisattava) {
        boolean lisattyAiemmin = false;
        
        for (Ostos o : ostokset) {
            if (o.tuotteenNimi().equals(lisattava.getNimi())) {
                lisattyAiemmin = true;
                o.muutaLukumaaraa(1);
                break;
            }
        }
        // jos tuotetta ei ole vielä korissa, lisää uutena
        if (!lisattyAiemmin) {
            Ostos uusiOstos = new Ostos(lisattava);
            ostokset.add(uusiOstos);
        }
        
        tavaroidenLkm++;
        hintaYht += lisattava.getHinta();
    }
 
    public void poista(Tuote poistettava) {
        // poistaa tuotteen
        for (Ostos o : ostokset) {
            if (o.tuotteenNimi().equals(poistettava.getNimi())) {
                o.muutaLukumaaraa(-1);
                if (o.lukumaara() == 0) {
                    ostokset.remove(o);
                }
                break;
            }
        }
        
        tavaroidenLkm--;
        hintaYht -= poistettava.getHinta();
        
    }
 
    public List<Ostos> ostokset() {
        // palauttaa listan jossa on korissa olevat ostokset
        
        return ostokset;
    }
 
    public void tyhjenna() {
        // tyhjentää korin
    }
}
