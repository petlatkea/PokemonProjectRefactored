package main.java.opal.pokemon.main.view;

import main.java.opal.pokemon.main.controller.OverWorldController;

import java.awt.*;
import java.awt.image.BufferedImage;

public class OverWorldScreen extends Screen {

    // === Pokedex Icon ===
    private BufferedImage pokedexIcon;

    // === Area Icons ===
    private Image[] areaIcons = new Image[10];
    private String[] areaNames = new String[8];

    private int currentArea = -1;
    private long areaDisplayStartTime = 0;
    private static final long AREA_DISPLAY_DURATION = 3000; // 3 seconds
    private int animatedIconY = -200;

    public OverWorldScreen(OverWorldController controller) {
        super(controller);
    }

    @Override
    public void init() {
        loadImages();
        getAreaNames();
    }

    public void loadImages() {
        pokedexIcon = setup("/images/pokedexSprites/pokedexIcon");

        areaIcons[0] = setup("/images/ui/zoneVillage");
        areaIcons[1] = setup("/images/ui/zoneOcean");
        areaIcons[2] = setup("/images/ui/zoneBeach");
        areaIcons[3] = setup("/images/ui/zonePlains");
        areaIcons[4] = setup("/images/ui/zoneForest");
        areaIcons[5] = setup("/images/ui/zoneOcean");
        areaIcons[6] = setup("/images/ui/zoneSmallCity");
        areaIcons[7] = setup("/images/ui/zoneMountain");
    }

    private void getAreaNames() {
        areaNames[0] = "Twinleaf Town";
        areaNames[1] = "Route 201";
        areaNames[2] = "Opal Springs";
        areaNames[3] = "Floaroma Fields";
        areaNames[4] = "Eterna Forest";
        areaNames[5] = "Route 202";
        areaNames[6] = "Celestic Town";
        areaNames[7] = " Mt.Coronet";
    }


    @Override
    public void drawScreen(Graphics2D g2) {
        drawPokedexIcon();
        drawAreaIcons();
    }

    // ===== AREA ICONS =====
    private void drawAreaIcons() {
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 18));
        g2.setColor(Color.BLACK);

        int x = (controller.getGameController().player.worldX / controller.getGameController().tileSize) + 1;
        int y = (controller.getGameController().player.worldY / controller.getGameController().tileSize) + 1;

        long elapsed = System.currentTimeMillis() - areaDisplayStartTime;

        int iconX = 635;
        int iconY = -200;

        int nameX = 660;
        int nameY = -130;

        int iconWidth = 345;
        int iconHeight = 95;

        int newArea = -1;

        if (x > 42 && x <= 71 && y >= 5 && y <= 22) {
            newArea = 3;
        } else if (x >= 49 && x <= 55 && y >= 62 && y <= 66) {
            newArea = 5;
        } else if (x >= 38 && x < 94 && y >= 83 && y <= 94) {
            newArea = 7;
        } else if (x <= 38 && y >= 62 && y <= 91) {
            newArea = 6;
        } else if (x >= 27 && x <= 54 && y >= 17 && y <= 65) {
            newArea = 1;
        } else if (x >= 40 && x <= 92 && y >= 55 && y <= 73) {
            newArea = 5;
        } else if (x <= 27 && y >= 38 && y <= 57) {
            newArea = 0;
        } else if (x <= 42 && y < 34) {
            newArea = 2;
        } else if (x >= 71 && x <= 92 && y >= 7 && y <= 54) {
            newArea = 4;
        }

        // If area changed, reset timer
        if (newArea != -1 && newArea != currentArea) {
            currentArea = newArea;
            areaDisplayStartTime = System.currentTimeMillis();
            animatedIconY = -200;
        }

        // Draw only if within 3 seconds
        if (currentArea != -1 && elapsed <= AREA_DISPLAY_DURATION) {
            if (animatedIconY < 0) {
                animatedIconY += 4;
            }
            g2.drawImage(areaIcons[currentArea], iconX, animatedIconY, iconWidth, iconHeight, null);
            g2.drawString(areaNames[currentArea], nameX, animatedIconY + 70);
        }

        if (elapsed > AREA_DISPLAY_DURATION) {
            if (animatedIconY > -200) {
                animatedIconY -= 4;
            }
            g2.drawImage(areaIcons[currentArea], iconX, animatedIconY, iconWidth, iconHeight, null);
            g2.drawString(areaNames[currentArea], nameX, animatedIconY + 70);
        }
    }

    private void drawPokedexIcon() {
        int x = 25;
        int y = 690;
        BufferedImage image = pokedexIcon;
        g2.drawImage(image, x, y, image.getWidth() * 2, image.getHeight() * 2, null);
    }
}
