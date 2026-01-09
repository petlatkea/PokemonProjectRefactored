package main.java.opal.pokemon.game.screens.title;

import main.java.opal.pokemon.game.GameController;
import main.java.opal.pokemon.game.GameState;
import main.java.opal.pokemon.game.screens.ScreenController;
import main.java.opal.pokemon.game.input.Controls;

public class TitleController extends ScreenController {

    public TitleController(GameController gameController) {
        super(gameController);
        screen = new TitleScreen(this);
    }

    @Override
    public void update() {

    }

    private void startGame() {
        gameController.soundController.stopSound();
        gameController.soundController.setFile();
        gameController.soundController.play();
        gameController.gameState = GameState.playState;
    }

    @Override
    public void keyPressed(int keyCode) {
        Controls controls = gameController.getControls();

        if (controls.enterPressed) {
            startGame();
        }
    }
}
