package main.java.opal.pokemon.game.screens.overworld.characters.npc;

import main.java.opal.pokemon.game.GameController;
import main.java.opal.pokemon.game.GameState;
import main.java.opal.pokemon.game.screens.overworld.characters.EntityModel;

public class NPC_Male3 extends NPC {

    public NPC_Male3(GameController gp) {
        super(gp);

        model.direction = EntityModel.Direction.DOWN;
        model.speed = 3;

        view.loadSprites("/images/characters/npc/male_3/");
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0] = "SSSSSSHHHH! \nThere's a shiny Wurmple right there!";
        dialogues[1] = "i'm gonna catch it and..";
        dialogues[2] = "OH GREAT! \nYOU MADE IT RUN AWAY!";
        dialogues[3] = "Do you even know how RARE a shiny \npokemon is?!!!";
        dialogues[4] = "They say its a 1 in 8.192 chance..";
        dialogues[5] = "Arceus damn it man, i might never \nsee a shiny ever again!";
        dialogues[6] = "Leave before i make my Bidoof defeat \nall your pokemon.";
    }

    public void speak() {
        if (dialogues[dialogueIndex] == dialogues[7]) {
            gp.gameState = GameState.playState;
        }
        super.speak();
    }
}
