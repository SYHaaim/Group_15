import board.Board;
import board.BoardNavigator;

import java.util.*;

public class Game {
    //handles player turns and endgame etc..

    Player[] players;
    int numPlayers;
    public Game(Player[] players, int numPlayers){
        this.players = players;
        this.numPlayers = numPlayers;
    }
    public void PrintLeaderboard(Player[] players){
        SortByPoints(players);
        for (Player player : players)
            System.out.println(player.getId() + ". " + player.getName() + " punti: " + player.getPoints());
    }
    void SortByPoints(Player[] players){
        for (int i = 0; i < players.length-1; i++){
            if (players[i].getPoints() < players[i+1].getPoints()){
                Player tmp = players[i+1];
                players[i+1] = players[i];
                players[i]=tmp;
            }
        }
    }
    public void PlayerTurn(Player pl, int numPlayers) throws NoSuchFieldException {

        BoardNavigator nav = new BoardNavigator(numPlayers);
        int column = 0;
        char row = ' ';
        char prevRow = ' ';
        int prevCol = 0;
        int pickedCount = 0;
        boolean pickMore = true;
        Scanner sc = new Scanner(System.in);

        System.out.println("TURNO DI: " + pl.getName());
        while(pickMore){


            row = ' '; //   se il giocatore decide di prendere un'altra casella, una volta finito il primo ciclo, resetto riga e colonna -
            column = 0; // - cosicchè non mi venga dato l'errore di casella vuota, visto che i valori precedenti rimarrebbero salvati

            while(!isValidTile(row, column, prevRow, prevCol, numPlayers)){
                row = provideValidInput(row);
                column = provideValidInput(column);
            }

            pl.pickTiles(row, column, pickedCount, prevRow, prevCol,numPlayers);
            pickedCount++;
            if(pickedCount > 2)
                break;

            System.out.println("vuoi prendere altre caselle? (si/no)");
            if (sc.next().equalsIgnoreCase("NO")){
                pickMore = false;
            }

            prevRow = row;
            prevCol = column;

        }

        if(Board.checkFillBoard()) {
        	nav.fillBoard();
        }
    }

    public void insertPicked(Player pl) {
        System.out.println("\n\n"  + "OBIETTIVI DI " + pl.getName() + ": ");
        pl.printObjective();
        System.out.println("\n\n" + pl.getName() + "HA PRESO: ");
        pl.printPicked();
        System.out.println("\n\n");
        Scanner sc = new Scanner(System.in);
        System.out.println("in caso di errore ricomicerai il processo di inserimento da capo");
        System.out.print(pl.getName() + "\n\nIn che colonna vuoi inserire le tessere prese?.....\t");
        int insertionColumn = sc.nextInt()-1;

        try{
            pl.insertInLibrary(insertionColumn);
        }catch (Exception e){
            System.out.println(e + ", riprova");
            insertPicked(pl);
        }

        System.out.println("LIBRERIA DI " +pl.getName());
        System.out.println("\n");

        pl.printPlayerLibrary();
        System.out.println("\n");
    }

      char provideValidInput(char in){
        boolean isValid = false;
        Scanner sc = new Scanner(System.in);

        while(!isValid){

            System.out.println("Scegli la riga da cui prendere una casella (A - I)");
            try {
                in = sc.next().toUpperCase().charAt(0);
                char startRow = 'A';
                char endRow = 'I';
                if (Character.getNumericValue(in) >= (Character.getNumericValue(startRow)) && Character.getNumericValue(in) <= (Character.getNumericValue(endRow))) {
                    isValid = true;
                } else {
                    System.out.println("input invalido. scegli una lettera tra A ed I");
                }
            } catch (InputMismatchException e) {
                System.out.println("input invalido. scegli una lettera tra A ed I");
                sc.nextLine(); // consume the remaining input
            }
        }
        return in;
    }
      int provideValidInput(int in){
        boolean isValid = false;
        Scanner sc = new Scanner(System.in);
        while(!isValid){

            System.out.println("Scegli la colonna da cui prendere una casella (1 - 9)");
            try {
                in = sc.nextInt();
                if (in >= 1 && in <= 9) {
                    isValid = true;
                } else {
                    System.out.println("input invalido. scegli un numero tra 1 e 9");
                }
            } catch (InputMismatchException e) {
                System.out.println("input invalido. scegli un numero tra 1 e 9");
                sc.nextLine(); // consume the remaining input
            }
        }
        return in;
    }


    public boolean isValidTile(char row, int col, char prevRow, int prevCol, int numPlayers) {

        BoardNavigator nav = new BoardNavigator(numPlayers);
        int correctRow = (Character.getNumericValue(row) - 9)-1;
        int correctCol = col-1;
        int correctPrevRow = (Character.getNumericValue(prevRow) - 9)-1;
        int correctPrevCol = prevCol-1;

        if (correctRow < 0)
            return false;

        if (nav.isTileNullOrEmpty(correctRow, correctCol)){
            System.out.println("casella vuota in riga: " + row + " colonna: " + col);
            return false;
        }

        if (!nav.IsTilePickable(correctRow, correctCol)){
            System.out.println("casella circondata, non possibile prenderla");
            return false;
        }

        if (!nav.isAdjacent(correctRow, correctCol,correctPrevRow,correctPrevCol)){
            System.out.println("la casella selezionata non è adiacente a quella  presa in precedenza");
            return false;
        }

        return true;
    }
    public boolean isEndgame(Player[] pl){
        for (Player player : pl){
            if (player.isPlayerLibraryFull()){
                System.out.println(player.getName() + " ha riempito la libreria");
                return true;
            }

        }
        return false;
    }
}
