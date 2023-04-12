import java.util.Random;

public class Board {
    //board class, handles main board generation and tiles positioning
		
		private final int LEN=9;
		
		//this board is used to save the structure that is different according with the number of players
		private int[][] structureBoard;			
		private TileType[][] mainBoard;
		
		private int cardsLeft[] = new int[] {22,22,22,22,22,22};
		
		Random random = new Random();

		public Board(int numPlayers) {
			structureBoard = new int[LEN][LEN];
			initBoard(numPlayers);
			setMainBoard();
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
			
			int randNum;
			
			for(int i=0; i<LEN; i++) {
				for(int j=0; j<LEN; j++) {
					
					if(structureBoard[i][j]==0)
						mainBoard[i][j]=null;
					else {
						randNum = random.nextInt(6)+1;
						cardsLeft[randNum-1]--;
						switch(randNum) {
						case 1:	mainBoard[i][j]= TileType.CATS;  break;
						case 2:	mainBoard[i][j]= TileType.BOOKS;  break;
						case 3:	mainBoard[i][j]= TileType.TOYS;  break;
						case 4:	mainBoard[i][j]= TileType.FRAMES;  break;
						case 5:	mainBoard[i][j]= TileType.TROPHIES;  break;
						case 6:	mainBoard[i][j]= TileType.PLANTS; break;
						}
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
			for (int i=0; i<LEN; i++) {
				for (int j=0; j<LEN; j++) {
					if(mainBoard[i][j]==null)
						System.out.printf("%-10s", "X");
						//System.out.print("%10s %10s %10s", mainBoard[i][j]);
					else
						System.out.printf("%-10s", mainBoard[i][j]);
				}
				System.out.println();
			}
		}
		
		public int[][] getstructureBoard() {
			return structureBoard;
		}

		public void setstructureBoard(int[][] board) {
			this.structureBoard = board;
		}
		
		public TileType[][] getMainBoard() {
			return mainBoard;
		}

		public void setMainBoard() {
			mainBoard = new TileType[LEN][LEN];
		}
}
