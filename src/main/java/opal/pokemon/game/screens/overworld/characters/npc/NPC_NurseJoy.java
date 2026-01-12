package main.java.opal.pokemon.game.screens.overworld.characters.npc;

import main.java.opal.pokemon.game.GameController;
import main.java.opal.pokemon.game.GameState;
import main.java.opal.pokemon.game.screens.overworld.characters.EntityModel;

public class NPC_NurseJoy extends NPC {

    public NPC_NurseJoy(GameController gp) {
        super(gp);

        model.direction = EntityModel.Direction.DOWN;
        model.speed = 3;

        getNPCImage();
        setDialogue();
    }

    public void getNPCImage() {
        view.up1 = view.setup("/images/characters/npc/nurse_joy/up_1");
        view.up2 = view.setup("/images/characters/npc/nurse_joy/up_2");
        view.up3 = view.setup("/images/characters/npc/nurse_joy/up_3");
        view.left1 = view.setup("/images/characters/npc/nurse_joy/left_1");
        view.left2 = view.setup("/images/characters/npc/nurse_joy/left_2");
        view.left3 = view.setup("/images/characters/npc/nurse_joy/left_3");
        view.down1 = view.setup("/images/characters/npc/nurse_joy/down_1");
        view.down2 = view.setup("/images/characters/npc/nurse_joy/down_2");
        view.down3 = view.setup("/images/characters/npc/nurse_joy/down_3");
        view.right1 = view.setup("/images/characters/npc/nurse_joy/right_1");
        view.right2 = view.setup("/images/characters/npc/nurse_joy/right_2");
        view.right3 = view.setup("/images/characters/npc/nurse_joy/right_3");
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
