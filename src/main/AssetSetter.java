package main;

import entity.NPC_Female1;
import object.OBJ_Pokeball;
import object.OBJ_Rock;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new OBJ_Pokeball(gp);
        gp.obj[0].worldX = 18 * gp.tileSize;
        gp.obj[0].worldY = 35 * gp.tileSize;
        gp.obj[0].collision = true;

        gp.obj[1] = new OBJ_Pokeball(gp);
        gp.obj[1].worldX = 41 * gp.tileSize;
        gp.obj[1].worldY = 5 * gp.tileSize;
        gp.obj[1].collision = true;

        gp.obj[2] = new OBJ_Pokeball(gp);
        gp.obj[2].worldX = 91 * gp.tileSize;
        gp.obj[2].worldY = 31 * gp.tileSize;
        gp.obj[0].collision = true;

        gp.obj[3] = new OBJ_Pokeball(gp);
        gp.obj[3].worldX = 30 * gp.tileSize;
        gp.obj[3].worldY = 63 * gp.tileSize;
        gp.obj[3].collision = true;
    }

    public void setNPC() {
        gp.npc[0] = new NPC_Female1(gp);
        gp.npc[0].worldX = 23 * gp.tileSize;
        gp.npc[0].worldY = 39 * gp.tileSize;

        gp.npc[1] = new NPC_Female1(gp);
        gp.npc[1].worldX = 41 * gp.tileSize;
        gp.npc[1].worldY = 11 * gp.tileSize;

    }
}
