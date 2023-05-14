import java.util.*;

public class Game {
    //handles player turns and endgame etc..

    public static void PrintLeaderboard(Player[] players){
        Player.SortByPoints(players);
        for (Player player : players)
            System.out.println(player.getId() + ". " + player.getName() + " punti: " + player.getPoints());
    }

    public static void PlayerTurn(Player pl) throws NoSuchFieldException {

        int column = 0;
        int pickedCount = 0;
        char row = ' ';
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

            pl.pickTiles(row, column, pickedCount);

            pickedCount++;
            if(pickedCount > 2)
                pickMore = false;
        }

    }


     static char provideValidInput(char in){
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
     static int provideValidInput(int in){
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
