package main.java.opal.pokemon.game.screens.overworld.characters.npc;

import main.java.opal.pokemon.game.GameController;
import main.java.opal.pokemon.game.GameState;

public class NPC_Female5 extends NPC {

    public NPC_Female5(GameController gp) {
        super(gp);

        model.direction = "down";
        model.speed = 3;

        getNPCImage();
        setDialogue();
    }

    public void getNPCImage() {
        view.up1 = view.setup("/images/characters/npc/female_5/up_1", this);
        view.up2 = view.setup("/images/characters/npc/female_5/up_2", this);
        view.up3 = view.setup("/images/characters/npc/female_5/up_3", this);
        view.left1 = view.setup("/images/characters/npc/female_5/left_1", this);
        view.left2 = view.setup("/images/characters/npc/female_5/left_2", this);
        view.left3 = view.setup("/images/characters/npc/female_5/left_3", this);
        view.down1 = view.setup("/images/characters/npc/female_5/down_1", this);
        view.down2 = view.setup("/images/characters/npc/female_5/down_2", this);
        view.down3 = view.setup("/images/characters/npc/female_5/down_3", this);
        view.right1 = view.setup("/images/characters/npc/female_5/right_1", this);
        view.right2 = view.setup("/images/characters/npc/female_5/right_2", this);
        view.right3 = view.setup("/images/characters/npc/female_5/right_3", this);
    }

    public void setDialogue() {
        dialogues[0] = "I told my friend to come meet me \nat the entrance of the forest";
        dialogues[1] = "but i've been waiting here for hours, \nand he's still not here.";
        dialogues[2] = "Do you think he forgot? \nor maybe he doesn't like me..";
    }

    public void speak() {
        if (dialogues[dialogueIndex] == dialogues[3]) {
            gp.gameState = GameState.playState;
        }
        super.speak();
    }
}
