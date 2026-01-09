package main.java.opal.pokemon.entity;

import main.java.opal.pokemon.main.controller.GameController;
import main.java.opal.pokemon.main.controller.GameState;

public class NPC_Male5 extends NPC {

    public NPC_Male5(GameController gp) {
        super(gp);

        model.direction = "down";
        model.speed = 3;

        getNPCImage();
        setDialogue();
    }

    public void getNPCImage() {
        view.up1 = view.setup("/images/characters/npc/male_5/up_1", this);
        view.up2 = view.setup("/images/characters/npc/male_5/up_2", this);
        view.up3 = view.setup("/images/characters/npc/male_5/up_3", this);
        view.left1 = view.setup("/images/characters/npc/male_5/left_1", this);
        view.left2 = view.setup("/images/characters/npc/male_5/left_2", this);
        view.left3 = view.setup("/images/characters/npc/male_5/left_3", this);
        view.down1 = view.setup("/images/characters/npc/male_5/down_1", this);
        view.down2 = view.setup("/images/characters/npc/male_5/down_2", this);
        view.down3 = view.setup("/images/characters/npc/male_5/down_3", this);
        view.right1 = view.setup("/images/characters/npc/male_5/right_1", this);
        view.right2 = view.setup("/images/characters/npc/male_5/right_2", this);
        view.right3 = view.setup("/images/characters/npc/male_5/right_3", this);
    }

    public void setDialogue() {
        dialogues[0] = "My friend told me to meet her at the \nentrance of the forest.";
        dialogues[1] = "Here i am. \nBut shes nowhere to be seen.";
        dialogues[2] = "I've been waiting for hours.. \nDo you think she pranked me?";
    }

    public void speak() {
        if (dialogues[dialogueIndex] == dialogues[3]) {
            gp.gameState = GameState.playState;
        }
        super.speak();
    }
}
