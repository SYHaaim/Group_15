package objectives;

import java.io.FileNotFoundException;

import board.TileType;
import library.Library;

public class CommonObjective{

		private int cardId;
		private String descrizione;
		private CommonObjectiveGenerator generator;
		private int[] objectivePoiints = {8,4,6,2};

 		public CommonObjective() throws FileNotFoundException {
			this.generator = new CommonObjectiveGenerator();
			this.descrizione = generator.getGeneratedObjective();
			this.cardId = generator.getGeneratedId();

		}

		public void printCommonObj(){
			System.out.println(this.cardId +" - "+this.descrizione);
		}

		public void checkCommonObjectives(int objectiveId, TileType[][] tileTypes){
			 //gli id sono progressivi rispettivamente il loro ordinamento nel file "description.txt"
			
			switch (objectiveId){
				case 1:
							
								
				case 2: //Quattro tessere dello stesso tipo ai quattro angoli della Libreria.
					
					if(tileTypes[5][0]==tileTypes[0][0] &&
							tileTypes[0][4]==tileTypes[5][0] &&
									tileTypes[5][4]==tileTypes[5][0] )
							{
							System.out.println("4 tessere uguali agli angoli......");}
					break;
				case 3:
					 
					int groupCounter = 0;
					for(int c=0;c<4;c++)
					{
						int tileCounter = 0;
						for(int r=0;r<5;r++)
						{
							if(tileTypes[r][c] == tileTypes[r+1][c]) {
								tileCounter++;
								
								if(tileCounter == 3) {
									groupCounter++;
								}
							}
							else {
								tileCounter = 0;
							}
						}
					}
					
					for(int r=0;r<5;r++)
					{
						int tileCounter = 0;
						for(int c=0;c<4;c++)
						{
							if(tileTypes[r][c] == tileTypes[r][c+1]) {
								tileCounter++;
								
								if(tileCounter == 3) {
									groupCounter++;
								}
							}
							else {
								tileCounter = 0;
							}
						}
					}
					
					if(groupCounter > 1) {						
						System.out.println("Quattro gruppi separati formati ciascuno da quattro tessere adiacenti dello stesso tipo");
					}
					
					
					
					
					
				case 4:
				case 5:
				case 6:
				case 7: //5 tessere dello stesso tipo che formano una diagonale
					boolean find = true;
					
					for(int i=0; i<2; i++) {
						int r=i, c=0;
						TileType t = tileTypes[r][c];
						for(int j=0; j<5; j++) {
							find=true;
							if(t!=tileTypes[r][c]) {
								find=false;
								break;
							}
							r++; c++;
						}
					}
					
					for(int i=0; i<2; i++) {
						int r=i, c=4;
						TileType t = tileTypes[r][c];
						for(int j=0; j<5; j++) {
							find=true;
							if(t!=tileTypes[r][c]) {
								find=false;
								break;
							}
							r++; c--;
						}
					}
					
					if(find==true) {
						System.out.println("5 tessere uguali in diagonale......");
					}
					
					break;
					
				case 8:
				case 9: //Due colonne formate ciascuna da 6 diversi tipi di tessere
					
					int colonne=0,countcolonne=0;
					for(int i=0;i<tileTypes.length;i++)
					{
						for(int j=0;j<tileTypes.length;j++)
						{
							if(tileTypes[i][j]!=tileTypes[i+1][j])
							{
								colonne++;
								if(colonne==5)
								{
									System.out.println("colonna "+(j+1)+ "con tutti elementi diversi");
									countcolonne++;
								}
							}
							if(countcolonne==2)
								break;
						}
						
					}
				
					
				case 10: //Due righe formate ciascuna da 5 diversi tipi di tessere.
					
					int Righe=0,contRighe=0;
					for(int i=0;i<tileTypes.length;i++)
					{
						for(int j=0;j<tileTypes.length;j++)
						{
							if(tileTypes[i][j]!=tileTypes[i][j+1])
							{
								Righe++;
								if(Righe==4)
								{
									System.out.println("riga "+(i+1)+ "con tutti elementi diversi");
									contRighe++;
								}
							}
							if(contRighe==2)
								break;
						}
						
					}
					
				case 11:
				case 12:
					int prec = Integer.MAX_VALUE;
					boolean objectiveCompleted = true;
					for(int i=0; i<5; i++) {
						int cont=0;
						for(int j=0; j<6; j++) {
							if(tileTypes[i][j]!=null)
								cont++;
						}
						if(cont>prec) {
							objectiveCompleted = false;
							break;
						}	
						prec=cont;
					}
					if(objectiveCompleted == true) {
						System.out.println("5 colonne di altezza decrescente");
						break;
					}
					
					prec = 0;
					objectiveCompleted = true;
					for(int i=0; i<5; i++) {
						int cont=0;
						for(int j=0; j<6; j++) {
							if(tileTypes[i][j]!=null)
								cont++;
						}
						if(cont<prec) {
							objectiveCompleted = false;
							break;
						}
						prec=cont;
					}
					
					if(objectiveCompleted == true) {
						System.out.println("5 colonne di altezza crescente");
						break;
					}
			}
		}

	
}
	               
	       


