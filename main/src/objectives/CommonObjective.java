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
				case 4:
				case 5:
				case 6:
				case 7:
				case 8:
				case 9: //Due righe formate ciascuna da 5 diversi tipi di tessere.
					
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
					
				case 10:
			}
		}

	
}
	               
	       


