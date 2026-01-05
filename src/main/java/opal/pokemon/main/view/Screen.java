package main.java.opal.pokemon.main.view;

import main.java.opal.pokemon.main.controller.ScreenController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

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

    // keep the same Font available for all extending classes
    public static Font pkmnFont;

    protected final ScreenController controller;

    public Screen(ScreenController controller) {
        this.controller = controller;

        // only initialize font once
        if (pkmnFont == null) {
            try {
                InputStream is = getClass().getResourceAsStream("/font/pkmnFont.ttf");
                pkmnFont = Font.createFont(Font.TRUETYPE_FONT, is);
            } catch (FontFormatException | IOException e) {
                throw new RuntimeException(e);
            }
        }
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

    // Text helpers
    public int getXForCenteredText(Graphics2D g2, String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return controller.getGameController().screenWidth / 2 - length / 2;
    }

}
