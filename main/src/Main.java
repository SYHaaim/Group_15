
import java.util.*;
public class Main {
    public static void main(String[] args) throws NoSuchFieldException {
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

        System.out.println("Board generata, premere un tasto per riempirla");
        sc.nextLine();
        board.fillBoard();
        board.printBoard();

        //riga 21-27: azioni temporanee per ogni giocatore, test per vedere se i turni funzionano
        for (Player pl: giocatori) {
            Game.PlayerTurn(pl);
        }
        for (Player pl : giocatori){
            System.out.println(pl.getName() + " ha preso: ");
            pl.printPicked();
        }

        //test per la board dopo aver preso X tessere
        board.printBoard();


      // setting dei punti temporaneo, da rimuovere
        giocatori[0].setPoints(32);
        giocatori[1].setPoints(43);

        Game.PrintLeaderboard(giocatori);



        //prova obiettivi personali
        giocatori[0].printObjective();

       
    }
}
