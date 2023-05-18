import java.util.*;

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
				libreria[i][z] = tileTypes[pickedCounter];
				pickedCounter++;
				if (pickedCounter > (tileTypes.length-1))
					break;
		}
	
	System.out.println("*****************************************************************************************\n");
	
	int Cont=0;    //visualizzazione libreria
	int ContColonne=0;
	int ContRighe=0;

	while(Cont<6 && ContRighe<6)
	{
	
	for(ContColonne=0;ContColonne<5;ContColonne++)
	{
		
			System.out.print(libreria[ContRighe][ContColonne]+"\t\t");
			
		}
	System.out.print("\n");
	ContRighe++;
	Cont++;
	}
	
	System.out.println("\n*************************************************************************************");
	}
	
}
