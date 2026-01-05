package main.java.opal.pokemon.main.controller;

import main.java.opal.pokemon.main.MouseClick;
import main.java.opal.pokemon.main.view.StartersScreen;

public class StartersController extends ScreenController {

    public StartersController(GameController gameController) {
        super(gameController);
        // create model and view - but skip model until actually needed
        screen = new StartersScreen(this);
    }

    @Override
    public void update() {

    }

    @Override
    public void handleLeftClick(MouseClick mouseClick) {
        // Pressed on Turtwig
        if (mouseClick.insideBox(99, ((gameController.screenHeight - 200) / 2) + 4, 192, 192)) {
            gameController.playerPokemon = 387;
            gameController.music.playSound(23);
            gameController.gameState = GameState.playState;
        }

        // Pressed on Chimchar
        if (mouseClick.insideBox(416, ((gameController.screenHeight - 200) / 2) + 4, 192, 192)) {
            gameController.playerPokemon = 390;
            gameController.music.playSound(24);
            gameController.gameState = GameState.playState;
        }

        // Pressed on Chimchar
        if (mouseClick.insideBox(733, ((gameController.screenHeight - 200) / 2) + 4, 192, 192)) {
            gameController.playerPokemon = 393;
            gameController.music.playSound(25);
            gameController.gameState = GameState.playState;
        }
    }
}
