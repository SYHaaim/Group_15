package objectives;

import java.io.FileNotFoundException;
import java.util.*;

import board.TileType;
import library.Library;

public class CommonObjective {

    private int cardId;
    private String descrizione;
    private CommonObjectiveGenerator generator;
    private final ArrayList<Integer> points = new ArrayList<>();
    private final int ROWLEN = 6;
    private final int COLUMNLEN = 5;

    public CommonObjective(int numPlayers) throws FileNotFoundException {
        this.generator = new CommonObjectiveGenerator();
        this.descrizione = generator.getGeneratedObjective();
        this.cardId = generator.getGeneratedId();
        initPoints(numPlayers);

    }

    void initPoints(int numPlayers) {
        switch (numPlayers) {
            case 2:
                points.add(8);
                points.add(4);
                break;

            case 3:
                points.add(8);
                points.add(6);
                points.add(4);
                break;

            case 4:
                points.add(8);
                points.add(6);
                points.add(4);
                points.add(2);
                break;
        }
    }

    public void printCommonObj() {
        System.out.println(this.cardId + " - " + this.descrizione);
    }

    public int checkCommonObjectives(TileType[][] tileTypes) {
        //gli id sono progressivi rispettivamente il loro ordinamento nel file "description.txt"
        int completedScore = 0;
        switch (cardId) {
            case 1: //Sei gruppi separati formati ciascuno da due tessere adiacenti dello stesso tipo.
                int groupCounter = 0;
                for (int c = 0; c < 4; c++) {
                    int tileCounter = 0;
                    for (int r = 0; r < 5; r++) {
                        if (tileTypes[r][c] != null && tileTypes[r][c] == tileTypes[r + 1][c]) {
                            tileCounter++;

                            if (tileCounter == 1) {
                                groupCounter++;
                                tileCounter = 0;
                                r++;
                            }
                        } else {
                            tileCounter = 0;
                        }
                    }
                }

                for (int r = 0; r < 5; r++) {
                    int tileCounter = 0;
                    for (int c = 0; c < 4; c++) {
                        if (tileTypes[r][c] != null && tileTypes[r][c] == tileTypes[r][c + 1]) {
                            tileCounter++;

                            if (tileCounter == 1) {
                                groupCounter++;
                                tileCounter = 0;
                                c++;
                            }
                        } else {
                            tileCounter = 0;
                        }
                    }
                }

                if (groupCounter > 5) {
                    completedScore = points.get(0);
                    points.remove(0);
                    return completedScore;
                }

                break;


            case 2: //Quattro tessere dello stesso tipo ai quattro angoli della Libreria.

                if (tileTypes[5][0] == tileTypes[0][0] &&
                        tileTypes[0][4] == tileTypes[5][0] &&
                        tileTypes[5][4] == tileTypes[5][0]) {
                    completedScore = points.get(0);
                    points.remove(0);
                    return completedScore;
                }
                break;
            case 3: //Quattro gruppi separati formati ciascuno da quattro tessere adiacenti dello stesso tipo

                groupCounter = 0;
                for (int c = 0; c < 4; c++) {
                    int tileCounter = 0;
                    for (int r = 0; r < 5; r++) {
                        if (tileTypes[r][c] == tileTypes[r + 1][c]) {
                            tileCounter++;

                            if (tileCounter == 3) {
                                groupCounter++;
                                tileCounter = 0;
                                r++;
                            }
                        } else {
                            tileCounter = 0;
                        }
                    }
                }

                for (int r = 0; r < 5; r++) {
                    int tileCounter = 0;
                    for (int c = 0; c < 4; c++) {
                        if (tileTypes[r][c] == tileTypes[r][c + 1]) {
                            tileCounter++;

                            if (tileCounter == 3) {
                                groupCounter++;
                                tileCounter = 0;
                                c++;
                            }
                        } else {
                            tileCounter = 0;
                        }
                    }
                }

                if (groupCounter > 1) {
                    completedScore = points.get(0);
                    points.remove(0);
                    return completedScore;
                }

                break;


            case 4:
            	
				groupCounter = 0;
				List<TileType> groupList = new  ArrayList<TileType>();
				TileType controlList[][] = tileTypes;
				for(int c=0;c<3;c++)
				{
											
					for(int r=0;r<4;r++)
					{
						if(controlList[r][c] == controlList[r][c+1] &&
								controlList[r][c+1] == controlList[r+1][c+1] &&
										controlList[r+1][c+1] == controlList[r+1][c]) {														
							
							groupList.add(controlList[r][c]);
							
							controlList[r][c] = null;
							controlList[r][c+1] = null;
							controlList[r+1][c] = null;
							controlList[r+1][c+1] = null;
																								
							r++;
							}
					}							
				}
				
				for (int i = 0; i < groupList.size();i++) {
					groupCounter = 0;
					for (int j = 0; j < groupList.size(); i++) {
						if(j != i) {
							if(groupList.get(i) == groupList.get(j)) {
								groupCounter++;
							}
						}
						if(groupCounter == 2) {
							completedScore = points.get(0);
                            points.remove(0);
                            return completedScore;
						}
					}
				}
            	
            	
            	
            	
            case 5:
            case 6:
            case 7: //5 tessere dello stesso tipo che formano una diagonale
                boolean find = true;

                for (int i = 0; i < 2; i++) {
                    int r = i, c = 0;
                    TileType t = tileTypes[r][c];
                    for (int j = 0; j < 5; j++) {
                        find = true;
                        if (t != tileTypes[r][c]) {
                            find = false;
                            break;
                        }
                        r++;
                        c++;
                    }
                }

                for (int i = 0; i < 2; i++) {
                    int r = i, c = 4;
                    TileType t = tileTypes[r][c];
                    for (int j = 0; j < 5; j++) {
                        find = true;
                        if (t != tileTypes[r][c]) {
                            find = false;
                            break;
                        }
                        r++;
                        c--;
                    }
                }

                if (find) {
                    completedScore = points.get(0);
                    points.remove(0);
                    return completedScore;
                }

                break;

            case 8:
            case 9: //Due colonne formate ciascuna da 6 diversi tipi di tessere

                int colonne = 0, countcolonne = 0;
                for (int i = 0; i < ROWLEN; i++) {
                    for (int j = 0; j < COLUMNLEN; j++) {
                        if ((i + 1) < ROWLEN && tileTypes[i][j] != tileTypes[i + 1][j]) {
                            colonne++;
                            if (colonne == 5) {
                                System.out.println("colonna " + (j + 1) + "con tutti elementi diversi");
                                countcolonne++;
                            }
                        }
                        if (countcolonne == 2) {
                            completedScore = points.get(0);
                            points.remove(0);
                            return completedScore;
                        }
                    }
                }
                break;

            case 10: //Due righe formate ciascuna da 5 diversi tipi di tessere.

                int Righe = 0, contRighe = 0;
                for (int i = 0; i < ROWLEN; i++) {
                    for (int j = 0; j < COLUMNLEN; j++) {
                        if ((j + 1) < COLUMNLEN && tileTypes[i][j] != tileTypes[i][j + 1]) {
                            Righe++;
                            if (Righe == 4) {
                                contRighe++;
                            }
                        }
                        if (contRighe == 2) {
                            completedScore = points.get(0);
                            points.remove(0);
                            return completedScore;
                        }
                    }
                }
                break;

            case 11:
            case 12:
                int prec = Integer.MAX_VALUE;
                boolean objectiveCompleted = true;
                for (int i = 0; i < ROWLEN; i++) {
                    int cont = 0;
                    for (int j = 0; j < COLUMNLEN; j++) {
                        if (tileTypes[i][j] != null)
                            cont++;
                    }
                    if (cont > prec) {
                        objectiveCompleted = false;
                        break;
                    }
                    prec = cont;
                }
                if (objectiveCompleted) {
                    System.out.println("5 colonne di altezza decrescente");
                    break;
                }

                prec = 0;
                objectiveCompleted = true;
                for (int i = 0; i < ROWLEN; i++) {
                    int cont = 0;
                    for (int j = 0; j < COLUMNLEN; j++) {
                        if (tileTypes[i][j] != null)
                            cont++;
                    }
                    if (cont < prec) {
                        objectiveCompleted = false;
                        break;
                    }
                    prec = cont;
                }

                if (objectiveCompleted) {
                    completedScore = points.get(0);
                    points.remove(0);
                    return completedScore;

                }
                break;
        }
        return 0;
    }

}
	               
	       


