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

        /*
        // TITLE SCREEN STATE
        if (gp.gameState == GameState.titleScreenState) {
            if (code == KeyEvent.VK_ENTER) {
                gp.music.stopSound();
                gp.gameState = GameState.playState;
                gp.music.setFile();
                gp.music.play();
            }
        }*/

        // PLAY STATE
        /*
        if (gp.gameState == GameState.playState) {
            if (code == KeyEvent.VK_W) {
                controls.upPressed = true;
            }
            if (code == KeyEvent.VK_A) {
                controls.leftPressed = true;
            }
            if (code == KeyEvent.VK_S) {
                controls.downPressed = true;
            }
            if (code == KeyEvent.VK_D) {
                controls.rightPressed = true;
            }
            if (code == KeyEvent.VK_B){
                controls.bPressed = true;
            }
            if (code == KeyEvent.VK_E) {
                controls.ePressed = true;
            }
            if (code == KeyEvent.VK_P) {
                gp.gameState = GameState.pokedexState;
            }
            if (code == KeyEvent.VK_SHIFT) {
                controls.shiftPressed = true;
            }
            if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState = GameState.pauseState;
            }
        } */

        // PAUSE STATE
        /*
        else if (gp.gameState == GameState.pauseState) {
            if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState = GameState.playState;
            }
        }
*/
        // DIALOGUE STATE
        /*
        else if (gp.gameState == GameState.dialogueState) {
            if (code == KeyEvent.VK_ENTER) {
                System.out.println("Press ENTER on dialogue");
                gp.getControls().enterPressed = true;
                gp.buttonSound.playButtonSound();
                gp.gameState = GameState.playState;
                // NOTE: This makes the dialogue exit to playstate on every bit of dialogue
                //       and then the Player expects it to continue, because the 'e' key is released manually
                // ... really this should all be handled by the dialogue-controller!
            }
        }
         */

        // POKEDEX STATE
        else if (gp.gameState == GameState.pokedexState) {
            if (code == KeyEvent.VK_P) {
                gp.gameState = GameState.playState;
                // TODO: Hack!? - Transfer to pokedex controller, once it also handles keys
//                gp.getView().getClickH().onOff=false;
                int reset = 0;
                gp.originalPokemon.setId(reset);
                gp.pokedex.pokemonSprite = null;
            }
            if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState = GameState.playState;
            }
        }
 /*
        // BATTLE STATE
        else if (gp.gameState == GameState.battleState){
            //
            if (code == KeyEvent.VK_SPACE){
                gp.getControls().spacePressed = true;
            }
        }

  */
    }

    @Override
    public void keyReleased(KeyEvent e) {
        inputController.keyReleased(e.getKeyCode());
        /*
        if (code == KeyEvent.VK_W) {
            controls.upPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            controls.leftPressed = false;
        }
        if (code == KeyEvent.VK_B){
            controls.bPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            controls.downPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            controls.rightPressed = false;
        }
        if (code == KeyEvent.VK_SHIFT) {
            controls.shiftPressed = false;
        }
        if (code == KeyEvent.VK_SPACE){
            controls.spacePressed = false;
        }
        if (code == KeyEvent.VK_ENTER){
            controls.enterPressed = false;
        }
*/
    }
}