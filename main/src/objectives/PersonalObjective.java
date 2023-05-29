package objectives;

/**
 * gestisce l'assegnazione degli obbiettivi personali
 */
public class PersonalObjective {


    /**
     * rappresenta una carta degli obbiettivi personali, lista di 6 obbiettivi
     */
    private final String[] objectives;
    private final PersonalObjectiveCards generator;

    public PersonalObjective() {
        this.generator = new PersonalObjectiveCards();
        this.objectives = generator.getGeneratedCard();
    }

    /**
     * stampa la lista degli obbiettivi personali del giocatore corrente
     */
    public void printObj() {
        for (String obj : this.objectives)
            System.out.println(obj);

    }

    /**
     *
     * @return lista degli obbiettivi personali del giocatore corrente
     */
    public String[] getObjectives() {
        return objectives;
    }
}


