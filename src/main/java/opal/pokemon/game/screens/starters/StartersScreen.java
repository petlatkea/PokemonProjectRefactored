package main.java.opal.pokemon.game.screens.starters;

import main.java.opal.pokemon.game.ViewSettings;
import main.java.opal.pokemon.main.UtilityTool;
import main.java.opal.pokemon.game.screens.Screen;

import java.awt.*;
import java.awt.image.BufferedImage;

public class StartersScreen extends Screen {

    private BufferedImage turtwig, chimchar, piplup;

    public StartersScreen(StartersController controller) {
        super(controller);
    }

    @Override
    public void init() {
        loadImages();
    }

    public void loadImages() {
        UtilityTool uTool = new UtilityTool();
        turtwig = setup("/images/starterPokemon/turtwig");
        turtwig = uTool.scaleImage(turtwig, 192, 192);
        chimchar = setup("/images/starterPokemon/chimchar");
        chimchar = uTool.scaleImage(chimchar, 192, 192);
        piplup = setup("/images/starterPokemon/piplup");
        piplup = uTool.scaleImage(piplup, 192, 192);
    }

    @Override
    public void drawScreen(Graphics2D g2) {
        int width = ViewSettings.screenWidth;
        int height = 200;
        int x = 0;
        int y = (ViewSettings.screenHeight - height) / 2;
        Color c = new Color(50, 50, 50, 150);
        g2.setColor(c);
        g2.fillRect(x, y, width, height);

        g2.drawImage(turtwig, 99, y + 4, null);
        g2.drawImage(chimchar, 416, y + 4, null);
        g2.drawImage(piplup, 733, y + 4, null);
    }
}
