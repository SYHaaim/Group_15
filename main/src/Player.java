import board.BoardNavigator;
import board.TileType;
import library.Library;
import objectives.PersonalObjective;

import java.util.Arrays;
import java.util.Scanner;

/**
 * classe che gestisce la generazione e il conteggio di punti dei giocatori
 */
public class Player {


    private final String name;
    private final int id;
    private int points;
    /**
     * oggetto libreria del giocatore corrente
     */
    private Library shelf;
    /**
     * lista degli obbiettivi personali del giocatore corrente
     */
    private final PersonalObjective persObj;
    private final boolean isFirst;
    /**
     * tessere prese dal giocatore ogni turno
     */
    private TileType[] picked;


    /**
     * @param id      id del giocatore
     * @param name    nome o soprannome del giocatore
     * @param isFirst flag per capire se il giocatore in questione è il primo
     */
    public Player(int id, String name, boolean isFirst) {
        this.name = name;
        this.id = id;
        this.points = 0;
        this.persObj = new PersonalObjective();
        this.picked = new TileType[3];
        this.isFirst = isFirst;
        this.shelf = new Library();
    }

    /**
     * metodo che genera un numero specificato di giocatori dall'utente
     *
     * @param numPlayers il numero di giocatori che partecipano al game
     * @return un array di giocatori, con tutti i campi settati
     */
    public static Player[] GeneratePlayers(int numPlayers) {
        Scanner sc = new Scanner(System.in);
        Player[] players = new Player[numPlayers];
        for (int i = 0; i < players.length; i++) {
            System.out.println("come si chiama il " + (i + 1) + "° giocatore?");
            players[i] = new Player(i + 1, sc.next(), (i + 1 == 1));


        }
        return players;
    }

    //temporary method to see personal objective formatting
    public void printObjective() {
        persObj.printObj();
    }

    /***
     * intermezzo tra la plancia e il flow di gioco, prende le tessere specificate dalla board
     * @param row riga in cui si trova la tessera specificata dall'utente
     * @param column colonna in cui si trova la tessera specificata dall'utente
     * @param pickedCount numero progressivo, indica prima, seconda o terza tessera
     * @param numPlayers numero totale di giocatori in partita
     *
     */
    public void pickTiles(char row, int column, int pickedCount, int numPlayers) {
        BoardNavigator nav = new BoardNavigator(numPlayers);
        int rowNum;
        //letters from A to Z range with a numeric value 10 to 35, subtracting 9 i get their alphabet position
        rowNum = (Character.getNumericValue(row) - 9);

        this.picked[pickedCount] = nav.pickFromBoard(rowNum, column);
    }

    public void insertInLibrary(int column) throws Exception {
        this.shelf.inserimentoLibrary(this.picked, column);
    }

    public void printPicked() {
        int tileCounter = 1;
        for (TileType pickedTiles : this.picked) {
            if (pickedTiles != null) {
                System.out.println(tileCounter + ". " + pickedTiles);

            } else System.out.println(tileCounter + ". *");

            tileCounter++;
        }

    }

    public void resetPicked() {
        Arrays.fill(this.picked, null);
    }

    public void groupedTiles() {
        this.addPoints(this.shelf.contPointsAdjacentTiles());
    }

    public boolean isPlayerLibraryFull() {
        return this.shelf.isLibraryFull();
    }

    public void playerObjectivesCheck() {
        this.addPoints(this.shelf.checkPersonal(this.persObj));
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public void printPlayerLibrary() {
        this.shelf.printLibrary();
    }

    //region getter/setters
    public Library getShelf() {
        return shelf;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }
    //endregion


}



