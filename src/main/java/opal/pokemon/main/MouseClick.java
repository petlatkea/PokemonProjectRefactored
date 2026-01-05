package main.java.opal.pokemon.main;

import java.awt.*;

public class MouseClick {
    private int x;
    private int y;

    public MouseClick(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean insideBox(int worldX, int worldY, int width, int height) {
        Rectangle rect = new Rectangle(worldX, worldY, width, height);
        return rect.contains(x, y);
    }
}
