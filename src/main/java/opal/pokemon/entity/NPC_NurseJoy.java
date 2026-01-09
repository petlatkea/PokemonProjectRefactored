package main.java.opal.pokemon.entity;

import main.java.opal.pokemon.main.controller.GameController;
import main.java.opal.pokemon.main.controller.GameState;

public class NPC_NurseJoy extends NPC {

    public NPC_NurseJoy(GameController gp) {
        super(gp);

        model.direction = "down";
        model.speed = 3;

        getNPCImage();
        setDialogue();
    }

    public void getNPCImage() {
        view.up1 = view.setup("/images/characters/npc/nurse_joy/up_1", this);
        view.up2 = view.setup("/images/characters/npc/nurse_joy/up_2", this);
        view.up3 = view.setup("/images/characters/npc/nurse_joy/up_3", this);
        view.left1 = view.setup("/images/characters/npc/nurse_joy/left_1", this);
        view.left2 = view.setup("/images/characters/npc/nurse_joy/left_2", this);
        view.left3 = view.setup("/images/characters/npc/nurse_joy/left_3", this);
        view.down1 = view.setup("/images/characters/npc/nurse_joy/down_1", this);
        view.down2 = view.setup("/images/characters/npc/nurse_joy/down_2", this);
        view.down3 = view.setup("/images/characters/npc/nurse_joy/down_3", this);
        view.right1 = view.setup("/images/characters/npc/nurse_joy/right_1", this);
        view.right2 = view.setup("/images/characters/npc/nurse_joy/right_2", this);
        view.right3 = view.setup("/images/characters/npc/nurse_joy/right_3", this);
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
