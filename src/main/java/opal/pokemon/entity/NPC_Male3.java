package main.java.opal.pokemon.entity;

import main.java.opal.pokemon.main.controller.GameController;

public class NPC_Male3 extends Entity {

    public NPC_Male3(GameController gp) {
        super(gp);

        direction = "down";
        speed = 3;

        getNPCImage();
        setDialogue();
    }

    public void getNPCImage() {
        up1 = setup("/images/characters/npc/male_3/up_1");
        up2 = setup("/images/characters/npc/male_3/up_2");
        up3 = setup("/images/characters/npc/male_3/up_3");
        left1 = setup("/images/characters/npc/male_3/left_1");
        left2 = setup("/images/characters/npc/male_3/left_2");
        left3 = setup("/images/characters/npc/male_3/left_3");
        down1 = setup("/images/characters/npc/male_3/down_1");
        down2 = setup("/images/characters/npc/male_3/down_2");
        down3 = setup("/images/characters/npc/male_3/down_3");
        right1 = setup("/images/characters/npc/male_3/right_1");
        right2 = setup("/images/characters/npc/male_3/right_2");
        right3 = setup("/images/characters/npc/male_3/right_3");
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
        if (dialogues[dialogueIndex] == dialogues[6]) {
            gp.getControls().ePressed = false;
        }
        super.speak();
    }
}
