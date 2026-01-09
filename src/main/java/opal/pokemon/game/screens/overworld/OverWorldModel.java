package main.java.opal.pokemon.game.screens.overworld;

import main.java.opal.pokemon.game.GameController;
import main.java.opal.pokemon.game.screens.overworld.tiles.TileDefinitions;
import main.java.opal.pokemon.game.screens.overworld.tiles.TileMap;
import main.java.opal.pokemon.game.screens.overworld.tiles.TileType;

public class OverWorldModel {
    // === WORLD SETTINGS ===
    public final int maxWorldCol = 100;
    public final int maxWorldRow = 100;

    public TileMap backgroundTileMap;
    public TileMap environmentBTileMap;
    public TileMap environmentFTileMap;

    private final TileDefinitions tileDefinitions;

    // TODO: Sometime in the near future, the model should contain the CollisionChecker
//    public CollisionChecker cChecker; //= new CollisionChecker(this, model);

    public OverWorldModel(GameController gameController) {

        tileDefinitions = new TileDefinitions();

        backgroundTileMap = new TileMap("background", maxWorldCol, maxWorldRow);
        environmentBTileMap = new TileMap("environmentB", maxWorldCol, maxWorldRow);
        environmentFTileMap = new TileMap("environmentF", maxWorldCol, maxWorldRow);

        backgroundTileMap.loadMap("/maps/mapBackground.csv");
        environmentBTileMap.loadMap("/maps/mapEnvironmentB.csv");
        environmentFTileMap.loadMap("/maps/mapEnvironmentF.csv");
    }

    public TileType getTileType(int tileId) {
        return tileDefinitions.getTileType(tileId);
    }
}
