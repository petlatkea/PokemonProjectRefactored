package main.java.opal.pokemon.main.controller;

import main.java.opal.pokemon.main.view.Screen;

/**
 * a ScreenController is a "sub-controller" that handles a single screen, e.g. play, battle, battleintro, etc.
 * It creates and contains the screen, the view, and extending classes are expected to create their own 'screen'
 * as an extension of the Screen class.
 *
 * The main GameController calls update on every sub-controller every frame.
 * And the main GameView / UI handles all the drawing - none of that is done from the controller.
 */
public abstract class ScreenController {

    protected GameController gameController;
    protected Screen screen;

    public ScreenController(GameController gameController) {
        this.gameController = gameController;
    }

    public Screen getScreen() {
        return screen;
    }

    abstract public void update();
}
