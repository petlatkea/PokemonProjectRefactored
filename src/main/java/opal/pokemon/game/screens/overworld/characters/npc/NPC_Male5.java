package main.java.opal.pokemon.game.screens.overworld.characters.npc;

import main.java.opal.pokemon.game.GameController;
import main.java.opal.pokemon.game.GameState;
import main.java.opal.pokemon.game.screens.overworld.characters.EntityModel;

public class NPC_Male5 extends NPC {

    public NPC_Male5(GameController gp) {
        super(gp);

        model.direction = EntityModel.Direction.DOWN;
        model.speed = 3;

        getNPCImage();
        setDialogue();
    }

    public void getNPCImage() {
        view.up1 = view.setup("/images/characters/npc/male_5/up_1");
        view.up2 = view.setup("/images/characters/npc/male_5/up_2");
        view.up3 = view.setup("/images/characters/npc/male_5/up_3");
        view.left1 = view.setup("/images/characters/npc/male_5/left_1");
        view.left2 = view.setup("/images/characters/npc/male_5/left_2");
        view.left3 = view.setup("/images/characters/npc/male_5/left_3");
        view.down1 = view.setup("/images/characters/npc/male_5/down_1");
        view.down2 = view.setup("/images/characters/npc/male_5/down_2");
        view.down3 = view.setup("/images/characters/npc/male_5/down_3");
        view.right1 = view.setup("/images/characters/npc/male_5/right_1");
        view.right2 = view.setup("/images/characters/npc/male_5/right_2");
        view.right3 = view.setup("/images/characters/npc/male_5/right_3");
    }

    public void setDialogue() {
        dialogues[0] = "My friend told me to meet her at the \nentrance of the forest.";
        dialogues[1] = "Here i am. \nBut shes nowhere to be seen.";
        dialogues[2] = "I've been waiting for hours.. \nDo you think she pranked me?";
    }

    public void speak() {
        if (dialogues[dialogueIndex] == dialogues[3]) {
            gp.gameState = GameState.playState;
        }
        super.speak();
    }
}
