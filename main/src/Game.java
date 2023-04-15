import java.util.*;

public class Game {
    //handles player turns and endgame etc..

    public static void PrintLeaderboard(Player[] players){
        Player.SortByPoints(players);
        for(int i = 0; i < players.length; i++)
            System.out.println((i+1)+". "+ players[i].getName() + " punti: " + players[i].getPoints());
    }

    public static void PlayerTurn(Player pl){
        //temporary action, to replace with picking from 1 to 3 tiles
        String action = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("è il turno di " + pl.getName() + ", Indica una mossa");
        action = sc.next();
        pl.setActionString(action);
    }
}
