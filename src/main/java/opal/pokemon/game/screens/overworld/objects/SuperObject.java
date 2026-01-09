package main.java.opal.pokemon.game.screens.overworld.objects;

import main.java.opal.pokemon.game.screens.overworld.characters.player.PlayerView;
import main.java.opal.pokemon.main.UtilityTool;
import main.java.opal.pokemon.game.GameController;

import java.awt.*;
import java.awt.image.BufferedImage;

// TODO: This seems very similar to Entity - except that one is a Character - maybe join them!
public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = true;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 64, 64);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    UtilityTool uTool = new UtilityTool();

    public void draw(Graphics2D g2, GameController gp) {
        int cameraLeft = gp.getPlayer().model.worldX - ((PlayerView) gp.getPlayer().view).screenX;
        int cameraTop = gp.getPlayer().model.worldY - ((PlayerView) gp.getPlayer().view).screenY;
        int cameraRight = cameraLeft + gp.screenWidth;
        int cameraBottom = cameraTop + gp.screenHeight;

        if (worldX + gp.tileSize >= cameraLeft &&
                worldX <= cameraRight &&
                worldY + gp.tileSize >= cameraTop &&
                worldY <= cameraBottom) {

            int screenX = worldX - cameraLeft;
            int screenY = worldY - cameraTop;

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
}
