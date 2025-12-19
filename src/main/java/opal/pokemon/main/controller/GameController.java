package main.java.opal.pokemon.main.controller;

import main.java.opal.pokemon.battleSystem.Battle;
import main.java.opal.pokemon.entity.Entity;
import main.java.opal.pokemon.entity.Player;
import main.java.opal.pokemon.main.*;
import main.java.opal.pokemon.main.view.GameView;
import main.java.opal.pokemon.object.SuperObject;
import main.java.opal.pokemon.pokedex.Pokedex;
import main.java.opal.pokemon.pokedex.Pokemon;
import main.java.opal.pokemon.tile.TileManager;

import java.util.Random;

public class GameController implements Runnable {

    // View
    private GameView view;

    // TODO: Maybe remove asap??
    public GameView getView() {
        return view;
    }

    // === SCREEN SETTINGS ===
    final int originalTileSize = 16;    // 16x16 px
    public final int ScaleMultiplier = 4;

    public final int tileSize = originalTileSize * ScaleMultiplier;     // 64x64 px
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;

    public final int screenWidth = tileSize * maxScreenCol;     // 1024 px
    public final int screenHeight = tileSize * maxScreenRow;    // 768 px

    // == GENDER & STARTER POKEMON ==
    public int genderState = 1;
    public int playerPokemon = 25;

    // === SYSTEM ===


    // TODO: Extract away from public here ...
    public Pokemon originalPokemon = new Pokemon();
    public Pokedex pokedex;// = new Pokedex(this, view.getKeyH(), originalPokemon);

    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter; // = new AssetSetter(this, view.getClickH());
    public UI ui; // = new UI(this, view.getClickH(), originalPokemon, pokedex);
    Random rng = new Random();
    Thread gameThread;

    // == ENTITY & OBJECT ===
    public Player player; // = new Player(this, view.getKeyH());
    public SuperObject[] obj = new SuperObject[10];
    public Entity[] npc = new Entity[20];

    // == GAME STATE ==
    public int gameState;
    public final int titleScreenState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int pokedexState = 4;
    public final int battleState = 5;
    public final int starterChoiceState = 6;

    // === WORLD SETTINGS ===
    public final int maxWorldCol = 100;
    public final int maxWorldRow = 100;

    // === SOUND ===
    public Sound music;
    public Sound collisionSound;
    public Sound buttonSound;
    public Sound grassSound;


    // === BATTLE SYSTEM ===
    public Battle battle;

    // === FPS ===
    int FPS = 60;

    // === CONSTRUCTOR ===
    public GameController() {
        view = new GameView(this);
        pokedex = new Pokedex(this, view.getKeyH(), originalPokemon);
        aSetter = new AssetSetter(this, view.getClickH());
        ui = new UI(this, view.getClickH(), originalPokemon, pokedex);

        player = new Player(this, view.getKeyH());

        music = new Sound(this, player);
        collisionSound = new Sound(this, player);
        buttonSound = new Sound(this, player);
        grassSound = new Sound(this, player);
    }

    public void setupGame() {
        music.playSound(40);
        aSetter.setObject();
        aSetter.setNPC();
        gameState = titleScreenState;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }


    // === GAME LOOP ===
    @Override
    public void run() {
        double drawInterval = 1_000_000_000.0 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        int drawCount = 0;

        while (gameThread != null) {
            long currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            while (delta >= 1) {
                update();
                delta--;
                drawCount++;
                view.repaint();
            }

//            if (System.currentTimeMillis() - timer >= 1000) {
//                System.out.println("FPS: " + drawCount);
//                drawCount = 0;
//                timer += 1000;
//            }
        }
    }


    private void update() {
        if (gameState == playState) {
            player.update();
            music.updateMusic();
            music.updateFade();
            ui.updateGrassFade();
             if(ui.enterWild){
                 if (ui.getGrassFadeCounter()>=ui.grassFadeCounterMax){
                     ui.enterWild = false;
                     ui.setGrassFadeCounter(0);
                     startWildBattle();
                 }
             }
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].update();
                }
            }


        }
        if (gameState == pauseState) {

        }

        if (gameState == battleState) {
            // Battle screen
            if (battle != null) {
                battle.update();
            }

            if (view.getKeyH().spacePressed && battle != null) {
                battle.endBattle();
            }
        }
    }

    public long getDrawCount() {
        return view.getDrawCount();
    }

    public void initializeUIComponents() {
        if (ui != null) {
            ui.inputSetup();
        }
    }

    public void startGymBattle() {
        Pokemon playerPokemon = Pokemon.load(String.valueOf(this.playerPokemon));
        Pokemon enemyPokemon = Pokemon.load("448");

        battle = new Battle(this, playerPokemon, enemyPokemon, view.getClickH(), music);
        gameState = battleState;
        music.updateMusic();
    }

    public void startWildBattle() {
        Pokemon playerPokemon = Pokemon.load(String.valueOf(this.playerPokemon));
        int poke = rng.nextInt(2);
        String enemyID;
        int x = (player.worldX / tileSize) + 1;
        int y = (player.worldY / tileSize) + 1;

        if (x >= 33 && y <= 60 && x <= 54 && y >= 20){
            // 201
            if (poke == 1){
            enemyID = "396";
            } else{
                enemyID = "399";
            }
        } else if (x >= 0 && y <= 20 && x <= 41 && y >= 0){
            // Opal Springs
            if (poke == 1){
                enemyID = "129";
            } else{
                enemyID = "54";
            }
        }else if (x >= 71 && y <= 55 && x <= 999 && y >= 0){
            // Forest
            if (poke == 1){
                enemyID = "315";
            } else{
                enemyID = "265";
            }
        }else if (x >= 40 && y <= 73 && x <= 999 && y >= 56){
            // 202
            if (poke == 1){
                enemyID = "185";
            } else{
                enemyID = "299";
            }
        }else if (x >= 43 && y <= 999 && x <= 999 && y >= 80){
            // Valley
            if (poke == 1){
                enemyID = "74";
            } else{
                enemyID = "436";
            }
        } else {
            if (poke == 1){
                enemyID = "25";
            } else{
                enemyID = "69";
            }
        }

        Pokemon enemyPokemon = Pokemon.load(enemyID);

        battle = new Battle(this, playerPokemon, enemyPokemon, view.getClickH(), music);
        view.getClickH().clicked = false;
        gameState = battleState;
        music.updateMusic();
    }
}