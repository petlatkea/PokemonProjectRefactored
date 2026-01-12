package main.java.opal.pokemon.game.screens.overworld.characters.npc;

import main.java.opal.pokemon.game.GameController;
import main.java.opal.pokemon.game.GameState;
import main.java.opal.pokemon.game.screens.overworld.characters.EntityModel;

public class NPC_Female5 extends NPC {

    public NPC_Female5(GameController gp) {
        super(gp);

        model.direction = EntityModel.Direction.DOWN;
        model.speed = 3;

        view.loadSprites("/images/characters/npc/female_5/");
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0] = "I told my friend to come meet me \nat the entrance of the forest";
        dialogues[1] = "but i've been waiting here for hours, \nand he's still not here.";
        dialogues[2] = "Do you think he forgot? \nor maybe he doesn't like me..";
    }

    public void speak() {
        if (dialogues[dialogueIndex] == dialogues[3]) {
            gp.gameState = GameState.playState;
        }
        super.speak();
    }
}
