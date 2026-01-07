package main.java.opal.pokemon.main.view;

import main.java.opal.pokemon.main.ClickHandler;
import main.java.opal.pokemon.main.KeyHandler;
import main.java.opal.pokemon.main.controller.GameController;
import main.java.opal.pokemon.main.controller.GameState;
import main.java.opal.pokemon.main.model.GameModel;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {

    private final GameController controller;
    private final GameModel model;

    private KeyHandler keyH;
    private ClickHandler clickH;

    // SCREENS
    private Screen titleScreen;
    private Screen overWorldScreen;
    private Screen battleIntroScreen;
    private Screen battleScreen;
    private Screen startersScreen;
    private Screen pauseScreen;
    private Screen dialogueScreen;
    private Screen pokedexScreen;

    // === DEBUG ===
    private int warmupFrames = 30;
    private long highestDrawTime = 0;
    private long totalDrawTime = 0;
    private long drawCount = 0;
    private int frameSincePrint = 0;

    public GameView(GameController gameController, GameModel model) {
        this.controller = gameController;
        this.model = model;

        // input controllers
        keyH = new KeyHandler(controller);
        this.addKeyListener(keyH);

        clickH = new ClickHandler(controller);
        this.addMouseListener(clickH);

        // screens
        initializeScreens();

        // window settings
        this.setPreferredSize(new Dimension(controller.screenWidth, controller.screenHeight));
        this.setBackground(new java.awt.Color(120, 192, 248));
        this.setDoubleBuffered(true);

        this.setFocusable(true);
        this.setOpaque(true);
        this.setIgnoreRepaint(false);
        this.setLayout(null);
    }

    public KeyHandler getKeyH() {
        return keyH;
    }

    public ClickHandler getClickH() {
        return clickH;
    }

    private void initializeScreens() {
        // initialise screens - by getting them from the controller one at a time.
        titleScreen = controller.titleScreenController.getScreen();
        titleScreen.init();
        overWorldScreen = controller.overWorldController.getScreen();
        overWorldScreen.init();
        battleIntroScreen = controller.battleIntroController.getScreen();
        battleIntroScreen.init();
        battleScreen = controller.battleController.getScreen();
        battleScreen.init();
        startersScreen = controller.startersController.getScreen();
        startersScreen.init();
        pauseScreen = controller.pauseController.getScreen();
        pauseScreen.init();
        dialogueScreen = controller.dialogueController.getScreen();
        dialogueScreen.init();
        pokedexScreen = controller.pokedexController.getScreen();
        pokedexScreen.init();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // initialize g2
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

        // DEBUG
        long drawStart = System.nanoTime();

        drawScreens(g2);

        // DEBUG
        long passedTime = System.nanoTime() - drawStart;

        double passedMs = passedTime / 1_000_000.0;

        if (warmupFrames > 0) {
            warmupFrames--;
        } else {
            // TRACK AVG & MAX
            if (passedTime > highestDrawTime) {
                highestDrawTime = passedTime;
            }

            totalDrawTime += passedTime;
            drawCount++;

            double averageMs = (totalDrawTime / (double) drawCount) / 1_000_000.0;
            double highestMs = highestDrawTime / 1_000_000.0;

            frameSincePrint++;
            int printInterval = 30;
            if (frameSincePrint >= printInterval) {
//                System.out.printf(
//                        "Draw: %.3f ms | Highest: %.3f ms | Average: %.3f ms%n",
//                        passedMs, highestMs, averageMs
//                );
//                System.out.println("xPos: " + ((player.worldX/64)+1) + " yPos: " + ((player.worldY/64)+1));
                frameSincePrint = 0;
            }
        }

        g2.dispose();
    }

    private void drawScreens(Graphics2D g2) {
        // always draw the overWorldScreen before anything else
        // - unless state is title, battle or battleIntro
        switch(controller.gameState) {
            case GameState.titleScreenState,
                 GameState.battleIntroState,
                 GameState.battleState: // don't draw overworld
                break;
            default:
                overWorldScreen.draw(g2);
        }

        // then draw the screen for the current state
        switch(controller.gameState) {
            case GameState.titleScreenState -> titleScreen.draw(g2);
            case GameState.battleIntroState -> battleIntroScreen.draw(g2);
            case GameState.battleState ->  battleScreen.draw(g2);
            case GameState.pauseState -> pauseScreen.draw(g2);
            case GameState.dialogueState -> dialogueScreen.draw(g2);
            case GameState.pokedexState -> pokedexScreen.draw(g2);
            case GameState.starterChoiceState -> startersScreen.draw(g2);
        }
    }

    public long getDrawCount() {
        return drawCount;
    }
}
