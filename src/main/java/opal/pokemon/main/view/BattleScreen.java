package main.java.opal.pokemon.main.view;

import main.java.opal.pokemon.battleSystem.Battle;
import main.java.opal.pokemon.main.controller.BattleController;
import main.java.opal.pokemon.pokedex.Pokemon;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class BattleScreen extends Screen {

    // === Pictures ===
    private BufferedImage battleBG, playerGround, enemyGround, myPokemonPic, enemyPokemonPic, playerInfoPanel, enemyInfoPanel, dialogBox;

    // Font
    private final Font standardFont = new Font("Dialog", Font.PLAIN, 12);

    public BattleScreen(BattleController controller) {
        super(controller);
    }

    @Override
    public void init() {
        loadImages();
    }

    public void loadImages() {
        battleBG = setup("/images/battle/battleBG");
        playerGround = setup("/images/battle/playerGround");
        enemyGround = setup("/images/battle/enemyGround");
        playerInfoPanel = setup("/images/battle/myHpBar");
        enemyInfoPanel = setup("/images/battle/enemyHpBar");
        dialogBox = setup("/images/ui/dialogueBox");
    }

    public void setPlayerAndEnemyGraphics(Pokemon playerPokemon, Pokemon enemyPokemon) {
        try {
            String myPokemonURL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/" + playerPokemon.getId() + ".png";
            myPokemonPic = ImageIO.read(new URI(myPokemonURL).toURL());
            String enemyPokeURL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + enemyPokemon.getId() + ".png";
            enemyPokemonPic = ImageIO.read(new URI(enemyPokeURL).toURL());
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void drawScreen(Graphics2D g2) {
        // convenient short-cut to get the battle - TODO: Make more integrated into controller and model!
        Battle battle = ((BattleController)controller).getBattle();

        // Set Battle font to DIALOG - the standard font, and NOT the custom Pokemon font used by other screens
        g2.setFont(standardFont);

        // Clear background
        g2.setColor(new Color(200, 230, 255));
        g2.fillRect(0, 0, controller.getGameController().screenWidth, controller.getGameController().screenHeight);

        // Draw arena
        g2.drawImage(battleBG, 0, 0, controller.getGameController().screenWidth, controller.getGameController().screenHeight, null);
        g2.drawImage(playerGround, 50, 460, 500, 100, null);
        g2.drawImage(enemyGround, 700, 280, 300, 80, null);

        // Place Pokémon
        drawImageBottomCenterScaled(g2, myPokemonPic, 275, 730, 500, 500);
        if (battle.enemyPokemon.getId() == 448) {
            drawImageBottomCenterScaled(g2, enemyPokemonPic, 845, 375, 250, 250);
        } else{
            drawImageBottomCenterScaled(g2, enemyPokemonPic, 845, 400, 250, 250);
        }
        // Enemy info box (top-left)
        g2.drawImage(enemyInfoPanel, 0, 160, 500, 100, null);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20));
        g2.setColor(Color.WHITE);

        g2.drawString((battle.enemyPokemon != null ? battle.enemyPokemon.getName().toUpperCase() : "Enemy"), 50, 200);
        g2.drawString("??", 350, 202);

        // enemy HP bar in that box
        double eRatio = (double) battle.enemyCurrentHp / Math.max(1, battle.enemyMaxHp);
        int eBarX = 206, eBarY = 232, eBarW = 195, eBarH = 5;
        g2.setColor(Color.GREEN);
        g2.fillRect(eBarX, eBarY, (int) (eBarW * eRatio), eBarH);
        g2.setColor(Color.BLACK);
        g2.drawRect(eBarX, eBarY, eBarW, eBarH);

        // Player info box (lower-right)
        g2.drawImage(playerInfoPanel, 530, 390, 500, 105, null);

        g2.setColor(Color.WHITE);
        g2.drawString((battle.playerPokemon != null ? battle.playerPokemon.getName().toUpperCase() : "You"), 650, 425);
        g2.drawString("1", 940, 428);

        double pRatio = (double) battle.playerCurrentHp / Math.max(1, battle.playerMaxHp);
        int pBarX = 809, pBarY = 449, pBarW = 184, pBarH = 5;
        g2.drawString(String.valueOf(battle.playerCurrentHp), 840, 477);
        g2.drawString(String.valueOf(battle.playerMaxHp), 910, 477);
        g2.setColor(Color.GREEN);
        g2.fillRect(pBarX, pBarY, (int) (pBarW * pRatio), pBarH);
        g2.setColor(Color.BLACK);
        g2.drawRect(pBarX, pBarY, pBarW, pBarH);

        // Message box bottom-left
        g2.drawImage(dialogBox, 0, 560, controller.getGameController().screenWidth, 145, null);

        // Message Text
        g2.setColor(Color.BLACK);

        drawWrappedText(g2, battle.message, 50, 620, 550, 25);

        // MAIN MENU / MOVE UI bottom-right
        int menuRightX = 630;
        int menuRightY = 580;
        int buttonW = 150;
        int buttonH = 50;
        int gapX = 5;
        int gapY = 5;

        // Colors for each button
        Color fightColor = new Color(220, 80, 80);   // red
        Color bagColor = new Color(200, 140, 60);     // brown/yellow
        Color pokeColor = new Color(80, 160, 120);   // green
        Color runColor = new Color(60, 120, 200);   // blue

        // button rectangles
        Rectangle btnFight = new Rectangle(menuRightX, menuRightY, buttonW, buttonH);
        Rectangle btnBag = new Rectangle(menuRightX + buttonW + gapX, menuRightY, buttonW, buttonH);
        Rectangle btnPoke = new Rectangle(menuRightX, menuRightY + buttonH + gapY, buttonW, buttonH);
        Rectangle btnRun = new Rectangle(menuRightX + buttonW + gapX, menuRightY + buttonH + gapY, buttonW, buttonH);

        // Draw either the pokemonproject.main buttons or the move grid depending on state
        if (battle.menuState == Battle.MenuState.mainMenu) {
            // FIGHT
            g2.setColor(fightColor);
            g2.fillRoundRect(btnFight.x, btnFight.y, btnFight.width, btnFight.height, 3, 3);
            g2.setColor(Color.BLACK);
            g2.drawRoundRect(btnFight.x, btnFight.y, btnFight.width, btnFight.height, 3, 3);
            g2.drawString("FIGHT", btnFight.x + 24, btnFight.y + 32);

            // BAG
            g2.setColor(bagColor);
            g2.fillRoundRect(btnBag.x, btnBag.y, btnBag.width, btnBag.height, 3, 3);
            g2.setColor(Color.BLACK);
            g2.drawRoundRect(btnBag.x, btnBag.y, btnBag.width, btnBag.height, 3, 3);
            g2.drawString("BAG", btnBag.x + 36, btnBag.y + 32);

            // POKéMON
            g2.setColor(pokeColor);
            g2.fillRoundRect(btnPoke.x, btnPoke.y, btnPoke.width, btnPoke.height, 3, 3);
            g2.setColor(Color.BLACK);
            g2.drawRoundRect(btnPoke.x, btnPoke.y, btnPoke.width, btnPoke.height, 3, 3);
            g2.drawString("POKÉMON", btnPoke.x + 12, btnPoke.y + 32);

            // RUN
            g2.setColor(runColor);
            g2.fillRoundRect(btnRun.x, btnRun.y, btnRun.width, btnRun.height, 3, 3);
            g2.setColor(Color.BLACK);
            g2.drawRoundRect(btnRun.x, btnRun.y, btnRun.width, btnRun.height, 3, 3);
            g2.drawString("RUN", btnRun.x + 42, btnRun.y + 32);

        } else if (battle.menuState == Battle.MenuState.fightMenu) {
            // Draw the 4 move boxes and highlight on mouse hover (optional)
            for (int i = 0; i < 4; i++) {
                int col = i % 2;
                int row = i / 2;
                int x = menuRightX + col * (buttonW + gapX);
                int y = menuRightY + row * (buttonH + gapY);

                g2.setColor(Color.BLACK);
                g2.drawRoundRect(x, y, buttonW, buttonH, 8, 8);

                String name = "-";
                if (i < battle.playerMoves.length && battle.playerMoves[i] != null) name = battle.playerMoves[i].name;
                g2.drawString(name, x + 10, y + 30);
            }
        }
    }

    private void drawImageBottomCenterScaled(Graphics2D g2, BufferedImage img, int centerX, int bottomY, int width, int height) {
        if (img == null) return;

        int x = centerX - width / 2;
        int y = bottomY - height;

        g2.drawImage(img, x, y, width, height, null);
    }

}
