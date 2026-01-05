package main.java.opal.pokemon.main.controller;

import main.java.opal.pokemon.main.view.DialogueScreen;
import main.java.opal.pokemon.main.view.PokedexScreen;
import main.java.opal.pokemon.pokedex.Pokedex;
import main.java.opal.pokemon.pokedex.Pokemon;

public class PokedexController extends ScreenController {

    public Pokedex pokedex;
    private Pokemon pokemon;

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
}
