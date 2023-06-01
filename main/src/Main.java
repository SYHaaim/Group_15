
import board.Board;
import objectives.CommonObjective;

import java.util.*;

public class Main {


    public static void main(String[] args) throws Exception {
        //codici per testi colorati

        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_BLUE = "\u001B[34m";

        Scanner sc = new Scanner(System.in);
        int numPlayers = 0;
        char playerIn;
        System.out.print("Quanti giocatori stanno giocando? (Min 2 o Max di 4)\t");
        do {
            playerIn = sc.next().charAt(0);
            numPlayers = Character.getNumericValue(playerIn);

            if (numPlayers < 2 || numPlayers > 4) {
                System.out.println(ANSI_RED + "Errore, inserire un numero compreso tra 2 e 4" + ANSI_RESET);
            }

        } while (numPlayers < 2 || numPlayers > 4 || !Character.isDigit(playerIn));

        CommonObjective common1 = new CommonObjective(numPlayers);
        CommonObjective common2 = new CommonObjective(numPlayers);// test obbiettivo comune

        Player[] giocatori = Player.GeneratePlayers(numPlayers);
        Board board = new Board(numPlayers);
        Game newGame = new Game(giocatori, numPlayers);


        board.fillBoard();
        System.out.println("\nGiocatori..........");
        System.out.println("\n *********** ");

        for (Player pl : giocatori)
            System.out.println("    " + pl.getName());
        System.out.println(" *********** \n\n");


        int playCount = 0;
        Scanner white = new Scanner(System.in); // scanner usato solo per promptare l'invio dell'utente
        System.out.println("SI CONSIGLIA DI ALLARGARE AL MASSIMO LA CONSOLE PER UN'ESPERIENZA MIGLIORE");
        System.out.println(ANSI_BLUE + "PREMI INVIO PER INIZIARE" + ANSI_RESET);
        white.nextLine();


        board.printBoard();
        while (!newGame.isEndgame(giocatori)) {

            System.out.println("OBIETTIVI COMUNI: " + "\n");
            common1.printCommonObj();
            common2.printCommonObj();

            newGame.PlayerTurn(giocatori[playCount], numPlayers);

            System.out.println("\n");
            System.out.println("LIBRERIA DI " + giocatori[playCount].getName());
            giocatori[playCount].printPlayerLibrary();
            System.out.println("premi invio per iniziare ad inserire le tue tessere");
            white.nextLine();
            //inserisce tessere nella libreria
            newGame.insertPicked(giocatori[playCount]);
            System.out.println("LIBRERIA DI " + giocatori[playCount].getName());
            System.out.println("\n");
            giocatori[playCount].printPlayerLibrary();
            System.out.println("\n");

            giocatori[playCount].resetPicked();

            //controllo degli obbiettivi comuni ogni turno
            try{
            giocatori[playCount].addPoints(common1.checkCommonObjectives(giocatori[playCount].getShelf().getLibreria()));
            giocatori[playCount].addPoints(common2.checkCommonObjectives(giocatori[playCount].getShelf().getLibreria()));
            }catch (Exception e){ //unexpected exceptions might be generated thus a wait of 1 second
                System.out.println(ANSI_RED + "errore di calcolo, attendi 1 secondo" + ANSI_RESET);
                Thread.sleep(1000);
            }

            playCount++;
            if (playCount > numPlayers - 1)
                playCount = 0;

            System.out.println("premi invio per il prossimo turno...");
            white.nextLine();

            if (Board.checkFillBoard()) {
                board.fillBoard();
            }

            board.printBoard();


        }

        System.out.println("\n");

        //conteggio punti
        for (int i = 0; i < giocatori.length; i++) {
            giocatori[i].playerObjectivesCheck();
            giocatori[i].groupedTiles();
        }


        newGame.PrintLeaderboard(giocatori);

    }

}
