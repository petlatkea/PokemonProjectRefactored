package main.java.opal.pokemon.entity;

import main.java.opal.pokemon.main.controller.GameController;
import main.java.opal.pokemon.main.controller.GameState;

public class NPC_Male1 extends NPC {

    public NPC_Male1(GameController gp) {
        super(gp);

        model.direction = "down";
        model.speed = 3;

        getNPCImage();
        setDialogue();
    }

    public void getNPCImage() {
        view.up1 = view.setup("/images/characters/npc/male_1/up_1", this);
        view.up2 = view.setup("/images/characters/npc/male_1/up_2", this);
        view.up3 = view.setup("/images/characters/npc/male_1/up_3", this);
        view.left1 = view.setup("/images/characters/npc/male_1/left_1", this);
        view.left2 = view.setup("/images/characters/npc/male_1/left_2", this);
        view.left3 = view.setup("/images/characters/npc/male_1/left_3", this);
        view.down1 = view.setup("/images/characters/npc/male_1/down_1", this);
        view.down2 = view.setup("/images/characters/npc/male_1/down_2", this);
        view.down3 = view.setup("/images/characters/npc/male_1/down_3", this);
        view.right1 = view.setup("/images/characters/npc/male_1/right_1", this);
        view.right2 = view.setup("/images/characters/npc/male_1/right_2", this);
        view.right3 = view.setup("/images/characters/npc/male_1/right_3", this);
    }

    public void setDialogue() {
        dialogues[0] = "Look at my Machop go!";
        dialogues[1] = "They're really useful for moving rocks, \nsince they're stronger than us humans";
        dialogues[2] = "Sometimes i wish i was a Machop..\nThen i'd be super strong!";
    }

    public void speak() {
        if (dialogues[dialogueIndex] == dialogues[3]) {
            gp.gameState = GameState.playState;
        }
        super.speak();
    }
}
