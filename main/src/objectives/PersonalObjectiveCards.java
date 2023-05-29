package objectives;



import java.util.ArrayList;
import java.util.Random;

public class PersonalObjectiveCards {


    String[] generatedCard;
    //region cards

    private static final String[] card_1 = {
            "1 - 1 - P",
            "3 - 1 - F",
            "5 - 2 - C",
            "3 - 4 - B",
            "4 - 2 - T",
            "6 - 3 - TR"
    };

    private static final String[] card_2 = {
            "2 - 2 - P",
            "3 - 1 - C",
            "3 - 3 - T",
            "4 - 5 - B",
            "5 - 4 - TR",
            "6 - 5 - F"
    };

    private static final String[] card_3 = {
            "2 - 1 - F",
            "2 - 4 - T",
            "3 - 3 - P",
            "4 - 2 - C",
            "4 - 5 - TR",
            "6 - 1 - B"
    };

    private static final String[] card_4 = {
            "5 - 1 - T",
            "3 - 1 - TR",
            "3 - 3 - F",
            "4 - 4 - P",
            "5 - 2 - B",
            "5 - 3 - C"
    };

    private static final String[] card_5 = {
            "2 - 2 - TR",
            "4 - 2 - F",
            "4 - 3 - B",
            "5 - 5 - P",
            "6 - 1 - T",
            "6 - 4 - C"
    };

    private static final String[] card_6 = {
            "1 - 3 - TR",
            "1 - 5 - C",
            "3 - 4 - B",
            "5 - 2 - T",
            "5 - 4 - F",
            "6 - 1 - P"
    };

    private static final String[] card_7 = {
            "1 - 1 - C",
            "2 - 4 - F",
            "3 - 2 - P",
            "1 - 4 - TR",
            "5 - 5 - T",
            "6 - 3 - B"
    };

    private static final String[] card_8 = {
            "1 - 5 - F",
            "2 - 2 - C",
            "3 - 3 - TR",
            "4 - 1 - P",
            "5 - 4 - B",
            "6 - 4 - T"
    };

    private static final String[] card_9 = {
            "1 - 3 - T",
            "3 - 3 - C",
            "4 - 5 - B",
            "5 - 2 - TR",
            "5 - 5 - P",
            "6 - 1 - F"
    };

    private static final String[] card_10 = {
            "1 - 5 - TR",
            "2 - 2 - T",
            "3 - 1 - B",
            "4 - 4 - C",
            "5 - 2 - F",
            "6 - 4 - P"
    };

    private static final String[] card_11 = {
            "1 - 3 - P",
            "2 - 2 - B",
            "3 - 1 - T",
            "4 - 3 - F",
            "5 - 5 - C",
            "6 - 4 - TR"
    };

    private static final String[] card_12 = {
            "1 - 3 - B",
            "2 - 2 - B",
            "3 - 3 - F",
            "4 - 4 - TR",
            "5 - 5 - T",
            "6 - 1 - C"
    };
    //endregion
    private static final ArrayList<String[]> cards = new ArrayList<>();

    public PersonalObjectiveCards() {
        initCards();
        generatedCard = getRandomObjectiveCard();
    }

    public String[] getGeneratedCard() {
        return generatedCard;
    }

    void initCards() {
        cards.add(card_1);
        cards.add(card_2);
        cards.add(card_3);
        cards.add(card_4);
        cards.add(card_5);
        cards.add(card_6);
        cards.add(card_8);
        cards.add(card_9);
        cards.add(card_10);
        cards.add(card_11);
        cards.add(card_12);
    }

    public String[] getRandomObjectiveCard() {
        String[] generated;
        Random r = new Random();
        int randCard = r.nextInt(11);
        generated = cards.get(randCard);
        cards.remove(randCard);
        return generated;
    }

}
