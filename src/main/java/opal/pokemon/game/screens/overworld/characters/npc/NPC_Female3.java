package main.java.opal.pokemon.game.screens.overworld.characters.npc;

import main.java.opal.pokemon.game.GameController;
import main.java.opal.pokemon.game.GameState;

public class NPC_Female3 extends NPC {

    public NPC_Female3(GameController gp) {
        super(gp);

        model.direction = "down";
        model.speed = 3;

        getNPCImage();
        setDialogue();
    }

    public void getNPCImage() {
        view.up1 = view.setup("/images/characters/npc/female_3/up_1", this);
        view.up2 = view.setup("/images/characters/npc/female_3/up_2", this);
        view.up3 = view.setup("/images/characters/npc/female_3/up_3", this);
        view.left1 = view.setup("/images/characters/npc/female_3/left_1", this);
        view.left2 = view.setup("/images/characters/npc/female_3/left_2", this);
        view.left3 = view.setup("/images/characters/npc/female_3/left_3", this);
        view.down1 = view.setup("/images/characters/npc/female_3/down_1", this);
        view.down2 = view.setup("/images/characters/npc/female_3/down_2", this);
        view.down3 = view.setup("/images/characters/npc/female_3/down_3", this);
        view.right1 = view.setup("/images/characters/npc/female_3/right_1", this);
        view.right2 = view.setup("/images/characters/npc/female_3/right_2", this);
        view.right3 = view.setup("/images/characters/npc/female_3/right_3", this);
    }

    public void setDialogue() {
        dialogues[0] = "Oh hello there dear, have you come \nto see my pretty flowers?";
        dialogues[1] = "I've been growing them for as long \nas i can remember.";
        dialogues[2] = "You're welcome to look around, \nbut please don't tramble any of them.";
    }

    public void speak() {
        if (dialogues[dialogueIndex] == dialogues[3]) {
            gp.gameState = GameState.playState;
        }
        super.speak();
    }
}
