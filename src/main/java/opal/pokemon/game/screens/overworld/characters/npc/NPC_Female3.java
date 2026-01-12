package main.java.opal.pokemon.game.screens.overworld.characters.npc;

import main.java.opal.pokemon.game.GameController;
import main.java.opal.pokemon.game.GameState;
import main.java.opal.pokemon.game.screens.overworld.characters.EntityModel;

public class NPC_Female3 extends NPC {

    public NPC_Female3(GameController gp) {
        super(gp);

        model.direction = EntityModel.Direction.DOWN;
        model.speed = 3;

        view.loadSprites("/images/characters/npc/female_3/");
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0] = "Oh hello there dear, have you come \nto see my pretty flowers?";
        dialogues[1] = "I've been growing them for as long \nas i can remember.";
        dialogues[2] = "You're welcome to look around, \nbut please don't tramble any of them.";
    }

    public void speak() {
        if (dialogues[dialogueIndex] == dialogues[3]) {
            gp.gameState = GameState.playState;
        }
        super.speak();
    }
}
