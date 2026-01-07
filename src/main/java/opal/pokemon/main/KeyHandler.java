package main.java.opal.pokemon.main;

import main.java.opal.pokemon.main.controller.GameController;
import main.java.opal.pokemon.main.controller.GameState;
import main.java.opal.pokemon.main.controller.InputController;
import main.java.opal.pokemon.main.controller.PokedexController;

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
        if (gp.gameState == GameState.pokedexState && ((PokedexController)gp.pokedexController).isDrawingInput()) {
            char key = e.getKeyChar();
            if (Character.isLetterOrDigit(key) || key == ' ') {
                if (((PokedexController)gp.pokedexController).getInputBuffer().length() < MAX_INPUT_LENGTH) {
                    ((PokedexController)gp.pokedexController).setInputBuffer(((PokedexController)gp.pokedexController).getInputBuffer() + key);
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        inputController.keyPressed(e.getKeyCode());

        // SEARCH
        if (gp.gameState == GameState.pokedexState && ((PokedexController)gp.pokedexController).isDrawingInput()) {
            if (code == KeyEvent.VK_BACK_SPACE) {
                if (((PokedexController)gp.pokedexController).getInputBuffer().length() > 0) {
                    ((PokedexController)gp.pokedexController).setInputBuffer(((PokedexController)gp.pokedexController).getInputBuffer().substring(0, ((PokedexController)gp.pokedexController).getInputBuffer().length() - 1));
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                String input = ((PokedexController)gp.pokedexController).getInputBuffer().trim();

                ((PokedexController)gp.pokedexController).setInputBuffer("");
                ((PokedexController)gp.pokedexController).setDrawingInput(false);

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