package main.java.opal.pokemon.game.screens.pause;

import main.java.opal.pokemon.game.ViewSettings;
import main.java.opal.pokemon.game.screens.Screen;

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

        int x = getXForCenteredText(text);
        int y = ViewSettings.screenHeight / 2;
        g2.drawString(text, x, y);
    }
}
