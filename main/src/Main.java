
import board.Board;
import board.TileType;
import library.Library;
import objectives.CommonObjective;


import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {


    	Scanner sc = new Scanner(System.in);
        int numPlayers = 0; int count=0;
        System.out.print("Quanti giocatori stanno giocando? (Min 2 o Max di 4)\t");
		do {
			numPlayers=sc.nextInt();
			if(numPlayers<2 || numPlayers>4)
			{System.out.println("Errore, inserire un numero compreso tra 2 e 4");}

		}while(numPlayers<2 || numPlayers>4);

        CommonObjective common1 = new CommonObjective(); // test obbiettivo comune
        common1.printCommonObj();
        Player[] giocatori = Player.GeneratePlayers(numPlayers);
        Board board= new Board(numPlayers);
        Game newGame = new Game(giocatori, numPlayers);
        
    
        
        board.fillBoard();
        System.out.println("\nGiocatori..........");
        System.out.println("\n ***********");



        
        for(Player pl : giocatori)
            System.out.println("    "+pl.getName());
        System.out.println(" ***********");

        common1.printCommonObj();



        System.out.println("board.Board generata, premere un tasto per riempirla\n");
        sc.nextLine();
        //board.fillBoard();
        board.printBoard();
        do {

        for (Player pl : giocatori)
        {
            System.out.println("\n");
            newGame.PlayerTurn(pl, numPlayers);
        }

        for (Player pl : giocatori)
        {
            System.out.println(pl.getName() + " ha preso: ");
            pl.printPicked();
        }



        //region da spostare nella classe Game (inserimento in libreria )
        System.out.println("\n");

        
        
    for (Player pl : giocatori){  //inserisce tessere nella libreria
        newGame.insertPicked(pl);
    }

        //testing sugli obbiettivi
  //      giocatori[0].playerObjectivesCheck();
      
      //  controlli obbiettivi comuni
 /*       for (Player pl : giocatori){  
        	System.out.println("giocatoree... " +pl.getId());
        	common1.checkCommonObjectives(2,pl.printPlayerLibrary());
        }*/
        
        //endregion

        //test per la board dopo aver preso X tessere
        board.printBoard();
        count++;
        }while(count<3);
        //prova obiettivi personali
        System.out.println("\n");
        giocatori[0].printObjective();


      // setting dei punti temporaneo, da rimuovere
         giocatori[0].setPoints(32);
         giocatori[1].setPoints(43);
        newGame.PrintLeaderboard(giocatori);

    }
}
