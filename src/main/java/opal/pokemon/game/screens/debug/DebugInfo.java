package main.java.opal.pokemon.game.screens.debug;

/**
 * DebugInfo is the 'model' for the DebugController
 * - it contains all the information that the DebugScreen can display,
 *   and the DebugController has the job of gathering the information
 *   from everywhere else in the game, and storing them here in a format
 *   suitable for display.
 * <p>
 *  This means that the DebugScreen should NOT read info directly from
 *  anywhere else!
 */
public class DebugInfo {
    public boolean enabled = false;
    public PlayerInfo player = new PlayerInfo();

    class PlayerInfo {
        public String x;
        public String y;
        public String direction;
        public String collision;
        public String grass;
    }
}
