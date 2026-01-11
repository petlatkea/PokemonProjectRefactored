package main.java.opal.pokemon.game.screens.debug;

import main.java.opal.pokemon.game.screens.Screen;

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

        // TODO: Draw other selected debug-info blocks

    }


    private void drawPlayerInfo() {
        g2.setFont(dataFont);
        printGroupName("Player");
        printValue("x", debuginfo.player.x);
        printValue("y", debuginfo.player.y);
        println("");

        printValueln("direction", debuginfo.player.direction);
        printValueln("collision", debuginfo.player.collision);
        printValueln("grass", debuginfo.player.grass);

        printValueln("moving", debuginfo.player.moving);
        printValueln("pixel", debuginfo.player.pixelCounter);
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
