package main.java.opal.pokemon.game.input;

import main.java.opal.pokemon.game.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ClickHandler implements MouseListener {

    private GameController controller;

    public boolean clicked = false;
    public boolean leftClicked = false;
    public boolean rightClicked = false;
    private int mouseX;
    private int mouseY;

    public ClickHandler(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

        if (SwingUtilities.isLeftMouseButton(e)) {
            leftClicked = true;
            handleLeftClick();
        }

        if (SwingUtilities.isRightMouseButton(e)) {
            rightClicked = true;
            handleRightClick();
        }
    }

    private void handleLeftClick() {
        // when a left click happens, notify the controller - with the coordinates as an object
        controller.leftClick(new MouseClick(mouseX, mouseY));
    }

    private void handleRightClick() {
        // when a right click happens, notify the controller - with the coordinates as an object
        controller.rightClick(new MouseClick(mouseX, mouseY));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // and when the mouse is released, also notify the controller
        controller.mouseReleased(new MouseClick(mouseX, mouseY));
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public boolean consumeLeftClick(int x, int y, int w, int h) {
        if (!leftClicked) return false;

        Rectangle r = new Rectangle(x, y, w, h);
        if (r.contains(mouseX, mouseY)) {
            leftClicked = false;
            return true;
        }
        return false;
    }

    private boolean consumeRightClick() {
        if (!rightClicked) return false;
        rightClicked = false;
        return true;
    }
}
