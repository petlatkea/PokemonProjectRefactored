package main.java.opal.pokemon.game.screens.overworld.characters;

import main.java.opal.pokemon.game.GameController;
import main.java.opal.pokemon.game.ViewSettings;
import main.java.opal.pokemon.game.screens.overworld.Camera;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class EntityView {

    // animation images
    private BufferedImage[] up;
    private BufferedImage[] left;
    private BufferedImage[] down;
    private BufferedImage[] right;

    // animation control
    protected boolean animating = false;
    protected boolean stoppingAnimation = false;
    // TODO: Rename these properties to something with frames and animation
    // animation status
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

    // TODO: At some point combine with "GraphicsObject" and use the same load method for all graphics
    //       also see if we can get rid of the image scaling here - shouldn't happen before drawing,
    //       but that might be long into the future!
    public BufferedImage loadImage(String imagePath) {
        BufferedImage image = null;

        try {
            InputStream input = this.getClass().getResourceAsStream(imagePath + ".png");
            if (input != null) {
                image = ImageIO.read(input);
            }
        } catch (Exception e) {
            System.err.println("EntityView cannot load image: " + imagePath + ".png");
            System.err.println(e);
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

    public void draw(Graphics2D g2) {
        if (model.moving) {
            startAnimation();
        } else {
            stopAnimation();
        }

        animate();

        Camera camera = gp.getCamera();

        if (camera.isInView(model)) {
            BufferedImage image = null;

            switch (model.direction) {
                case UP -> image = up[spriteNum - 1];
                case LEFT -> image = left[spriteNum - 1];
                case DOWN -> image = down[spriteNum - 1];
                case RIGHT -> image = right[spriteNum - 1];
            }

            g2.drawImage(image, model.worldX-ViewSettings.tileSize/2 - camera.left, model.worldY-ViewSettings.tileSize/2-16 - camera.top, entitySize, entitySize, null);
        }
    }

    public void loadSprites(String filepath) {
        BufferedImage up1 = loadImage(filepath + "up_1");
        BufferedImage up2 = loadImage(filepath + "up_2");
        BufferedImage up3 = loadImage(filepath + "up_3");
        BufferedImage left1 = loadImage(filepath + "left_1");
        BufferedImage left2 = loadImage(filepath + "left_2");
        BufferedImage left3 = loadImage(filepath + "left_3");
        BufferedImage down1 = loadImage(filepath + "down_1");
        BufferedImage down2 = loadImage(filepath + "down_2");
        BufferedImage down3 = loadImage(filepath + "down_3");
        BufferedImage right1 = loadImage(filepath + "right_1");
        BufferedImage right2 = loadImage(filepath + "right_2");
        BufferedImage right3 = loadImage(filepath + "right_3");

        up = new BufferedImage[]{up1, up2, up3};
        left = new BufferedImage[]{left1, left2, left3};
        down = new BufferedImage[]{down1, down2, down3};
        right = new BufferedImage[]{right1, right2, right3};

    }
}