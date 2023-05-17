import java.util.*;
public class Library {
	
	private ArrayList<Enum> Righe= new ArrayList();
	private ArrayList<Enum> Colonne= new ArrayList();
	
	
	public Library() {
		
		this.Righe=Righe;
		this.Colonne=Colonne;
	
		
		
	}
	
	public ArrayList<ArrayList<Enum>> CreaLibrary(ArrayList<Enum> Righe, ArrayList<Enum> Colonne) {
		
	ArrayList <ArrayList<Enum>> libreria= new ArrayList();
		
	libreria.add(Righe);
	libreria.add(Colonne);
	
	return libreria;
	
	}
	
	public void inserimentoLibrary(TileType tileType, ArrayList<ArrayList<Enum>> libreria) {
		int i=0;
		
		
		//	Colonne.add(tileType);
		
		
		System.out.println(libreria);
		
	}
	
}
