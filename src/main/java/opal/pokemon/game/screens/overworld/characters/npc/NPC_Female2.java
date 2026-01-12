package main.java.opal.pokemon.game.screens.overworld.characters.npc;

import main.java.opal.pokemon.game.GameController;
import main.java.opal.pokemon.game.GameState;
import main.java.opal.pokemon.game.screens.overworld.characters.EntityModel;

public class NPC_Female2 extends NPC {

    public NPC_Female2(GameController gp) {
        super(gp);

        model.direction = EntityModel.Direction.DOWN;
        model.speed = 3;

        getNPCImage();
        setDialogue();
    }

    public void getNPCImage() {
        view.up1 = view.setup("/images/characters/npc/female_2/up_1");
        view.up2 = view.setup("/images/characters/npc/female_2/up_2");
        view.up3 = view.setup("/images/characters/npc/female_2/up_3");
        view.left1 = view.setup("/images/characters/npc/female_2/left_1");
        view.left2 = view.setup("/images/characters/npc/female_2/left_2");
        view.left3 = view.setup("/images/characters/npc/female_2/left_3");
        view.down1 = view.setup("/images/characters/npc/female_2/down_1");
        view.down2 = view.setup("/images/characters/npc/female_2/down_2");
        view.down3 = view.setup("/images/characters/npc/female_2/down_3");
        view.right1 = view.setup("/images/characters/npc/female_2/right_1");
        view.right2 = view.setup("/images/characters/npc/female_2/right_2");
        view.right3 = view.setup("/images/characters/npc/female_2/right_3");
    }

    public void setDialogue() {
        dialogues[0] = "Such a lovely weather outside today \ndon't you think?";
        dialogues[1] = "My husband went to the beach at \nOpal Springs to fish for Magikarp";
        dialogues[2] = "But he has forgotten his fishing rod \nhere at home";
        dialogues[3] = "What should we eat for dinner then?";
    }

    public void speak() {
        if (dialogues[dialogueIndex] == dialogues[4]) {
            gp.gameState = GameState.playState;
        }
        super.speak();
    }
}
