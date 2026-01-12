package main.java.opal.pokemon.game.screens.overworld.characters.npc;

import main.java.opal.pokemon.game.GameController;
import main.java.opal.pokemon.game.GameState;
import main.java.opal.pokemon.game.screens.overworld.characters.EntityModel;

public class NPC_Male6 extends NPC {

    public NPC_Male6(GameController gp) {
        super(gp);

        model.direction = EntityModel.Direction.DOWN;
        model.speed = 3;

        view.loadSprites("/images/characters/npc/male_6/");
        setDialogue();
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
