import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CommonObjectiveManager {
    private static final ArrayList<String[]> TessereObbComuni = new ArrayList<>();

    private void readFromFile() throws FileNotFoundException {
        String nomeFile = "carte_obbiettivo_comune";
        Scanner sc = new Scanner(new File(nomeFile));
    }
}
