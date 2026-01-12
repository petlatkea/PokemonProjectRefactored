package main.java.opal.pokemon.game.screens.overworld.characters.npc;

import main.java.opal.pokemon.game.GameController;
import main.java.opal.pokemon.game.GameState;
import main.java.opal.pokemon.game.screens.overworld.characters.EntityModel;

public class NPC_Female2 extends NPC {

    public NPC_Female2(GameController gp) {
        super(gp);

        model.direction = EntityModel.Direction.DOWN;
        model.speed = 3;

        view.loadSprites("/images/characters/npc/female_2/");
        setDialogue();
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
