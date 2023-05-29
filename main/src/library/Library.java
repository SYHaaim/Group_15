package library;

import objectives.PersonalObjective;
import board.*;

import java.util.Scanner;

public class Library {

    private final int ROWLEN = 6;
    private final int COLUMNLEN = 5;
    TileType[][] libreria;

    public Library() {
        this.libreria = new TileType[ROWLEN][COLUMNLEN];
    }

    public TileType[][] getLibreria() {
        return libreria;
    }

    public void inserimentoLibrary(TileType[] tileTypes, int insertionCol) throws Exception {

        Scanner sc = new Scanner(System.in);
        //int pickedCounter = 0;
        int occupiedSpots = 0;
        int insertRow = 0;
        int tileSelector = 0;

        if (!hasEnoughFreeSpots(tileTypes, insertionCol))
            throw new Exception("non ci sono abbastanza spazi in questa colonna ");

        for (int i = 0; i < tileTypes.length; i++) {

            for (int j = ROWLEN - 1; j >= 0; j--) {
                if (this.libreria[j][insertionCol] != null) {
                    occupiedSpots++;
                }
            }
            insertRow = (ROWLEN - occupiedSpots) - 1;
            if (insertRow < 0)
                throw new Exception("colonna tutta occupata ");

            System.out.println("quale tessera vuoi inserire per " + (i + 1) + "^a? (utilizza i numeri alla sinistra delle tessere per scegliere)");
            tileSelector = sc.nextInt() - 1;
            if (tileTypes[tileSelector] == null) {
                throw new Exception("tessera vuota o già piazzata");
            }
            if (tileSelector > 2) {
                throw new Exception("tessera inesistente");
            }

            libreria[insertRow][insertionCol] = tileTypes[tileSelector];
            occupiedSpots = 0;

            if (i == 0 && tileTypes[1] == null)
                break;
            else if (i == 1 && tileTypes[2] == null)
                break;

        }

    }

    public void printLibrary() {
        System.out.println("*****************************************************************************************\n");

        //Print the numeric coordinates on top

        for (int i = 1; i <= COLUMNLEN; i++) {
            System.out.printf("%-10s", i);
        }
        //separates coordinates and library
        System.out.println("\n----------------------------------------------------------------------------------------------");

        //print the board with vertical coordinates, * if the cell is empty, or the name of the cell
        for (int i = 0; i < ROWLEN; i++) {
            for (int j = 0; j < COLUMNLEN; j++) {
                if (libreria[i][j] == null)
                    System.out.printf("%-10s", "*");
                else
                    System.out.printf("%-10s", libreria[i][j]);
            }
            System.out.println();
        }

        System.out.println("\n*************************************************************************************");

    }

    public int checkPersonal(PersonalObjective personal) {
        String[] tempObj = personal.getObjectives();
        int completedCounter = 0;
        int objectiveRow;
        int objectiveColumn;
        TileType objectiveType;
        TileType[][] copy = this.libreria;

        for (int i = 0; i < tempObj.length; i++) {
            // gli obb. personali sono formattati come "coord. x - coord. y - tipo tessera"

            String[] split;
            try {
                split = tempObj[i].split("-");
            } catch (Exception e) {
                System.out.println(e);
                continue;
            }

            objectiveRow = (Integer.parseInt(split[0].trim())) - 1;
            objectiveColumn = (Integer.parseInt(split[1].trim())) - 1;
            objectiveType = TileType.valueOf(split[2].trim());

            if (copy[objectiveRow][objectiveColumn] != null && copy[objectiveRow][objectiveColumn].equals(objectiveType)) {
                tempObj[i] = "completato";
            }
        }
        for (String s : tempObj) {
            if (s.equals("completato")) {
                completedCounter++;
            }
        }
        return completedCounter;
    }

    public int contPointsAdjacentTiles() {
        int contPoints = 0;
        TileType[][] copiaLibreria = libreria;        //necessaria una copia della libreria che verrà modificata man mano all'interno di questo metodo

        for (int i = 0; i < ROWLEN; i++) {
            for (int j = 0; j < COLUMNLEN; j++) {

                if (copiaLibreria[i][j] != null) {
                    int contAdjacentTile = checkAroundTiles(copiaLibreria, i, j) + 1;            //vengono trovate le celle adiacenti uguali alla cella data

                    //assegnamento punti in base al numero di celle adiacenti
                    switch (contAdjacentTile) {
                        case 3:
                            contPoints += 2;
                            break;
                        case 4:
                            contPoints += 3;
                            break;
                        case 5:
                            contPoints += 5;
                            break;
                        default:
                            if (contAdjacentTile >= 6)
                                contPoints += 8;
                    }
                }

            }
        }

        return contPoints;

    }

    //METODO RICHIAMATO RICORSIVAMENTE ALL'INTERNO DI contPointsAdjacentTiles(board.TileType[][] libreria)
    public int checkAroundTiles(TileType[][] copiaLibreria, int r, int c) {

        int cont = 0;
        TileType t = copiaLibreria[r][c];
        copiaLibreria[r][c] = null;

        if ((r + 1) < ROWLEN && copiaLibreria[r + 1][c] == t) {
            cont++;
            cont += checkAroundTiles(copiaLibreria, r + 1, c);
        }
        if ((r - 1) > 0 && copiaLibreria[r - 1][c] == t) {
            cont++;
            cont += checkAroundTiles(copiaLibreria, r - 1, c);
        }
        if ((c + 1) < COLUMNLEN && copiaLibreria[r][c + 1] == t) {
            cont++;
            cont += checkAroundTiles(copiaLibreria, r, c + 1);
        }
        if ((c - 1) > 0 && copiaLibreria[r][c - 1] == t) {
            cont++;
            cont += checkAroundTiles(copiaLibreria, r, c - 1);
        }

        return cont;
    }

    boolean hasEnoughFreeSpots(TileType[] picked, int insertionCol) {

        int pickedCounter = 0;
        int freeSpots = 0;

        for (int k = 0; k < picked.length; k++)
            if (picked[k] != null)
                pickedCounter++;

        for (int i = 0; i < ROWLEN; i++)
            if (this.libreria[i][insertionCol] == null)
                freeSpots++;

        return pickedCounter <= freeSpots;
    }

    public boolean isLibraryFull() {
        for (int i = 0; i < ROWLEN; i++) {
            for (int j = 0; j < COLUMNLEN; j++) {
                if (this.libreria[i][j] == null)
                    return false;
            }
        }

        return true;
    }

    //test method
    public void testFill() {
        for (int i = 0; i < ROWLEN; i++) {
            for (int j = 0; j < COLUMNLEN; j++) {
                this.libreria[i][j] = TileType.B;
            }
        }
    }
}


































