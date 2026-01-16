package main.java.opal.pokemon.game.screens.debug;

import main.java.opal.pokemon.game.screens.overworld.Camera;

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
    private final DebugController controller;

    public boolean enabled = false;
    public PlayerInfo player = new PlayerInfo();
    public Camera camera;
    public GridInfo grid;

    public DebugInfo(DebugController controller) {
        this.controller = controller;
        grid = new GridInfo();
    }

    class PlayerInfo {
        public String x, y;
        public String col, row;
        public String direction;
        public String collision;
        public String grass;

        public String moving;
        public String pixelCounter;
    }

    class GridInfo {
        public CheckBox grid = new CheckBox("grid");
        public CheckBox coords = new CheckBox("coords");
        public CheckBox tileType = new CheckBox("tile type");

        public GridInfo() {
            controller.registerCheckBox(grid);
            controller.registerCheckBox(coords);
            controller.registerCheckBox(tileType);
        }
    }
}
