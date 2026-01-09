package main.java.opal.pokemon.main.controller;

import main.java.opal.pokemon.entity.NPC;
import main.java.opal.pokemon.main.MouseClick;
import main.java.opal.pokemon.main.model.Controls;
import main.java.opal.pokemon.main.view.DialogueScreen;

public class DialogueController extends ScreenController {

    public String currentDialogue = "";
    private NPC currentNPC = null;

    public DialogueController(GameController gameController) {
        super(gameController);
        // create model and view - but skip model until actually needed
        screen = new DialogueScreen(this);
    }

    @Override
    public void update() {
        // if there is an npc selected - call speak on it every time a button is pressed
    }

    @Override
    public void keyPressed(int keyCode) {
        Controls controls = gameController.getControls();
        // check if it is ENTER or E
        if (controls.enterPressed || controls.ePressed) {
            // make NPC speak - if we have one
            if (currentNPC != null) {
                gameController.soundController.playButtonSound();
                currentNPC.speak();
            }
        }
    }

    @Override
    public void handleLeftClick(MouseClick mouseClick) {
        // Pressed on Dialogue
        // TODO: Not sure if this is supposed to work - nothing seems to happen ...
        if (mouseClick.insideBox((gameController.screenWidth - (254 * 4)) / 2, gameController.screenHeight - (46 * 4) - (gameController.tileSize / 8), 254 * 4, 46 * 4)) {
            gameController.getControls().enterPressed = true;
            gameController.soundController.playButtonSound();
            gameController.gameState = GameState.playState;
        }
    }

    public void initiateDialogueWithNPC(NPC npc) {
        // set this NPC
        this.currentNPC = npc;
        // and initialize speak
        this.currentNPC.speak();
        // Play Sound now??
        gameController.soundController.playButtonSound(); // TODO: Decide if sound should play for the first dialogue, or only for subsequent button-presses

        // change the state to Dialogue
        gameController.gameState = GameState.dialogueState;
    }
}
