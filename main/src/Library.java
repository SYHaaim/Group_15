import objectives.PersonalObjective;

public class Library {

	private final int ROWLEN = 6;
	private final int COLUMNLEN = 5;
	TileType[][] libreria;
	
	public Library() {
		this.libreria = new TileType[ROWLEN][COLUMNLEN];
	}
	
	public void inserimentoLibrary(TileType[] tileTypes, int z) {

		int pickedCounter = 0;
		
		for(int i=COLUMNLEN;i>0;i--)
		{
			if (libreria[i][z]!=null)
			{
				libreria[(i-1)][z] = tileTypes[pickedCounter];
				pickedCounter++;
			}
			if(libreria.length==30)
			{ System.out.println(" LIBRERIA COMPLETATA !!!!!!!!");               }
			else {
			
				libreria[i][z] = tileTypes[pickedCounter];
				pickedCounter++;
				if (pickedCounter > (tileTypes.length-1))
					break;
			}
		}

	}

	public void printLibrary(){
		System.out.println("*****************************************************************************************\n");

		char startRow = 'A';

		//Print the numeric coordinates on top

		for (int i=1; i<=COLUMNLEN; i++) {
			System.out.printf("%-10s", i);
		}
		//separates coordinates and library
		System.out.println("\n----------------------------------------------------------------------------------------------");

		//print the board with vertical coordinates, * if the cell is empty, or the name of the cell
		for (int i=0; i<ROWLEN; i++) {
			for (int j=0; j<COLUMNLEN; j++) {
				if(libreria[i][j]==null)
					System.out.printf("%-10s", "*");
				else
					System.out.printf("%-10s", libreria[i][j]);
			}
			System.out.println();
		}

		System.out.println("\n*************************************************************************************");
	}

	public int checkPersonal(PersonalObjective personal){
		String[] tempObj = personal.getObjectives();
		int completedCounter = 0;
		int objectiveRow;
		int objectiveColumn;
		TileType objectiveType;

		for (int i = 0; i < tempObj.length; i++) {
			// gli obb. personali sono formattati come "coord. x - coord. y - tipo tessera"
			String[] split;
			try{
			split = tempObj[i].split("-");}
			catch (Exception e){
				System.out.println(e);
				continue;
			}

			objectiveRow = (Integer.parseInt(split[0].trim()))-1;
			objectiveColumn = (Integer.parseInt(split[1].trim()))-1;
			objectiveType = TileType.valueOf(split[2].trim());
			if (this.libreria[objectiveRow][objectiveColumn] != null && this.libreria[objectiveRow][objectiveColumn].equals(objectiveType)){
				tempObj[i]= "completato";
			}
		}
		for (String s : tempObj){
			if (s.equals("completato")){
				completedCounter++;
			}
		}
		return completedCounter;
	}
}


