package main.java.opal.pokemon.main;

import java.awt.*;
import java.awt.image.BufferedImage;

// TODO: Get rid of this, or integrate into generic View class ...
public class UtilityTool {
    public BufferedImage scaleImage(BufferedImage original, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, 2);
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();

        return scaledImage;
    }
}
