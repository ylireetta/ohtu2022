package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players.txt";

        Statistics stats = new Statistics(new PlayerReaderImpl(url));
        System.out.println(stats.matches(new All()).size());
        
        QueryBuilder query = new QueryBuilder();
        Matcher m4 = query.playsIn("NYR")
                .hasAtLeast(5, "goals")
                .hasFewerThan(10, "goals")
                .build();
        
        for (Player player : stats.matches(m4)) {
            System.out.println( player );
        }
          
        Matcher a = new And( new HasAtLeast(5, "goals"),
                             new HasAtLeast(5, "assists"),
                             new PlaysIn("PHI")
        );
        Matcher b = new And( new HasFewerThan(1, "goals"),
                             new PlaysIn("NYR")
        );
        
        Matcher c = new And( 
            new Not( new HasAtLeast(1, "goals") ), 
            new PlaysIn("NYR")
        );
        
        Matcher d = new Or( 
            new HasAtLeast(30, "goals"),
            new HasAtLeast(50, "assists")
        );
        
        Matcher e = new And(
            new HasAtLeast(40, "points"),
            new Or( 
                new PlaysIn("NYR"),
                new PlaysIn("NYI"),
                new PlaysIn("BOS")
            )
        );
        
        Matcher m1 = query.playsIn("PHI")
          .hasAtLeast(10, "assists")
          .hasFewerThan(5, "goals").build();
        
        for (Player player : stats.matches(m1)) {
            System.out.println(player);
        }

        // tässä on ongelma
        // nyt olen tehnyt niin, että jokainen ketjutus luo uuden and-ehdon ja ottaa pohjaksi entisen ehtojoukon
        // nämä seuraavat ehdot menevät siis entisten jatkoksi, eli näiden jälkeen ei tulostu enää mitään, sillä mikään pelaaja ei täytä kaikkia ehtoja
        Matcher m2 = query.playsIn("EDM")
                  .hasAtLeast(40, "points").build();
        
        System.out.println("");
        
        for (Player player : stats.matches(m2)) {
            System.out.println(player);
        }

        Matcher m = query.oneOf(m1, m2).build();
        
        System.out.println("");
        
        
        for (Player player : stats.matches(m)) {
            System.out.println(player);
        }
        
        Matcher m3 = query.oneOf(
    query.playsIn("PHI")
        .hasAtLeast(10, "assists")
        .hasFewerThan(5, "goals").build(),

    query.playsIn("EDM")
        .hasAtLeast(40, "points").build()
).build();
        
        System.out.println("");
        
        for (Player player : stats.matches(m3)) {
            System.out.println(player);
        }

        

    }
}
