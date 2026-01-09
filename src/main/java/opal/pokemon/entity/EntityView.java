package main.java.opal.pokemon.entity;

import main.java.opal.pokemon.main.UtilityTool;
import main.java.opal.pokemon.main.controller.GameController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class EntityView {
    public BufferedImage up1;
    public BufferedImage up2;
    public BufferedImage up3;
    public BufferedImage left1;
    public BufferedImage left2;
    public BufferedImage left3;
    public BufferedImage down1;
    public BufferedImage down2;
    public BufferedImage down3;
    public BufferedImage right1;
    public BufferedImage right2;
    public BufferedImage right3;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    final int originalEntitySize = 32;  // 32x32 px
    public final int entitySize = originalEntitySize * 4; // 128x128 px

    protected EntityModel model;
    protected GameController gp;

    public EntityView(GameController gp, EntityModel entityModel) {
        // TODO: Get rid of the GameController here - it is only used for generic view-information
        this.gp = gp;
        this.model = entityModel;
    }

    public BufferedImage setup(String imagePath, Entity entity) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image;

        try {
            image = ImageIO.read(entity.getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, entitySize, entitySize);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return image;
    }

    public void draw(Graphics2D g2) {
        int cameraLeft = gp.getPlayer().model.worldX - ((PlayerView)gp.getPlayer().view).screenX;
        int cameraTop = gp.getPlayer().model.worldY - ((PlayerView)gp.getPlayer().view).screenY;
        int cameraRight = cameraLeft + gp.screenWidth;
        int cameraBottom = cameraTop + gp.screenHeight;

        if (model.worldX + gp.tileSize >= cameraLeft &&
                model.worldX <= cameraRight &&
                model.worldY + gp.tileSize >= cameraTop &&
                model.worldY <= cameraBottom) {

            int screenX = model.worldX - cameraLeft - 32;  // Sprite offset fixes
            int screenY = model.worldY - cameraTop - 56; // =o=

            BufferedImage image = null;
            BufferedImage[] up = {up1, up2, up3};
            BufferedImage[] left = {left1, left2, left3};
            BufferedImage[] down = {down1, down2, down3};
            BufferedImage[] right = {right1, right2, right3};

            switch (model.direction) {
                case "up" -> image = up[spriteNum - 1];
                case "left" -> image = left[spriteNum - 1];
                case "down" -> image = down[spriteNum - 1];
                case "right" -> image = right[spriteNum - 1];
            }

            g2.drawImage(image, screenX, screenY, entitySize, entitySize, null);
        }
    }
}