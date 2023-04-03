public class Game {
    //handles player turns and endgame etc..

    public static void Leaderboard(Player[] players){
        Player.SortByPoints(players);
        for(int i = 0; i < players.length; i++)
            System.out.println((i+1)+". "+ players[i].getName() + " punti: " + players[i].getPoints());
    }
}
