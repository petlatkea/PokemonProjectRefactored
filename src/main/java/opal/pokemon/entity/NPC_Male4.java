package main.java.opal.pokemon.entity;

import main.java.opal.pokemon.main.controller.GameController;
import main.java.opal.pokemon.main.controller.GameState;

public class NPC_Male4 extends NPC {

    public NPC_Male4(GameController gp) {
        super(gp);

        model.direction = "down";
        model.speed = 3;

        getNPCImage();
        setDialogue();
    }

    public void getNPCImage() {
        view.up1 = view.setup("/images/characters/npc/male_4/up_1", this);
        view.up2 = view.setup("/images/characters/npc/male_4/up_2", this);
        view.up3 = view.setup("/images/characters/npc/male_4/up_3", this);
        view.left1 = view.setup("/images/characters/npc/male_4/left_1", this);
        view.left2 = view.setup("/images/characters/npc/male_4/left_2", this);
        view.left3 = view.setup("/images/characters/npc/male_4/left_3", this);
        view.down1 = view.setup("/images/characters/npc/male_4/down_1", this);
        view.down2 = view.setup("/images/characters/npc/male_4/down_2", this);
        view.down3 = view.setup("/images/characters/npc/male_4/down_3", this);
        view.right1 = view.setup("/images/characters/npc/male_4/right_1", this);
        view.right2 = view.setup("/images/characters/npc/male_4/right_2", this);
        view.right3 = view.setup("/images/characters/npc/male_4/right_3", this);
    }

    public void setDialogue() {
        dialogues[0] = "Hi there kid! wanna hear some facts \nabout Psyducks?";
        dialogues[1] = "Of course you do!!!";
        dialogues[2] = "I mean who doesnt?";
        dialogues[3] = "Psyducks are like the coolest Pokemon \nin the whole world!";
        dialogues[4] = "Anyways..";
        dialogues[5] = "Fact number 1: \nPsyducks are pure water type!";
        dialogues[6] = "You would think with all the Psychic moves \nit can learn that it would be Psychic too";
        dialogues[7] = "But no! its only water type, which also \nmakes it weak to Electric and Grass.";
        dialogues[8] = "Fact number 2: \nPsyducks National Pokedex Nr. is #054";
        dialogues[9] = "You can look it up yourself if you \nhave a Pokedex on you.";
        dialogues[10] = "Fact number 3: \nA shiny Psyduck is Cyan!";
        dialogues[11] = "I hope to one day see a shiny one myself \nbut for now i can only imagine.";
        dialogues[12] = "Fact number 4: \nWhen a Psyduck reaches lvl 33 it evolves!";
        dialogues[13] = "Though Psyducks are obviously cooler \nI must admit Golducks are cool too.";
        dialogues[14] = "Fact number 5: \nPsyducks hidden ability is Swift Swim";
        dialogues[15] = "This ability increases Psyducks speed \nwhen it rains!";
        dialogues[16] = "Pair this with their incredible base speed of 55 you get a speedy \na duck that outperforms even legendary pokemon \nlike Palkia, Dialga, Giratina and even Arceus!";
        dialogues[17] = "HEY!\nARE YOU EVEN LISTENING?";
        dialogues[18] = "Ugh! Kids these days, here i am trying \nto teach them something interesting";
        dialogues[19] = "But they are always glued to that stupid \nPokedex, as if it knows everything.";
    }

    public void speak() {
        if (dialogues[dialogueIndex] == dialogues[20]) {
            gp.gameState = GameState.playState;
        }
        super.speak();
    }
}
