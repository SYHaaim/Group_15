import java.util.*;

public class Game {
    //handles player turns and endgame etc..

    Player[] players;
    int numPlayers;
    public Game(Player[] players, int numPlayers){
        this.players = players;
        this.numPlayers = numPlayers;
    }
    public void PrintLeaderboard(Player[] players){
        SortByPoints(players);
        for (Player player : players)
            System.out.println(player.getId() + ". " + player.getName() + " punti: " + player.getPoints());
    }
    void SortByPoints(Player[] players){
        for (int i = 0; i < players.length-1; i++){
            if (players[i].getPoints() < players[i+1].getPoints()){
                Player tmp = players[i+1];
                players[i+1] = players[i];
                players[i]=tmp;
            }
        }
    }
    public void PlayerTurn(Player pl, int numPlayers) throws NoSuchFieldException {

        BoardNavigator nav = new BoardNavigator(numPlayers);
        int column = 0;
        char row = ' ';
        int prevCol = 0;
        char prevRow = ' ';
        int pickedCount = 0;
        boolean pickMore = true;
        Scanner sc = new Scanner(System.in);

        System.out.println("TURNO DI: " + pl.getName());
        while(pickMore){

            row = provideValidInput(row);
            column = provideValidInput(column);

            System.out.println("vuoi prendere altre caselle? (si/no)");
            if (sc.next().equalsIgnoreCase("NO")){
                pickMore = false;
            }

            try{
                pl.pickTiles(row, column, pickedCount, prevRow, prevCol);
            }catch (Exception e){
                System.out.println(e);
                row = provideValidInput(row);
                column = provideValidInput(column);
                pl.pickTiles(row, column, pickedCount, prevRow, prevCol);
            }

            prevRow = row;
            prevCol = column;


            pickedCount++;
            if(pickedCount > 2)
                pickMore = false;
        }

        if(Board.checkFillBoard()) {
        	nav.fillBoard();
        }
    }


      char provideValidInput(char in){
        boolean isValid = false;
        Scanner sc = new Scanner(System.in);

        while(!isValid){

            System.out.println("Scegli la riga da cui prendere una casella (A - I)");
            try {
                in = sc.next().toUpperCase().charAt(0);
                char startRow = 'A';
                char endRow = 'I';
                if (Character.getNumericValue(in) >= (Character.getNumericValue(startRow)) && Character.getNumericValue(in) <= (Character.getNumericValue(endRow))) {
                    isValid = true;
                } else {
                    System.out.println("input invalido. scegli una lettera tra A ed I");
                }
            } catch (InputMismatchException e) {
                System.out.println("input invalido. scegli una lettera tra A ed I");
                sc.nextLine(); // consume the remaining input
            }
        }
        return in;
    }
      int provideValidInput(int in){
        boolean isValid = false;
        Scanner sc = new Scanner(System.in);
        while(!isValid){

            System.out.println("Scegli la colonna da cui prendere una casella (1 - 9)");
            try {
                in = sc.nextInt();
                if (in >= 1 && in <= 9) {
                    isValid = true;
                } else {
                    System.out.println("input invalido. scegli un numero tra 1 e 9");
                }
            } catch (InputMismatchException e) {
                System.out.println("input invalido. scegli un numero tra 1 e 9");
                sc.nextLine(); // consume the remaining input
            }
        }
        return in;
    }
}
