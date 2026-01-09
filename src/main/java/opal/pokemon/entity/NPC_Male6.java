package main.java.opal.pokemon.entity;

import main.java.opal.pokemon.main.controller.GameController;
import main.java.opal.pokemon.main.controller.GameState;

public class NPC_Male6 extends NPC {

    public NPC_Male6(GameController gp) {
        super(gp);

        model.direction = "down";
        model.speed = 3;

        getNPCImage();
        setDialogue();
    }

    public void getNPCImage() {
        view.up1 = view.setup("/images/characters/npc/male_6/up_1", this);
        view.up2 = view.setup("/images/characters/npc/male_6/up_2", this);
        view.up3 = view.setup("/images/characters/npc/male_6/up_3", this);
        view.left1 = view.setup("/images/characters/npc/male_6/left_1", this);
        view.left2 = view.setup("/images/characters/npc/male_6/left_2", this);
        view.left3 = view.setup("/images/characters/npc/male_6/left_3", this);
        view.down1 = view.setup("/images/characters/npc/male_6/down_1", this);
        view.down2 = view.setup("/images/characters/npc/male_6/down_2", this);
        view.down3 = view.setup("/images/characters/npc/male_6/down_3", this);
        view.right1 = view.setup("/images/characters/npc/male_6/right_1", this);
        view.right2 = view.setup("/images/characters/npc/male_6/right_2", this);
        view.right3 = view.setup("/images/characters/npc/male_6/right_3", this);
    }

    public void setDialogue() {
        dialogues[0] = "It is such a lovely day to catch some \nMagikarp for dinner i thought.";
        dialogues[1] = "But of course i went and forgot my \nfishing rod at home..";
        dialogues[2] = "My wife is gonna be so mad when i \ncome home without dinner...";
    }

    public void speak() {
        if (dialogues[dialogueIndex] == dialogues[3]) {
            gp.gameState = GameState.playState;
        }
        super.speak();
    }
}
