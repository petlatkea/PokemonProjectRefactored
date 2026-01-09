package main.java.opal.pokemon.main;

import main.java.opal.pokemon.entity.*;
import main.java.opal.pokemon.main.controller.GameController;
import main.java.opal.pokemon.object.OBJ_Pokeball;
import main.java.opal.pokemon.object.OBJ_Rock;
import main.java.opal.pokemon.object.SuperObject;

public class AssetSetter {
    GameController gp;
    public AssetSetter(GameController gp) {
        this.gp = gp;
    }

    public void setObject(SuperObject[] obj) {
        obj[0] = new OBJ_Pokeball(gp);
        obj[0].worldX = 18 * gp.tileSize;
        obj[0].worldY = 35 * gp.tileSize;
        obj[0].collision = true;

        obj[1] = new OBJ_Pokeball(gp);
        obj[1].worldX = 41 * gp.tileSize;
        obj[1].worldY = 5 * gp.tileSize;
        obj[1].collision = true;

        obj[2] = new OBJ_Pokeball(gp);
        obj[2].worldX = 91 * gp.tileSize;
        obj[2].worldY = 31 * gp.tileSize;
        obj[0].collision = true;

        obj[3] = new OBJ_Pokeball(gp);
        obj[3].worldX = 31 * gp.tileSize;
        obj[3].worldY = 62 * gp.tileSize;
        obj[3].collision = true;

        obj[4] = new OBJ_Pokeball(gp);
        obj[4].worldX = 7 * gp.tileSize;
        obj[4].worldY = 71 * gp.tileSize;
        obj[4].collision = true;


        obj[5] = new OBJ_Rock(gp);
        obj[5].worldX = 86 * gp.tileSize;
        obj[5].worldY = 89 * gp.tileSize;
        obj[5].collision = true;
    }


    public void setNPC(NPC[] npc) {
        npc[0] = new NPC_Prof(gp);
        npc[0].worldX = 12 * gp.tileSize;
        npc[0].worldY = 40 * gp.tileSize;
        npc[0].direction = "up";

        npc[1] = new NPC_Female1(gp);
        npc[1].worldX = 23 * gp.tileSize;
        npc[1].worldY = 39 * gp.tileSize;

        npc[2] = new NPC_Female2(gp);
        npc[2].worldX = 9 * gp.tileSize;
        npc[2].worldY = 47 * gp.tileSize;

        npc[3] = new NPC_Female3(gp);
        npc[3].worldX = 52 * gp.tileSize;
        npc[3].worldY = 8 * gp.tileSize;

        npc[4] = new NPC_Male6(gp);
        npc[4].worldX = 5 * gp.tileSize;
        npc[4].worldY = 12 * gp.tileSize;
        npc[4].direction = "left";

        npc[5] = new NPC_Male4(gp);
        npc[5].worldX = 19 * gp.tileSize;
        npc[5].worldY = 12 * gp.tileSize;
        npc[5].direction = "up";

        npc[6] = new NPC_Female5(gp);
        npc[6].worldX = 71 * gp.tileSize;
        npc[6].worldY = 9 * gp.tileSize;

        npc[7] = new NPC_Male3(gp);
        npc[7].worldX = 90 * gp.tileSize;
        npc[7].worldY = 7 * gp.tileSize;
        npc[7].direction = "right";

        npc[8] = new NPC_Male5(gp);
        npc[8].worldX = 87 * gp.tileSize;
        npc[8].worldY = 43 * gp.tileSize;

        npc[9] = new NPC_Female4(gp);
        npc[9].worldX = 51 * gp.tileSize;
        npc[9].worldY = 63 * gp.tileSize;
        npc[9].direction = "up";

        npc[10] = new NPC_Female6(gp);
        npc[10].worldX = 26 * gp.tileSize;
        npc[10].worldY = 76 * gp.tileSize;

        npc[11] = new NPC_Female6(gp);
        npc[11].worldX = 25 * gp.tileSize;
        npc[11].worldY = 76 * gp.tileSize;

        // FUTURE EXPANSION: The Gymleader would like to ask a question and get an answer
        // - this is for the DialogueController to handle!
        npc[12] = new NPC_GymLeader(gp);
        npc[12].worldX = 11 * gp.tileSize;
        npc[12].worldY = 77 * gp.tileSize;

        npc[13] = new NPC_NurseJoy(gp);
        npc[13].worldX = 11 * gp.tileSize;
        npc[13].worldY = 86 * gp.tileSize;


        npc[14] = new NPC_Male1(gp);
        npc[14].worldX = 60 * gp.tileSize;
        npc[14].worldY = 90 * gp.tileSize;
        npc[14].direction = "up";


        npc[15] = new NPC_Machop(gp);
        npc[15].worldX = 59 * gp.tileSize;
        npc[15].worldY = 89 * gp.tileSize;
        npc[15].direction = "right";

        npc[16] = new NPC_Machop(gp);
        npc[16].worldX = 70 * gp.tileSize;
        npc[16].worldY = 84 * gp.tileSize;
        npc[16].direction = "left";

        npc[17] = new NPC_Machop(gp);
        npc[17].worldX = 85 * gp.tileSize;
        npc[17].worldY = 93 * gp.tileSize;

        npc[18] = new NPC_Male2(gp);
        npc[18].worldX = 84 * gp.tileSize;
        npc[18].worldY = 88 * gp.tileSize;
    }
}
