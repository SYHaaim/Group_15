import java.util.Scanner;

public class Player {

    //player class, handles player generation, points and personal objectives
    private final String name;
    private final int id;
    private int points;
    private Library libreria;
    private PersonalObjective persObj;

    //temporary string that indicates the action a player executes in their turn
    //to be changed with array of "Tile" with max length of 3
    private TileType[] picked;


    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Player(int id, String name) {
        this.name = name;
        this.id = id;
        this.points = 0;
        this.persObj = new PersonalObjective();
        this.picked = new TileType[3];
    }

    public static Player[] GeneratePlayers(int numPlayers){
        Scanner sc = new Scanner(System.in);
        Player[] players = new Player[numPlayers];
        for (int i = 0; i < players.length; i ++){
            System.out.println("come si chiama il " + (i+1) + "° giocatore?");
            players[i] = new Player(i+1, sc.next());
        }
        return players;
    }

    //temporary method to see personal objective formatting
    public void printObjective(){
        persObj.printObj();
    }

    public void pickTiles(char row, int column, int pickedCount){
        int rowNum = 0;
        //letters from A to Z range with a numeric value 10 to 35, subtracting 9 i get their alphabet position
         rowNum = (Character.getNumericValue(row) - 9) ;
        this.picked[pickedCount] = Board.pickFromBoard(rowNum, column);
    }

    public void printPicked(){
        for(int i = 0; i < this.picked.length; i++){
            if (this.picked[i] != null){
                System.out.println(this.picked[i]);
            }
            else
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
