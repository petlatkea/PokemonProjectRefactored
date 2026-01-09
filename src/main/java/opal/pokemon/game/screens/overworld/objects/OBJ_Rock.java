package main.java.opal.pokemon.game.screens.overworld.objects;

import main.java.opal.pokemon.game.GameController;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Rock extends SuperObject {
    GameController gp;

    public OBJ_Rock(GameController gp) {
        this.gp = gp;

        name = "Rock";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/images/objects/rocksmash.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
