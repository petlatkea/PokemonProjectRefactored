package main.java.opal.pokemon.entity;

import main.java.opal.pokemon.main.controller.GameController;
import main.java.opal.pokemon.main.controller.GameState;

public class NPC_Female2 extends NPC {

    public NPC_Female2(GameController gp) {
        super(gp);

        model.direction = "down";
        model.speed = 3;

        getNPCImage();
        setDialogue();
    }

    public void getNPCImage() {
        view.up1 = view.setup("/images/characters/npc/female_2/up_1", this);
        view.up2 = view.setup("/images/characters/npc/female_2/up_2", this);
        view.up3 = view.setup("/images/characters/npc/female_2/up_3", this);
        view.left1 = view.setup("/images/characters/npc/female_2/left_1", this);
        view.left2 = view.setup("/images/characters/npc/female_2/left_2", this);
        view.left3 = view.setup("/images/characters/npc/female_2/left_3", this);
        view.down1 = view.setup("/images/characters/npc/female_2/down_1", this);
        view.down2 = view.setup("/images/characters/npc/female_2/down_2", this);
        view.down3 = view.setup("/images/characters/npc/female_2/down_3", this);
        view.right1 = view.setup("/images/characters/npc/female_2/right_1", this);
        view.right2 = view.setup("/images/characters/npc/female_2/right_2", this);
        view.right3 = view.setup("/images/characters/npc/female_2/right_3", this);
    }

    public void setDialogue() {
        dialogues[0] = "Such a lovely weather outside today \ndon't you think?";
        dialogues[1] = "My husband went to the beach at \nOpal Springs to fish for Magikarp";
        dialogues[2] = "But he has forgotten his fishing rod \nhere at home";
        dialogues[3] = "What should we eat for dinner then?";
    }

    public void speak() {
        if (dialogues[dialogueIndex] == dialogues[4]) {
            gp.gameState = GameState.playState;
        }
        super.speak();
    }
}
