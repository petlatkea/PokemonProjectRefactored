package main.java.opal.pokemon.main.controller;

import main.java.opal.pokemon.entity.Player;
import main.java.opal.pokemon.main.MouseClick;
import main.java.opal.pokemon.main.model.Controls;
import main.java.opal.pokemon.main.view.OverWorldScreen;

public class OverWorldController extends ScreenController {

    private Player player;

    public OverWorldController(GameController gameController) {
        super(gameController);
        // create model and view - but skip model until actually needed
        player = new Player(gameController);
        screen = new OverWorldScreen(this);
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void update() {
        player.update();
        gameController.music.updateMusic(player); // NOTE: used for updating the music for the zone
        gameController.music.updateFade();

        for (int i = 0; i < gameController.npc.length; i++) {
            if (gameController.npc[i] != null) {
                gameController.npc[i].update();
            }
        }
    }

    @Override
    public void keyPressed(int keyCode) {
        Controls controls = gameController.getControls();

        if (controls.pokedexPressed) {
            gameController.openPokedex();
        } else if(controls.escapePressed) {
            gameController.gameState =  GameState.pauseState;
        }
    }

    @Override
    public void handleLeftClick(MouseClick mouseClick) {
        // pokedex icon
        if (mouseClick.insideBox(40, 696, 44, 58)) {
            gameController.openPokedex();
        }
    }
}
