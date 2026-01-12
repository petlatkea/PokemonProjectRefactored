package main.java.opal.pokemon.game.screens.debug;

import main.java.opal.pokemon.game.GameController;
import main.java.opal.pokemon.game.screens.ScreenController;
import main.java.opal.pokemon.game.screens.overworld.characters.player.Player;
import main.java.opal.pokemon.game.screens.overworld.characters.player.PlayerView;

import java.awt.event.KeyEvent;

public class DebugController extends ScreenController {

    private final DebugInfo debuginfo;

    public DebugController(GameController gameController) {
        super(gameController);
        debuginfo = new DebugInfo();
        screen = new DebugScreen(this, debuginfo);
    }

    @Override
    public void update() {
        // only update model if the display is enabled - to save a bit of time
        if(debuginfo.enabled) {
            updatePlayerInfo();
        }
    }

    private void updatePlayerInfo() {
        Player player = gameController.getPlayer();
        debuginfo.player.x = "" + player.model.worldX;
        debuginfo.player.y = "" + player.model.worldY;
        debuginfo.player.direction = String.valueOf(player.model.direction);
        debuginfo.player.collision = player.model.collisionOn?"on":"off";
        debuginfo.player.grass = player.model.isGrassOn?"on":"off";

        debuginfo.player.moving = player.model.moving?"yes":"no";
        debuginfo.player.pixelCounter = "" + ((PlayerView)player.view).pixelCounter;
    }

    private void toggleEnabled() {
        debuginfo.enabled = !debuginfo.enabled;
        System.out.println((debuginfo.enabled?"Displaying":"No longer displaying") + " debug-info on screen");
    }

    private boolean allowToggling = true;

    @Override
    public void keyPressed(int keyCode) {
        if(allowToggling && keyCode == KeyEvent.VK_F4) {
            // toggle debug-info
            allowToggling = false;
            toggleEnabled();
        }
    }

    @Override
    public void keyReleased(int keyCode) {
        if(keyCode == KeyEvent.VK_F4) {
            allowToggling = true;
        }
    }
}
