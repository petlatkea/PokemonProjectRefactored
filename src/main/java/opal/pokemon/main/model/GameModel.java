package main.java.opal.pokemon.main.model;

import main.java.opal.pokemon.main.controller.GameController;

public class GameModel {
    private GameController gameController;

    public TileMap backgroundTileMap;
    public TileMap environmentBTileMap;
    public TileMap environmentFTileMap;

    public GameModel(GameController controller) {
        this.gameController = controller;

        backgroundTileMap = new TileMap("background", controller.maxWorldCol, controller.maxWorldRow);
        environmentBTileMap = new TileMap("environmentB", controller.maxWorldCol, controller.maxWorldRow);
        environmentFTileMap = new TileMap("environmentF", controller.maxWorldCol, controller.maxWorldRow);

        backgroundTileMap.loadMap("/maps/mapBackground.csv");
        environmentBTileMap.loadMap("/maps/mapEnvironmentB.csv");
        environmentFTileMap.loadMap("/maps/mapEnvironmentF.csv");
    }
}
