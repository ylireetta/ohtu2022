/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author ylireett
 */
public class StatisticsTest {
    
    Reader readerStub = new Reader() {
        
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
            
            players.add(new Player("Semenko", "EDM", 4, 12)); // 16
            players.add(new Player("Lemieux", "PIT", 45, 54)); // 99
            players.add(new Player("Kurri",   "EDM", 37, 53)); // 90
            players.add(new Player("Yzerman", "DET", 42, 56)); // 98
            players.add(new Player("Gretzky", "EDM", 35, 89)); // 124
 
            return players;
        }
    };
    
    Statistics stats;
    
    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void pelaajaaVoiEtsia() {
        Player etsitty = stats.search("Semenko");
        
        assertEquals("EDM", etsitty.getTeam());
        assertEquals("Semenko", etsitty.getName());
        assertEquals(4, etsitty.getGoals());
        assertEquals(12, etsitty.getAssists());
    }
    
    @Test
    public void nullJosPelaajaaEiLoydy() {
        assertNull(stats.search("EiLöydy"));
    }
    
    @Test
    public void joukkueenPelaajatListalle() {
        List<Player> pelaajat = stats.team("EDM");
        
        assertEquals(3, pelaajat.size());
    }
    
    @Test
    public void pistehirmutListalle() {
        List<Player> parhaat = stats.topScorers(3);
        
        assertEquals(124, parhaat.get(0).getPoints());
        assertEquals("Gretzky", parhaat.get(0).getName());
        
        assertEquals(99, parhaat.get(1).getPoints());
        assertEquals("Lemieux", parhaat.get(1).getName());
        
        assertEquals(98, parhaat.get(2).getPoints());
        assertEquals("Yzerman", parhaat.get(2).getName());
        
    }
    
}
