package pokedex;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class Pokedex {
    public int worldX, worldY = 0;

    public BufferedImage pokedexBoy, pokedexGirl, pokemonSprite;

    GamePanel gp;
    KeyHandler keyH;
    Pokemon pokemon = new Pokemon();


    final int originalPokedexWidth = 256;  // 256*192 px
    final int originalPokedexHeight = 192;

    public final int pokedexSizeWidth = originalPokedexWidth * 4; // 1024*768 px
    public final int pokedexSizeHeight = originalPokedexHeight * 4;

    public Pokedex(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        getPlayerImage();
    }

    public void getPlayerImage() {
        try {
            pokedexBoy = ImageIO.read(getClass().getResourceAsStream("/pokedexSprites/boy.png"));
            pokedexGirl = ImageIO.read(getClass().getResourceAsStream("/pokedexSprites/girl.png"));

            String imageUrl = pokemon.getPokemonSprite();
            URL url;
            try {
                URI uri = new URI("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/51.png");
                url = uri.toURL();
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }

            pokemonSprite = loadImageFromUrl(url);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void drawPokedexGirl(Graphics2D g2) {
        BufferedImage image = pokedexGirl;

        g2.drawImage(image, worldX, worldY, pokedexSizeWidth, pokedexSizeHeight, null);
    }

    public void drawPokedexBoy(Graphics2D g2) {
        BufferedImage image = pokedexBoy;

        g2.drawImage(image, worldX, worldY, pokedexSizeWidth, pokedexSizeHeight, null);
    }


    public void drawPokedexSprite(Graphics2D g2, int x, int y, int width, int height) {
        BufferedImage image = pokemonSprite;

        g2.drawImage(image, x, y, width*4, height*4, null);



    }

    private BufferedImage loadImageFromUrl(URL url) {
        try {
            // 1. Create a URL object from the string


            // 2. Use ImageIO.read(URL) to fetch and decode the image
            // This method handles the networking (open connection, get input stream)
            BufferedImage bufferedImage = ImageIO.read(url);

            // ImageIO.read() returns null if no registered ImageReader can decode the stream
            if (bufferedImage == null) {
                System.err.println("ImageIO could not decode image format from the URL.");
            }

            return bufferedImage;

        } catch (IOException e) {
            // Catches MalformedURLException (if the URL string is invalid)
            // and other IOExceptions (network errors, file not found, etc.)
            System.err.println("Error loading image from URL: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

}


