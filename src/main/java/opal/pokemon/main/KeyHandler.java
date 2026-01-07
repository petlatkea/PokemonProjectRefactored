package main.java.opal.pokemon.main;

import main.java.opal.pokemon.main.controller.GameController;
import main.java.opal.pokemon.main.controller.InputController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private final InputController inputController;

    public KeyHandler(GameController gameController) {
        this.inputController = gameController.inputController;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        inputController.keyTyped(e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        inputController.keyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        inputController.keyReleased(e.getKeyCode());
    }
}