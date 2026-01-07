package main.java.opal.pokemon.main.controller;

import main.java.opal.pokemon.main.model.Controls;
import main.java.opal.pokemon.main.view.TitleScreen;

public class TitleController extends ScreenController {

    public TitleController(GameController gameController) {
        super(gameController);
        screen = new TitleScreen(this);
    }

    @Override
    public void update() {

    }

    private void startGame() {
        gameController.music.stopSound();
        gameController.music.setFile();
        gameController.music.play();
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
