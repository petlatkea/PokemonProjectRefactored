package main.java.opal.pokemon.game.screens.overworld.characters.npc;

import main.java.opal.pokemon.game.GameController;
import main.java.opal.pokemon.game.GameState;
import main.java.opal.pokemon.game.screens.overworld.characters.EntityModel;

public class NPC_Male2 extends NPC {

    public NPC_Male2(GameController gp) {
        super(gp);

        model.direction = EntityModel.Direction.DOWN;
        model.speed = 3;

        getNPCImage();
        setDialogue();
    }

    public void getNPCImage() {
        view.up1 = view.setup("/images/characters/npc/male_2/up_1");
        view.up2 = view.setup("/images/characters/npc/male_2/up_2");
        view.up3 = view.setup("/images/characters/npc/male_2/up_3");
        view.left1 = view.setup("/images/characters/npc/male_2/left_1");
        view.left2 = view.setup("/images/characters/npc/male_2/left_2");
        view.left3 = view.setup("/images/characters/npc/male_2/left_3");
        view.down1 = view.setup("/images/characters/npc/male_2/down_1");
        view.down2 = view.setup("/images/characters/npc/male_2/down_2");
        view.down3 = view.setup("/images/characters/npc/male_2/down_3");
        view.right1 = view.setup("/images/characters/npc/male_2/right_1");
        view.right2 = view.setup("/images/characters/npc/male_2/right_2");
        view.right3 = view.setup("/images/characters/npc/male_2/right_3");
    }

    public void setDialogue() {
        dialogues[0] = "A recent rockslide has blocked the \nentrance to the cave";
        dialogues[1] = "and no human is strong enough to move \nor break those rocks";
        dialogues[2] = "So unless you have the move Rock Smash,\ni suggest you turn back.";
    }

    public void speak() {
        if (dialogues[dialogueIndex] == dialogues[3]) {
            gp.gameState = GameState.playState;
        }
        super.speak();
    }
}
