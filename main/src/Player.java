import java.util.Scanner;

public class Player extends PersonalObjective {

    //player class, handles player generation, points and personal objectives
    private final String name;
    private final int id;
    private int points;
    Library libreria;

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Player(int id, String name) {
        this.name = name;
        this.id = id;
        this.points = 0;
    }

    public static Player[] GeneratePlayers(int numPlayers){
        Scanner sc = new Scanner(System.in);
        Player[] players = new Player[numPlayers];
        for (int i = 0; i < players.length; i ++){
            System.out.println("come si chiama il " + (i+1) + "° giocatore?");
            players[i] = new Player(i+1, sc.next());
        }
        return players;
    }

    public static void SortByPoints(Player[] players){
        for (int i = 0; i < players.length-1; i++){
            if (players[i].points < players[i+1].points){
                Player tmp = players[i+1];
                players[i+1] = players[i];
                players[i]=tmp;
            }
        }
    }
}
