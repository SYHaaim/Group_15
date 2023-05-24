package objectives;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.math.*;

public class CommonObjectiveGenerator {
    //private static final ArrayList<String[]> TessereObbComuni = new ArrayList<>();

	private String generatedObjective;
	private int generatedId;

	public CommonObjectiveGenerator() throws FileNotFoundException {
		generatedObjective = readFromFile();
	}

	public String getGeneratedObjective() {
		return generatedObjective;
	}

	public int getGeneratedId() {
		return generatedId;
	}

	private String readFromFile() throws FileNotFoundException {
    	String line = null;
		String goals;
		Random r = new Random();
		Scanner scdescription=new Scanner(new File("resources/description.txt"));
		int i = 0;
		while(scdescription.hasNext()) {                 
			line += scdescription.nextLine();
		}
		assert line != null;
		String[] objs = line.split("-");
		this.generatedId = r.nextInt(9)+1;
		goals = objs[this.generatedId];
		return goals;
    }
    
   // private String[] randomGoal(int nPlayer) {
    	
    	//int prova = (int) (Math.random() * nPlayer);
    
    //}
}

