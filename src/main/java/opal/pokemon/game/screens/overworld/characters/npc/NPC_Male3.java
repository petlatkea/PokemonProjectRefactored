package main.java.opal.pokemon.game.screens.overworld.characters.npc;

import main.java.opal.pokemon.game.GameController;
import main.java.opal.pokemon.game.GameState;

public class NPC_Male3 extends NPC {

    public NPC_Male3(GameController gp) {
        super(gp);

        model.direction = "down";
        model.speed = 3;

        getNPCImage();
        setDialogue();
    }

    public void getNPCImage() {
        view.up1 = view.setup("/images/characters/npc/male_3/up_1");
        view.up2 = view.setup("/images/characters/npc/male_3/up_2");
        view.up3 = view.setup("/images/characters/npc/male_3/up_3");
        view.left1 = view.setup("/images/characters/npc/male_3/left_1");
        view.left2 = view.setup("/images/characters/npc/male_3/left_2");
        view.left3 = view.setup("/images/characters/npc/male_3/left_3");
        view.down1 = view.setup("/images/characters/npc/male_3/down_1");
        view.down2 = view.setup("/images/characters/npc/male_3/down_2");
        view.down3 = view.setup("/images/characters/npc/male_3/down_3");
        view.right1 = view.setup("/images/characters/npc/male_3/right_1");
        view.right2 = view.setup("/images/characters/npc/male_3/right_2");
        view.right3 = view.setup("/images/characters/npc/male_3/right_3");
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
