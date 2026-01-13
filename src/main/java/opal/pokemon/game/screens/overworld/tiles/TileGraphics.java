package main.java.opal.pokemon.game.screens.overworld.tiles;

import main.java.opal.pokemon.game.ViewSettings;
import main.java.opal.pokemon.game.screens.overworld.Camera;
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    // Draw tilemap (previously known as drawLayer)
    public void drawTileMap(Graphics2D g2, TileMap tileMap) {
        Camera camera = controller.getCamera();

        for (int worldCol = 0; worldCol < tileMap.getCols(); worldCol++) {
            for (int worldRow = 0; worldRow < tileMap.getRows(); worldRow++) {

                int tileNum = tileMap.map[worldCol][worldRow];
                int worldX = worldCol * ViewSettings.tileSize;
                int worldY = worldRow * ViewSettings.tileSize;

                if (worldX + ViewSettings.tileSize >= camera.left &&
                        worldX <= camera.right &&
                        worldY + ViewSettings.tileSize >= camera.top &&
                        worldY <= camera.bottom) {

                    g2.drawImage(getTileImage(tileNum), worldX - camera.left, worldY - camera.top, ViewSettings.tileSize, ViewSettings.tileSize, null);
                    // NOTE: For debugging purposes - make toggleable!
//                    g2.setColor(new Color(255,0,255));
//                    g2.drawRect(worldX - camera.left, worldY - camera.top, ViewSettings.tileSize, ViewSettings.tileSize);
                }
            }
        }
    }
}
