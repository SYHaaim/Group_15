public class BoardNavigator extends Board {

    private int [][] tempStructure;
    TileType[][] tempBoard;
    private final int LEN = 9;

    public BoardNavigator(int numPlayers) {
        super(numPlayers);
        tempBoard = getMainBoard();
        tempStructure = getstructureBoard();
    }

    TileType getRight(int row, int column){

        if ((column+1) < LEN)
            return tempBoard[row][column+1];

        return tempBoard[row][column];
    }

     TileType getLeft(int row, int column){

        if ((column-1) > 0)
            return tempBoard[row][column-1];

        return tempBoard[row][column];
    }

     TileType getUp(int row, int column){

        if ((row-1) > 0)
            return tempBoard[row-1][column];

        return tempBoard[row][column];
    }

     TileType getDown(int row, int column){

        if ((row+1) < LEN)
            return tempBoard[row+1][column];

        return tempBoard[row][column];
    }

    public boolean IsTilePickable(int row, int column){

        if (getUp(row, column) == null)
            return true;
        else if (getDown(row, column) == null)
            return true;
        else if (getRight(row, column) == null)
            return true;
        else return getLeft(row, column) == null;
    }

    public boolean isTileNullOrEmpty(int row, int column){
        if (tempStructure[row][column] == 0)
            return true;

        return tempBoard[row][column] == null;
    }

}
