package main.java.opal.pokemon.game.screens.overworld.characters.npc;

import main.java.opal.pokemon.game.GameController;
import main.java.opal.pokemon.game.GameState;

public class NPC_Female6 extends NPC {

    public NPC_Female6(GameController gp) {
        super(gp);

        model.direction = "down";
        model.speed = 3;

        getNPCImage();
        setDialogue();
    }

    public void getNPCImage() {
        view.up1 = view.setup("/images/characters/npc/female_6/up_1");
        view.up2 = view.setup("/images/characters/npc/female_6/up_2");
        view.up3 = view.setup("/images/characters/npc/female_6/up_3");
        view.left1 = view.setup("/images/characters/npc/female_6/left_1");
        view.left2 = view.setup("/images/characters/npc/female_6/left_2");
        view.left3 = view.setup("/images/characters/npc/female_6/left_3");
        view.down1 = view.setup("/images/characters/npc/female_6/down_1");
        view.down2 = view.setup("/images/characters/npc/female_6/down_2");
        view.down3 = view.setup("/images/characters/npc/female_6/down_3");
        view.right1 = view.setup("/images/characters/npc/female_6/right_1");
        view.right2 = view.setup("/images/characters/npc/female_6/right_2");
        view.right3 = view.setup("/images/characters/npc/female_6/right_3");
    }

    public void setDialogue() {
        dialogues[0] = "Hello and welcome to Celestic Town!!!";
        dialogues[1] = "My twin sister and I like to welcome \nevery new person that visits!";
        dialogues[2] = "She's known for being a copycat though. \nSo she'll probably just repeat what i said.";
    }

    public void speak() {
        if (dialogues[dialogueIndex] == dialogues[3]) {
            gp.gameState = GameState.playState;
        }
        super.speak();
    }
}
