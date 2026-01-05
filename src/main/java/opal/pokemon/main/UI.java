package main.java.opal.pokemon.main;

import main.java.opal.pokemon.main.controller.GameController;
import main.java.opal.pokemon.main.controller.GameState;
import main.java.opal.pokemon.main.view.Screen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class UI {
    GameController gp;
    Graphics2D g2;

    public Font pkmnFont;

    // TODO: Move to PokedexController
    public String inputBuffer = "";
    public boolean drawingInput = false;

    // SCREENS
    private Screen titleScreen;
    private Screen overWorldScreen;
    private Screen battleIntroScreen;
    private Screen battleScreen;
    private Screen startersScreen;
    private Screen pauseScreen;
    private Screen dialogueScreen;
    private Screen pokedexScreen;


    public UI(GameController gp) {
        this.gp = gp;

        InputStream is = getClass().getResourceAsStream("/font/pkmnFont.ttf");
        try {
            pkmnFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }

        // initialise screens - by getting them from the controller one at a time.
        titleScreen = gp.titleScreenController.getScreen();
        titleScreen.init();
        overWorldScreen = gp.overWorldController.getScreen();
        overWorldScreen.init();
        battleIntroScreen = gp.battleIntroController.getScreen();
        battleIntroScreen.init();
        battleScreen = gp.battleController.getScreen();
        battleScreen.init();
        startersScreen = gp.startersController.getScreen();
        startersScreen.init();
        pauseScreen = gp.pauseController.getScreen();
        pauseScreen.init();
        dialogueScreen = gp.dialogueController.getScreen();
        dialogueScreen.init();
        pokedexScreen = gp.pokedexController.getScreen();
        pokedexScreen.init();
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(pkmnFont);
        g2.setColor(Color.white);

        switch(gp.gameState) {
            case GameState.titleScreenState -> titleScreen.drawScreen(g2);
            case GameState.playState -> overWorldScreen.drawScreen(g2);
            case GameState.battleIntroState -> battleIntroScreen.drawScreen(g2);
            case GameState.battleState ->  battleScreen.drawScreen(g2);
            case GameState.pauseState -> pauseScreen.drawScreen(g2);
            case GameState.dialogueState -> dialogueScreen.drawScreen(g2);
            case GameState.pokedexState -> pokedexScreen.drawScreen(g2);
            case GameState.starterChoiceState -> startersScreen.drawScreen(g2);
        }
    }
}
