package main.java.opal.pokemon.game.screens.overworld;

import main.java.opal.pokemon.game.ViewSettings;
import main.java.opal.pokemon.game.screens.overworld.characters.Entity;
import main.java.opal.pokemon.game.screens.overworld.characters.EntityModel;
import main.java.opal.pokemon.game.screens.overworld.objects.SuperObject;

public class Camera {
    public int left;
    public int top;
    public int right;
    public int bottom;

    /**
     * centers the camera on the specific entity / character
     * making it be the exact center of the screen.
     * <p>
     * Everything else is then drawn relative to the camera's left and top
     *
     * @param entity
     */
    public void centerOn(Entity entity) {
        left = entity.model.worldX - ViewSettings.screenWidth / 2 + ViewSettings.tileSize / 2;
        top = entity.model.worldY - ViewSettings.screenHeight / 2 + ViewSettings.tileSize / 2;

        right = left + ViewSettings.screenWidth;
        bottom = top + ViewSettings.screenHeight;
    }

    public boolean isInView(EntityModel model) {
        return model.worldX + ViewSettings.tileSize >= left &&
                model.worldX - ViewSettings.tileSize <= right &&
                model.worldY + ViewSettings.tileSize >= top &&
                model.worldY - ViewSettings.tileSize <= bottom;
    }
    // TODO: Join EntityModel and SuperObject into one graphicsObject model thingy ...
    public boolean isInView(SuperObject model) {
        return model.worldX + ViewSettings.tileSize >= left &&
                model.worldX - ViewSettings.tileSize <= right &&
                model.worldY + ViewSettings.tileSize >= top &&
                model.worldY - ViewSettings.tileSize <= bottom;
    }
}
