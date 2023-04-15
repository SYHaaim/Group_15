public enum TileType {
    C("CATS"),
    B("BOOKS"),
    T("TOYS"),
    F("FRAMES"),
    TR("TROPHIES"),
    P("PLANTS");

    public final String tileName;
    private TileType(String tileName){
        this.tileName = tileName;
    }
}
