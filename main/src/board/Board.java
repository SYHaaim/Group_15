package board;

import java.util.Random;

/**
 * classe della plancia soggiorno, contiene tutte le tessere e gestisce il loro posizionamento
 */
public class Board {
    //board class, handles main board generation and tiles positioning

    private static final int LEN = 9;
    static final String ANSI_GREEN = "\u001B[32m";
    static final String ANSI_RESET = "\u001B[0m";
    static final String ANSI_WHITE = "\u001B[37m";

    //this board is used to save the structure that is different according to the number of players
    /**
     * matrice usata come struttra per la board principale, cambia dimensioni in base al numero di giocatori
     */
    private static int[][] structureBoard = new int[LEN][LEN];
    /**
     * board principale, contiene le tessere
     */
    private static TileType[][] mainBoard = new TileType[LEN][LEN];
    private static int Players;

    public Board(int numPlayers) {
        Players = numPlayers;
        initBoard(Players);
    }

    //initialize board with numbers 1 if it's a card space, 0 if it's an empty space.

    /**
     * la struttura della board cambia in base al numero di giocatori, se è uno spazio tessera sarà 1, altrimenti 0
     *
     * @param numPlayers numero di giocatori nella partita
     */
    private void initBoard(int numPlayers) {
        //this variable will be 1 only if there are 3 or 4 players else, they'll be 0
        int p3 = 0;
        int p4 = 0;

        switch (numPlayers) {
            case 4:
                p4 = 1;
            case 3:
                p3 = 1;
        }

        structureBoard = new int[][]{{0, 0, 0, p3, p4, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, p4, 0, 0, 0},
                {0, 0, p3, 1, 1, 1, p3, 0, 0},
                {0, p4, 1, 1, 1, 1, 1, 1, p3},
                {p4, 1, 1, 1, 1, 1, 1, 1, p4},
                {p3, 1, 1, 1, 1, 1, 1, p4, 0},
                {0, 0, p3, 1, 1, 1, p3, 0, 0},
                {0, 0, 0, p4, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, p4, p3, 0, 0, 0}};


    }

    /**
     * riempe la plancia con tipi di tessere randomiche
     */
    public void fillBoard() {

        Random random = new Random();

        int randNum;

        for (int i = 0; i < LEN; i++) {
            for (int j = 0; j < LEN; j++) {

                if (structureBoard[i][j] == 0)
                    mainBoard[i][j] = null;
                else {
                    randNum = random.nextInt(6) + 1;
                    //cardsLeft[randNum-1]--;
                    mainBoard[i][j] = TileType.assignTileType(randNum);
                }

            }
        }

    }

    public void printBoard() {
        char startRow = 'A';

        //Print the numeric coordinates on top
        System.out.printf("%-10s", "");
        for (int i = 1; i <= LEN; i++) {
            System.out.printf("%-6s", i);
        }
        //separates coordinates and board
        System.out.println("\n----------------------------------------------------------------------------------------------");

        //print the board with vertical coordinates, * if the cell is empty, or the name of the cell
        for (int i = 0; i < LEN; i++) {
            System.out.printf("%-5s", (char) (startRow + i));
            //separates coordinates and board
            System.out.printf("%-5s", " | ");
            for (int j = 0; j < LEN; j++) {
                if (mainBoard[i][j] == null)
                    System.out.printf(ANSI_WHITE + "%-10s", "*" + ANSI_RESET);
                else
                    System.out.printf(ANSI_GREEN + "%-10s", mainBoard[i][j] + ANSI_RESET);
            }
            System.out.println();
        }
    }

    /**
     * metodo dove avviene l'estrazione delle tessera da parte del giocatore
     *
     * @param row    riga dove si trova la tessera che si vuole prendere
     * @param column colonna dove si trova la tessera che si vuole prendere
     * @return valore della tessera specificata
     */
    public TileType pickFromBoard(int row, int column) {

        int correctRow = row - 1;
        int correctCol = column - 1;

        TileType picked = mainBoard[correctRow][correctCol];
        mainBoard[correctRow][correctCol] = null;
        return picked;
    }

    public static boolean checkFillBoard() {
        for (int i = 0; i < LEN; i++) {
            for (int j = 0; j < LEN; j++) {
                if (mainBoard[i][j] != null) {
                    try {
                        if ((i < LEN && mainBoard[i + 1][j] != null) ||
                                (i > 0 && mainBoard[i - 1][j] != null) ||
                                (j < LEN && mainBoard[i][j + 1] != null) ||
                                (j > 0 && mainBoard[i][j - 1] != null)) {
                            return false;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(e.toString());
                    }
                }
            }
        }
        return true;
    }

    public int[][] getstructureBoard() {
        return structureBoard;
    }

    public TileType[][] getMainBoard() {
        return mainBoard;
    }

}
