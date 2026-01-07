package main.java.opal.pokemon.main.controller;

import main.java.opal.pokemon.main.model.Controls;
import main.java.opal.pokemon.main.view.PauseScreen;

import java.awt.event.KeyEvent;

public class PauseController extends ScreenController {

    public PauseController(GameController gameController) {
        super(gameController);
        screen = new PauseScreen(this);
    }

    @Override
    public void update() {

    }

    @Override
    public void keyPressed(int keyCode) {
        // only accept escape-key presses, if it has been released previously on this screen
        if (escapeKeyReleased && keyCode == KeyEvent.VK_ESCAPE ) {
            escapeKeyReleased = false;
            // and if it has, end the pause, and return to the game
            gameController.gameState = GameState.playState;
        }
    }

    @Override
    public void keyReleased(int keyCode) {
        // if escape-key is released - mark it as released
        if (keyCode == KeyEvent.VK_ESCAPE) {
            escapeKeyReleased = true;
        }
    }

    // ensure that we have to release the escape-key before being allowed to press it again
    private boolean escapeKeyReleased = false;
}
