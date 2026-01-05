package main.java.opal.pokemon.main.view;

import main.java.opal.pokemon.main.UtilityTool;
import main.java.opal.pokemon.main.controller.DialogueController;

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
        int x = (controller.getGameController().screenWidth - (dialogueWindowImage.getWidth() * 4)) / 2;
        int y = controller.getGameController().screenHeight - (dialogueWindowImage.getHeight() * 4) - (controller.getGameController().tileSize / 8);
        drawDialogueWindow(x, y, dialogueWindowImage);

        // TEXT
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 26));
        x += controller.getGameController().tileSize - 5;
        y += controller.getGameController().tileSize + 10;
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
