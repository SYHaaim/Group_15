package objectives;


import java.util.*;

import board.TileType;


/**
 * gestisce i controlli sugli obbiettivi comuni
 */
public class CommonObjective {


    /**
     * id dell'obbiettivo generato dal generator
     */
    private final int cardId;
    /**
     * descrizione dell'obbiettivo comune generato
     */
    private String descrizione;
    private CommonObjectiveGenerator generator;
    private final ArrayList<Integer> points = new ArrayList<>();
    private final int ROWLEN = 6;
    private final int COLUMNLEN = 5;

    public CommonObjective(int numPlayers) {
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
        System.out.println((this.cardId + 1) + " - " + this.descrizione);
    }

    /**
     * controllo sul completamento degli obbiettivi comuni
     *
     * @param tileTypes libreria del giocatore corrente
     * @return numero di punti associati all'obbiettivo
     */
    public int checkCommonObjectives(TileType[][] tileTypes) {
        //gli id sono progressivi rispettivamente il loro ordinamento nel file "description.txt"
        int completedScore = 0;
        switch ((cardId + 1)) {
            case 1: //Sei gruppi separati formati ciascuno da due tessere adiacenti dello stesso tipo.
                int groupCounter = 0;
                for (int c = 0; c <= 4; c++) {
                    int tileCounter = 0;
                    for (int r = 0; r <= 5; r++) {
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

                for (int r = 0; r <= 5; r++) {
                    int tileCounter = 0;
                    for (int c = 0; c <= 4; c++) {
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
                for (int c = 0; c <= 4; c++) {
                    int tileCounter = 0;
                    for (int r = 0; r <= 5; r++) {
                        if (tileTypes[r][c] != null && tileTypes[r][c] == tileTypes[r + 1][c]) {
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

                for (int r = 0; r <= 5; r++) {
                    int tileCounter = 0;
                    for (int c = 0; c <= 4; c++) {
                        if (tileTypes[r][c] != null && tileTypes[r][c] == tileTypes[r][c + 1]) {
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

                if (groupCounter > 3) {
                    completedScore = points.get(0);
                    points.remove(0);
                    return completedScore;
                }

                break;


            case 4: //Due gruppi separati di 4 tessere dello stesso tipo che formano un quadrato 2x2

                groupCounter = 0;
                List<TileType> groupList = new ArrayList<TileType>();
                TileType[][] controlList = new TileType[ROWLEN][COLUMNLEN];

                for (int i = 0; i < ROWLEN; i++) {
                    for (int j = 0; j < COLUMNLEN; j++)
                        controlList[i][j] = tileTypes[i][j];
                }
                for (int c = 0; c <= 3; c++) {

                    for (int r = 0; r <= 4; r++) {
                        if (controlList[r][c] != null &&
                                controlList[r][c + 1] != null &&
                                controlList[r + 1][c] != null &&
                                controlList[r + 1][c + 1] != null) {

                            if (controlList[r][c] == controlList[r][c + 1] &&
                                    controlList[r][c + 1] == controlList[r + 1][c + 1] &&
                                    controlList[r + 1][c + 1] == controlList[r + 1][c]) {

                                groupList.add(controlList[r][c]);

                                controlList[r][c] = null;
                                controlList[r][c + 1] = null;
                                controlList[r + 1][c] = null;
                                controlList[r + 1][c + 1] = null;

                                r++;
                            }
                        }
                    }
                }

                for (int i = 0; i < groupList.size(); i++) {
                    groupCounter = 0;
                    for (int j = 0; j < groupList.size(); i++) {
                        if (j != i) {
                            if (groupList.get(i) == groupList.get(j)) {
                                groupCounter++;
                            }
                        }
                        if (groupCounter == 2) {
                            completedScore = points.get(0);
                            points.remove(0);
                            return completedScore;
                        }
                    }
                }

                break;


            case 5:  //Tre colonne formate ciascuna da 6 tessere di uno, due o tre tipi differenti

                int counterDifferentTyle = 0;
                int colGroup = 0;
                for (int c = 0; c <= 4; c++) {
                    int counterTyleC = 0;
                    int counterTyleB = 0;
                    int counterTyleT = 0;
                    int counterTyleF = 0;
                    int counterTyleTR = 0;
                    int counterTyleP = 0;
                    boolean notFull = false;
                    for (int r = 0; r <= 5; r++) {
                        if (tileTypes[r][c] != null) {
                            if (tileTypes[r][c] == TileType.C) {
                                counterTyleC++;
                            } else if (tileTypes[r][c] == TileType.B) {
                                counterTyleB++;
                            } else if (tileTypes[r][c] == TileType.T) {
                                counterTyleT++;
                            } else if (tileTypes[r][c] == TileType.F) {
                                counterTyleF++;
                            } else if (tileTypes[r][c] == TileType.TR) {
                                counterTyleTR++;
                            } else if (tileTypes[r][c] == TileType.P) {
                                counterTyleP++;
                            }
                        } else {
                            notFull = true;
                        }
                    }
                    if (notFull == false) {
                        counterDifferentTyle = 0;
                        if (counterTyleC > 0) {
                            counterDifferentTyle++;
                        }
                        if (counterTyleB > 0) {
                            counterDifferentTyle++;
                        }
                        if (counterTyleT > 0) {
                            counterDifferentTyle++;
                        }
                        if (counterTyleF > 0) {
                            counterDifferentTyle++;
                        }
                        if (counterTyleTR > 0) {
                            counterDifferentTyle++;
                        }
                        if (counterTyleP > 0) {
                            counterDifferentTyle++;
                        }
                        if (counterDifferentTyle < 4) {
                            colGroup++;
                            if (colGroup == 3) {
                                completedScore = points.get(0);
                                points.remove(0);
                                return completedScore;
                            }
                        }
                    }
                }

                break;

            case 6:  // Otto tessere dello stesso tipo, nessuna restrizione sulle posizioni
                int counterC = 0;
                int counterB = 0;
                int counterT = 0;
                int counterF = 0;
                int counterTR = 0;
                int counterP = 0;

                for (int c = 0; c <= 4; c++) {
                    for (int r = 0; r <= 5; r++) {
                        if (tileTypes[r][c] != null) {
                            if (tileTypes[r][c] == TileType.C) {
                                counterC++;
                            } else if (tileTypes[r][c] == TileType.B) {
                                counterB++;
                            } else if (tileTypes[r][c] == TileType.T) {
                                counterT++;
                            } else if (tileTypes[r][c] == TileType.F) {
                                counterF++;
                            } else if (tileTypes[r][c] == TileType.TR) {
                                counterTR++;
                            } else if (tileTypes[r][c] == TileType.P) {
                                counterP++;
                            }
                        }

                        if (counterC >= 8 || counterB >= 8 || counterT >= 8 || counterF >= 8 || counterTR >= 8 || counterP >= 8) {
                            completedScore = points.get(0);
                            points.remove(0);
                            return completedScore;
                        }

                    }
                }

                break;


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

            case 8: //Quattro righe formate ciascuna da 5 tessere di uno, due o tre tipi differenti.

                int counterDifferentTyle2 = 0;
                int colGroup2 = 0;
                for (int r = 0; r <= 5; r++) {
                    int counterTyleC2 = 0;
                    int counterTyleB2 = 0;
                    int counterTyleT2 = 0;
                    int counterTyleF2 = 0;
                    int counterTyleTR2 = 0;
                    int counterTyleP2 = 0;
                    boolean notFull2 = false;
                    for (int c = 0; c <= 4; c++) {
                        if (tileTypes[r][c] != null) {
                            if (tileTypes[r][c] == TileType.C) {
                                counterTyleC2++;
                            } else if (tileTypes[r][c] == TileType.B) {
                                counterTyleB2++;
                            } else if (tileTypes[r][c] == TileType.T) {
                                counterTyleT2++;
                            } else if (tileTypes[r][c] == TileType.F) {
                                counterTyleF2++;
                            } else if (tileTypes[r][c] == TileType.TR) {
                                counterTyleTR2++;
                            } else if (tileTypes[r][c] == TileType.P) {
                                counterTyleP2++;
                            }
                        } else {
                            notFull2 = true;
                        }
                    }
                    if (notFull2 == false) {
                        counterDifferentTyle2 = 0;
                        if (counterTyleC2 > 0) {
                            counterDifferentTyle2++;
                        }
                        if (counterTyleB2 > 0) {
                            counterDifferentTyle2++;
                        }
                        if (counterTyleT2 > 0) {
                            counterDifferentTyle2++;
                        }
                        if (counterTyleF2 > 0) {
                            counterDifferentTyle2++;
                        }
                        if (counterTyleTR2 > 0) {
                            counterDifferentTyle2++;
                        }
                        if (counterTyleP2 > 0) {
                            counterDifferentTyle2++;
                        }
                        if (counterDifferentTyle2 < 4) {
                            colGroup2++;
                            if (colGroup2 == 3) {
                                completedScore = points.get(0);
                                points.remove(0);
                                return completedScore;
                            }
                        }
                    }
                }

                break;

            case 9: //Due colonne formate ciascuna da 6 diversi tipi di tessere
  
                int colCounter = 0;
                for (int c = 0; c < COLUMNLEN; c++) {
                	int counterTyleC3 = 0;
                    int counterTyleB3 = 0;
                    int counterTyleT3 = 0;
                    int counterTyleF3 = 0;
                    int counterTyleTR3 = 0;
                    int counterTyleP3 = 0;
                    for (int r = 0; r < ROWLEN; r++) {                    	
                    	if (tileTypes[r][c] != null) {
                            if (tileTypes[r][c] == TileType.C) {
                                counterTyleC3++;
                            } else if (tileTypes[r][c] == TileType.B) {
                                counterTyleB3++;
                            } else if (tileTypes[r][c] == TileType.T) {
                                counterTyleT3++;
                            } else if (tileTypes[r][c] == TileType.F) {
                                counterTyleF3++;
                            } else if (tileTypes[r][c] == TileType.TR) {
                                counterTyleTR3++;
                            } else if (tileTypes[r][c] == TileType.P) {
                                counterTyleP3++;
                            }
                        }
                    }
                    if(counterTyleC3 == 1 && counterTyleB3 == 1 && counterTyleT3 == 1 && counterTyleF3 == 1 && counterTyleTR3 == 1 && counterTyleP3 == 1) {
                    	colCounter++;
                    }
                    if(colCounter == 2) {
                    	completedScore = points.get(0);
                        points.remove(0);
                        return completedScore;
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

            case 11: //cinque tessere uguali che formano una X
                TileType center;
                TileType upRight;
                TileType upLeft;
                TileType downRight;
                TileType downLeft;

                for (int i = 1; i < (ROWLEN - 1); i++) {
                    for (int j = 1; j < (COLUMNLEN - 1); j++) {
                        center = tileTypes[i][j];
                        if (center == null)
                            continue;
                        upRight = tileTypes[i - 1][j + 1];
                        upLeft = tileTypes[i - 1][j - 1];
                        downRight = tileTypes[i + 1][j + 1];
                        downLeft = tileTypes[i - 1][j - 1];

                        if (upRight.equals(center) && upLeft.equals(center)
                                && downRight.equals(center) && downLeft.equals(center)) {
                            completedScore = points.get(0);
                            points.remove(0);
                            return completedScore;
                        }
                    }
                }


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
	               
	       


