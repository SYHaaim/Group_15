public class Player {

    //player class, handles player generation, points and personal objectives
    private final String name;
    private final int id;
    private int points;
    Library libreria;
    PersonalObjective personal;

    public String getName() {
        return name;
    }

    public Player(int id, String name) {
        this.name = name;
        this.id = id;

    }
}
