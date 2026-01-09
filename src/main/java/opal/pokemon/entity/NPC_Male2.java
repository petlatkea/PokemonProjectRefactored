package main.java.opal.pokemon.entity;

import main.java.opal.pokemon.main.controller.GameController;
import main.java.opal.pokemon.main.controller.GameState;

public class NPC_Male2 extends NPC {

    public NPC_Male2(GameController gp) {
        super(gp);

        model.direction = "down";
        model.speed = 3;

        getNPCImage();
        setDialogue();
    }

    public void getNPCImage() {
        view.up1 = view.setup("/images/characters/npc/male_2/up_1", this);
        view.up2 = view.setup("/images/characters/npc/male_2/up_2", this);
        view.up3 = view.setup("/images/characters/npc/male_2/up_3", this);
        view.left1 = view.setup("/images/characters/npc/male_2/left_1", this);
        view.left2 = view.setup("/images/characters/npc/male_2/left_2", this);
        view.left3 = view.setup("/images/characters/npc/male_2/left_3", this);
        view.down1 = view.setup("/images/characters/npc/male_2/down_1", this);
        view.down2 = view.setup("/images/characters/npc/male_2/down_2", this);
        view.down3 = view.setup("/images/characters/npc/male_2/down_3", this);
        view.right1 = view.setup("/images/characters/npc/male_2/right_1", this);
        view.right2 = view.setup("/images/characters/npc/male_2/right_2", this);
        view.right3 = view.setup("/images/characters/npc/male_2/right_3", this);
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
