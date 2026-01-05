package main.java.opal.pokemon.main.view;

import main.java.opal.pokemon.main.controller.ScreenController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * a Screen is a "sub-view" that displays a single screen, e.g. play, battle, battleintro, etc.
 * It expects a ScreenController to contain it, and that controller is then used as a sub-controller
 * by the main GameController, which calls update on every frame.
 * The Screen must implement two methods:
 * - init() which initializes the view, usually by loading graphics
 * - drawScreen(g2) which is called on every frame with a graphics-context, and expects the extending
 *    class to, well, draw the entire screen.
 */
public abstract class Screen {
    protected final ScreenController controller;

    public Screen(ScreenController controller) {
        this.controller = controller;
    }


    protected BufferedImage setup(String imagePath) {
        BufferedImage image;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return image;
    }

    public abstract void init();

    public abstract void drawScreen(Graphics2D g2);

}
