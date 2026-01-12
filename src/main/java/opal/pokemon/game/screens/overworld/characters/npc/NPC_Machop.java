package main.java.opal.pokemon.game.screens.overworld.characters.npc;

import main.java.opal.pokemon.game.GameController;
import main.java.opal.pokemon.game.GameState;

import java.util.Objects;

public class NPC_Machop extends NPC {
    public NPC_Machop (GameController gp) {
        super(gp);

        model.direction = "down";
        model.speed = 3;

        getNPCImage();
        setDialogue();
    }

    public void getNPCImage() {
        view.up1 = view.setup("/images/characters/npc/machop/up_1");
        view.down1 = view.setup("/images/characters/npc/machop/down_1");
        view.left1 = view.setup("/images/characters/npc/machop/left_1");
        view.right1 = view.setup("/images/characters/npc/machop/right_1");

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
