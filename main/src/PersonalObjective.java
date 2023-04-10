public class PersonalObjective{
	
	private int[][] personal;

	public void NewPersonal(){
		
		this.personal = new int[6][5];
			
		for(int i=1;i<=6;i++){
			personal[(int)(Math.random()*5)][(int)(Math.random()*4)]=i;
		}
										
		int rows = 6;
		int columns = 5;
	
		for(int k=0;k<rows;k++){
			for(int j=0;j<columns;j++) {
				System.out.print(personal[k][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
}	
		
	
	