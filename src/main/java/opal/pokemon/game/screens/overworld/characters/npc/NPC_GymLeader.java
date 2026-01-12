package main.java.opal.pokemon.game.screens.overworld.characters.npc;

import main.java.opal.pokemon.game.GameController;
import main.java.opal.pokemon.game.screens.overworld.characters.EntityModel;


public class NPC_GymLeader extends NPC {

    public NPC_GymLeader(GameController gp) {
        super(gp);

        model.direction = EntityModel.Direction.DOWN;
        model.speed = 3;

        view.loadSprites("/images/characters/npc/gym_leader/");
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0] = "Have you come to challenge me?";
        dialogues[1] = "That's quite brave of you kid.";
        dialogues[2] = "Are you ready for a battle against \nthe strongest guy in this town?!";
        dialogues[3] = "";
    }

    public void speak() {
        if (dialogues[dialogueIndex] == dialogues[4]) {
            // TODO: Answer Yes or No??
            gp.startGymBattle();
        }
        super.speak();

    }
}
