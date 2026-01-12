package main.java.opal.pokemon.game.screens.overworld.characters.npc;

import main.java.opal.pokemon.game.GameController;
import main.java.opal.pokemon.game.GameState;
import main.java.opal.pokemon.game.screens.overworld.characters.EntityModel;

public class NPC_Female1 extends NPC {

    public NPC_Female1(GameController gp) {
        super(gp);

        model.direction = EntityModel.Direction.DOWN;
        model.speed = 3;

        getNPCImage();
        setDialogue();
    }

    public void getNPCImage() {
        view.up1 = view.setup("/images/characters/npc/female_1/up_1");
        view.up2 = view.setup("/images/characters/npc/female_1/up_2");
        view.up3 = view.setup("/images/characters/npc/female_1/up_3");
        view.left1 = view.setup("/images/characters/npc/female_1/left_1");
        view.left2 = view.setup("/images/characters/npc/female_1/left_2");
        view.left3 = view.setup("/images/characters/npc/female_1/left_3");
        view.down1 = view.setup("/images/characters/npc/female_1/down_1");
        view.down2 = view.setup("/images/characters/npc/female_1/down_2");
        view.down3 = view.setup("/images/characters/npc/female_1/down_3");
        view.right1 = view.setup("/images/characters/npc/female_1/right_1");
        view.right2 = view.setup("/images/characters/npc/female_1/right_2");
        view.right3 = view.setup("/images/characters/npc/female_1/right_3");
    }

    public void setDialogue() {
        dialogues[0] = "Hey kid! looks like you don't have a \npokemon";
        dialogues[1] = "You really shouldn't leave town without \none, its dangerous out there.";
        dialogues[2] = "Go visit prof. Peter at the laboratory,\nhe will surely give you one!";
    }

    public void speak() {
        if (dialogues[dialogueIndex] == dialogues[3]) {
            gp.gameState = GameState.playState;
        }
        super.speak();
    }
}
