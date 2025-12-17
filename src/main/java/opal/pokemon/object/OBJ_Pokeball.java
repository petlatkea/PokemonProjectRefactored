package main.java.opal.pokemon.object;

import main.java.opal.pokemon.main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Pokeball extends SuperObject {
    GamePanel gp;

    public OBJ_Pokeball(GamePanel gp) {
        this.gp = gp;

        name = "Pokeball";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/images/objects/pokeball.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
