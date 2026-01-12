package main.java.opal.pokemon.game.screens.overworld.characters.npc;

import main.java.opal.pokemon.game.GameController;
import main.java.opal.pokemon.game.GameState;
import main.java.opal.pokemon.game.screens.overworld.characters.EntityModel;

public class NPC_Male1 extends NPC {

    public NPC_Male1(GameController gp) {
        super(gp);

        model.direction = EntityModel.Direction.DOWN;
        model.speed = 3;

        view.loadSprites("/images/characters/npc/male_1/");
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0] = "Look at my Machop go!";
        dialogues[1] = "They're really useful for moving rocks, \nsince they're stronger than us humans";
        dialogues[2] = "Sometimes i wish i was a Machop..\nThen i'd be super strong!";
    }

    public void speak() {
        if (dialogues[dialogueIndex] == dialogues[3]) {
            gp.gameState = GameState.playState;
        }
        super.speak();
    }
}
