package main.java.opal.pokemon.game.screens.overworld.characters.npc;

import main.java.opal.pokemon.game.screens.overworld.characters.Entity;
import main.java.opal.pokemon.game.screens.dialogue.DialogueController;
import main.java.opal.pokemon.game.GameController;
import main.java.opal.pokemon.game.screens.overworld.characters.EntityModel.Direction;

public abstract class NPC extends Entity {

    String[] dialogues = new String[25];
    int dialogueIndex = 0;

    public NPC(GameController gp) {
        super(gp);
    }

    public void setAction() {} // Might be used later for NPC interaction

    public void speak() {
        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        // TODO: This is a bit nasty - move that into the controller in some other way!
        ((DialogueController)gp.dialogueController).currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch(gp.getPlayer().model.direction) {
            case UP -> model.direction = Direction.DOWN;
            case DOWN -> model.direction = Direction.UP;
            case LEFT -> model.direction = Direction.RIGHT;
            case RIGHT -> model.direction = Direction.LEFT;
        }
    }

}
