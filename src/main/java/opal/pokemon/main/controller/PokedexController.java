package main.java.opal.pokemon.main.controller;

import main.java.opal.pokemon.main.MouseClick;
import main.java.opal.pokemon.main.view.PokedexScreen;
import main.java.opal.pokemon.pokedex.Pokedex;
import main.java.opal.pokemon.pokedex.Pokemon;

public class PokedexController extends ScreenController {

    public Pokedex pokedex;
    private Pokemon pokemon;

    // flags for UI-buttons pressed - used by the Screen/view - should really be in a model
    public boolean previousButtonPressed, nextButtonPressed, searchButtonPressed, searching, onOff, onOffAction = true;

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

    @Override
    public void handleLeftClick(MouseClick mouseClick) {
        // Pressed on Pokedex Search Button
        if (mouseClick.insideBox(245, 565, 147, 64)) {
            searchButtonPressed = true;
            searching = true;
            gameController.ui.drawingInput = true;
            gameController.ui.inputBuffer = "";
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
            onOff = true;
            int reset = 0;
            gameController.originalPokemon.setId(reset);
            gameController.pokedex.pokemonSprite = null;
        }
    }

    @Override
    public void handleMouseReleased(MouseClick mouseClick) {
        searchButtonPressed = false;
        previousButtonPressed = false;
        nextButtonPressed = false;
        onOff = false;

        if (mouseClick.insideBox(605, 220, 66, 60)) {
            onOffAction = false;
        }
    }
}
