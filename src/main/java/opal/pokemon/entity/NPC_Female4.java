package main.java.opal.pokemon.entity;

import main.java.opal.pokemon.main.controller.GameController;
import main.java.opal.pokemon.main.controller.GameState;

public class NPC_Female4 extends NPC {

    public NPC_Female4(GameController gp) {
        super(gp);

        model.direction = "down";
        model.speed = 3;

        getNPCImage();
        setDialogue();
    }

    public void getNPCImage() {
        view.up1 = view.setup("/images/characters/npc/female_4/up_1", this);
        view.up2 = view.setup("/images/characters/npc/female_4/up_2", this);
        view.up3 = view.setup("/images/characters/npc/female_4/up_3", this);
        view.left1 = view.setup("/images/characters/npc/female_4/left_1", this);
        view.left2 = view.setup("/images/characters/npc/female_4/left_2", this);
        view.left3 = view.setup("/images/characters/npc/female_4/left_3", this);
        view.down1 = view.setup("/images/characters/npc/female_4/down_1", this);
        view.down2 = view.setup("/images/characters/npc/female_4/down_2", this);
        view.down3 = view.setup("/images/characters/npc/female_4/down_3", this);
        view.right1 = view.setup("/images/characters/npc/female_4/right_1", this);
        view.right2 = view.setup("/images/characters/npc/female_4/right_2", this);
        view.right3 = view.setup("/images/characters/npc/female_4/right_3", this);
    }

    public void setDialogue() {
        dialogues[0] = "Hi there, im miss Stone. \nI'm what you would call a Geologist";
        dialogues[1] = "I study rocks, like this Geodude here.";
        dialogues[2] = "What? you're telling me this \nisn't a Geodude but just a normal rock?";
        dialogues[3] = "You think i don't know the difference \nbetween a rock and a Geodude?";
        dialogues[4] = "There's a reason i'm a geologist \nand you aren't.";
        dialogues[5] = "It's clearly just, uh.. \nsleeping. Yeah, sleeping..";
    }

    public void speak() {
        if (dialogues[dialogueIndex] == dialogues[6]) {
            gp.gameState = GameState.playState;
        }
        super.speak();
    }
}
