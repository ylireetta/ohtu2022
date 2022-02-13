package ohtu;

public class TennisGame {
    
    private int player1score = 0;
    private int player2score = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name))
            player1score += 1;
        else
            player2score += 1;
    }

    public String getScore() {
        String score = "";
        
        // tasapeli
        if (player1score == player2score)
        {
            score = printEvenScore();
        }
        // toinen pelaaja johtaa
        else if (player1score >= 4 || player2score >= 4)
        {
            score = printWinnerScore();
        }
        // peliä ei ole pelattu vielä niin kauan, että jompikumpi olisi saanut yli neljä pistettä
        else
        {
            score = printCurrentScore();
        }
        return score;
    }
    
    private String printEvenScore() {
        // xxx-All tarkoittaa siis sitä, että molemmilla on yhtä paljon pisteitä
        // Deuce tarkoittaa, että tilanne on 40-40 ja sille pitää olla erityinen printtaus
        
        switch (player1score)
        {
            case 4:
                return "Deuce";
            default:
                return stringForPoints(player1score) + "-All";
        }
    }
    
    private String printWinnerScore() {
        String winningPlayer = "";
        
        if (player1score > player2score) {
            winningPlayer = player1Name;   
        } else {
            winningPlayer = player2Name;
        }
            
        int playerDifference = player1score - player2score;
        
        if (Math.abs(playerDifference) == 1) {
            return "Advantage " + winningPlayer;
        } else if (Math.abs(playerDifference) >= 2) {
            return "Win for " + winningPlayer;
        }
        
        return "";
    }
    
    private String printCurrentScore() {
        String result = stringForPoints(player1score) + "-" + stringForPoints(player2score);
        
        return result;
    }
    
    private String stringForPoints(int score) {
        switch(score)
        {
            case 0:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
                return "Forty";
            default:
                return "";
        }
    }
}