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
    }

    public void setNPC() {
        gp.npc[0] = new NPC_Female1(gp);
        gp.npc[0].worldX = 23 * gp.tileSize;
        gp.npc[0].worldY = 39 * gp.tileSize;
    }
}
