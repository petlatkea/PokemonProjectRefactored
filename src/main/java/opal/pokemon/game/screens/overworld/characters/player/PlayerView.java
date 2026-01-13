package main.java.opal.pokemon.game.screens.overworld.characters.player;

import main.java.opal.pokemon.game.GameController;
import main.java.opal.pokemon.game.ViewSettings;
import main.java.opal.pokemon.game.screens.overworld.Camera;
import main.java.opal.pokemon.game.screens.overworld.characters.EntityModel;
import main.java.opal.pokemon.game.screens.overworld.characters.EntityView;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerView extends EntityView {
    public int pixelCounter = 0;
    public int chance = 0;

    public PlayerView(GameController gp, EntityModel model) {
        super(gp, model);
    }

    public void draw(Graphics2D g2) {

        if (model.moving) {
            startAnimation();
        } else {
            stopAnimation();
        }

        // TODO: Let the generic EntityView handle all of this:
        animate();

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

        Camera camera = gp.getCamera();
        g2.drawImage(image, model.worldX - ViewSettings.tileSize / 2 - camera.left, model.worldY - ViewSettings.tileSize / 2 - 16 - camera.top, entitySize, entitySize, null);
    }
}