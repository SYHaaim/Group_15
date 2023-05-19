import java.util.Random;

public class Board {
    //board class, handles main board generation and tiles positioning

    private static final int LEN=9;

    //this board is used to save the structure that is different according to the number of players
    private static int[][] structureBoard = new int[LEN][LEN];
    private static TileType[][] mainBoard = new TileType[LEN][LEN];

    //private static int[] cardsLeft = new int[] {22,22,22,22,22,22};


    private static int Players;
    public Board(int numPlayers) {
        Players = numPlayers;
        initBoard(Players);

        //printBoard();
    }

    //initialize board with numbers 1 if it's a card space, 0 if it's an empty space.
    private void initBoard(int numPlayers){
        //this variable will be 1 only if there are 3 or 4 players else, they'll be 0
        int p3 = 0;
        int p4 = 0;

        switch(numPlayers){
            case 4: p4=1;
            case 3:	p3=1;
        }

        structureBoard = new int[][] {{0, 0, 0, p3, p4, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, p4, 0, 0, 0},
                {0, 0, p3, 1, 1, 1, p3, 0, 0},
                {0, p4, 1, 1, 1, 1, 1, 1, p3},
                {p4, 1, 1, 1, 1, 1, 1, 1, p4},
                {p3, 1, 1, 1, 1, 1, 1, p4, 0},
                {0, 0, p3, 1, 1, 1, p3, 0, 0},
                {0, 0, 0, p4, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, p4, p3, 0, 0, 0}};


    }

    public void fillBoard() {
    	
    	Random random = new Random();

        int randNum;

        for(int i=0; i<LEN; i++) {
            for(int j=0; j<LEN; j++) {

                if(structureBoard[i][j]==0)
                    mainBoard[i][j]=null;
                else {
                    randNum = random.nextInt(6)+1;
                    //cardsLeft[randNum-1]--;
                    mainBoard[i][j] = TileType.assignTileType(randNum);
                }

            }
        }

    }

    public void printStructureBoard() {
        for (int i=0; i<LEN; i++) {
            for (int j=0; j<LEN; j++)
                System.out.print(structureBoard[i][j]+" ");

            System.out.println();
        }
    }

    public void printBoard() {
        char startRow = 'A';

        //Print the numeric coordinates on top
        System.out.printf("%-10s", "");
        for (int i=1; i<=LEN; i++) {
            System.out.printf("%-10s", i);
        }
        //separates coordinates and board
        System.out.println("\n----------------------------------------------------------------------------------------------");

        //print the board with vertical coordinates, * if the cell is empty, or the name of the cell
        for (int i=0; i<LEN; i++) {
            System.out.printf("%-5s", (char)(startRow + i));
            //separates coordinates and board
            System.out.printf("%-5s"," | ");
            for (int j=0; j<LEN; j++) {
                if(mainBoard[i][j]==null)
                    System.out.printf("%-10s", "*");
                else
                    System.out.printf("%-10s", mainBoard[i][j]);
            }
            System.out.println();
        }
    }

    public TileType pickFromBoard(int row, int column, int prevRow, int prevCol) throws NoSuchFieldException {

        BoardNavigator nav = new BoardNavigator(Players);
        int correctRow = row-1;
        int correctCol = column-1;
        int correctPrevRow = prevRow-1;
        int correvtPrevCol = prevCol-1;

        if (nav.isTileNullOrEmpty(correctRow, correctCol))
            throw new NoSuchFieldException("casella vuota in riga: " + row + " colonna: " + column );

        if (!nav.IsTilePickable(correctRow, correctCol))
            throw new IllegalArgumentException("casella circondata, non possibile prenderla");
        if (!nav.isAdjacent(correctRow, correctCol,correctPrevRow,correvtPrevCol))
            throw new IllegalArgumentException("la casella selezionata non è adiacente a quella  presa in precedenza");

        TileType picked = mainBoard[correctRow][correctCol];
        //structureBoard[row-1][column-1] = 0;
        mainBoard[correctRow][correctCol] = null;
        return picked;
    }

    public static boolean checkFillBoard(){
        for(int i=0; i<LEN; i++) {
            for(int j=0; j<LEN; j++) {
                if (mainBoard[i][j]!=null) {
                    try {
                        if((i<LEN && mainBoard[i+1][j]!=null) ||
                                (i>0 && mainBoard[i-1][j]!=null) ||
                                (j<LEN && mainBoard[i][j+1]!=null) ||
                                (j>0 && mainBoard[i][j-1]!=null)) {
                            return false;
                        }
                    }catch(ArrayIndexOutOfBoundsException e) {
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

    public void setstructureBoard(int[][] board) {
        structureBoard = board;
    }

    public TileType[][] getMainBoard() {
        return mainBoard;
    }

    public void setMainBoard() {
        mainBoard = new TileType[LEN][LEN];
    }
    
    public void setCasellaMainBoard() {
        mainBoard = new TileType[LEN][LEN];
    }
    
    public static TileType getCasellaMainBoard(int r,int c) {
    	return mainBoard[r][c];
    }
    
    public static int getCasellaStructureBoard(int r,int c) {
    	return structureBoard[r][c];
    }

}
