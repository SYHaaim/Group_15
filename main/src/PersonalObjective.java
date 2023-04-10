public class PersonalObjective{
	
	private int[][] personal;

	public void NewPersonal(){
		
		this.personal = new int[][] {   {0, 0, 0, 0, 0},
										{0, 0, 0, 0, 0},
										{0, 0, 0, 0, 0},
										{0, 0, 0, 0, 0},
										{0, 0, 0, 0, 0},
										{0, 0, 0, 0, 0}};
		
		int rows = 6;
		int columns = 5;
		int i;
		int j;
		for(i=0;i<rows;i++) {
			j=(int)(Math.random()*5);
				
			// i valori asegnati a (objective i,j), da 1 a 6 sono i colori delle tiles
			// che devono fare match con la libreria del giocatore 
			personal[i][j]=(int)(Math.random() *7); 
		}
		for(i=0;i<rows;i++){
			for(j=0;j<columns;j++) {
				System.out.print(personal[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
}	
		
	
	