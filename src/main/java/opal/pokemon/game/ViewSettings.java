package main.java.opal.pokemon.game;

public final class ViewSettings {
    // Settings
    private static final int originalTileSize = 16;    // 16x16 px
    private static final int ScaleMultiplier = 4;

    private static final int maxScreenCol = 16;
    private static final int maxScreenRow = 12;

    // Derived variables that others can use
    public static final int tileSize = originalTileSize * ScaleMultiplier;     // 64x64 px

    public static final int screenWidth = tileSize * maxScreenCol;     // 1024 px
    public static final int screenHeight = tileSize * maxScreenRow;    // 768 px

}
