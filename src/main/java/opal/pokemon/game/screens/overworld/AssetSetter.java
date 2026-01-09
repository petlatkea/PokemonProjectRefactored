package main.java.opal.pokemon.game.screens.overworld;

import main.java.opal.pokemon.game.GameController;
import main.java.opal.pokemon.game.ViewSettings;
import main.java.opal.pokemon.game.screens.overworld.characters.npc.*;
import main.java.opal.pokemon.game.screens.overworld.objects.OBJ_Pokeball;
import main.java.opal.pokemon.game.screens.overworld.objects.OBJ_Rock;
import main.java.opal.pokemon.game.screens.overworld.objects.SuperObject;

public class AssetSetter {
    private GameController gp;

    public AssetSetter(GameController gp) {
        this.gp = gp;
    }

    public void setObject(SuperObject[] obj) {
        obj[0] = new OBJ_Pokeball(gp);
        obj[0].worldX = 18 * ViewSettings.tileSize;
        obj[0].worldY = 35 * ViewSettings.tileSize;
        obj[0].collision = true;

        obj[1] = new OBJ_Pokeball(gp);
        obj[1].worldX = 41 * ViewSettings.tileSize;
        obj[1].worldY = 5 * ViewSettings.tileSize;
        obj[1].collision = true;

        obj[2] = new OBJ_Pokeball(gp);
        obj[2].worldX = 91 * ViewSettings.tileSize;
        obj[2].worldY = 31 * ViewSettings.tileSize;
        obj[0].collision = true;

        obj[3] = new OBJ_Pokeball(gp);
        obj[3].worldX = 31 * ViewSettings.tileSize;
        obj[3].worldY = 62 * ViewSettings.tileSize;
        obj[3].collision = true;

        obj[4] = new OBJ_Pokeball(gp);
        obj[4].worldX = 7 * ViewSettings.tileSize;
        obj[4].worldY = 71 * ViewSettings.tileSize;
        obj[4].collision = true;


        obj[5] = new OBJ_Rock(gp);
        obj[5].worldX = 86 * ViewSettings.tileSize;
        obj[5].worldY = 89 * ViewSettings.tileSize;
        obj[5].collision = true;
    }


    public void setNPC(NPC[] npc) {
        npc[0] = new NPC_Prof(gp);
        npc[0].model.worldX = 12 * ViewSettings.tileSize;
        npc[0].model.worldY = 40 * ViewSettings.tileSize;
        npc[0].model.direction = "up";

        npc[1] = new NPC_Female1(gp);
        npc[1].model.worldX = 23 * ViewSettings.tileSize;
        npc[1].model.worldY = 39 * ViewSettings.tileSize;

        npc[2] = new NPC_Female2(gp);
        npc[2].model.worldX = 9 * ViewSettings.tileSize;
        npc[2].model.worldY = 47 * ViewSettings.tileSize;

        npc[3] = new NPC_Female3(gp);
        npc[3].model.worldX = 52 * ViewSettings.tileSize;
        npc[3].model.worldY = 8 * ViewSettings.tileSize;

        npc[4] = new NPC_Male6(gp);
        npc[4].model.worldX = 5 * ViewSettings.tileSize;
        npc[4].model.worldY = 12 * ViewSettings.tileSize;
        npc[4].model.direction = "left";

        npc[5] = new NPC_Male4(gp);
        npc[5].model.worldX = 19 * ViewSettings.tileSize;
        npc[5].model.worldY = 12 * ViewSettings.tileSize;
        npc[5].model.direction = "up";

        npc[6] = new NPC_Female5(gp);
        npc[6].model.worldX = 71 * ViewSettings.tileSize;
        npc[6].model.worldY = 9 * ViewSettings.tileSize;

        npc[7] = new NPC_Male3(gp);
        npc[7].model.worldX = 90 * ViewSettings.tileSize;
        npc[7].model.worldY = 7 * ViewSettings.tileSize;
        npc[7].model.direction = "right";

        npc[8] = new NPC_Male5(gp);
        npc[8].model.worldX = 87 * ViewSettings.tileSize;
        npc[8].model.worldY = 43 * ViewSettings.tileSize;

        npc[9] = new NPC_Female4(gp);
        npc[9].model.worldX = 51 * ViewSettings.tileSize;
        npc[9].model.worldY = 63 * ViewSettings.tileSize;
        npc[9].model.direction = "up";

        npc[10] = new NPC_Female6(gp);
        npc[10].model.worldX = 26 * ViewSettings.tileSize;
        npc[10].model.worldY = 76 * ViewSettings.tileSize;

        npc[11] = new NPC_Female6(gp);
        npc[11].model.worldX = 25 * ViewSettings.tileSize;
        npc[11].model.worldY = 76 * ViewSettings.tileSize;

        // FUTURE EXPANSION: The Gymleader would like to ask a question and get an answer
        // - this is for the DialogueController to handle!
        npc[12] = new NPC_GymLeader(gp);
        npc[12].model.worldX = 11 * ViewSettings.tileSize;
        npc[12].model.worldY = 77 * ViewSettings.tileSize;

        npc[13] = new NPC_NurseJoy(gp);
        npc[13].model.worldX = 11 * ViewSettings.tileSize;
        npc[13].model.worldY = 86 * ViewSettings.tileSize;


        npc[14] = new NPC_Male1(gp);
        npc[14].model.worldX = 60 * ViewSettings.tileSize;
        npc[14].model.worldY = 90 * ViewSettings.tileSize;
        npc[14].model.direction = "up";


        npc[15] = new NPC_Machop(gp);
        npc[15].model.worldX = 59 * ViewSettings.tileSize;
        npc[15].model.worldY = 89 * ViewSettings.tileSize;
        npc[15].model.direction = "right";

        npc[16] = new NPC_Machop(gp);
        npc[16].model.worldX = 70 * ViewSettings.tileSize;
        npc[16].model.worldY = 84 * ViewSettings.tileSize;
        npc[16].model.direction = "left";

        npc[17] = new NPC_Machop(gp);
        npc[17].model.worldX = 85 * ViewSettings.tileSize;
        npc[17].model.worldY = 93 * ViewSettings.tileSize;

        npc[18] = new NPC_Male2(gp);
        npc[18].model.worldX = 84 * ViewSettings.tileSize;
        npc[18].model.worldY = 88 * ViewSettings.tileSize;
    }
}
