
import java.util.ArrayList;
import java.util.Random;

public enum TileType {
    C("CATS"),
    B("BOOKS"),
    T("TOYS"),
    F("FRAMES"),
    TR("TROPHIES"),
    P("PLANTS");

    public final String tileName;
     TileType(String tileName){
        this.tileName = tileName;
    }

    public static TileType generateRandomType(int cases){

        TileType result = null;
        switch (cases){
            case 1:
                result = C;
                break;
            case 2:
                result = B;
                break;
            case 3:
                result = T;
                break;
            case 4:
                result = F;
                break;
            case 5:
                result = TR;
                break;
            case 6:
                result = P;
                break;

        }

        return result;
    }
}
