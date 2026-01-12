package main.java.opal.pokemon.game.screens.overworld.characters.npc;

import main.java.opal.pokemon.game.GameController;
import main.java.opal.pokemon.game.GameState;
import main.java.opal.pokemon.game.screens.overworld.characters.EntityModel;

public class NPC_Female1 extends NPC {

    public NPC_Female1(GameController gp) {
        super(gp);

        model.direction = EntityModel.Direction.DOWN;
        model.speed = 3;

        view.loadSprites("/images/characters/npc/female_1/");
        setDialogue();
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
