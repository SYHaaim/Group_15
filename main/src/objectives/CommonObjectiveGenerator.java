package objectives;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CommonObjectiveGenerator {
    private static final ArrayList<String[]> TessereObbComuni = new ArrayList<>();

    private void readFromFile() throws FileNotFoundException {
    	String line = null;
		String[] campi = new String[10];
		Scanner scdescription=new Scanner(new File("description.txt"));             
		int i = 0;
		while(scdescription.hasNext()) {                 
		line =scdescription.nextLine();                 
		campi[i]= line;
		i++;
		}
    }
}
