package main.java.opal.pokemon.entity;

import main.java.opal.pokemon.main.controller.GameController;
import main.java.opal.pokemon.main.controller.GameState;

public class NPC_Male2 extends NPC {

    public NPC_Male2(GameController gp) {
        super(gp);

        direction = "down";
        speed = 3;

        getNPCImage();
        setDialogue();
    }

    public void getNPCImage() {
        up1 = setup("/images/characters/npc/male_2/up_1");
        up2 = setup("/images/characters/npc/male_2/up_2");
        up3 = setup("/images/characters/npc/male_2/up_3");
        left1 = setup("/images/characters/npc/male_2/left_1");
        left2 = setup("/images/characters/npc/male_2/left_2");
        left3 = setup("/images/characters/npc/male_2/left_3");
        down1 = setup("/images/characters/npc/male_2/down_1");
        down2 = setup("/images/characters/npc/male_2/down_2");
        down3 = setup("/images/characters/npc/male_2/down_3");
        right1 = setup("/images/characters/npc/male_2/right_1");
        right2 = setup("/images/characters/npc/male_2/right_2");
        right3 = setup("/images/characters/npc/male_2/right_3");
    }

    public void setDialogue() {
        dialogues[0] = "A recent rockslide has blocked the \nentrance to the cave";
        dialogues[1] = "and no human is strong enough to move \nor break those rocks";
        dialogues[2] = "So unless you have the move Rock Smash,\ni suggest you turn back.";
    }

    public void speak() {
        if (dialogues[dialogueIndex] == dialogues[3]) {
            gp.gameState = GameState.playState;
        }
        super.speak();
    }
}
