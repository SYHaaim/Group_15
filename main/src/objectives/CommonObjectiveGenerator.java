package objectives;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.math.*;

public class CommonObjectiveGenerator {
    //private static final ArrayList<String[]> TessereObbComuni = new ArrayList<>();

    private String[] readFromFile() throws FileNotFoundException {
    	String line = null;
		String[] goals = new String[10];
		Scanner scdescription=new Scanner(new File("description.txt"));             
		int i = 0;
		while(scdescription.hasNext()) {                 
		line =scdescription.nextLine();                 
		goals[i]= line;
		i++;
		}
		return goals;
    }
    
   // private String[] randomGoal(int nPlayer) {
    	
    	//int prova = (int) (Math.random() * nPlayer);
    
    //}
}

