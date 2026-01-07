package main.java.opal.pokemon.main;

import main.java.opal.pokemon.main.controller.GameController;
import main.java.opal.pokemon.main.controller.GameState;
import main.java.opal.pokemon.main.controller.InputController;
import main.java.opal.pokemon.main.model.Controls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private InputController inputController;

    private GameController gp;
    private int count = 0;
    private final int MAX_INPUT_LENGTH = 15;

    public KeyHandler(GameController gp) {
        this.gp = gp;
        this.inputController = gp.inputController;
    }

    @Override
    public void keyTyped(KeyEvent e) {

        // USER INPUT POKEDEX
        if (gp.gameState == GameState.pokedexState && gp.ui.drawingInput) {
            char key = e.getKeyChar();
            if (Character.isLetterOrDigit(key) || key == ' ') {
                if (gp.ui.inputBuffer.length() < MAX_INPUT_LENGTH) {
                    gp.ui.inputBuffer += key;
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        inputController.keyPressed(e.getKeyCode());

        // SEARCH
        if (gp.gameState == GameState.pokedexState && gp.ui.drawingInput) {
            if (code == KeyEvent.VK_BACK_SPACE) {
                if (gp.ui.inputBuffer.length() > 0) {
                    gp.ui.inputBuffer = gp.ui.inputBuffer.substring(0, gp.ui.inputBuffer.length() - 1);
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                String input = gp.ui.inputBuffer.trim();

                gp.ui.inputBuffer = "";
                gp.ui.drawingInput = false;

                if (!input.isEmpty()) {
                    gp.pokedex.search(input);
                }
                // TODO: HACK! Fix asap.
                gp.getView().requestFocusInWindow();
            }
            return;
        }

      }

    @Override
    public void keyReleased(KeyEvent e) {
        inputController.keyReleased(e.getKeyCode());
    }
}