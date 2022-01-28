
package ohtu;

public class Player implements Comparable<Player> {
    private String name;
    private String team;
    private int goals;
    private int assists;
    private String nationality;
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public String getTeam() {
        return team;
    }
    
    public int getGoals() {
        return goals;
    }
    
    public int getAssists() {
        return assists;
    }

    public String getNationality() {
        return nationality;
    }
    
    public int getPoints() {
        return goals + assists;
    }
    
    @Override
    public String toString() {
        String output = String.format("%-20s %s %d + %d = %d", name, team, goals, assists, getPoints());
        return output;
    }
    
    @Override
    public int compareTo(Player player) {
        
        return player.getPoints() - getPoints();
    }
      
}
