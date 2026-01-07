package main.java.opal.pokemon.entity;

import main.java.opal.pokemon.main.controller.GameController;
import main.java.opal.pokemon.main.controller.GameState;

public class NPC_Female6 extends Entity {

    public NPC_Female6(GameController gp) {
        super(gp);

        direction = "down";
        speed = 3;

        getNPCImage();
        setDialogue();
    }

    public void getNPCImage() {
        up1 = setup("/images/characters/npc/female_6/up_1");
        up2 = setup("/images/characters/npc/female_6/up_2");
        up3 = setup("/images/characters/npc/female_6/up_3");
        left1 = setup("/images/characters/npc/female_6/left_1");
        left2 = setup("/images/characters/npc/female_6/left_2");
        left3 = setup("/images/characters/npc/female_6/left_3");
        down1 = setup("/images/characters/npc/female_6/down_1");
        down2 = setup("/images/characters/npc/female_6/down_2");
        down3 = setup("/images/characters/npc/female_6/down_3");
        right1 = setup("/images/characters/npc/female_6/right_1");
        right2 = setup("/images/characters/npc/female_6/right_2");
        right3 = setup("/images/characters/npc/female_6/right_3");
    }

    public void setDialogue() {
        dialogues[0] = "Hello and welcome to Celestic Town!!!";
        dialogues[1] = "My twin sister and I like to welcome \nevery new person that visits!";
        dialogues[2] = "She's known for being a copycat though. \nSo she'll probably just repeat what i said.";
    }

    public void speak() {
        if (dialogues[dialogueIndex] == dialogues[3]) {
            gp.gameState = GameState.playState;
        }
        super.speak();
    }
}
