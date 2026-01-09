package main.java.opal.pokemon.game.screens.overworld.tiles;

import main.java.opal.pokemon.game.screens.overworld.characters.player.PlayerView;
import main.java.opal.pokemon.main.UtilityTool;
import main.java.opal.pokemon.game.GameController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TileGraphics {
    GameController controller;

    private BufferedImage tileImages[];

    public BufferedImage getTileImage(int tileId) {
        return tileImages[tileId];
    }

    public TileGraphics(GameController controller) {
        this.controller = controller;

        tileImages = new BufferedImage[338];
        loadImages();
    }

    private void loadImages() {
        UtilityTool uTool = new UtilityTool();

        for (int i = 0; i <= 337; i++) {
            try {
                String fileName = String.format("tile%03d", i);

                tileImages[i] = ImageIO.read(getClass().getResourceAsStream("/images/tiles/" + fileName + ".png"));
                tileImages[i] = uTool.scaleImage(tileImages[i], controller.tileSize, controller.tileSize);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    // Draw tilemap (previously known as drawLayer)
    public void drawTileMap(Graphics2D g2, TileMap tileMap) {
        int cameraLeft = controller.getPlayer().model.worldX - ((PlayerView)controller.getPlayer().view).screenX;
        int cameraTop = controller.getPlayer().model.worldY - ((PlayerView)controller.getPlayer().view).screenY;
        int cameraRight = cameraLeft + controller.screenWidth;
        int cameraBottom = cameraTop + controller.screenHeight;

        for (int worldCol = 0; worldCol < controller.maxWorldCol; worldCol++) {
            for (int worldRow = 0; worldRow < controller.maxWorldRow; worldRow++) {

                int tileNum = tileMap.map[worldCol][worldRow];
                int worldX = worldCol * controller.tileSize;
                int worldY = worldRow * controller.tileSize;

                if (worldX + controller.tileSize >= cameraLeft &&
                        worldX <= cameraRight &&
                        worldY + controller.tileSize >= cameraTop &&
                        worldY <= cameraBottom) {

                    int screenX = worldX - cameraLeft;
                    int screenY = worldY - cameraTop;


                    g2.drawImage(getTileImage(tileNum), screenX, screenY, null);
                }
            }
        }
    }
}
