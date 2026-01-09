package main.java.opal.pokemon.game.screens.battle;

import main.java.opal.pokemon.game.input.ClickHandler;
import main.java.opal.pokemon.game.GameController;
import main.java.opal.pokemon.game.GameState;
import main.java.opal.pokemon.game.screens.pokedex.stats.EntryStats;
import main.java.opal.pokemon.game.screens.pokedex.Pokemon;

import java.awt.*;
import java.util.Objects;
import java.util.Random;

public class Battle {

    GameController gp;
    ClickHandler clickH;

    // === Pokemons ===
    // TODO: Move to model, so it isn't public - this is just a hack to make the BattleScreen work
    public Pokemon playerPokemon, enemyPokemon;

    // === Pokemon HP ===
    public int playerMaxHp;
    public int playerCurrentHp;
    public int enemyMaxHp;
    public int enemyCurrentHp;

    // === Pokemon Moves ===
    public Moves[] playerMoves;
    private Moves[] enemyMoves;
    private int leerCounter = 0;

    // === Turn Base Control ===
    private boolean isPlayerTurn = true;
    private boolean isBattleFinished = false;

    // Messages
    // TODO: Move messages into BattleScreen entirely!
    public String message = "";
    private long messageUntil = 0;
    private final long messageDuration = 2000; //Millisecond

    private Random rng = new Random();

    // === UI Menu ===
    public enum MenuState {
        mainMenu,
        fightMenu,
        bagMenu,
        pokeMenu,
        runAway
    }

    public MenuState menuState = MenuState.mainMenu;


    public Battle(GameController gp, Pokemon playerPokemon, Pokemon enemyPokemon, ClickHandler clickH) {
        this.gp = gp;
        this.playerPokemon = playerPokemon;
        this.enemyPokemon = enemyPokemon;
        this.clickH = clickH;

        // === HP INIT ===
        playerMaxHp = getBaseStat(playerPokemon, "hp");
        playerCurrentHp = playerMaxHp;
        enemyMaxHp = getBaseStat(enemyPokemon, "hp");
        enemyCurrentHp = enemyMaxHp;

        // === HARCODED MOVES === will change or add more later
        playerMoves = new Moves[4];
        if (gp.playerPokemon == 387) {
            playerMoves[0] = new Moves("Tackle", 40);
            playerMoves[1] = new Moves("Bite", 60);
            playerMoves[2] = new Moves("Razor Leaf", 55);
            playerMoves[3] = new Moves("Leer", 0);
        } else if (gp.playerPokemon == 390) {
            playerMoves[0] = new Moves("Scratch", 40);
            playerMoves[1] = new Moves("Mach Punch", 40);
            playerMoves[2] = new Moves("Flame Wheel", 60);
            playerMoves[3] = new Moves("Leer", 0);
        } else if (gp.playerPokemon == 393) {
            playerMoves[0] = new Moves("Pound", 40);
            playerMoves[1] = new Moves("Peck", 35);
            playerMoves[2] = new Moves("BubbleBeam", 65);
            playerMoves[3] = new Moves("Leer", 0);
        } else {
            playerMoves[0] = new Moves("Tackle", 40);
            playerMoves[1] = new Moves("Bite", 60);
            playerMoves[2] = new Moves("Thunderbolt", 90);
            playerMoves[3] = new Moves("Leer", 0);
        }


        enemyMoves = new Moves[4];
        // ROUTE 201
        if (enemyPokemon.getId() == 396) {
            enemyMoves[0] = new Moves("Peck", 35);
            enemyMoves[1] = new Moves("Quick Attack", 40);
            enemyMoves[2] = new Moves("Wing Attack", 60);
            enemyMoves[3] = new Moves("Growl", 0);
        } else if (enemyPokemon.getId() == 399) {
            enemyMoves[0] = new Moves("Tackle", 35);
            enemyMoves[1] = new Moves("Headbutt", 70);
            enemyMoves[2] = new Moves("Superpower", 120);
            enemyMoves[3] = new Moves("Growl", 0);
            // OPAL SPRINGS
        } else if (enemyPokemon.getId() == 129) {
            enemyMoves[0] = new Moves("Splash", 0);
            enemyMoves[1] = new Moves("Splash", 0);
            enemyMoves[2] = new Moves("Splash", 0);
            enemyMoves[3] = new Moves("Hydro Pump", 120);
        } else if (enemyPokemon.getId() == 54) {
            enemyMoves[0] = new Moves("Water Gun", 40);
            enemyMoves[1] = new Moves("Water Pulse", 60);
            enemyMoves[2] = new Moves("Scratch", 40);
            enemyMoves[3] = new Moves("Amnesia", 0);
            // ETERNA FOREST
        } else if (enemyPokemon.getId() == 315) {
            enemyMoves[0] = new Moves("Razor Leaf", 55);
            enemyMoves[1] = new Moves("Magical Lead", 60);
            enemyMoves[2] = new Moves("Pin Missile", 25);
            enemyMoves[3] = new Moves("Charm", 0);
        } else if (enemyPokemon.getId() == 265) {
            enemyMoves[0] = new Moves("Tackle", 35);
            enemyMoves[1] = new Moves("Poison Sting", 15);
            enemyMoves[2] = new Moves("Harden", 0);
            enemyMoves[3] = new Moves("Growl", 0);
            // ROUTE 202
        } else if (enemyPokemon.getId() == 185) {
            enemyMoves[0] = new Moves("Rock Throw", 50);
            enemyMoves[1] = new Moves("Faint Attack", 60);
            enemyMoves[2] = new Moves("Rock Smash", 40);
            enemyMoves[3] = new Moves("Charm", 0);
        } else if (enemyPokemon.getId() == 299) {
            enemyMoves[0] = new Moves("Tackle", 35);
            enemyMoves[1] = new Moves("Rock Throw", 50);
            enemyMoves[2] = new Moves("Rock Slide", 75);
            enemyMoves[3] = new Moves("Harden", 0);
            // VALLEY
        } else if (enemyPokemon.getId() == 74) {
            enemyMoves[0] = new Moves("Rock Throw", 50);
            enemyMoves[1] = new Moves("Rollout", 30);
            enemyMoves[2] = new Moves("Rock Blast", 25);
            enemyMoves[3] = new Moves("Defense Curl", 0);
        } else if (enemyPokemon.getId() == 436) {
            enemyMoves[0] = new Moves("Tackle", 35);
            enemyMoves[1] = new Moves("Confusion", 50);
            enemyMoves[2] = new Moves("Extrasensory", 100);
            enemyMoves[3] = new Moves("Harden", 0);
            // GYM LEADER
        } else if (enemyPokemon.getId() == 448) {
            enemyMoves[0] = new Moves("Force Palm", 60);
            enemyMoves[1] = new Moves("Aura Sphere", 90);
            enemyMoves[2] = new Moves("Close Combat", 120);
            enemyMoves[3] = new Moves("Dragon Pulse", 90);
        } else {
            enemyMoves[0] = new Moves("Tackle", 40);
            enemyMoves[1] = new Moves("Bite", 60);
            enemyMoves[2] = new Moves("Thunderbolt", 90);
            enemyMoves[3] = new Moves("Growl", 0);
        }

    }

    public void update() {
        // Timer to show messages
        if (System.currentTimeMillis() < messageUntil) {
            return;
        }

        // If player run or Pokémon feint, ends battle
        if (isBattleFinished) {
            endBattle();
        }

        // Battle flow
        if (isPlayerTurn) {
            handlePlayerInput();
        } else {
            clickH.clicked = false;
            enemyTurn();
        }
    }

    private void handlePlayerInput() {

        int menuRightX = 680;
        int menuRightY = 580;
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

        if (menuState == MenuState.mainMenu) {

            if (clickH.consumeLeftClick(fightButton.x, fightButton.y, fightButton.width, fightButton.height)) {
                menuState = MenuState.fightMenu;
                return;
            }

            if (clickH.consumeLeftClick(bagButton.x, bagButton.y, bagButton.width, bagButton.height)) {
                menuState = MenuState.bagMenu;
                return;
            }

            if (clickH.consumeLeftClick(pokeButton.x, pokeButton.y, pokeButton.width, pokeButton.height)) {
                menuState = MenuState.pokeMenu;
                showMessage("You look at your Pokemons... (left click)");
                return;
            }

            if (clickH.consumeLeftClick(runButton.x, runButton.y, runButton.width, runButton.height)) {
                showMessage("You ran away safely!");
                gp.soundController.playSound(27);
                menuState = MenuState.runAway;
                isBattleFinished = true;
            }
        } else if (menuState == MenuState.fightMenu) {
            for (int i = 0; i < moveBoxes.length; i++) {
                Rectangle r = moveBoxes[i];

                if (clickH.consumeLeftClick(r.x, r.y, r.width, r.height)) {
                    if (i < playerMoves.length) {
                        usePlayerMove(i);
                        // return to pokemonproject.main menu
                        menuState = MenuState.mainMenu;
                        return;
                    } else {
                        showMessage("No move in that slot.");
                        return;
                    }
                }
            }
        } else if (menuState == MenuState.bagMenu) {
            int potionHealth = 20;
            if (playerCurrentHp + potionHealth > playerMaxHp) {
                potionHealth = playerMaxHp - playerCurrentHp;
                showMessage("You used a potion and healed " + playerPokemon.getName().toUpperCase() + " for " + potionHealth + "HP");
                playerCurrentHp += potionHealth;
            } else {
                showMessage("You used a potion and healed " + playerPokemon.getName().toUpperCase() + " for 20HP");
                playerCurrentHp += potionHealth;
            }
            gp.soundController.playSound(26);
            menuState = MenuState.mainMenu;
            isPlayerTurn = false;
        }
    }

    private void usePlayerMove(int moveIndex) {
        if (moveIndex < 0 || moveIndex >= playerMoves.length) return;
        Moves move = playerMoves[moveIndex];
        if (move == null) return;

        if (Objects.equals(move.name, "Leer")) {
            gp.soundController.playSound(33);
            leerCounter++;
            showMessage(playerPokemon.getName().toUpperCase() + " used " + move.name + "! \nThe enemy " + enemyPokemon.getName().toUpperCase() + "'s defense fell.");
        } else if (move.power <= 0) {
            showMessage(playerPokemon.getName().toUpperCase() + " used " + move.name + "! \nIt did no damage...");
        } else {
            int damage = (int) Math.floor(calculateDamage(move.power) * (1 + (leerCounter * 0.25)));
            enemyCurrentHp = Math.max(0, enemyCurrentHp - damage);
            showMessage(playerPokemon.getName().toUpperCase() + " used " + move.name + " \nand dealt " + damage + "HP!");
            switch (move.name) {
                // TODO: This suggests having a playSoundEffect method that receives a string directly - and translates it to the sound in question
                case "Bite" -> gp.soundController.playSound(28);
                case "BubbleBeam" -> gp.soundController.playSound(29);
                case "Ember" -> gp.soundController.playSound(30);
                case "Flame Wheel" -> gp.soundController.playSound(31);
                case "Mach Punch" -> gp.soundController.playSound(34);
                case "Peck" -> gp.soundController.playSound(35);
                case "Pound" -> gp.soundController.playSound(36);
                case "Razor Leaf" -> gp.soundController.playSound(37);
                case "Tackle" -> gp.soundController.playSound(38);
                case "Thunderbolt" -> gp.soundController.playSound(39);
                default -> gp.soundController.playSound(32);
            }
        }

        if (enemyCurrentHp <= 0) {
            showMessage("Enemy " + enemyPokemon.getName().toUpperCase() + " fainted!");
            isBattleFinished = true;
        }

        isPlayerTurn = false;
    }

    private void enemyTurn() {
        if (enemyMoves == null) {
            showMessage("Enemy has no moves...");
            isBattleFinished = true;
            return;
        }

        Moves move = enemyMoves[rng.nextInt(enemyMoves.length)];
        if (Objects.equals(move.name, "Growl") || Objects.equals(move.name, "Amnesia") || Objects.equals(move.name, "Charm")) {
            gp.soundController.playSound(42);
            leerCounter--;
            showMessage(enemyPokemon.getName().toUpperCase() + " used " + move.name + "! \nYour " + playerPokemon.getName().toUpperCase() + "'s attack fell.");
        } else if (Objects.equals(move.name, "Harden") || Objects.equals(move.name, "Defense Curl")) {
            gp.soundController.playSound(43);
            leerCounter--;
            showMessage(enemyPokemon.getName().toUpperCase() + " used " + move.name + "! \nIt´s defense rose.");
        } else if (move.power <= 0) {
            showMessage("Enemy " + enemyPokemon.getName().toUpperCase() + " used " + move.name + "! \nIt did no damage...");
        } else {
            int damage = calculateDamage(move.power);
            playerCurrentHp = Math.max(0, playerCurrentHp - damage);
            gp.soundController.playSound(32);
            showMessage("Enemy " + enemyPokemon.getName().toUpperCase() + " used " + move.name + " \nand dealt " + damage + "HP!");
        }

        if (playerCurrentHp <= 0) {
            showMessage("Your " + playerPokemon.getName().toUpperCase() + " fainted!");
            isBattleFinished = true;
            return;
        }
        if (leerCounter == -4) {
            leerCounter = -3;
        }
        isPlayerTurn = true;
    }

    private int calculateDamage(int basePower) {
        // lower = longer battle
        double scale = 0.10;
        return (int) Math.ceil((basePower * scale));
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

    public void endBattle() {
        gp.gameState = GameState.playState;
        gp.soundController.stopMusic();
    }

    public void rightClick() {
        menuState = MenuState.mainMenu;
    }

    private void showMessage(String text) {
        this.message = text;
        this.messageUntil = System.currentTimeMillis() + messageDuration;
    }


}
