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
    private GameController gp;

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
        switch(gp.gameState) {
            case GameState.titleScreenState -> titleScreen.draw(g2);
            case GameState.playState -> overWorldScreen.draw(g2);
            case GameState.battleIntroState -> battleIntroScreen.draw(g2);
            case GameState.battleState ->  battleScreen.draw(g2);
            case GameState.pauseState -> pauseScreen.draw(g2);
            case GameState.dialogueState -> dialogueScreen.draw(g2);
            case GameState.pokedexState -> pokedexScreen.draw(g2);
            case GameState.starterChoiceState -> startersScreen.draw(g2);
        }
    }
}
