package main.java.opal.pokemon.game.screens.overworld.characters.npc;

import main.java.opal.pokemon.game.GameController;
import main.java.opal.pokemon.game.GameState;

public class NPC_Prof extends NPC {

    public NPC_Prof(GameController gp) {
        super(gp);

        model.direction = "down";
        model.speed = 3;

        getNPCImage();
        setDialogue();
    }

    public void getNPCImage() {
        view.up1 = view.setup("/images/characters/npc/prof_peter/up_1");
        view.up2 = view.setup("/images/characters/npc/prof_peter/up_2");
        view.up3 = view.setup("/images/characters/npc/prof_peter/up_3");
        view.left1 = view.setup("/images/characters/npc/prof_peter/left_1");
        view.left2 = view.setup("/images/characters/npc/prof_peter/left_2");
        view.left3 = view.setup("/images/characters/npc/prof_peter/left_3");
        view.down1 = view.setup("/images/characters/npc/prof_peter/down_1");
        view.down2 = view.setup("/images/characters/npc/prof_peter/down_2");
        view.down3 = view.setup("/images/characters/npc/prof_peter/down_3");
        view.right1 = view.setup("/images/characters/npc/prof_peter/right_1");
        view.right2 = view.setup("/images/characters/npc/prof_peter/right_2");
        view.right3 = view.setup("/images/characters/npc/prof_peter/right_3");
    }

    public void setDialogue() {
        dialogues[0] = "Ah, there you are, i was wondering when \nyou would show up.";
        dialogues[1] = "Anyways, i've talked to your mother \nabout you wanting to learn about pokemon";
        dialogues[2] = "She hesitated at first, but has since \ndecided that you are old enough.";
        dialogues[3] = "I can therefore with great pride award \nyou with your first ever pokemon!";
        dialogues[4] = "But before i do, i just wanna show you \nthis video i took of my Glameow";
        dialogues[5] = "Isn't she adorable? look how she \nfetches the stick for me!";
        dialogues[6] = "Oh..";
        dialogues[7] = "Right..";
        dialogues[8] = "Silly me, going on about my Glameow again. \nYou're here for a pokemon";
        dialogues[9] = "Please pick one of the pokemon \nyou see before you.";
        dialogues[10] = "";
    }

    public void speak() {
        if (dialogues[dialogueIndex] == dialogues[11]) {
            gp.gameState = GameState.starterChoiceState;
        }
        super.speak();
    }
}
