package main.java.opal.pokemon.game.screens.debug;

import main.java.opal.pokemon.game.ViewSettings;
import main.java.opal.pokemon.game.screens.Screen;
import main.java.opal.pokemon.game.screens.overworld.Camera;
import main.java.opal.pokemon.game.screens.overworld.OverWorldController;
import main.java.opal.pokemon.game.screens.overworld.OverWorldModel;
import main.java.opal.pokemon.game.screens.overworld.tiles.TileMap;

import java.awt.*;

public class DebugScreen extends Screen {

    private Font headlineFont;
    private Font dataFont;

    private final Color backgroundColor = new Color(64, 64, 64, 200);
    private final Color white = new Color(218, 218, 218);
    private final Color yellow = new Color(179, 179, 89);
    private final Color red = new Color(223, 111, 111);

    private final DebugInfo debuginfo;

    public DebugScreen(DebugController controller, DebugInfo model) {
        super(controller);
        this.debuginfo = model;
    }

    @Override
    public void init() {
        headlineFont = new Font("Dialog", Font.BOLD, 18);
        dataFont = new Font("Monospaced", Font.PLAIN, 16);

    }

    private Graphics2D g2;

    @Override
    public void drawScreen(Graphics2D g2) {
        this.g2 = g2;

        if (debuginfo.enabled) {
            drawDebugInfo();
        }
    }

    private void drawDebugInfo() {

        // Draw grid before anything else - as that otherwise covers text
        drawGrid();

        // draw semi-transparent box in upper left corner
        g2.setColor(backgroundColor);
        g2.fillRect(10, 10, 200, 400);

        // draw headline
        g2.setFont(headlineFont);
        g2.setColor(white);
        g2.drawString("Debug-info", 16, 30);

        // reset text
        textX = 0;
        textY = 0;

        // then draw player-info
        drawPlayerInfo();
        // and camera info
        drawCameraInfo();
        // and tile info
        drawTileInfo();

        // TODO: Draw other selected debug-info blocks

    }


    private void drawPlayerInfo() {
        g2.setFont(dataFont);
        printGroupName("Player");
        printValue("x", debuginfo.player.x);
        printValue("y", debuginfo.player.y);
        println("");
        printValue("col,row", debuginfo.player.col);
        print(red,",");
        println(white, debuginfo.player.row);

        printValueln("direction", debuginfo.player.direction);
        printValueln("collision", debuginfo.player.collision);
        printValueln("grass", debuginfo.player.grass);

        printValueln("moving", debuginfo.player.moving);
        printValueln("pixel", debuginfo.player.pixelCounter);
    }

    private void drawCameraInfo() {
        g2.setFont(dataFont);
        printGroupName("Camera");
        printValueln("left, top", "" + debuginfo.camera.left +"," + debuginfo.camera.top);
    }

    private void drawTileInfo() {
        g2.setFont(dataFont);
        printGroupName("Tiles");
        printCheckBox(debuginfo.grid.grid);
        print(" ");
        printCheckBox(debuginfo.grid.coords);
        println("");
        printCheckBox(debuginfo.grid.tileType);
    }

    private void drawGrid() {
        Camera camera = controller.getGameController().getCamera();
        // TODO: Check if there's a better way of getting the map ...
        TileMap map = ((OverWorldController)controller.getGameController().overWorldController).getModel().backgroundTileMap;

        Font boldFont = dataFont.deriveFont(Font.BOLD);
        FontMetrics metrics = g2.getFontMetrics();
        int c = ViewSettings.tileSize / 2 - metrics.stringWidth("000");
        int h = ViewSettings.tileSize / 2 - metrics.getAscent() / 2;


        for (int worldCol = 0; worldCol < map.getCols(); worldCol++) {
            for (int worldRow = 0; worldRow < map.getRows(); worldRow++) {

                int tileNum = map.map[worldCol][worldRow];
                int worldX = worldCol * ViewSettings.tileSize;
                int worldY = worldRow * ViewSettings.tileSize;

                if (worldX + ViewSettings.tileSize >= camera.left &&
                        worldX <= camera.right &&
                        worldY + ViewSettings.tileSize >= camera.top &&
                        worldY <= camera.bottom) {



                    if(debuginfo.grid.grid.isChecked()) {
                        g2.setFont(dataFont);
                        g2.setColor(new Color(131, 0, 131));
                        g2.drawRect(worldX - camera.left, worldY - camera.top, ViewSettings.tileSize, ViewSettings.tileSize);
                    }
                    if(debuginfo.grid.coords.isChecked()) {
                        g2.setFont(dataFont);
                        g2.setColor(new Color(253, 0, 253));
                        g2.drawString(worldCol+","+worldRow, worldX - camera.left+2, worldY - camera.top + 14);
                    }
                    if(debuginfo.grid.tileType.isChecked()) {
                        g2.setFont(boldFont);
                        g2.setColor(new Color(230, 205, 26));
                        g2.drawString(""+tileNum, worldX-camera.left+c, worldY-camera.top+h);
                    }
                }
            }
        }
    }

    // ========== Interaction Helpers ===========

    private void printCheckBox(CheckBox checkbox) {
        checkbox.draw(g2, textOffsetX+textX, textOffsetY+textY);
        textX += 16;
        print(red, checkbox.getName());
    }

    // ========== Text Writing Helpers ==========

    // prints a "group" name in yellow at the left-most-edge
    private void printGroupName(String text) {
        textX = 0;
        println(yellow, text);
    }

    // prints a value, with the name in red, value in white, entire line indented
    private void printValue(String name, String value) {
        print(red, " " + name + ":");
        print(white, value);
    }

    // prints a value, with the name in red, value in white, entire line indented
    // and adds a linefeed afterwards
    private void printValueln(String name, String value) {
        print(red, " " + name + ":");
        println(white, value);
    }

    // the textOffset in the debug-info box - so far doesn't change
    // this is the top-left corner of text at position 0,0
    private int textOffsetX = 16;
    private int textOffsetY = 50;

    // keeps track of the "cursor" - the position where to print the next text
    private int textX;
    private int textY;

    // prints a text in a given color - adds a newline afterwards
    private void println(Color color, String text) {
        g2.setColor(color);
        println(text);
    }

    // prints a text in the given color, without adding a newline
    private void print(Color color, String text) {
        g2.setColor(color);
        print(text);
    }

    // prints a text in a previous set color
    // adds a newline afterwards, by adding the "line-height" to the textY
    private void println(String text) {
        FontMetrics metrics = g2.getFontMetrics();
        int h = metrics.getAscent();//  .getHeight(); // Ascent looks better!

        print(text);
        textX = 0;
        textY += h;
    }

    // prints a text in a previously set color
    // increments the textX with the width of the text, so that
    // any subsequent prints are printed to the right of this one
    private void print(String text) {
        FontMetrics metrics = g2.getFontMetrics();
        int w = metrics.stringWidth(text);

        g2.drawString(text, textOffsetX + textX, textOffsetY + textY);

        textX += w;
    }
}
