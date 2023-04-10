import java.util.Random;

public class PersonalObjective{

	private String[] objectives;

	public PersonalObjective(){
		this.objectives = new String[6];
		generatePersonal();
	}

	//da aggiungere il tipo della tessera nella formattazione degli obbiettivi: "x coord. - y coord. - tile type"
	public void generatePersonal(){
		String toAdd = " ";
		Random r = new Random();

		for (int i = 0; i < objectives.length; i++){
			toAdd = (r.nextInt(5) + 1) + " - " + (r.nextInt(6)+1);
			objectives[i] = toAdd;
		}
	}

	public void printObj(){
		for (String obj : this.objectives)
			System.out.println(obj);
	}
}


