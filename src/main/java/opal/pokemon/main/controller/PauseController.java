package main.java.opal.pokemon.main.controller;

import main.java.opal.pokemon.main.view.PauseScreen;

public class PauseController extends ScreenController {

    public PauseController(GameController gameController) {
        super(gameController);
        screen = new PauseScreen(this);
    }

    @Override
    public void update() {

    }
}
