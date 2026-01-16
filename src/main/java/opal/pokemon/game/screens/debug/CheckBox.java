package main.java.opal.pokemon.game.screens.debug;

import java.awt.*;

public class CheckBox {
    private String name;
    private boolean checked = false;

    public int x, y, w, h;

    private int size = 12;
    private final Color white = new Color(218, 218, 218);

    public CheckBox(String name) {
        this.name = name;
    }

    public boolean toggle() {
        checked = !checked;
        return checked;
    }

    public boolean isChecked() {
        return checked;
    }

    public void draw(Graphics2D g2, int x, int y) {
        g2.setColor(white);
        g2.fillRect(x, y - size, size, size);
        g2.setColor(Color.BLACK);
        g2.drawRect(x, y - size, size, size);

        // draw check-dot - if checked
        if (checked) {
            g2.fillRect(x + 2, y - size + 2, size - 3, size - 3);
        }

        this.x = x;
        this.y = y - size;
        this.w = size;
        this.h = size;
    }

    public String getName() {
        return name;
    }
}
