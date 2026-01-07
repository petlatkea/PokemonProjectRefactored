package main.java.opal.pokemon.main.model;

/**
 * The Controls class is the "model" used by the KeyHandler to store which keys are currently pressed
 *
 * The View has a KeyHandler that receives keypresses (and releases), and notifies an
 * InputController that a certain key has been pressed.
 * Then that InputController modifies this model appropriately, and notifies the main controller that
 * it has changed.
 *
 */
public class Controls {
    public boolean upPressed;
    public boolean leftPressed;
    public boolean downPressed;
    public boolean rightPressed;
    public boolean shiftPressed;
    public boolean enterPressed;
    public boolean ePressed;
    public boolean bPressed;
    public boolean spacePressed;
    public boolean pokedexPressed;
    public boolean escapePressed;

    public Controls() {
    }
}