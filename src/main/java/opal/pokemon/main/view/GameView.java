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

    private final TileGraphics tileGraphics;

    // === DEBUG ===
    private int warmupFrames = 30;
    private long highestDrawTime = 0;
    private long totalDrawTime = 0;
    private long drawCount = 0;
    private int frameSincePrint = 0;

    public GameView(GameController gameController, GameModel model) {
        this.controller = gameController;
        this.model = model;

        tileGraphics = new TileGraphics(controller);

        this.setPreferredSize(new Dimension(controller.screenWidth, controller.screenHeight));
        this.setBackground(new java.awt.Color(120, 192, 248));
        this.setDoubleBuffered(true);

        keyH = new KeyHandler(controller);
        this.addKeyListener(keyH);

        clickH = new ClickHandler(controller);

        this.addMouseListener(clickH);
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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);


        // DEBUG
        long drawStart = System.nanoTime();

        if (controller.gameState != GameState.titleScreenState && controller.gameState != GameState.battleState) {

            // Background Layer
            tileGraphics.drawTileMap(g2, model.backgroundTileMap);

            // Object Layer
            for (int i = 0; i < controller.obj.length; i++) {
                if (controller.obj[i] != null) {
                    controller.obj[i].draw(g2, controller);
                }
            }

            // Environment Behind player
            tileGraphics.drawTileMap(g2, model.environmentBTileMap);

            // NPCs
            for (int i = 0; i < controller.npc.length; i++) {
                if (controller.npc[i] != null) {
                    controller.npc[i].draw(g2);
                }
            }

            // Player
            controller.player.draw(g2);

            // Environment Front of player
            tileGraphics.drawTileMap(g2, model.environmentFTileMap );
        }

        // UI
        controller.ui.draw(g2);


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

    public long getDrawCount() {
        return drawCount;
    }
}
