package main.java.opal.pokemon.game.screens.overworld.characters.player;

import main.java.opal.pokemon.game.GameController;
import main.java.opal.pokemon.game.screens.overworld.characters.EntityModel;
import main.java.opal.pokemon.game.screens.overworld.characters.EntityView;

public class PlayerView extends EntityView {
    public int pixelCounter = 0;
    public int chance = 0;

    public PlayerView(GameController gp, EntityModel model) {
        super(gp, model);
    }
}