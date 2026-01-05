package main.java.opal.pokemon.main;

import main.java.opal.pokemon.main.controller.GameController;
import main.java.opal.pokemon.main.controller.GameState;
import main.java.opal.pokemon.main.view.BattleIntroScreen;
import main.java.opal.pokemon.main.view.Screen;
import main.java.opal.pokemon.main.view.TitleScreen;
import main.java.opal.pokemon.pokedex.EntryStats;
import main.java.opal.pokemon.pokedex.Pokedex;
import main.java.opal.pokemon.pokedex.Pokemon;
import main.java.opal.pokemon.pokedex.TypeEntry;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class UI {
    GameController gp;
    Graphics2D g2;
//    ClickHandler clickH;
//    Pokemon pokemon;
//    Pokedex pokedex;
//    UtilityTool uTool = new UtilityTool();
   // public BufferedImage pokedexBoy, pokedexGirl, pokedexIcon, searchButtonReleased, searchButtonPressed, previousButtonReleased, nextButtonReleased, previousButtonPressed, nextButtonPressed, onOffButtonOn, onOffButtonOff, opal;
    public BufferedImage pokedexIcon;

    public Font pkmnFont;

    // TODO: Move to PokedexController
    public String inputBuffer = "";
    public boolean drawingInput = false;

    // === Area Icons ===
    Image[] areaIcons = new Image[10];
    String[] areaNames = new String[8];

    private int currentArea = -1;
    private long areaDisplayStartTime = 0;
    private static final long AREA_DISPLAY_DURATION = 3000; // 3 seconds
    int animatedIconY = -200;

    // SCREENS
    private Screen titleScreen;
    private Screen battleIntroScreen;
    private Screen startersScreen;
    private Screen pauseScreen;
    private Screen dialogueScreen;
    private Screen pokedexScreen;


    public UI(GameController gp) {
        this.gp = gp;
//        this.clickH = clickH;
//        this.pokemon = pokemon;
//        this.pokedex = pokedex;

        InputStream is = getClass().getResourceAsStream("/font/pkmnFont.ttf");
        try {
            pkmnFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        getUIImages();
        getAreaNames();

        // initialise screens - by getting them from the controller one at a time.
        titleScreen = gp.titleScreenController.getScreen();
        titleScreen.init();
        battleIntroScreen = gp.battleIntroController.getScreen();
        battleIntroScreen.init();
        startersScreen = gp.startersController.getScreen();
        startersScreen.init();
        pauseScreen = gp.pauseController.getScreen();
        pauseScreen.init();
        dialogueScreen = gp.dialogueController.getScreen();
        dialogueScreen.init();
        pokedexScreen = gp.pokedexController.getScreen();
        pokedexScreen.init();
    }

    private void getUIImages() {

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

    private BufferedImage setup(String imagePath) {
        BufferedImage image;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return image;
    }

    private BufferedImage setup(String imagePath, int scaleX, int scaleY) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, scaleX, scaleY);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return image;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(pkmnFont);
        g2.setColor(Color.white);

        switch(gp.gameState) {
            case GameState.titleScreenState -> titleScreen.drawScreen(g2);
            case GameState.playState -> {
                    drawPokedexIcon();
                    drawAreaIcons();
            }
            case GameState.battleIntroState -> battleIntroScreen.drawScreen(g2);
            case GameState.pauseState -> pauseScreen.drawScreen(g2);
            case GameState.dialogueState -> dialogueScreen.drawScreen(g2);
            case GameState.pokedexState -> pokedexScreen.drawScreen(g2);
            case GameState.starterChoiceState -> startersScreen.drawScreen(g2);
        }
    }

    private void drawPokedexIcon() {
        int x = 25;
        int y = 690;
        BufferedImage image = pokedexIcon;
        g2.drawImage(image, x, y, image.getWidth() * 2, image.getHeight() * 2, null);
    }

    // ===== AREA ICONS =====
    private void drawAreaIcons() {
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 18));
        g2.setColor(Color.BLACK);

        int x = (gp.player.worldX / gp.tileSize) + 1;
        int y = (gp.player.worldY / gp.tileSize) + 1;

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

    // NOTE: Duplicated in Screen - remove from here as soon as no more usages of this version!
    public int drawWrappedText(Graphics2D g2, String text, int startX, int startY, int maxLineWidth, int lineSpacing) {
        FontMetrics fm = g2.getFontMetrics();
        String[] words = text.split(" ");
        String currentLine = "";
        int y = startY;

        for (String word : words) {
            String potentialLine = currentLine.isEmpty() ? word : currentLine + " " + word;
            int potentialWidth = fm.stringWidth(potentialLine);

            if (potentialWidth <= maxLineWidth) {
                currentLine = potentialLine;
            } else {
                if (!currentLine.isEmpty()) {
                    g2.drawString(currentLine, startX, y);
                    y += lineSpacing;
                }
                currentLine = word;
            }
        }
        if (!currentLine.isEmpty()) {
            g2.drawString(currentLine, startX, y);
            y += lineSpacing;
        }

        return y;
    }

    public void inputSetup() {
        InputStream is = getClass().getResourceAsStream("/font/pkmnFont.ttf");
        try {
            pkmnFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        getUIImages();
    }

}
