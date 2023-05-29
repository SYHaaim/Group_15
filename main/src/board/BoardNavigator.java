package board;

/**
 * usata per navigare tra gli spazi della board principale
 */
public class BoardNavigator extends Board {

    private int [][] tempStructure;
    TileType[][] tempBoard;
    private final int LEN = 9;

    public BoardNavigator(int numPlayers) {
        super(numPlayers);
        tempBoard = getMainBoard();
        tempStructure = getstructureBoard();
    }

    /**
     *
     * @param row riga della tessera corrente
     * @param column colonna della tessera corrente
     * @return tessera a destra di quella specificata
     */
    TileType getRight(int row, int column){

        if ((column+1) < LEN)
            return tempBoard[row][column+1];

        return tempBoard[row][column];
    }

    /**
     *
     * @param row riga della tessera corrente
     * @param column colonna della tessera corrente
     * @return tessera a sinistra di quella specificata
     */
     TileType getLeft(int row, int column){

        if ((column-1) > 0)
            return tempBoard[row][column-1];

        return tempBoard[row][column];
    }

    /**
     *
     * @param row riga della tessera corrente
     * @param column colonna della tessera corrente
     * @return tessera sopra a quella specificata
     */
     TileType getUp(int row, int column){

        if ((row-1) > 0)
            return tempBoard[row-1][column];

        return tempBoard[row][column];
    }

    /**
     *
     * @param row riga della tessera corrente
     * @param column colonna della tessera corrente
     * @return tessera sotto a quella specificata
     */
     TileType getDown(int row, int column){

        if ((row+1) < LEN)
            return tempBoard[row+1][column];

        return tempBoard[row][column];
    }

    /**
     *
     * @param row riga della tessera specificata
     * @param column colonna della tessera specificata
     * @return true: la tessera ha almeno un lato libero / false: la tessera è circondata
     */
    public boolean IsTilePickable(int row, int column){
        return getUp(row, column) == null || getDown(row, column) == null ||
                getLeft(row, column) == null || getRight(row, column) == null;
    }

     public boolean isTileNullOrEmpty(int row, int column){
        if (tempStructure[row][column] == 0)
            return true;

        return tempBoard[row][column] == null;
    }

    public boolean isAdjacent(int row,int column, int prevRow, int prevCol){
        if (prevRow < 0 && prevCol < 0)
            // se prevRow e preCol valgono minore di 0 vuol dire che la tessera presa è la prima delle (massimo) 3 che si possono prendere
            return true;

        if (row == prevRow){
            return tempBoard[prevRow][prevCol] == getRight(row, column) || tempBoard[prevRow][prevCol] == getLeft(row, column);
        } else if (column == prevCol) {
            return tempBoard[prevRow][prevCol] == getUp(row, column) || tempBoard[prevRow][prevCol] == getDown(row, column);
        }

        return false;
    }

}
