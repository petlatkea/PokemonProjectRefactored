package main.java.opal.pokemon.main.controller;

import main.java.opal.pokemon.main.MouseClick;
import main.java.opal.pokemon.main.model.Controls;
import main.java.opal.pokemon.main.view.PokedexScreen;
import main.java.opal.pokemon.pokedex.Pokedex;
import main.java.opal.pokemon.pokedex.Pokemon;

public class PokedexController extends ScreenController {

    public Pokedex pokedex;
    private Pokemon pokemon;

    // flags for UI-buttons pressed - used by the Screen/view - should really be in a model
    public boolean previousButtonPressed, nextButtonPressed, searchButtonPressed, searching, onOffButtonPressed;

    // flag for whether to show the start text or not
    private boolean showPokedexStartText;
    // and a public getter
    public boolean shouldShowStartText() {
        return showPokedexStartText;
    }
    // and re-setter
    public void dontShowStartText() {
        this.showPokedexStartText = false;
    }

    public PokedexController(GameController gameController) {
        super(gameController);
        // create model and view - but skip model until actually needed
        screen = new PokedexScreen(this);
    }

    @Override
    public void update() {

    }

    public void setPokedex(Pokedex pokedex) {
        this.pokedex = pokedex;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    // statuses for when receiving text input in the search - needs improvement!

    private boolean drawingInput = false;

    public boolean isDrawingInput() {
        return drawingInput;
    }

    public void setDrawingInput(boolean drawingInput) {
        this.drawingInput = drawingInput;
    }

    private String inputBuffer = "";

    public String getInputBuffer() {
        return inputBuffer;
    }

    public void setInputBuffer(String inputBuffer) {
        this.inputBuffer = inputBuffer;
    }


    // opens the pokedex - ie. initializes it for initial display
    // - and changes state to the pokedexState
    public void openPokedex() {
//        System.out.println("Opening Pokedex");
        onOffButtonPressed = false;
        closingPokedex = false;
        showPokedexStartText = true;
        getPokemon().name = null;
        gameController.gameState = GameState.pokedexState;
    }

    // closes the pokedex - ie. stops displaying it
    // - but actually only begins to close it - waits for release of a key
    //   or mouse to finish closing it
    private void closePokedex() {
//        System.out.println("Closing Pokedex");
        onOffButtonPressed = true;
        int reset = 0;
        gameController.originalPokemon.setId(reset);
        gameController.pokedex.pokemonSprite = null;
        closingPokedex = true;
    }

    private boolean closingPokedex = false;

    private void finishClosingPokedex() {
        closingPokedex = false;
        gameController.gameState = GameState.playState;
    }


    @Override
    public void handleLeftClick(MouseClick mouseClick) {
        // Pressed on Pokedex Search Button
        if (mouseClick.insideBox(245, 565, 147, 64)) {
            searchButtonPressed = true;
            searching = true;
            setDrawingInput(true);
            setInputBuffer("");
            gameController.getView().repaint();
        }

        //Pressed on Pokedex left button
        if (mouseClick.insideBox(190, 576, 45, 45)) {
            previousButtonPressed = true;
            String input = String.valueOf((gameController.originalPokemon.getId() - 1));
            if (!input.isEmpty()) {
                gameController.pokedex.search(input);
            }
        }

        //Pressed on Pokedex right button
        if (mouseClick.insideBox(398, 576, 45, 45)) {
            nextButtonPressed = true;
            String input = String.valueOf((gameController.originalPokemon.getId() + 1));
            if (!input.isEmpty()) {
                gameController.pokedex.search(input);
            }
        }

        // Pressed on Pokedex ON/OFF button
        if (mouseClick.insideBox(605, 220, 66, 60)) {
            closePokedex();
        }
    }

    @Override
    public void handleMouseReleased(MouseClick mouseClick) {
        searchButtonPressed = false;
        previousButtonPressed = false;
        nextButtonPressed = false;
        onOffButtonPressed = false;

        if (closingPokedex) {
            finishClosingPokedex();
        }
    }

    @Override
    public void keyPressed(int keyCode) {
        // ignore keypresses if drawing input
        if (!isDrawingInput()) {
            Controls controls = gameController.getControls();
            // Only accept pokedex-keypresses if the key was previously released
            // but accept escape-keypresses all the time
            if (controls.escapePressed || (controls.pokedexPressed && pokedexKeyReleased)) {
                pokedexKeyReleased = false;
                closePokedex();
            }
        }
    }

    @Override
    public void keyReleased(int keyCode) {
        // ignore keypresses when drawing input
        if (!isDrawingInput()) {
            Controls controls = gameController.getControls();
            // if pokedex-key is released - mark it as released
            if (!controls.pokedexPressed && !controls.escapePressed) {
                pokedexKeyReleased = true;
                if (closingPokedex) {
                    finishClosingPokedex();
                }
            }
        }
    }

    private boolean pokedexKeyReleased = false;
}