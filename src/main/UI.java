package main;

import entity.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Player player;

    UtilityTool uTool = new UtilityTool();
    public BufferedImage dialogueWindowImage;


    Font pkmnFont;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public String currentDialogue = "";

    // ===== Area Icons =====
    Image[] areaIcons = new Image[10];
    String[] areaNames = new String[8];


    public UI(GamePanel gp, Player player) {
        this.gp = gp;
        this.player = player;

        InputStream is = getClass().getResourceAsStream("/font/pkmnFont.ttf");
        try {
            pkmnFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        getUIImage();
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void getUIImage() {
        try {
            dialogueWindowImage = ImageIO.read(getClass().getResourceAsStream("/ui/dialogueBox.png"));
            uTool.scaleImage(dialogueWindowImage, 3, 3);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(pkmnFont);
        g2.setColor(Color.white);

        // PLAY STATE
        if (gp.gameState == gp.playState) {
            drawAreaIcons();

        }

        // PAUSE STATE
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }

        // DIALOGUE STATE
        if (gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
        }
    }

    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80));

        String text = "PAUSED";

        int x = getXForCenteredText(text);
        int y = gp.screenHeight / 2;
        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen() {
        // WINDOW
        int x = (gp.screenWidth - (dialogueWindowImage.getWidth() * 4)) / 2;
        int y = gp.screenHeight - (dialogueWindowImage.getHeight() * 4) - (gp.tileSize / 8);
        drawDialogueWindow(x, y, dialogueWindowImage);

        // TEXT
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 26));
        x += gp.tileSize - 5;
        y += gp.tileSize + 10;
        g2.setColor(Color.black);
        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 55;
        }
    }

    public void drawDialogueWindow(int x, int y, BufferedImage image) {
        g2.drawImage(image, x, y, image.getWidth() * 4, image.getHeight() * 4, null);
    }

    public int getXForCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth / 2 - length / 2;
    }


    public void getAreaIcons() {
        try {
            areaIcons[0] = ImageIO.read(getClass().getResourceAsStream("/ui/zoneSmallCity.png"));
            areaIcons[1] = ImageIO.read(getClass().getResourceAsStream("/ui/zoneOcean.png"));
            areaIcons[2] = ImageIO.read(getClass().getResourceAsStream("/ui/zoneBeach.png"));
            areaIcons[3] = ImageIO.read(getClass().getResourceAsStream("/ui/zonePlains.png"));
            areaIcons[4] = ImageIO.read(getClass().getResourceAsStream("/ui/zoneForest.png"));
            areaIcons[5] = ImageIO.read(getClass().getResourceAsStream("/ui/zoneVillage.png"));
            areaIcons[6] = ImageIO.read(getClass().getResourceAsStream("/ui/zoneMountain.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getAreaNames() {
        areaNames[0] = "Twinleaf Town";
        areaNames[1] = "Route 201";
        areaNames[2] = "Lake of Rage";
        areaNames[3] = "Floaroma Fields";
        areaNames[4] = "Eterna Forest";
        areaNames[5] = "Route 202";
        areaNames[6] = "Solaceon Town";
        areaNames[7] = " Mt.Coronet";


    }


    public void drawAreaIcons() {
        getAreaIcons();
        getAreaNames();
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 18));
        g2.setColor(Color.BLACK);

        int x = (player.worldX / gp.tileSize) + 1;
        int y = (player.worldY / gp.tileSize) + 1;

        int iconX = 635;
        int iconY = 0;

        int nameX = 660;
        int nameY = 70;

        int iconWidth = 345;
        int iconHeight = 95;

        if (x > 42 && x <= 71 && y >= 5 && y <= 22) {
            g2.drawImage(areaIcons[3], iconX, iconY, iconWidth, iconHeight, null);
            g2.drawString(areaNames[3], nameX, nameY);
        }
        else if (x>=49 && x <= 55 && y>=62 && y <=66){
            g2.drawImage(areaIcons[1], iconX, iconY, iconWidth, iconHeight, null);
            g2.drawString(areaNames[5], nameX, nameY);
        }
        else if (x >= 38 && x < 94 && y >= 83 && y <= 94){
            g2.drawImage(areaIcons[6], iconX, iconY, iconWidth, iconHeight, null);
            g2.drawString(areaNames[7], nameX, nameY);
        }
        else if (x <= 38 && y >= 62 && y <= 91){
            g2.drawImage(areaIcons[5], iconX, iconY, iconWidth, iconHeight, null);
            g2.drawString(areaNames[6], nameX, nameY);
        }
        else if (x >= 27 && x <= 54 && y >= 17 && y <= 65){
            g2.drawImage(areaIcons[1], iconX, iconY, iconWidth, iconHeight, null);
            g2.drawString(areaNames[1], nameX, nameY);
        }
        else if (x >= 40 && x <= 92 && y >= 55 && y <= 73){
            g2.drawImage(areaIcons[1], iconX, iconY, iconWidth, iconHeight, null);
            g2.drawString(areaNames[5], nameX, nameY);
        }
        else if (x <= 27 && y >= 38 && y <= 57){
            g2.drawImage(areaIcons[0], iconX, iconY, iconWidth, iconHeight, null);
            g2.drawString(areaNames[0], nameX, nameY);
        }
        else if (x <= 42 && y < 34){
            g2.drawImage(areaIcons[2], iconX, iconY, iconWidth, iconHeight, null);
            g2.drawString(areaNames[2], nameX, nameY);
        }
        else if (x >= 71 && x <= 92 && y >= 7 && y <= 54){
            g2.drawImage(areaIcons[4], iconX, iconY, iconWidth, iconHeight, null);
            g2.drawString(areaNames[4], nameX, nameY);
        }
    }
}



