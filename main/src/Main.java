
import java.util.*;
public class Main {
    public static void main(String[] args) throws NoSuchFieldException {
        Scanner sc = new Scanner(System.in);
        int numPlayers = 0; int Righe=6; int Colonne=5;
        System.out.println("quanti giocatori stanno giocando? (non meno di 2 o più di 4)");
        while(numPlayers < 2 || numPlayers > 4){
        numPlayers = sc.nextInt();
        }
        Player[] giocatori = Player.GeneratePlayers(numPlayers);
        Board board= new Board(numPlayers);
        Game newGame = new Game(giocatori, numPlayers);
        for(Player pl : giocatori)
            System.out.println(pl.getName());


        //Create board with field number of players


        System.out.println("Board generata, premere un tasto per riempirla");
        sc.nextLine();
        board.fillBoard();
        board.printBoard();

        //riga 21-27: azioni temporanee per ogni giocatore, test per vedere se i turni funzionano
        for (Player pl : giocatori) {
            newGame.PlayerTurn(pl, numPlayers);
        }
        /*      for (Player pl : giocatori){
        System.out.println(pl.getName() + " ha preso: ");
        pl.printPicked();
    }
*/        
    System.out.println("\n\n\n");
    Library library=new Library();
    int z=0;
    for (Player pl : giocatori){  //inserisce tessere nella libreria
    
    	
    	System.out.println("LIBRERIA " +pl.getId()+ " GIOCATORE" );
    	
    	library.inserimentoLibrary(pl.printPicked(), library.CreaLibrary(Righe, Colonne), z);
    	z++;
    }

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
