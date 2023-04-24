
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

    public static void concatenateToString(String[] objvs){
        Random r = new Random();

        //randInts contains 6 unique integers to generate 6 unique tile types in random order
        ArrayList<Integer> randInts = new ArrayList<>();

        while (randInts.size() < 6) {
            int a = r.nextInt(6)+1;

            if (!randInts.contains(a)) {
                randInts.add(a);
            }
        }

        for(int i = 0; i < objvs.length; i++){
            objvs[i] += " - " + generateRandomType(randInts.get(i));
        }
    }
    private static String generateRandomType(int cases){

        String result = "";
        switch (cases){
            case 1:
                result = "C";
                break;
            case 2:
                result = "B";
                break;
            case 3:
                result = "T";
                break;
            case 4:
                result = "F";
                break;
            case 5:
                result = "TR";
                break;
            case 6:
                result = "P";
                break;

        }

        return result;
    }
}
