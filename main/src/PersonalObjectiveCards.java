import java.util.ArrayList;
import java.util.Random;
public class PersonalObjectiveCards {

    private static final ArrayList<String[]> usedCards = new ArrayList<>();
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
            "6 - 6 - F"
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
            "1 - 6 - F"
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


    public static String[] getRandomObjectiveCard(){
        Random r = new Random();
        int randCard = r.nextInt((12)+1);
        String[] generatedCard = new String[6];
        switch (randCard){
            //region cases

            case 1: if (!usedCards.contains(card_1)){
                usedCards.add(card_1);
                generatedCard = card_1;
                break;
            }
            case 2: if (!usedCards.contains(card_2)){
                usedCards.add(card_2);
                generatedCard = card_2;
                break;
            }
            case 3: if (!usedCards.contains(card_3)){
                usedCards.add(card_3);
                generatedCard = card_3;
                break;
            }
            case 4: if (!usedCards.contains(card_4)){
                usedCards.add(card_4);
                generatedCard = card_4;
                break;
            }
            case 5: if (!usedCards.contains(card_5)){
                usedCards.add(card_5);
                generatedCard = card_5;
                break;
            }
            case 6: if (!usedCards.contains(card_6)){
                usedCards.add(card_6);
                generatedCard = card_6;
                break;
            }
            case 7: if (!usedCards.contains(card_7)){
                usedCards.add(card_7);
                generatedCard = card_7;
                break;
            }
            case 8: if (!usedCards.contains(card_8)){
                usedCards.add(card_8);
                generatedCard = card_8;
                break;
            }
            case 9: if (!usedCards.contains(card_9)){
                usedCards.add(card_9);
                generatedCard = card_9;
                break;
            }
            case 10: if (!usedCards.contains(card_10)){
                usedCards.add(card_10);
                generatedCard = card_10;
                break;
            }
            case 11: if (!usedCards.contains(card_11)){
                usedCards.add(card_11);
                generatedCard = card_11;
                break;
            }
            case 12: if (!usedCards.contains(card_12)){
                usedCards.add(card_12);
                generatedCard = card_12;
                break;
            }
                //endregion
        }
        return generatedCard;
    }

}
