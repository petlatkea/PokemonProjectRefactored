package main.java.opal.pokemon.entity;

import main.java.opal.pokemon.main.controller.GameController;
import main.java.opal.pokemon.main.controller.GameState;

public class NPC_Prof extends NPC {

    public NPC_Prof(GameController gp) {
        super(gp);

        model.direction = "down";
        model.speed = 3;

        getNPCImage();
        setDialogue();
    }

    public void getNPCImage() {
        view.up1 = view.setup("/images/characters/npc/prof_peter/up_1", this);
        view.up2 = view.setup("/images/characters/npc/prof_peter/up_2", this);
        view.up3 = view.setup("/images/characters/npc/prof_peter/up_3", this);
        view.left1 = view.setup("/images/characters/npc/prof_peter/left_1", this);
        view.left2 = view.setup("/images/characters/npc/prof_peter/left_2", this);
        view.left3 = view.setup("/images/characters/npc/prof_peter/left_3", this);
        view.down1 = view.setup("/images/characters/npc/prof_peter/down_1", this);
        view.down2 = view.setup("/images/characters/npc/prof_peter/down_2", this);
        view.down3 = view.setup("/images/characters/npc/prof_peter/down_3", this);
        view.right1 = view.setup("/images/characters/npc/prof_peter/right_1", this);
        view.right2 = view.setup("/images/characters/npc/prof_peter/right_2", this);
        view.right3 = view.setup("/images/characters/npc/prof_peter/right_3", this);
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
