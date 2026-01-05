package main.java.opal.pokemon.main.controller;

import main.java.opal.pokemon.main.MouseClick;
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

    @Override
    public void handleLeftClick(MouseClick mouseClick) {
        // Pressed on Dialogue
        // TODO: Not sure if this is supposed to work - nothing seems to happen ...
        if (mouseClick.insideBox((gameController.screenWidth - (254 * 4)) / 2, gameController.screenHeight - (46 * 4) - (gameController.tileSize / 8), 254 * 4, 46 * 4)) {
            gameController.getView().getKeyH().enterPressed = true;
            gameController.buttonSound.playButtonSound();
            gameController.gameState = GameState.playState;
        }
    }
}
