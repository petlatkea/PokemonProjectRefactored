package main.java.opal.pokemon.main.controller;

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

    // TODO: move Handle clicks from ClickHandler to here!!
}
