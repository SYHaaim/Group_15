package objectives;

import java.util.Random;


/**
 * gestisce la generazione randomica di un obbiettivo comune
 */

public class CommonObjectiveGenerator {

    private final String generatedObjective;
    private int generatedId;
    private final String descr = "Sei gruppi separati formati ciascuno da due tessere adiacenti dello stesso tipo. Le tessere di un gruppo possono essere diverse da quelle di un altro gruppo.\n" +
            "- Quattro tessere dello stesso tipo ai quattro angoli della Libreria.\n" +
            "- Quattro gruppi separati formati ciascuno da quattro tessere adiacenti dello stesso tipo. Le tessere di un gruppo possono essere diverse da quelle di un altro gruppo.\n" +
            "- Due gruppi separati di 4 tessere dello stesso tipo che formano un quadrato 2x2. Le tessere dei due gruppi devono essere dello stesso tipo.\n" +
            "- Tre colonne formate ciascuna da 6 tessere di uno, due o tre tipi differenti. Colonne diverse possono avere combinazioni diverse di tipi di tessere.\n" +
            "- Otto tessere dello stesso tipo, nessuna restrizione sulle posizioni.\n" +
            "- Cinque tessere dello stesso tipo che formano una diagonale.\n" +
            "- Quattro righe formate ciascuna da 5 tessere di uno, due o tre tipi differenti. Righe diverse possono avere combinazioni diverse di tipi di tessere\n" +
            "- Due colonne formate ciascuna da 6 diversi tipi di tessere.\n" +
            "- Due righe formate ciascuna da 5 diversi tipi di tessere.\n" +
            "- Cinque tessere dello stesso tipo che formano una X.\n" +
            "- Cinque colonne di altezza crescente o decrescente.\n";

    public CommonObjectiveGenerator() {
        generatedObjective = generateRandomCommon();
    }

    /**
     *
     * @return descrizione dell'obbiettivo generato
     */
    public String getGeneratedObjective() {
        return generatedObjective;
    }

    /**
     *
     * @return id dell'obbiettivo generato, compreso tra 0 e 11
     */
    public int getGeneratedId() {
        return generatedId;
    }

    /**
     * sceglie uno tra i 12 obbiettivi e salva l'id che viene richiamato per identificare l'obbiettivo generato
     * @return la descrizione dell'obbiettivo comune generato
     */
    private String generateRandomCommon() {
        String goals;
        Random r = new Random();
        String[] objs = descr.split("-");
        this.generatedId = r.nextInt(11);
        goals = objs[this.generatedId];
        return goals;
    }

}

