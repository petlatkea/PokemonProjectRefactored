package battleSystem;

import main.GamePanel;
import pokedex.EntryStats;
import pokedex.Pokemon;

import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

public class Battle {

    // === Pictures ===
    private GamePanel gp;
    private BufferedImage battleBG;
    private BufferedImage playerGround;
    private BufferedImage enemyGround;
    private BufferedImage myPokemonPic;
    private BufferedImage enemyPokemonPic;

    // === Pokemons ===
    private Pokemon playerPokemon;
    private Pokemon enemyPokemon;

    // === Pokemon HP ===
    private int playerMaxHp;
    private int playerCurrentHp;
    private int enemyMaxHp;
    private int enemyCurrentHp;

    // === Pokemon Moves ===
    private Moves[] playerMoves;
    private Moves[] enemyMoves;
    private int selectedMoveIndex = 0;

    // === Turn Base Control ===
    private boolean isPlayerTurn = true;
    private boolean isBattleFinished = false;

    // Messages
    private String message = "";

    private int messageTimer = 0;
    private int messageDuration = 30;

    private Random rng = new Random();

    // === UI Menu ===
    private final int mainMenu = 0;
    private final int fightMenu = 1;
    private final int bagMenu = 2;
    private final int pokeMenu = 3;
    private final int runAway = 4;
    private int menuState = mainMenu;


    public Battle(GamePanel gp, Pokemon playerPokemon, Pokemon enemyPokemon) {
        this.gp = gp;
        this.playerPokemon = playerPokemon;
        this.enemyPokemon = enemyPokemon;

        // === HP INIT ===
        playerMaxHp = getBaseStat(playerPokemon, "hp");
        playerCurrentHp = playerMaxHp;
        enemyMaxHp = getBaseStat(enemyPokemon, "hp");
        enemyCurrentHp = enemyMaxHp;

        // === HARCODED MOVES === May change later
        playerMoves = new Moves[] {
                new Moves("Tackle", 40),
                new Moves("Quick Attack", 40),
                new Moves("Thunderbolt", 90),
                new Moves("Growl", 0)
        };

        enemyMoves = new Moves[] {
                new Moves("Poison Sting", 15),
                new Moves("Fury Attack", 25),
                new Moves("Twineedle", 40),
                new Moves("Bug Bite", 60)
        };

        // === LOAD GRAPHICS ===
        try {
            battleBG = ImageIO.read(getClass().getResourceAsStream("/resources/battle/battleBG.png"));
            playerGround = ImageIO.read(getClass().getResourceAsStream("/resources/battle/playerGround.png"));
            enemyGround = ImageIO.read(getClass().getResourceAsStream("/resources/battle/enemyGround.png"));

            //String myPokeURL = playerPokemon.sprites.front_default;
            String myPokeURL1 = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/25.png";
            myPokemonPic = ImageIO.read(new URL(myPokeURL1));
            //String enemyPokeURL = enemyPokemon.sprites.front_default;
            String enemyPokeURL1 = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/11.png";
            enemyPokemonPic = ImageIO.read(new URL(enemyPokeURL1));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void mouseClick(){
        if (!gp.leftClick.clicked) return;

        int menuRightX = gp.screenWidth-320;
        int menuRightY = gp.screenHeight-220;
        int buttonW = 120;
        int buttonH = 50;
        int gapX = 5;
        int gapY = 5;

        // === Main Menu Buttons ===
        Rectangle fightButton = new Rectangle(menuRightX, menuRightY, buttonW, buttonH);
        Rectangle bagButton = new Rectangle(menuRightX + buttonW + gapX, menuRightY, buttonW, buttonH);
        Rectangle pokeButton = new Rectangle(menuRightX, menuRightY + buttonH + gapY, buttonW, buttonH);
        Rectangle runButton = new Rectangle(menuRightX + buttonW + gapX, menuRightY + buttonH + gapY, buttonW, buttonH);

        Rectangle[] moveBoxes = new Rectangle[4];
        moveBoxes[0] = new Rectangle(menuRightX, menuRightY, buttonW, buttonH);
        moveBoxes[1] = new Rectangle(menuRightX + buttonW + gapX, menuRightY, buttonW, buttonH);
        moveBoxes[2] = new Rectangle(menuRightX, menuRightY + buttonH + gapY, buttonW, buttonH);
        moveBoxes[3] = new Rectangle(menuRightX + buttonW + gapX, menuRightY + buttonH + gapY, buttonW, buttonH);

        if (menuState == mainMenu){

            if(gp.leftClick.mousePressedBox(fightButton.x, fightButton.y, fightButton.width, fightButton.height)){
                menuState = fightMenu;
                gp.leftClick.clicked = false;
                return;
            }

            if(gp.leftClick.mousePressedBox(bagButton.x, bagButton.y, bagButton.width, bagButton.height)){
                menuState = bagMenu;
                message = "You opened your bag... (not implemented)";
                messageTimer = messageDuration;
                gp.leftClick.clicked = false;
                return;
            }

            if(gp.leftClick.mousePressedBox(pokeButton.x, pokeButton.y, pokeButton.width, pokeButton.height)){
                menuState = pokeMenu;
                message = "You look at your Pokemons... (not implemented)";
                messageTimer = messageDuration;
                gp.leftClick.clicked = false;
                return;
            }

            if(gp.leftClick.mousePressedBox(runButton.x, runButton.y, runButton.width, runButton.height)){
                menuState = runAway;
                message = "You ran away safely!";
                messageTimer = messageDuration;
                isBattleFinished = true;
                gp.leftClick.clicked = false;
                return;
            }
        } else if (menuState == fightMenu){

            for (int i = 0; i < moveBoxes.length; i++){
                Rectangle r = moveBoxes[i];

                if (gp.leftClick.mousePressedBox(r.x, r.y, r.width, r.height)){
                    if (i < playerMoves.length){
                        usePlayerMove(i);
                    } else {
                        message = "No move in that slot.";
                        messageTimer = messageDuration;
                        return;
                    }
                }

                if (gp.leftClick. mousePressedBox(menuRightX - 40, menuRightY - 40, (buttonW+gapX)*2 + 80, (buttonH+gapY)*2 + 80));{
                    menuState = mainMenu;
                    gp.leftClick.clicked = false;
                    return;
                }
            }

        } else {
            message = "Menu not implemented";
            messageTimer = messageDuration;
            menuState = mainMenu;
            gp.leftClick.clicked = false;
            return;
        }

    gp.leftClick.clicked = false;
    }

    private void usePlayerMove(int moveIndex){
        if (moveIndex < 0 || moveIndex >= playerMoves.length) return;
        Moves move = playerMoves[moveIndex];
        if (move == null) return;

        if (move.power <= 0){
            message = playerPokemon.getName() + " used " + move.name + "! It did no damage...";

        } else {
            int damage = calculateDamage(move.power);
            enemyCurrentHp = Math.max(0, enemyCurrentHp - damage);
            message = playerPokemon.getName() + " used " + move.name + " and dealt " + damage + "HP!";
        }


        if (enemyCurrentHp <= 0) {
            message = "Enemy " + enemyPokemon.getName() + " fainted!";
            isBattleFinished = true;
            messageTimer = messageDuration;
        }

        // Should make it enemy turn;
        isPlayerTurn = false;
    }

    private void enemyTurn(){
        if (enemyMoves == null){
            message = "Enemy has no moves...";
            isBattleFinished = true;
            messageTimer = messageDuration;
            return;
        }

        Moves move = enemyMoves[rng.nextInt(enemyMoves.length)];
        if (move.power <=0){
            message = "Enemy " + enemyPokemon.getName() + " used " + move.name + "! It did no damage...";
        } else {
            int damage = calculateDamage(move.power);
            playerCurrentHp = Math.max(0, playerCurrentHp - damage);
            message = "Enemy " + enemyPokemon.getName() + " used " + move.name + " and dealt " + damage + "HP!";
        }

        if (playerCurrentHp <= 0){
            message = "Your " + playerPokemon.getName() + " fainted!";
            isBattleFinished = true;
            messageTimer = messageDuration;
            return;
        }

        isPlayerTurn = true;
    }

    private int calculateDamage(int basePower){
        // lower longer battle
        double scale = 0.10;
        return (int) Math.ceil(basePower * scale);
    }

    public void update(){
        if (isBattleFinished == true) {
            endBattle();
        }

        if (messageTimer > 0) {
            messageTimer--;
            return;
        }

        if (isPlayerTurn == true){
            mouseClick();
        } else {
            enemyTurn();
        }

    }

//    public void draw(Graphics2D g2) {
//        // Clear background
//        g2.setColor(new Color(200, 230, 255));
//        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
//
//        // Draw arena
//        g2.drawImage(battleBG, 0, 0, gp.screenWidth, gp.screenHeight, null);
//        g2.drawImage(playerGround, 50, 460, 500, 100, null);
//        g2.drawImage(enemyGround, 700, 280, 300, 80, null);
//
//        // Draw placeholder Pokémon
//        g2.drawImage(myPokemonPic, 50, 250, 500, 500, null);
//        g2.drawImage(enemyPokemonPic, 720, 160, 250, 250, null);
//
//        g2.setColor(Color.BLACK);
//        //g2.drawString("Your Pokémon: " + (playerPokemon != null ? playerPokemon.getName() : "None"), 100, 430);
//
//        //g2.drawString("Enemy Pokémon: " + (enemyPokemon != null ? enemyPokemon.getName() : "None"), 620, 180);
//
//        // player hp text
//        if (playerPokemon != null) {
//            g2.setColor(Color.BLACK);
//            g2.drawString(playerPokemon.getName().toUpperCase(), 680, 450);
//            g2.drawString("HP: " + playerCurrentHp + "/" + playerMaxHp, 680, 490);
//
//
//            double ratio = (double) playerCurrentHp / playerMaxHp;
//            int barWidth = 200;
//            int barHeight = 15;
//            int barX = 680;
//            int barY = 460;
//
//            g2.setColor(Color.GRAY);
//            g2.fillRect(barX, barY, barWidth, barHeight);
//            g2.setColor(Color.GREEN);
//            g2.fillRect(barX, barY, (int) (barWidth * ratio), barHeight);
//            g2.setColor(Color.BLACK);
//            g2.drawRect(barX, barY, barWidth, barHeight);
//        }
//
//        if (enemyPokemon != null) {
//            g2.setColor(Color.BLACK);
//            g2.drawString(enemyPokemon.getName().toUpperCase(), 530, 180);
//            g2.drawString("HP: " + enemyCurrentHp + "/" + enemyMaxHp, 530, 220);
//
//            double ratio = (double) enemyCurrentHp / enemyMaxHp;
//            int barWidth = 200;
//            int barHeight = 15;
//            int barX = 530;
//            int barY = 190;
//
//            g2.setColor(Color.GRAY);
//            g2.fillRect(barX, barY, barWidth, barHeight);
//            g2.setColor(Color.GREEN);
//            g2.fillRect(barX, barY, (int) (barWidth * ratio), barHeight);
//            g2.setColor(Color.BLACK);
//            g2.drawRect(barX, barY, barWidth, barHeight);
//        }
//
//
//        // Message box at bottom
//        g2.setColor(new Color(250, 250, 250));
//        g2.fillRoundRect(40, 560, gp.screenWidth - 80, 140, 20, 20);
//        g2.setColor(Color.BLACK);
//        g2.drawRoundRect(40, 560, gp.screenWidth - 80, 140, 20, 20);
//
//        // g2.drawString(message, 60, 590);
//
//        // Placeholder options
//        g2.setFont(g2.getFont().deriveFont(18f));
//
//        int baseX = 60;
//        int baseY = 610;
//        int boxWidth = 260;
//        int boxHeight = 35;
//        int gapX = 280;
//        int gapY = 45;
//
//        if (playerMoves != null) {
//            for (int i = 0; i < playerMoves.length; i++) {
//                int row = i / 2; // 0 or 1
//                int col = i % 2; // 0 or 1
//
//                int x = baseX + col * gapX;
//                int y = baseY + row * gapY;
//
//                // Highlight selected move
//                if (i == selectedMoveIndex) {
//                    g2.setColor(new Color(220, 220, 255));
//                    g2.fillRoundRect(x - 10, y - 25, boxWidth, boxHeight, 10, 10);
//                    g2.setColor(Color.BLACK);
//                    g2.drawRoundRect(x - 10, y - 25, boxWidth, boxHeight, 10, 10);
//                }
//
//                g2.setColor(Color.BLACK);
//                String moveName = (playerMoves[i] != null) ? playerMoves[i].name : "-";
//                g2.drawString(moveName, x, y);
//            }
//        }
//    }


    public void draw(Graphics2D g2) {
        // Clear background
        g2.setColor(new Color(200, 230, 255));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // Draw arena
        g2.drawImage(battleBG, 0, 0, gp.screenWidth, gp.screenHeight, null);
        g2.drawImage(playerGround, 50, 460, 500, 100, null);
        g2.drawImage(enemyGround, 700, 280, 300, 80, null);

        // Draw placeholder Pokémon
        g2.drawImage(myPokemonPic, 50, 250, 500, 500, null);
        g2.drawImage(enemyPokemonPic, 720, 160, 250, 250, null);

        // Enemy info box (top-left)
        g2.setColor(new Color(240,240,240));
        g2.fillRoundRect(40, 30, 380, 68, 12, 12);
        g2.setColor(Color.BLACK);
        g2.drawRoundRect(40, 30, 380, 68, 12, 12);

        g2.drawString((enemyPokemon != null ? enemyPokemon.getName() : "Enemy"), 60, 50);
        g2.drawString("Lv. ??", 320, 50);

        // enemy HP bar in that box
        g2.drawString("HP", 60, 70);
        double eRatio = (double) enemyCurrentHp / Math.max(1, enemyMaxHp);
        int eBarX = 100, eBarY = 56, eBarW = 300, eBarH = 10;
        g2.setColor(Color.DARK_GRAY); g2.fillRect(eBarX, eBarY, eBarW, eBarH);
        g2.setColor(Color.GREEN); g2.fillRect(eBarX, eBarY, (int) (eBarW * eRatio), eBarH);
        g2.setColor(Color.BLACK); g2.drawRect(eBarX, eBarY, eBarW, eBarH);

        // Player info box (lower-right)
        g2.setColor(new Color(240,240,240));
        g2.fillRoundRect(540, 360, 420, 120, 12, 12);
        g2.setColor(Color.BLACK);
        g2.drawRoundRect(540, 360, 420, 120, 12, 12);

        g2.drawString((playerPokemon != null ? playerPokemon.getName() : "You"), 560, 385);
        g2.drawString("Lv. ??", 900, 385);

        g2.drawString("HP", 560, 405);
        double pRatio = (double) playerCurrentHp / Math.max(1, playerMaxHp);
        int pBarX = 600, pBarY = 392, pBarW = 320, pBarH = 10;
        g2.setColor(Color.DARK_GRAY); g2.fillRect(pBarX, pBarY, pBarW, pBarH);
        g2.setColor(Color.GREEN); g2.fillRect(pBarX, pBarY, (int) (pBarW * pRatio), pBarH);
        g2.setColor(Color.BLACK); g2.drawRect(pBarX, pBarY, pBarW, pBarH);

        // Message box bottom-left
        g2.setColor(new Color(250,250,250));
        g2.fillRoundRect(40, 560, gp.screenWidth - 420, 140, 16, 16);
        g2.setColor(Color.BLACK);
        g2.drawRoundRect(40, 560, gp.screenWidth - 420, 140, 16, 16);
        g2.drawString(message, 60, 590);

        // MAIN MENU / MOVE UI bottom-right (mimic screenshot: 4 button grid)
        int menuRightX = gp.screenWidth - 320;
        int menuRightY = gp.screenHeight - 220;
        int buttonW = 120;
        int buttonH = 48;
        int gapX = 8;
        int gapY = 8;

        // Draw main frame
        g2.setColor(new Color(240,240,240));
        g2.fillRoundRect(menuRightX - 8, menuRightY - 8, (buttonW + gapX) * 2 + 16, (buttonH + gapY) * 2 + 16, 12, 12);
        g2.setColor(Color.BLACK);
        g2.drawRoundRect(menuRightX - 8, menuRightY - 8, (buttonW + gapX) * 2 + 16, (buttonH + gapY) * 2 + 16, 12, 12);

        // Colors for each button similar to the screenshot
        Color fightColor = new Color(220, 80, 80);   // red/pink
        Color bagColor   = new Color(200,140,60);   // brownish
        Color pokeColor  = new Color(80,160,120);   // green
        Color runColor   = new Color(60,120,200);   // blue

        // button rectangles
        Rectangle btnFight = new Rectangle(menuRightX, menuRightY, buttonW, buttonH);
        Rectangle btnBag   = new Rectangle(menuRightX + buttonW + gapX, menuRightY, buttonW, buttonH);
        Rectangle btnPoke  = new Rectangle(menuRightX, menuRightY + buttonH + gapY, buttonW, buttonH);
        Rectangle btnRun   = new Rectangle(menuRightX + buttonW + gapX, menuRightY + buttonH + gapY, buttonW, buttonH);

        // Draw either the main buttons or the move grid depending on state
        if (menuState == mainMenu) {
            // FIGHT
            g2.setColor(fightColor); g2.fillRoundRect(btnFight.x, btnFight.y, btnFight.width, btnFight.height, 8, 8);
            g2.setColor(Color.BLACK); g2.drawRoundRect(btnFight.x, btnFight.y, btnFight.width, btnFight.height, 8, 8);
            g2.drawString("FIGHT", btnFight.x + 24, btnFight.y + 32);

            // BAG
            g2.setColor(bagColor); g2.fillRoundRect(btnBag.x, btnBag.y, btnBag.width, btnBag.height, 8, 8);
            g2.setColor(Color.BLACK); g2.drawRoundRect(btnBag.x, btnBag.y, btnBag.width, btnBag.height, 8, 8);
            g2.drawString("BAG", btnBag.x + 36, btnBag.y + 32);

            // POKéMON
            g2.setColor(pokeColor); g2.fillRoundRect(btnPoke.x, btnPoke.y, btnPoke.width, btnPoke.height, 8, 8);
            g2.setColor(Color.BLACK); g2.drawRoundRect(btnPoke.x, btnPoke.y, btnPoke.width, btnPoke.height, 8, 8);
            g2.drawString("POKÉMON", btnPoke.x + 12, btnPoke.y + 32);

            // RUN
            g2.setColor(runColor); g2.fillRoundRect(btnRun.x, btnRun.y, btnRun.width, btnRun.height, 8, 8);
            g2.setColor(Color.BLACK); g2.drawRoundRect(btnRun.x, btnRun.y, btnRun.width, btnRun.height, 8, 8);
            g2.drawString("RUN", btnRun.x + 42, btnRun.y + 32);

        } else if (menuState == fightMenu) {
            // Draw the 4 move boxes and highlight on mouse hover (optional)
            for (int i = 0; i < 4; i++) {
                int col = i % 2;
                int row = i / 2;
                int x = menuRightX + col * (buttonW + gapX);
                int y = menuRightY + row * (buttonH + gapY);

                // hovered effect: if mouse inside, slightly lighter
                if (gp.leftClick.mousePressedBox(x, y, buttonW, buttonH)) {
                    g2.setColor(new Color(240, 240, 255));
                    g2.fillRoundRect(x, y, buttonW, buttonH, 8, 8);
                } else {
                    g2.setColor(new Color(255, 255, 255));
                    g2.fillRoundRect(x, y, buttonW, buttonH, 8, 8);
                }

                g2.setColor(Color.BLACK);
                g2.drawRoundRect(x, y, buttonW, buttonH, 8, 8);

                String name = "-";
                if (i < playerMoves.length && playerMoves[i] != null) name = playerMoves[i].name;
                g2.drawString(name, x + 10, y + 30);
            }
        }
    }


    private int getBaseStat(Pokemon p, String statName) {
        if (p == null || p.stats == null) return 10;

        for (EntryStats entry : p.stats) {
            if (entry.stat != null &&
                    entry.stat.name != null &&
                    entry.stat.name.equalsIgnoreCase(statName)) {
                return entry.base_stat;
            }
        }
        return 10;
    }

    public void endBattle (){
        gp.gameState = GamePanel.statePlay;
        gp.battle = null;
    }
}
