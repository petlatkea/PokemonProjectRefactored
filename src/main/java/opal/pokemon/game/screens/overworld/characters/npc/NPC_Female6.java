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
        view.up1 = view.setup("/images/characters/npc/female_6/up_1", this);
        view.up2 = view.setup("/images/characters/npc/female_6/up_2", this);
        view.up3 = view.setup("/images/characters/npc/female_6/up_3", this);
        view.left1 = view.setup("/images/characters/npc/female_6/left_1", this);
        view.left2 = view.setup("/images/characters/npc/female_6/left_2", this);
        view.left3 = view.setup("/images/characters/npc/female_6/left_3", this);
        view.down1 = view.setup("/images/characters/npc/female_6/down_1", this);
        view.down2 = view.setup("/images/characters/npc/female_6/down_2", this);
        view.down3 = view.setup("/images/characters/npc/female_6/down_3", this);
        view.right1 = view.setup("/images/characters/npc/female_6/right_1", this);
        view.right2 = view.setup("/images/characters/npc/female_6/right_2", this);
        view.right3 = view.setup("/images/characters/npc/female_6/right_3", this);
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
