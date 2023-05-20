
import java.util.*;
public class Main {
    public static void main(String[] args) throws NoSuchFieldException {
    	Scanner sc = new Scanner(System.in);
        int numPlayers = 0;
        System.out.print("Quanti giocatori stanno giocando? (Min 2 o Max di 4)\t");
        while(numPlayers < 2 || numPlayers > 4){
        numPlayers = sc.nextInt();
        }
        Player[] giocatori = Player.GeneratePlayers(numPlayers);
        Board board= new Board(numPlayers);
        Game newGame = new Game(giocatori, numPlayers);

        board.fillBoard();
        System.out.println("\nGiocatori..........");
        System.out.println("\n ***********");
        
        for(Player pl : giocatori)
            System.out.println("    "+pl.getName());
        System.out.println(" ***********");

        //Create board with field number of players


        System.out.println("Board generata, premere un tasto per riempirla\n");
        sc.nextLine();
        //board.fillBoard();
        board.printBoard();

        //riga 21-27: azioni temporanee per ogni giocatore, test per vedere se i turni funzionano
       
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
    	
    	System.out.print("Giocatore " +pl.getId()+ "\nIn che colonna vuoi inserire le tessere prese?.....\t");
        int insertionColumn = sc.nextInt()-1;
        
    	System.out.println("LIBRERIA " +pl.getId()+ " GIOCATORE" );

    	pl.insertInLibrary(insertionColumn);
    	
    	System.out.println("\n");

        pl.printPlayerLibrary();
        System.out.println("\n");
    }

        //testing sugli obbiettivi
        giocatori[0].playerObjectivesCheck();
        //endregion

        //test per la board dopo aver preso X tessere
        board.printBoard();


        //prova obiettivi personali
        System.out.println("\n");
        giocatori[0].printObjective();


      // setting dei punti temporaneo, da rimuovere
         giocatori[0].setPoints(32);
         giocatori[1].setPoints(43);
        newGame.PrintLeaderboard(giocatori);

    }
}
