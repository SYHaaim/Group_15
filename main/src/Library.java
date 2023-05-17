import java.util.*;

public class Library {
	
	private int Righe=6;
	private int Colonne= 5;
	
	
	public Library() {
		
		this.Righe=Righe;
		this.Colonne=Colonne;
	
		
		
	}
	
	public TileType[][] CreaLibrary(int Righe, int Colonne) {
		
	TileType[][] libreria = new TileType[Righe][Colonne];	
	return libreria;
	
	}
	
	public void inserimentoLibrary(TileType[] tileTypes, TileType[][] libreria, int z) {
	
		for(int i=0;i<(tileTypes.length);i++)
		{
			libreria[i][z]=tileTypes[i];
		}
		System.out.println(libreria);
		
	}
	
}
