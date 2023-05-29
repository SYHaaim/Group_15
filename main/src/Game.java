import board.BoardNavigator;

import java.util.*;

/**
 * gestisce la turnanzione dei giocatori
 */
public class Game {
    //handles player turns and endgame etc..
    Player[] players;
    int numPlayers;
    //codici per testi colorati
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public Game(Player[] players, int numPlayers) {
        this.players = players;
        this.numPlayers = numPlayers;
    }

    /**
     * stampa ordinata rispetto ai punti per determinare il vincitore
     * @param players lista di tutti i giocatori nella partita corrente
     */
    public void PrintLeaderboard(Player[] players) {
        SortByPoints(players);
        int count = 1;
        for (Player player : players){
            System.out.println( count + ". " + player.getName() + " punti: " + player.getPoints());
            count++;
        }
    }

    /**
     * ordinamento decrescente rispetto ai punti dei giocatori
     * @param players lista di tutti i giocatori nella partita corrente
     */
    void SortByPoints(Player[] players) {
        for (int i = 0; i < players.length - 1; i++) {
            if (players[i].getPoints() < players[i + 1].getPoints()) {
                Player tmp = players[i + 1];
                players[i + 1] = players[i];
                players[i] = tmp;
            }
        }
    }

    /**
     *
     * @param pl giocatore corrente
     * @param numPlayers numero totale di giocatori nella partita
     */
    public void PlayerTurn(Player pl, int numPlayers) {

        BoardNavigator nav = new BoardNavigator(numPlayers);
        int column = 0;
        char row = ' ';
        char prevRow = ' ';
        int prevCol = 0;
        int pickedCount = 0;
        boolean pickMore = true;
        Scanner sc = new Scanner(System.in);


        System.out.println("TURNO DI: " + pl.getName());
        while (pickMore) {

            if (nav.isBoardAllEmpty()){
                break;
            }

            row = ' '; //   se il giocatore decide di prendere un'altra casella, una volta finito il primo ciclo, resetto riga e colonna -
            column = 0; // - cosicchè non mi venga dato l'errore di casella vuota, visto che i valori precedenti rimarrebbero salvati

            while (!isValidTile(row, column, prevRow, prevCol, numPlayers)) {
                row = provideValidInput(row);
                column = provideValidInput(column);
            }

            pl.pickTiles(row, column, pickedCount,numPlayers);
            pickedCount++;
            if (pickedCount > 2)
                break;

            System.out.println("vuoi prendere altre caselle? (si/no)");
            if (sc.next().equalsIgnoreCase("NO")) {
                pickMore = false;
            }

            prevRow = row;
            prevCol = column;

        }

    }

    /**
     * metodo usato per far inserire al giocatore corrente le tessere prese da esso nella sua libreria
     * @param pl giocatore corrente
     * @throws InterruptedException in caso il wait di 1s vada in errore
     */
    public void insertPicked(Player pl) throws InterruptedException {
        char col;
        int insertionColumn;
        System.out.println("\n" + "OBIETTIVI DI " + pl.getName() + ": ");
        pl.printObjective();
        System.out.println("\n" + pl.getName() + " HA PRESO: ");
        pl.printPicked();
        System.out.println("\n");
        Scanner sc = new Scanner(System.in);
        System.out.println(ANSI_BLUE + "in caso di errore ricomicerai il processo di inserimento da capo" + ANSI_RESET);
        System.out.print(pl.getName() + "\n\nIn che colonna vuoi inserire le tessere prese?.....\t");
        do{
             col = sc.next().charAt(0);
             insertionColumn = Character.getNumericValue(col);
             if (!Character.isDigit(col)){
                 System.out.println(ANSI_RED + "inserire un numero" + ANSI_RESET);
             }
        }while(!Character.isDigit(col));

        try {
            pl.insertInLibrary(insertionColumn);
        } catch (Exception e) {
            System.out.println(ANSI_RED + e + ", riprova (attendi 1 secondo)" +  ANSI_RESET);
            Thread.sleep(1000);
            pl.printPlayerLibrary();
            insertPicked(pl);
        }
    }

    char provideValidInput(char in) {
        boolean isValid = false;
        Scanner sc = new Scanner(System.in);

        while (!isValid) {

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

    int provideValidInput(int in) {
        boolean isValid = false;
        Scanner sc = new Scanner(System.in);
        while (!isValid) {

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


    /**
     * controlla che la tessera specificata dall'utente può essere presa
     * @param row riga in cui si trova la tessera specificata
     * @param col colonna in cui si trova la tessera specificata
     * @param prevRow riga della tessera presa in precedenza
     * @param prevCol colonna della tessera presa in precedenza
     * @param numPlayers numero totale dei giocatori in partita
     * @return true: se la tessera è valida false: se la tessera specificata non può essere presa
     */
    public boolean isValidTile(char row, int col, char prevRow, int prevCol, int numPlayers) {

        BoardNavigator nav = new BoardNavigator(numPlayers);
        int correctRow = (Character.getNumericValue(row) - 9) - 1;
        int correctCol = col - 1;
        int correctPrevRow = (Character.getNumericValue(prevRow) - 9) - 1;
        int correctPrevCol = prevCol - 1;

        if (correctRow < 0)
            return false;

        if (nav.isTileNullOrEmpty(correctRow, correctCol)) {
            System.out.println(ANSI_RED + "casella vuota in riga: " + row + " colonna: " + col + ANSI_RESET);
            return false;
        }

        if (!nav.IsTilePickable(correctRow, correctCol)) {
            System.out.println(ANSI_RED + "tessera circondata, non possibile prenderla" + ANSI_RESET);
            return false;
        }

        if (!nav.isAdjacent(correctRow, correctCol, correctPrevRow, correctPrevCol)) {
            System.out.println(ANSI_RED + "la tessera selezionata non è adiacente a quella presa in precedenza" + ANSI_RESET);
            return false;
        }

        return true;
    }

    /**
     *
     * @param pl lista di tutti i giocatori nella partita corrente
     * @return true: se almeno uno dei giocatori ha riempito la libreria / false: nessuno dei giocatori ha riempito la libreria
     */
    public boolean isEndgame(Player[] pl) {
        for (Player player : pl) {
            if (player.isPlayerLibraryFull()) {
                System.out.println(player.getName().toUpperCase() + " ha riempito la libreria");
                return true;
            }

        }
        return false;
    }
}
