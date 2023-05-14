
import java.util.Scanner;

public class Player {

    //player class, handles player generation, points and personal objectives
    private final String name;
    private final int id;
    private int points;
    private Library libreria;
    private PersonalObjective persObj;
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

    public Player(int id, String name) {
        this.name = name;
        this.id = id;
        this.points = 0;
        this.persObj = new PersonalObjective();
        this.picked = new TileType[3];
        this.isFirst = false;
    }

    private static Player flagFirst(Player pl){
        if (pl.id == 1)
         pl.isFirst = true;

        return pl;
    }
    public static Player[] GeneratePlayers(int numPlayers) {
        Scanner sc = new Scanner(System.in);
        Player[] players = new Player[numPlayers];
        for (int i = 0; i < players.length; i ++){
            System.out.println("come si chiama il " + (i+1) + "° giocatore?");
            players[i] = new Player(i+1, sc.next());

            players[i] = flagFirst(players[i]);
        }
        return players;
    }

    //temporary method to see personal objective formatting
    public void printObjective(){
        persObj.printObj();
    }

    public void pickTiles(char row, int column, int pickedCount) throws NoSuchFieldException {
        int rowNum = 0;
        //letters from A to Z range with a numeric value 10 to 35, subtracting 9 i get their alphabet position
        rowNum = (Character.getNumericValue(row) - 9) ;
        this.picked[pickedCount] = Board.pickFromBoard(rowNum, column);
    }

    public void printPicked(){
        for (TileType pickedTiles : this.picked) {
            if (pickedTiles != null) {
                System.out.println(pickedTiles);
            } else
                System.out.println("*");
        }
    }
    public static void SortByPoints(Player[] players){
        for (int i = 0; i < players.length-1; i++){
            if (players[i].points < players[i+1].points){
                Player tmp = players[i+1];
                players[i+1] = players[i];
                players[i]=tmp;
            }
        }
    }
}
