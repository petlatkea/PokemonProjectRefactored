package main.java.opal.pokemon.game.screens.overworld.characters.npc;

import main.java.opal.pokemon.game.GameController;
import main.java.opal.pokemon.game.GameState;
import main.java.opal.pokemon.game.screens.overworld.characters.EntityModel;

public class NPC_NurseJoy extends NPC {

    public NPC_NurseJoy(GameController gp) {
        super(gp);

        model.direction = EntityModel.Direction.DOWN;
        model.speed = 3;

        view.loadSprites("/images/characters/npc/nurse_joy/");
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0] = "Hi there! I'm Nurse Joy. \nAnd this is the Pokecenter.";
        dialogues[1] = "Normally you would heal your wounded \n or fainted pokemon here.";
        dialogues[2] = "but yesterday a group of Bidoofs \nravaged the place. So we had so close it.";
        dialogues[3] = "We're waiting for someone to come help \nus get rid of all the Bidoofs";
    }

    public void speak() {
        if (dialogues[dialogueIndex] == dialogues[4]) {
            gp.gameState = GameState.playState;
        }
        super.speak();
    }
}
