package main.java.opal.pokemon.game.screens.overworld.characters.player;

import main.java.opal.pokemon.game.ViewSettings;
import main.java.opal.pokemon.game.screens.overworld.characters.EntityModel;
import main.java.opal.pokemon.game.screens.overworld.characters.EntityView;
import main.java.opal.pokemon.game.GameController;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerView extends EntityView {
    public int pixelCounter = 0;
    public int chance = 0;



    // NOTE: I'm not sure what these screenX and screenY actually is - and they do seem to never change,
    //       so maybe it is simply something for calculating the center of the screen relative to the player
    public final int screenX;
    public final int screenY;


    public PlayerView(GameController gp, EntityModel model) {
        super(gp, model);
       // setPlayerImage();
        screenX = ViewSettings.screenWidth / 2 - (entitySize / 2);
        screenY = ViewSettings.screenHeight / 2 - (entitySize / 2);
    }

    public void draw(Graphics2D g2) {

        if(model.moving) {
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
            case "up" -> image = up[spriteNum - 1];
            case "left" -> image = left[spriteNum - 1];
            case "down" -> image = down[spriteNum - 1];
            case "right" -> image = right[spriteNum - 1];
        }
        g2.drawImage(image, screenX, screenY - 8, null);
    }
}