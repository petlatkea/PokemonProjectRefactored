package main.java.opal.pokemon.main.controller;

import main.java.opal.pokemon.main.model.Controls;

import java.awt.event.KeyEvent;

public class InputController {

    private GameController gameController;

    private Controls controls;

    public InputController(GameController controller) {
        this.gameController = controller;
        this.controls = new Controls();
    }

    public Controls getControls() {
        return controls;
    }

    public void keyPressed(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_W -> controls.upPressed = true;
            case KeyEvent.VK_A -> controls.leftPressed = true;
            case KeyEvent.VK_S -> controls.downPressed = true;
            case KeyEvent.VK_D -> controls.rightPressed = true;
            case KeyEvent.VK_B -> controls.bPressed = true;
            case KeyEvent.VK_E -> controls.ePressed = true;
            case KeyEvent.VK_SHIFT -> controls.shiftPressed = true;
            case KeyEvent.VK_P -> controls.pokedexPressed = true;
            case KeyEvent.VK_ESCAPE -> controls.escapePressed = true;
            case KeyEvent.VK_ENTER -> controls.enterPressed = true;
            case KeyEvent.VK_SPACE -> controls.spacePressed = true;
        }
        // inform game-controller that a key is pressed
        gameController.keyPressed(keyCode);
    }

    public void keyReleased(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_W -> controls.upPressed = false;
            case KeyEvent.VK_A -> controls.leftPressed = false;
            case KeyEvent.VK_S -> controls.downPressed = false;
            case KeyEvent.VK_D -> controls.rightPressed = false;
            case KeyEvent.VK_B -> controls.bPressed = false;
            case KeyEvent.VK_E -> controls.ePressed = false;
            case KeyEvent.VK_SHIFT -> controls.shiftPressed = false;
            case KeyEvent.VK_P -> controls.pokedexPressed = false;
            case KeyEvent.VK_ESCAPE -> controls.escapePressed = false;
            case KeyEvent.VK_ENTER -> controls.enterPressed = false;
            case KeyEvent.VK_SPACE -> controls.spacePressed = false;
        }

        // inform game-controller that a key is released
        gameController.keyReleased(keyCode);
    }
}
