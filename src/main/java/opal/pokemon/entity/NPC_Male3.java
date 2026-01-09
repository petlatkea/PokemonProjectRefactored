package main.java.opal.pokemon.entity;

import main.java.opal.pokemon.main.controller.GameController;
import main.java.opal.pokemon.main.controller.GameState;

public class NPC_Male3 extends NPC {

    public NPC_Male3(GameController gp) {
        super(gp);

        model.direction = "down";
        model.speed = 3;

        getNPCImage();
        setDialogue();
    }

    public void getNPCImage() {
        view.up1 = view.setup("/images/characters/npc/male_3/up_1", this);
        view.up2 = view.setup("/images/characters/npc/male_3/up_2", this);
        view.up3 = view.setup("/images/characters/npc/male_3/up_3", this);
        view.left1 = view.setup("/images/characters/npc/male_3/left_1", this);
        view.left2 = view.setup("/images/characters/npc/male_3/left_2", this);
        view.left3 = view.setup("/images/characters/npc/male_3/left_3", this);
        view.down1 = view.setup("/images/characters/npc/male_3/down_1", this);
        view.down2 = view.setup("/images/characters/npc/male_3/down_2", this);
        view.down3 = view.setup("/images/characters/npc/male_3/down_3", this);
        view.right1 = view.setup("/images/characters/npc/male_3/right_1", this);
        view.right2 = view.setup("/images/characters/npc/male_3/right_2", this);
        view.right3 = view.setup("/images/characters/npc/male_3/right_3", this);
    }

    public void setDialogue() {
        dialogues[0] = "SSSSSSHHHH! \nThere's a shiny Wurmple right there!";
        dialogues[1] = "i'm gonna catch it and..";
        dialogues[2] = "OH GREAT! \nYOU MADE IT RUN AWAY!";
        dialogues[3] = "Do you even know how RARE a shiny \npokemon is?!!!";
        dialogues[4] = "They say its a 1 in 8.192 chance..";
        dialogues[5] = "Arceus damn it man, i might never \nsee a shiny ever again!";
        dialogues[6] = "Leave before i make my Bidoof defeat \nall your pokemon.";
    }

    public void speak() {
        if (dialogues[dialogueIndex] == dialogues[7]) {
            gp.gameState = GameState.playState;
        }
        super.speak();
    }
}
