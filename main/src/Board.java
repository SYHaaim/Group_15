import java.util.Random;

public class Board {
    //board class, handles main board generation and tiles positioning
		
		private final int LEN=9;
		
		//this board is used to save the structure that is different according to the number of players
		private static int[][] structureBoard;
		private static TileType[][] mainBoard;
		
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
						case 1:	mainBoard[i][j]= TileType.C;  break;
						case 2:	mainBoard[i][j]= TileType.B;  break;
						case 3:	mainBoard[i][j]= TileType.T;  break;
						case 4:	mainBoard[i][j]= TileType.F;  break;
						case 5:	mainBoard[i][j]= TileType.TR;  break;
						case 6:	mainBoard[i][j]= TileType.P; break;
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
		public static TileType pickFromBoard(int row, int column){
			TileType picked = mainBoard[row-1][column-1];
			structureBoard[row-1][column-1] = 0;
			mainBoard[row-1][column-1] = null;
			return picked;
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
