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
        view.up1 = view.setup("/images/characters/npc/female_3/up_1");
        view.up2 = view.setup("/images/characters/npc/female_3/up_2");
        view.up3 = view.setup("/images/characters/npc/female_3/up_3");
        view.left1 = view.setup("/images/characters/npc/female_3/left_1");
        view.left2 = view.setup("/images/characters/npc/female_3/left_2");
        view.left3 = view.setup("/images/characters/npc/female_3/left_3");
        view.down1 = view.setup("/images/characters/npc/female_3/down_1");
        view.down2 = view.setup("/images/characters/npc/female_3/down_2");
        view.down3 = view.setup("/images/characters/npc/female_3/down_3");
        view.right1 = view.setup("/images/characters/npc/female_3/right_1");
        view.right2 = view.setup("/images/characters/npc/female_3/right_2");
        view.right3 = view.setup("/images/characters/npc/female_3/right_3");
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
