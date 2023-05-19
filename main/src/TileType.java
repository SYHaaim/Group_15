
import java.util.ArrayList;
import java.util.Random;

public enum TileType {
    C("CATS"),
    B("BOOKS"),
    T("TOYS"),
    F("FRAMES"),
    TR("TROPHIES"),
    P("PLANTS");
	
	private static int[] cardsLeft = new int[] {22,22,22,22,22,22};

    public final String tileName;
     TileType(String tileName){
        this.tileName = tileName;
    }
    
public static TileType assignTileType(int n) throws IllegalArgumentException{
    	
    	if(n<1 || n>6)
    		throw new IllegalArgumentException("IllegalArgumentException");
    	
    	switch(n) {
	        case 1:	cardsLeft[n-1]--;return C; 
	        case 2:	cardsLeft[n-1]--;return B; 
	        case 3:	cardsLeft[n-1]--;return T; 
	        case 4:	cardsLeft[n-1]--;return F; 
	        case 5:	cardsLeft[n-1]--;return TR; 
	        case 6:	cardsLeft[n-1]--;return P;
    	}
    	
    	return null;
    }
}
