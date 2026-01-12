package main.java.opal.pokemon.game.screens.overworld.characters;

import main.java.opal.pokemon.game.GameController;
import main.java.opal.pokemon.game.ViewSettings;
import main.java.opal.pokemon.game.screens.overworld.characters.player.PlayerView;
import main.java.opal.pokemon.main.UtilityTool;

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

    // animation
    protected boolean animating = false;
    protected boolean stoppingAnimation = false;
    // TODO: Rename these properties to something with frames and animation
    protected int spriteCounter = 0;
    protected int spriteNum = 1;
    protected int orderIndex = 0;
    protected int[] spriteOrder = {1, 2, 1, 3};

    final int originalEntitySize = 32;  // 32x32 px
    public final int entitySize = originalEntitySize * 4; // 128x128 px

    protected EntityModel model;
    protected GameController gp;

    public EntityView(GameController gp, EntityModel entityModel) {
        // TODO: Get rid of the GameController here - it is only used for generic view-information
        this.gp = gp;
        this.model = entityModel;
    }

    public BufferedImage setup(String imagePath) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image;

        try {
            image = ImageIO.read(this.getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, entitySize, entitySize);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return image;
    }

    public void animate() {
        if (animating) {

            // if the animation is stopping, and we are at spriteNum 1 : stop completely
            if (stoppingAnimation && this.spriteNum == 1) {
                animating = false;
                stoppingAnimation = false;
            } else {
                // otherwise continue animating

                // The spriteCounter counts frames
                this.spriteCounter--;

                // At 10 frames, the next sprite is selected
                if (this.spriteCounter <= 0) {
                    this.orderIndex = (orderIndex + 1) % spriteOrder.length;
                    this.spriteNum = spriteOrder[orderIndex];

                    // reset the framecounter to the framerate (currently hardcoded to 10)
                    this.spriteCounter = 10;
                }
            }
        }
    }

    /**
     * when animating, the animation should make sure to stop at a sprite 1
     * so don't stop immediately, just note that we are stopping!
     */
    public void stopAnimation() {
        if (animating && !stoppingAnimation) {
            stoppingAnimation = true;
        }
    }

    public void startAnimation() {
        animating = true;
        stoppingAnimation = false;
    }

    // TODO: Move animation and sprite selection from player into here
    public void draw(Graphics2D g2) {
        int cameraLeft = gp.getPlayer().model.worldX - ((PlayerView) gp.getPlayer().view).screenX;
        int cameraTop = gp.getPlayer().model.worldY - ((PlayerView) gp.getPlayer().view).screenY;
        int cameraRight = cameraLeft + ViewSettings.screenWidth;
        int cameraBottom = cameraTop + ViewSettings.screenHeight;

        if (model.worldX + ViewSettings.tileSize >= cameraLeft &&
                model.worldX <= cameraRight &&
                model.worldY + ViewSettings.tileSize >= cameraTop &&
                model.worldY <= cameraBottom) {

            int screenX = model.worldX - cameraLeft - 32;  // Sprite offset fixes
            int screenY = model.worldY - cameraTop - 56; // =o=

            BufferedImage image = null;
            BufferedImage[] up = {up1, up2, up3};
            BufferedImage[] left = {left1, left2, left3};
            BufferedImage[] down = {down1, down2, down3};
            BufferedImage[] right = {right1, right2, right3};

            switch (model.direction) {
                case UP -> image = up[spriteNum - 1];
                case LEFT -> image = left[spriteNum - 1];
                case DOWN -> image = down[spriteNum - 1];
                case RIGHT -> image = right[spriteNum - 1];
            }

            g2.drawImage(image, screenX, screenY, entitySize, entitySize, null);
        }
    }
}