package objectives;


public class PersonalObjective {

    //changes to be made?

    private final String[] objectives;
    private PersonalObjectiveCards generator;

    public PersonalObjective() {
        this.generator = new PersonalObjectiveCards();
        this.objectives = generator.getGeneratedCard();
    }

    //"x coord. - y coord. - tile type"

    public void printObj() {
        for (String obj : this.objectives)
            System.out.println(obj);

    }

    public String[] getObjectives() {
        return objectives;
    }
}


