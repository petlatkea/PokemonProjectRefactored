package main.java.opal.pokemon.entity;

import main.java.opal.pokemon.main.controller.GameController;
import main.java.opal.pokemon.main.controller.GameState;

import java.util.Objects;

public class NPC_Machop extends Entity {
    public NPC_Machop (GameController gp) {
        super(gp);

        direction = "down";
        speed = 3;

        getNPCImage();
        setDialogue();
    }

    public void getNPCImage() {
        up1 = setup("/images/characters/npc/machop/up_1");
        down1 = setup("/images/characters/npc/machop/down_1");
        left1 = setup("/images/characters/npc/machop/left_1");
        right1 = setup("/images/characters/npc/machop/right_1");

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
        gp.music.machopSound();
    }
}
