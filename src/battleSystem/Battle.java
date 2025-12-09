package battleSystem;

import main.GamePanel;
import pokedex.Pokemon;

import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Battle {
    private GamePanel gp;
    private BufferedImage battleBG;
    private BufferedImage playerGround;
    private BufferedImage enemyGround;

    private Pokemon playerPokemon;
    private Pokemon enemyPokemon;

    private String message = "A wild pokemon has appeared";

    private boolean playerTurn = true;
    private boolean battleFinished = false;

    public Battle(GamePanel gp, Pokemon playerPokemon, Pokemon enemyPokemon) {
        this.gp = gp;
        this.playerPokemon = playerPokemon;
        this.enemyPokemon = enemyPokemon;

        try {
            battleBG = ImageIO.read(getClass().getResourceAsStream("/resources/battle/battleBG.png"));
            playerGround = ImageIO.read(getClass().getResourceAsStream("/resources/battle/playerGround.png"));
            enemyGround = ImageIO.read(getClass().getResourceAsStream("/resources/battle/enemyGround.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(){

    }

    public void draw(Graphics2D g2){
        // Clear background
        g2.setColor(new Color(200,230,255));
        g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);

        // Draw arena
        g2.drawImage(battleBG, 0,0, gp.screenWidth, gp.screenHeight, null);
        g2.drawImage(playerGround, 50,460, 500, 100, null);
        g2.drawImage(enemyGround, 700,280, 300, 80, null);

        // Draw placeholder Pokémon (just text for now)
        g2.setColor(Color.BLACK);
        g2.drawString("Your Pokémon: " + (playerPokemon != null ? playerPokemon.getName() : "None"), 100, 430);
        g2.drawString("Enemy Pokémon: " + (enemyPokemon != null ? enemyPokemon.getName() : "None"), 620, 180);

        // Simple HP text (adjust according to your Pokemon class)
        if (playerPokemon != null) {
            g2.drawString("HP: " + "playerPokemon.getCurrentHp()" + "/" + "playerPokemon.getMaxHp()", 100, 450);
        }
        if (enemyPokemon != null) {
            g2.drawString("HP: " + "enemyPokemon.getCurrentHp()" + "/" + "enemyPokemon.getMaxHp()", 620, 200);
        }

        // Message box at bottom
        g2.setColor(new Color(250, 250, 250));
        g2.fillRoundRect(40, 560, gp.screenWidth - 80, 140, 20, 20);
        g2.setColor(Color.BLACK);
        g2.drawRoundRect(40, 560, gp.screenWidth - 80, 140, 20, 20);

        g2.drawString(message, 60, 590);

        // Placeholder options
        g2.drawString("Fight   Bag   Pokémon   Run", 60, 630);
    }

    public void endBattle (){
        gp.gameState = GamePanel.statePlay;
        gp.battle = null;
    }
}
