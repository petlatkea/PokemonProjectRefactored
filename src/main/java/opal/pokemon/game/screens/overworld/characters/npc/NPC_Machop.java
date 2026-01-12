package main.java.opal.pokemon.game.screens.overworld.characters.npc;

import main.java.opal.pokemon.game.GameController;
import main.java.opal.pokemon.game.GameState;
import main.java.opal.pokemon.game.screens.overworld.characters.EntityModel;

import java.util.Objects;

public class NPC_Machop extends NPC {
    public NPC_Machop (GameController gp) {
        super(gp);

        model.direction = EntityModel.Direction.DOWN;
        model.speed = 3;

        view.loadSprites("/images/characters/npc/machop/");
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0] = "MACHOP: Gwooh! Gogogooh!";
        dialogues[1] = "MACHOP: Gwagooh! Gwogaah!";
    }

    public void speak() {
        if (Objects.equals(dialogues[dialogueIndex], dialogues[2])) {
            gp.gameState = GameState.playState;
        }
        super.speak();
        gp.soundController.machopSound();
    }
}
