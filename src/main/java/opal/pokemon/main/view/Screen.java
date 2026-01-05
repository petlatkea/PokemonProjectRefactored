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

    // keep the same two Fonts available for all extending classes
    public static Font pkmnFont, stndFont;

    // and keep a local Graphics2D context for convenience - set by the draw method on every frame
    protected Graphics2D g2;

    // always keep a reference to the controller for this screen
    protected final ScreenController controller;

    public Screen(ScreenController controller) {
        this.controller = controller;

        // only initialize fonts once - so all screens use the same
        if (pkmnFont == null) {
            try {
                InputStream is = getClass().getResourceAsStream("/font/pkmnFont.ttf");
                pkmnFont = Font.createFont(Font.TRUETYPE_FONT, is);
            } catch (FontFormatException | IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (stndFont == null) {
            stndFont = new Font("Dialog", Font.PLAIN, 12);
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

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setColor(Color.white);
        g2.setFont(pkmnFont);
        drawScreen(g2);
    }

    public abstract void drawScreen(Graphics2D g2);

    // Text helpers
    public int getXForCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return controller.getGameController().screenWidth / 2 - length / 2;
    }

    public int getXForCenteredTextAt(String text, int targetCenterX) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return targetCenterX - length / 2;
    }

    public int drawWrappedText(String text, int startX, int startY, int maxLineWidth, int lineSpacing) {
        FontMetrics fm = g2.getFontMetrics();
        String[] words = text.split(" ");
        String currentLine = "";
        int y = startY;

        for (String word : words) {
            String potentialLine = currentLine.isEmpty() ? word : currentLine + " " + word;
            int potentialWidth = fm.stringWidth(potentialLine);

            if (potentialWidth <= maxLineWidth) {
                currentLine = potentialLine;
            } else {
                if (!currentLine.isEmpty()) {
                    g2.drawString(currentLine, startX, y);
                    y += lineSpacing;
                }
                currentLine = word;
            }
        }
        if (!currentLine.isEmpty()) {
            g2.drawString(currentLine, startX, y);
            y += lineSpacing;
        }

        return y;
    }

}
