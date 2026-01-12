package main.java.opal.pokemon.game.screens.overworld.characters.npc;

import main.java.opal.pokemon.game.GameController;
import main.java.opal.pokemon.game.GameState;
import main.java.opal.pokemon.game.screens.overworld.characters.EntityModel;

public class NPC_Female6 extends NPC {

    public NPC_Female6(GameController gp) {
        super(gp);

        model.direction = EntityModel.Direction.DOWN;
        model.speed = 3;

        view.loadSprites("/images/characters/npc/female_6/");
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0] = "Hello and welcome to Celestic Town!!!";
        dialogues[1] = "My twin sister and I like to welcome \nevery new person that visits!";
        dialogues[2] = "She's known for being a copycat though. \nSo she'll probably just repeat what i said.";
    }

    public void speak() {
        if (dialogues[dialogueIndex] == dialogues[3]) {
            gp.gameState = GameState.playState;
        }
        super.speak();
    }
}
