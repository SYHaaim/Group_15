
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
        int playCount =0;

        common1.printCommonObj();
        board.printBoard();


        while(!newGame.isEndgame(giocatori)) {

        newGame.PlayerTurn(giocatori[playCount], numPlayers);

            System.out.println(giocatori[playCount].getName() + " ha preso: ");
            giocatori[playCount].printPicked();

        System.out.println("\n");

        //inserisce tessere nella libreria
        newGame.insertPicked(giocatori[playCount]);


        giocatori[playCount].resetPicked();
        //test per la board dopo aver preso X tessere
        board.printBoard();
        playCount++;

        if (playCount > numPlayers-1)
            playCount = 0;
        }
        //prova obiettivi personali
        System.out.println("\n");
        giocatori[0].printObjective();


      // setting dei punti temporaneo, da rimuovere
         giocatori[0].setPoints(32);
         giocatori[1].setPoints(43);
        newGame.PrintLeaderboard(giocatori);

    }
}
