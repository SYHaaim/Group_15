
import board.Board;
import objectives.CommonObjective;


import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {

    	Scanner sc = new Scanner(System.in);
        int numPlayers = 0;
        System.out.print("Quanti giocatori stanno giocando? (Min 2 o Max di 4)\t");
		do {
			numPlayers=sc.nextInt();
			if(numPlayers<2 || numPlayers>4)
			{System.out.println("Errore, inserire un numero compreso tra 2 e 4");}

		}while(numPlayers<2 || numPlayers>4);

        CommonObjective common1 = new CommonObjective(numPlayers);
        CommonObjective common2 = new CommonObjective(numPlayers);// test obbiettivo comune

        Player[] giocatori = Player.GeneratePlayers(numPlayers);
        Board board= new Board(numPlayers);
        Game newGame = new Game(giocatori, numPlayers);
        
    
        
        board.fillBoard();
        System.out.println("\nGiocatori..........");
        System.out.println("\n *********** ");

        for(Player pl : giocatori)
            System.out.println("    "+pl.getName());
        System.out.println(" *********** \n\n");


        int playCount =0;
        Scanner white = new Scanner(System.in); // scanner usato solo per promptare l'invio dell'utente
        System.out.println("PREMI INVIO PER INIZIARE");
        white.nextLine();


        board.printBoard();
        while(!newGame.isEndgame(giocatori)) {

            System.out.println("OBIETTIVI COMUNI: " +  "\n");
            common1.printCommonObj();
            System.out.println("");
            common2.printCommonObj();
            System.out.println("\n");

        newGame.PlayerTurn(giocatori[playCount], numPlayers);

        System.out.println("\n");

        //inserisce tessere nella libreria
        newGame.insertPicked(giocatori[playCount]);
            System.out.println("LIBRERIA DI " + giocatori[playCount].getName());
            System.out.println("\n");
            giocatori[playCount].printPlayerLibrary();
            System.out.println("\n");

        giocatori[playCount].resetPicked();
        //test per la board dopo aver preso X tessere


        //controllo degli obbiettivi comuni ogni turno
        giocatori[playCount].addPoints(common1.checkCommonObjectives(giocatori[playCount].getShelf().getLibreria()));
        giocatori[playCount].addPoints(common2.checkCommonObjectives(giocatori[playCount].getShelf().getLibreria()));

        playCount++;
        if (playCount > numPlayers-1)
            playCount = 0;

        System.out.println("premi invio per il prossimo turno...");
        white.nextLine();
        board.printBoard();
        }

        System.out.println("\n");

        //conteggio punti
        for (Player pl : giocatori){
            pl.groupedTiles();
            pl.playerObjectivesCheck();
        }



        newGame.PrintLeaderboard(giocatori);

    }
}
