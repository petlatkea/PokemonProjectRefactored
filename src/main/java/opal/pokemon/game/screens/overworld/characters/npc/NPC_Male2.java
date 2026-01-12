package main.java.opal.pokemon.game.screens.overworld.characters.npc;

import main.java.opal.pokemon.game.GameController;
import main.java.opal.pokemon.game.GameState;
import main.java.opal.pokemon.game.screens.overworld.characters.EntityModel;

public class NPC_Male2 extends NPC {

    public NPC_Male2(GameController gp) {
        super(gp);

        model.direction = EntityModel.Direction.DOWN;
        model.speed = 3;

        view.loadSprites("/images/characters/npc/male_2/");
        setDialogue();
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
