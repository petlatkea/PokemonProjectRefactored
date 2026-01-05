package main.java.opal.pokemon.main.controller;

import main.java.opal.pokemon.main.view.TitleScreen;

public class TitleController extends ScreenController {

    public TitleController(GameController gameController) {
        super(gameController);
        screen = new TitleScreen(this);
    }

    @Override
    public void update() {

    }
}
