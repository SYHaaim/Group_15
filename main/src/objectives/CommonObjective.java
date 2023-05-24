package objectives;

import java.io.FileNotFoundException;
import library.Library;

public class CommonObjective{

		private int cardId;
		private String descrizione;
		private CommonObjectiveGenerator generator;
		private int[] objectivePoiints = {8,4,6,2};

 		public CommonObjective() throws FileNotFoundException {
			this.generator = new CommonObjectiveGenerator();
			this.descrizione = generator.getGeneratedObjective();
			this.cardId = generator.getGeneratedId();

		}

		public void printCommonObj(){
			System.out.println(this.cardId +" - "+this.descrizione);
		}

		public void checkCommonObjectives(int objectiveId, Library libreria){
			 //gli id sono progressivi rispettivamente il loro ordinamento nel file "description.txt"
			switch (objectiveId){
				case 1:
				case 2:
				case 3:
				case 4:
				case 5:
				case 6:
				case 7:
				case 8:
				case 9:
				case 10:
			}
		}

	
}
	               
	       


