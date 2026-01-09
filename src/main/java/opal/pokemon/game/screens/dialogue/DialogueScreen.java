package main.java.opal.pokemon.game.screens.dialogue;

import main.java.opal.pokemon.game.ViewSettings;
import main.java.opal.pokemon.main.UtilityTool;
import main.java.opal.pokemon.game.screens.Screen;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DialogueScreen extends Screen {

    private BufferedImage dialogueWindowImage;

    public DialogueScreen(DialogueController controller) {
        super(controller);
    }

    @Override
    public void init() {
        loadImages();
    }

    public void loadImages() {
        UtilityTool uTool = new UtilityTool();
        dialogueWindowImage = setup("/images/ui/dialogueBox");
    }

    @Override
    public void drawScreen(Graphics2D g2) {
        // WINDOW
        int x = (ViewSettings.screenWidth - (dialogueWindowImage.getWidth() * 4)) / 2;
        int y = ViewSettings.screenHeight - (dialogueWindowImage.getHeight() * 4) - (ViewSettings.tileSize / 8);
        drawDialogueWindow(x, y, dialogueWindowImage);

        // TEXT
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 26));
        x += ViewSettings.tileSize - 5;
        y += ViewSettings.tileSize + 10;
        g2.setColor(Color.black);

        // Cast the controller to a DialogueController to use custom properties ...
        DialogueController dialogueController = (DialogueController) controller;
        for (String line : dialogueController.currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 55;
        }
    }

    private void drawDialogueWindow(int x, int y, BufferedImage image) {
        g2.drawImage(image, x, y, image.getWidth() * 4, image.getHeight() * 4, null);
    }
}
