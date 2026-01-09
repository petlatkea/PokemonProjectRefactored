package main.java.opal.pokemon.entity;

import main.java.opal.pokemon.main.controller.GameController;
import main.java.opal.pokemon.main.controller.GameState;

public class NPC_Female1 extends NPC {

    public NPC_Female1(GameController gp) {
        super(gp);

        model.direction = "down";
        model.speed = 3;

        getNPCImage();
        setDialogue();
    }

    public void getNPCImage() {
        view.up1 = view.setup("/images/characters/npc/female_1/up_1", this);
        view.up2 = view.setup("/images/characters/npc/female_1/up_2", this);
        view.up3 = view.setup("/images/characters/npc/female_1/up_3", this);
        view.left1 = view.setup("/images/characters/npc/female_1/left_1", this);
        view.left2 = view.setup("/images/characters/npc/female_1/left_2", this);
        view.left3 = view.setup("/images/characters/npc/female_1/left_3", this);
        view.down1 = view.setup("/images/characters/npc/female_1/down_1", this);
        view.down2 = view.setup("/images/characters/npc/female_1/down_2", this);
        view.down3 = view.setup("/images/characters/npc/female_1/down_3", this);
        view.right1 = view.setup("/images/characters/npc/female_1/right_1", this);
        view.right2 = view.setup("/images/characters/npc/female_1/right_2", this);
        view.right3 = view.setup("/images/characters/npc/female_1/right_3", this);
    }

    public void setDialogue() {
        dialogues[0] = "Hey kid! looks like you don't have a \npokemon";
        dialogues[1] = "You really shouldn't leave town without \none, its dangerous out there.";
        dialogues[2] = "Go visit prof. Peter at the laboratory,\nhe will surely give you one!";
    }

    public void speak() {
        if (dialogues[dialogueIndex] == dialogues[3]) {
            gp.gameState = GameState.playState;
        }
        super.speak();
    }
}
