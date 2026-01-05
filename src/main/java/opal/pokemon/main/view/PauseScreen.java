package main.java.opal.pokemon.main.view;

import main.java.opal.pokemon.main.controller.PauseController;

import java.awt.*;

public class PauseScreen extends Screen {

    public PauseScreen(PauseController controller) {
        super(controller);
    }

    @Override
    public void init() {

    }

    @Override
    public void drawScreen(Graphics2D g2) {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80));

        String text = "PAUSED";

        int x = getXForCenteredText(g2, text);
        int y = controller.getGameController().screenHeight / 2;
        g2.drawString(text, x, y);
    }
}
