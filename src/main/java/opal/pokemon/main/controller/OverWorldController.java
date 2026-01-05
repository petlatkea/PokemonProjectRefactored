package main.java.opal.pokemon.main.controller;

import main.java.opal.pokemon.entity.Player;
import main.java.opal.pokemon.main.MouseClick;
import main.java.opal.pokemon.main.view.OverWorldScreen;

public class OverWorldController extends ScreenController {

    private Player player;

    public OverWorldController(GameController gameController) {
        super(gameController);
        // create model and view - but skip model until actually needed
        screen = new OverWorldScreen(this);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void update() {
        player.update();
        gameController.music.updateMusic();
        gameController.music.updateFade();

        for (int i = 0; i < gameController.npc.length; i++) {
            if (gameController.npc[i] != null) {
                gameController.npc[i].update();
            }
        }
    }

    @Override
    public void handleLeftClick(MouseClick mouseClick) {
        // pokedex icon
        if (mouseClick.insideBox(40, 696, 44, 58)) {
            gameController.gameState = GameState.pokedexState;
        }
    }
}
