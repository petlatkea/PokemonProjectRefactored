package main.java.opal.pokemon.game.screens.battle;

import main.java.opal.pokemon.main.UtilityTool;
import main.java.opal.pokemon.game.screens.Screen;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BattleIntroScreen extends Screen {

    private Image[] wildIntro = new Image[7];

    public BattleIntroScreen(BattleIntroController controller) {
        super(controller);
    }

    @Override
    public void init() {
        loadImages();
    }

    public void loadImages() {
        UtilityTool uTool = new UtilityTool();
        wildIntro[0] = setup("/images/battleIntro/wildIntro1");
        wildIntro[0] = uTool.scaleImage((BufferedImage) wildIntro[0], 1024, 768);
        wildIntro[1] = setup("/images/battleIntro/wildIntro2");
        wildIntro[1] = uTool.scaleImage((BufferedImage) wildIntro[1], 1024, 768);
        wildIntro[2] = setup("/images/battleIntro/wildIntro3");
        wildIntro[2] = uTool.scaleImage((BufferedImage) wildIntro[2], 1024, 768);
        wildIntro[3] = setup("/images/battleIntro/wildIntro4");
        wildIntro[3] = uTool.scaleImage((BufferedImage) wildIntro[3], 1024, 768);
        wildIntro[4] = setup("/images/battleIntro/wildIntro5");
        wildIntro[4] = uTool.scaleImage((BufferedImage) wildIntro[4], 1024, 768);
        wildIntro[5] = setup("/images/battleIntro/wildIntro6");
        wildIntro[5] = uTool.scaleImage((BufferedImage) wildIntro[5], 1024, 768);
        wildIntro[6] = setup("/images/battleIntro/wildIntro7");
        wildIntro[6] = uTool.scaleImage((BufferedImage) wildIntro[6], 1024, 768);
    }

    @Override
    public void drawScreen(Graphics2D g2) {
        // casting controller to specific BattleIntroController in order to use custom methods
        BattleIntroController introController = (BattleIntroController) controller;

        if (introController.getGrassFadeCounter() <= 40) {
            g2.drawImage(wildIntro[0], 0, 0, 1024, 768, null);
        } else if (introController.getGrassFadeCounter() <= 55) {
            g2.drawImage(wildIntro[1], 0, 0, 1024, 768, null);
        } else if (introController.getGrassFadeCounter() <= 65) {
            g2.drawImage(wildIntro[2], 0, 0, 1024, 768, null);
        } else if (introController.getGrassFadeCounter() <= 70) {
            g2.drawImage(wildIntro[3], 0, 0, 1024, 768, null);
        } else if (introController.getGrassFadeCounter() <= 75) {
            g2.drawImage(wildIntro[4], 0, 0, 1024, 768, null);
        } else if (introController.getGrassFadeCounter() <= 80) {
            g2.drawImage(wildIntro[5], 0, 0, 1024, 768, null);
        } else if (introController.getGrassFadeCounter() <= 90) {
            g2.drawImage(wildIntro[6], 0, 0, 1024, 768, null);
        }
    }


}
