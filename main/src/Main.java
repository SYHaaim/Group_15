import java.util.*;
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int numPlayers = 0;
        System.out.println("quanti giocatori stanno giocando? (non meno di 2 o più di 4)");
        while(numPlayers < 2 || numPlayers > 4){
        numPlayers = sc.nextInt();
        }
        Player[] giocatori = Player.GeneratePlayers(numPlayers);
        for(Player pl : giocatori)
            System.out.println(pl.getName());
        
        //Create board with field number of players 
        Board board= new Board(numPlayers);
        System.out.println("Board generata: ");
        board.printBoard();
        
        // setting dei punti temporaneo, da rimuovere
        giocatori[0].setPoints(32);
        giocatori[1].setPoints(43);

        Game.Leaderboard(giocatori);

    }
}
