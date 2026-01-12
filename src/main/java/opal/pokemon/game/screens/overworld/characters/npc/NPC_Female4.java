package main.java.opal.pokemon.game.screens.overworld.characters.npc;

import main.java.opal.pokemon.game.GameController;
import main.java.opal.pokemon.game.GameState;
import main.java.opal.pokemon.game.screens.overworld.characters.EntityModel;

public class NPC_Female4 extends NPC {

    public NPC_Female4(GameController gp) {
        super(gp);

        model.direction = EntityModel.Direction.DOWN;
        model.speed = 3;

        view.loadSprites("/images/characters/npc/female_4/");
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0] = "Hi there, im miss Stone. \nI'm what you would call a Geologist";
        dialogues[1] = "I study rocks, like this Geodude here.";
        dialogues[2] = "What? you're telling me this \nisn't a Geodude but just a normal rock?";
        dialogues[3] = "You think i don't know the difference \nbetween a rock and a Geodude?";
        dialogues[4] = "There's a reason i'm a geologist \nand you aren't.";
        dialogues[5] = "It's clearly just, uh.. \nsleeping. Yeah, sleeping..";
    }

    public void speak() {
        if (dialogues[dialogueIndex] == dialogues[6]) {
            gp.gameState = GameState.playState;
        }
        super.speak();
    }
}
