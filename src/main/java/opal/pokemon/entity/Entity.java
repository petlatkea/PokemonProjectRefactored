package main.java.opal.pokemon.entity;

import main.java.opal.pokemon.main.UtilityTool;
import main.java.opal.pokemon.main.controller.GameController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    GameController gp;
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, up3, left1, left2, left3, down1, down2, down3, right1, right2, right3;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    final int originalEntitySize = 32;  // 32x32 px
    public final int entitySize = originalEntitySize * 4; // 128x128 px

    public Rectangle solidArea = new Rectangle(0, 0, 64, 64);
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;
    public boolean collisionOn = false;
    public boolean isGrassOn = false;

    public Entity(GameController gp) {
        this.gp = gp;
    }

    // NOTE: Only implemented by the player - maybe not necessary?
    public void update() {
    }


    public void draw(Graphics2D g2) {
        int cameraLeft = gp.getPlayer().worldX - gp.getPlayer().screenX;
        int cameraTop = gp.getPlayer().worldY - gp.getPlayer().screenY;
        int cameraRight = cameraLeft + gp.screenWidth;
        int cameraBottom = cameraTop + gp.screenHeight;

        if (worldX + gp.tileSize >= cameraLeft &&
                worldX <= cameraRight &&
                worldY + gp.tileSize >= cameraTop &&
                worldY <= cameraBottom) {

            int screenX = worldX - cameraLeft - 32;  // Sprite offset fixes
            int screenY = worldY - cameraTop - 56; // =o=

            BufferedImage image = null;
            BufferedImage[] up = {up1, up2, up3};
            BufferedImage[] left = {left1, left2, left3};
            BufferedImage[] down = {down1, down2, down3};
            BufferedImage[] right = {right1, right2, right3};

            switch (direction) {
                case "up" -> image = up[spriteNum - 1];
                case "left" -> image = left[spriteNum - 1];
                case "down" -> image = down[spriteNum - 1];
                case "right" -> image = right[spriteNum - 1];
            }

            g2.drawImage(image, screenX, screenY, entitySize, entitySize, null);
        }
    }

    public BufferedImage setup(String imagePath) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, entitySize, entitySize);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return image;
    }
}
