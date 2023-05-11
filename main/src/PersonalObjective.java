


public class PersonalObjective{

	//changes to be made?

	private final String[] objectives;
	public PersonalObjective() {
		this.objectives = PersonalObjectiveCards.getRandomObjectiveCard();
	}

	//"x coord. - y coord. - tile type"

	public void printObj(){
		for (String obj : this.objectives)
			System.out.println(obj);

	}
}


