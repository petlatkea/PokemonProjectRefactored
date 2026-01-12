package main.java.opal.pokemon.game.screens.overworld.characters.npc;

import main.java.opal.pokemon.game.GameController;
import main.java.opal.pokemon.game.GameState;
import main.java.opal.pokemon.game.screens.overworld.characters.EntityModel;

public class NPC_Male6 extends NPC {

    public NPC_Male6(GameController gp) {
        super(gp);

        model.direction = EntityModel.Direction.DOWN;
        model.speed = 3;

        getNPCImage();
        setDialogue();
    }

    public void getNPCImage() {
        view.up1 = view.setup("/images/characters/npc/male_6/up_1");
        view.up2 = view.setup("/images/characters/npc/male_6/up_2");
        view.up3 = view.setup("/images/characters/npc/male_6/up_3");
        view.left1 = view.setup("/images/characters/npc/male_6/left_1");
        view.left2 = view.setup("/images/characters/npc/male_6/left_2");
        view.left3 = view.setup("/images/characters/npc/male_6/left_3");
        view.down1 = view.setup("/images/characters/npc/male_6/down_1");
        view.down2 = view.setup("/images/characters/npc/male_6/down_2");
        view.down3 = view.setup("/images/characters/npc/male_6/down_3");
        view.right1 = view.setup("/images/characters/npc/male_6/right_1");
        view.right2 = view.setup("/images/characters/npc/male_6/right_2");
        view.right3 = view.setup("/images/characters/npc/male_6/right_3");
    }

    public void setDialogue() {
        dialogues[0] = "It is such a lovely day to catch some \nMagikarp for dinner i thought.";
        dialogues[1] = "But of course i went and forgot my \nfishing rod at home..";
        dialogues[2] = "My wife is gonna be so mad when i \ncome home without dinner...";
    }

    public void speak() {
        if (dialogues[dialogueIndex] == dialogues[3]) {
            gp.gameState = GameState.playState;
        }
        super.speak();
    }
}
