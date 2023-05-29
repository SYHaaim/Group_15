package board;

/**
 * tipi di tessere esistenti nel gioco
 */
public enum TileType {
    C("CATS"),
    B("BOOKS"),
    T("TOYS"),
    F("FRAMES"),
    TR("TROPHIES"),
    P("PLANTS");

    private static int[] cardsLeft = new int[]{22, 22, 22, 22, 22, 22};

    public final String tileName;

    TileType(String tileName) {
        this.tileName = tileName;
    }

    /**
     * genera un tipo randomico di tessera che viene piazzato nella board principale
     *
     * @param n
     * @return un tipo casuale di tessera
     * @throws IllegalArgumentException
     */
    public static TileType assignTileType(int n) throws IllegalArgumentException {

        if (n < 1 || n > 6)
            throw new IllegalArgumentException("IllegalArgumentException");

        switch (n) {
            case 1:
                if (cardsLeft[n - 1] > 0) {
                    cardsLeft[n - 1]--;
                    return C;
                }
            case 2:
                if (cardsLeft[n - 1] > 0) {
                    cardsLeft[n - 1]--;
                    return B;
                }
            case 3:
                if (cardsLeft[n - 1] > 0) {
                    cardsLeft[n - 1]--;
                    return T;
                }
            case 4:
                if (cardsLeft[n - 1] > 0) {
                    cardsLeft[n - 1]--;
                    return F;
                }
            case 5:
                if (cardsLeft[n - 1] > 0) {
                    cardsLeft[n - 1]--;
                    return TR;
                }
            case 6:
                if (cardsLeft[n - 1] > 0) {
                    cardsLeft[n - 1]--;
                    return P;
                }

            default:
                return null;
        }

    }
}
