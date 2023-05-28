package objectives;

import java.util.Random;

public class CommonObjectiveGenerator {
    //private static final ArrayList<String[]> TessereObbComuni = new ArrayList<>();

	private String generatedObjective;
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
			"- Cinque tessere dello stesso tipo che formano una X.\n"+			
			"- Cinque colonne di altezza crescente o decrescente.\n";
	public CommonObjectiveGenerator() {
		generatedObjective = generateRandomCommon();
	}

	public String getGeneratedObjective() {
		return generatedObjective;
	}

	public int getGeneratedId() {
		return generatedId;
	}

	private String generateRandomCommon() {
		String goals;
		Random r = new Random();
		String[] objs = descr.split("-");
		this.generatedId = r.nextInt(11)+1;
		goals = objs[this.generatedId];
		return goals;
    }
    
   // private String[] randomGoal(int nPlayer) {
    	
    	//int prova = (int) (Math.random() * nPlayer);
    
    //}
}

