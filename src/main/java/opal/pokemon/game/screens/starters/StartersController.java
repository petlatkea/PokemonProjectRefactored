package main.java.opal.pokemon.game.screens.starters;

import main.java.opal.pokemon.game.ViewSettings;
import main.java.opal.pokemon.game.input.MouseClick;
import main.java.opal.pokemon.game.GameController;
import main.java.opal.pokemon.game.GameState;
import main.java.opal.pokemon.game.screens.ScreenController;

public class StartersController extends ScreenController {

    public StartersController(GameController gameController) {
        super(gameController);
        // create model and view - but skip model until actually needed
        screen = new StartersScreen(this);
    }

    @Override
    public void update() {

    }

    @Override
    public void handleLeftClick(MouseClick mouseClick) {
        // Pressed on Turtwig
        if (mouseClick.insideBox(99, ((ViewSettings.screenHeight - 200) / 2) + 4, 192, 192)) {
            gameController.playerPokemon = 387;
            gameController.soundController.playSound(23);
            gameController.gameState = GameState.playState;
        }

        // Pressed on Chimchar
        if (mouseClick.insideBox(416, ((ViewSettings.screenHeight - 200) / 2) + 4, 192, 192)) {
            gameController.playerPokemon = 390;
            gameController.soundController.playSound(24);
            gameController.gameState = GameState.playState;
        }

        // Pressed on Chimchar
        if (mouseClick.insideBox(733, ((ViewSettings.screenHeight - 200) / 2) + 4, 192, 192)) {
            gameController.playerPokemon = 393;
            gameController.soundController.playSound(25);
            gameController.gameState = GameState.playState;
        }
    }
}
