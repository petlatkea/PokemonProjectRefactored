package main.java.opal.pokemon.game.screens.overworld;

import main.java.opal.pokemon.game.GameController;
import main.java.opal.pokemon.game.screens.overworld.tiles.TileDefinitions;
import main.java.opal.pokemon.game.screens.overworld.tiles.TileMap;
import main.java.opal.pokemon.game.screens.overworld.tiles.TileType;

// TODO: Change this to be in the OverworldModel
public class GameModel {
    private GameController gameController;

    public TileMap backgroundTileMap;
    public TileMap environmentBTileMap;
    public TileMap environmentFTileMap;

    private final TileDefinitions tileDefinitions;

    public GameModel(GameController controller) {
        this.gameController = controller;

        tileDefinitions = new TileDefinitions();

        backgroundTileMap = new TileMap("background", controller.maxWorldCol, controller.maxWorldRow);
        environmentBTileMap = new TileMap("environmentB", controller.maxWorldCol, controller.maxWorldRow);
        environmentFTileMap = new TileMap("environmentF", controller.maxWorldCol, controller.maxWorldRow);

        backgroundTileMap.loadMap("/maps/mapBackground.csv");
        environmentBTileMap.loadMap("/maps/mapEnvironmentB.csv");
        environmentFTileMap.loadMap("/maps/mapEnvironmentF.csv");
    }

    public TileType getTileType(int tileId) {
        return tileDefinitions.getTileType(tileId);
    }
}
