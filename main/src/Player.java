
import java.util.Scanner;

public class Player {

    //player class, handles player generation, points and personal objectives
    private final String name;
    private final int id;
    private int points;
    private Library libreria;
    private PersonalObjective persObj;

    public Library getLibreria() {
        return libreria;
    }

    private boolean isFirst;
    private TileType[] picked;



    //region getter/setters
    public boolean isFirst() {
        return isFirst;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    //endregion

    public Player(int id, String name, boolean isFirst) {
        this.name = name;
        this.id = id;
        this.points = 0;
        this.persObj = new PersonalObjective();
        this.picked = new TileType[3];
        this.isFirst = isFirst;
        this.libreria = new Library();
    }

    public static Player[] GeneratePlayers(int numPlayers) {
        Scanner sc = new Scanner(System.in);
        Player[] players = new Player[numPlayers];
        for (int i = 0; i < players.length; i ++){
            System.out.println("come si chiama il " + (i+1) + "° giocatore?");
            players[i] = new Player(i+1, sc.next(), (i+1==1));


        }
        return players;
    }

    //temporary method to see personal objective formatting
    public void printObjective(){
        persObj.printObj();
    }

    public TileType[] getPicked() {
        return picked;
    }

    public void pickTiles(char row, int column, int pickedCount, char prevRow, int prevCol) throws NoSuchFieldException {
        int rowNum = 0;
        int prevRowNum = 0;
        //letters from A to Z range with a numeric value 10 to 35, subtracting 9 i get their alphabet position
        rowNum = (Character.getNumericValue(row) - 9) ;
        if (!(prevRow == ' '))
            prevRowNum = (Character.getNumericValue(prevRow) - 9);

        this.picked[pickedCount] = Board.pickFromBoard(rowNum, column, prevRowNum, prevCol);
    }

    public void insertInLibrary(int column){
        this.libreria.inserimentoLibrary(this.picked,column);
    }

    public void printPicked(){
        for (TileType pickedTiles : this.picked) {
            if (pickedTiles != null) {
                System.out.println(pickedTiles);
               
            } else
                System.out.println("*");
        }

    }

    public void printPlayerLibrary(){
        this.libreria.printLibrary();
    }

}



