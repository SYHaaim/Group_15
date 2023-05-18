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
	
	int g=0;    //visualizzazione libreria
	int f=0;
	int h=0;

	while(g<6 && h<6)
	{
	
	for(f=0;f<5;f++)
	{
		
			System.out.print(libreria[h][f]+"\t\t");
			
		}
	System.out.print("\n");
	h++;
	g++;
	}
	
	System.out.println("\n*************************************************************************************");
	}
	
}
