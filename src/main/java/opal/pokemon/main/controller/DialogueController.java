package main.java.opal.pokemon.main.controller;

import main.java.opal.pokemon.main.view.DialogueScreen;

public class DialogueController extends ScreenController {

    public String currentDialogue = "";

    public DialogueController(GameController gameController) {
        super(gameController);
        // create model and view - but skip model until actually needed
        screen = new DialogueScreen(this);
    }

    @Override
    public void update() {

    }
}
