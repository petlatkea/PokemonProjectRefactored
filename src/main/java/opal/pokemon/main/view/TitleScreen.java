package main.java.opal.pokemon.main.view;

import main.java.opal.pokemon.main.controller.TitleController;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TitleScreen extends Screen {

    private BufferedImage titleScreenBackground, logo, opal, rowan, lucas, dawn;

    private boolean display;
    private int resetter = 0;

    public TitleScreen(TitleController controller) {
        super(controller);
    }

    @Override
    public void init() {
        loadImages();
    }

    public void loadImages() {
        titleScreenBackground = setup("/images/titleScreen/background");
        logo = setup("/images/titleScreen/logo");
        opal = setup("/images/titleScreen/opal");
        rowan = setup("/images/titleScreen/rowan");
        lucas = setup("/images/titleScreen/lucas");
        dawn = setup("/images/titleScreen/dawn");
    }

    public void drawScreen(Graphics2D g2) {
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 18));
        g2.setColor(Color.BLACK);

        g2.drawImage(titleScreenBackground, 0, 0, controller.getGameController().screenWidth, controller.getGameController().screenHeight, null);
        g2.drawImage(logo, (controller.getGameController().screenWidth / 2) - 350, 25, 700, 250, null);

        if (!display) {
            g2.drawString("Press enter to start", controller.getGameController().screenWidth / 2 - 150, controller.getGameController().screenHeight / 2 + 100);
        }

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 16));
        g2.drawString("Made By: Andreas, Jakob, Theis & Bertram", 25, 750);
        g2.drawImage(opal, controller.getGameController().screenWidth / 2 - 150, 250, 350, 150, null);
        g2.drawImage(rowan, 750, 400, 94 * 2, 139 * 2, null);
        g2.drawImage(lucas, 100, 400, 56 * 2, 123 * 2, null);
        g2.drawImage(dawn, 200, 450, 63 * 2, 125 * 2, null);

        resetter += 1;

        if (resetter < 75) {
            display = false;
        }
        if (resetter > 75) {
            display = true;
        }
        if (resetter > 150) {
            resetter = 0;
        }
    }
}
