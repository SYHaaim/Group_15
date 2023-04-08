public class Board {
    //board class, handles main board generation and tiles positioning
	private final int LEN=9;
	private int[][] board;

	public Board(int numPlayers) {
		board = new int[LEN][LEN];
		initBoard(numPlayers);
		//printBoard();
	}

	//initialize board with numbers 1 if it's a card space, 0 if it's an empty space.
	private void initBoard(int numPlayers){
		//this variable will be 1 only if there are 3 or 4 players else, they'll be 0
		int p3 = 0;
		int p4 = 0;

		switch(numPlayers){
			case 4: p4=1;
			case 3:
				p3=1;
		}

		board = new int[][] {{0, 0, 0, p3, p4, 0, 0, 0, 0},
				{0, 0, 0, 1, 1, p4, 0, 0, 0},
				{0, 0, p3, 1, 1, 1, p3, 0, 0},
				{0, p4, 1, 1, 1, 1, 1, 1, p3},
				{p4, 1, 1, 1, 1, 1, 1, 1, p4},
				{p3, 1, 1, 1, 1, 1, 1, p4, 0},
				{0, 0, p3, 1, 1, 1, p3, 0, 0},
				{0, 0, 0, p4, 1, 1, 0, 0, 0},
				{0, 0, 0, 0, p4, p3, 0, 0, 0}};
	}

	public void printBoard() {
		for (int i=0; i<LEN; i++) {
			for (int j=0; j<board[i].length; j++){
				if(board[i][j] == 0) {
					System.out.print("  ");
				}
				else
					System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int[][] board) {
		this.board = board;
	}
}
