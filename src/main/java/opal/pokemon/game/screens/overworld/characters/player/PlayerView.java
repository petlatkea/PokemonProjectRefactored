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
       setPlayerImage();
        screenX = ViewSettings.screenWidth / 2 - (entitySize / 2);
        screenY = ViewSettings.screenHeight / 2 - (entitySize / 2);
    }

    private void setPlayerImage() {
        String gender = switch (gp.genderState) {
            case 1 -> "female";
            case 2 -> "male";
            default -> throw new IllegalStateException("No gender with the genderSate: " + gp.genderState);
        };

        String path = "/images/characters/player/" + gender + "/";

        up1 = setup(path + "walk_up_1");
        up2 = setup(path + "walk_up_2");
        up3 = setup(path + "walk_up_3");
        left1 = setup(path + "walk_left_1");
        left2 = setup(path + "walk_left_2");
        left3 = setup(path + "walk_left_3");
        down1 = setup(path + "walk_down_1");
        down2 = setup(path + "walk_down_2");
        down3 = setup(path + "walk_down_3");
        right1 = setup(path + "walk_right_1");
        right2 = setup(path + "walk_right_2");
        right3 = setup(path + "walk_right_3");
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
            case UP -> image = up[spriteNum - 1];
            case LEFT -> image = left[spriteNum - 1];
            case DOWN -> image = down[spriteNum - 1];
            case RIGHT -> image = right[spriteNum - 1];
        }
        g2.drawImage(image, screenX, screenY - 8, null);
    }
}