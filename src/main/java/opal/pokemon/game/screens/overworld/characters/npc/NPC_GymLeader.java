package main.java.opal.pokemon.game.screens.overworld.characters.npc;

import main.java.opal.pokemon.game.GameController;


public class NPC_GymLeader extends NPC {

    public NPC_GymLeader(GameController gp) {
        super(gp);

        model.direction = "down";
        model.speed = 3;

        getNPCImage();
        setDialogue();
    }

    public void getNPCImage() {
        view.up1 = view.setup("/images/characters/npc/gym_leader/up_1");
        view.up2 = view.setup("/images/characters/npc/gym_leader/up_2");
        view.up3 = view.setup("/images/characters/npc/gym_leader/up_3");
        view.left1 = view.setup("/images/characters/npc/gym_leader/left_1");
        view.left2 = view.setup("/images/characters/npc/gym_leader/left_2");
        view.left3 = view.setup("/images/characters/npc/gym_leader/left_3");
        view.down1 = view.setup("/images/characters/npc/gym_leader/down_1");
        view.down2 = view.setup("/images/characters/npc/gym_leader/down_2");
        view.down3 = view.setup("/images/characters/npc/gym_leader/down_3");
        view.right1 = view.setup("/images/characters/npc/gym_leader/right_1");
        view.right2 = view.setup("/images/characters/npc/gym_leader/right_2");
        view.right3 = view.setup("/images/characters/npc/gym_leader/right_3");
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
