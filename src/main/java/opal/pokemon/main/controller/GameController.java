package main.java.opal.pokemon.main.controller;

import main.java.opal.pokemon.entity.Entity;
import main.java.opal.pokemon.entity.Player;
import main.java.opal.pokemon.main.AssetSetter;
import main.java.opal.pokemon.main.Sound;
import main.java.opal.pokemon.main.UI;
import main.java.opal.pokemon.main.model.CollisionChecker;
import main.java.opal.pokemon.main.model.GameModel;
import main.java.opal.pokemon.main.view.GameView;
import main.java.opal.pokemon.object.SuperObject;
import main.java.opal.pokemon.pokedex.Pokedex;
import main.java.opal.pokemon.pokedex.Pokemon;

public class GameController implements Runnable {

    // View
    private GameView view;
    private GameModel model;

    // TODO: Remove most usages apart from MainWindow
    public GameView getView() {
        return view;
    }

    // TODO: Get rid of all usages - only used by CollisionChecker it seems
    public GameModel getModel() {
        return model;
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

    public CollisionChecker cChecker; // = new CollisionChecker(this);
    public AssetSetter aSetter; // = new AssetSetter(this, view.getClickH());
    public UI ui; // = new UI(this, view.getClickH(), originalPokemon, pokedex);

    Thread gameThread;

    // == ENTITY & OBJECT ===
    public Player player; // = new Player(this, view.getKeyH());
    public SuperObject[] obj = new SuperObject[10];
    public Entity[] npc = new Entity[20];

    // == GAME STATE ==
    public GameState gameState = GameState.titleScreenState;

    // === WORLD SETTINGS ===
    public final int maxWorldCol = 100;
    public final int maxWorldRow = 100;

    // === SOUND ===
    public Sound music;
    public Sound collisionSound;
    public Sound buttonSound;
    public Sound grassSound;

    // === FPS ===
    int FPS = 60;

    // sub-controllers
    public ScreenController titleScreenController;
    public ScreenController overWorldController;
    public ScreenController battleIntroController;
    public ScreenController battleController;
    public ScreenController startersController;
    public ScreenController pauseController;
    public ScreenController dialogueController;
    public ScreenController pokedexController;


    // === CONSTRUCTOR ===
    public GameController() {
        // create sub-controllers first
        titleScreenController = new TitleController(this);
        overWorldController = new OverWorldController(this);
        battleIntroController = new BattleIntroController(this);
        battleController = new BattleController(this);
        startersController = new StartersController(this);
        pauseController = new PauseController(this);
        dialogueController = new DialogueController(this);
        pokedexController = new PokedexController(this);

        // then create model and view - which might access some of these sub-controllers
        model = new GameModel(this);
        view = new GameView(this, model);

        cChecker = new CollisionChecker(this, model);

        // TODO: Make pokedexController include Pokedex!
        pokedex = new Pokedex(this, view.getKeyH(), originalPokemon);
        ((PokedexController)pokedexController).setPokemon(originalPokemon);
        ((PokedexController)pokedexController).setPokedex(pokedex);


        aSetter = new AssetSetter(this, view.getClickH());
        ui = new UI(this); //, view.getClickH(), originalPokemon, pokedex);

        player = new Player(this, view.getKeyH());
        ((OverWorldController) overWorldController).setPlayer(player);

        music = new Sound(this, player);
        collisionSound = new Sound(this, player);
        buttonSound = new Sound(this, player);
        grassSound = new Sound(this, player);
    }

    public void setupGame() {
        music.playSound(40);
        aSetter.setObject();
        aSetter.setNPC();
        gameState = GameState.titleScreenState;
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
        // update the appropriate subcontrollers - depending on the gamestate
        if(gameState == GameState.titleScreenState) {
            titleScreenController.update();
        }
        if (gameState == GameState.playState) {
            overWorldController.update();
        }

        if(gameState == GameState.starterChoiceState) {
            startersController.update(); // does nothing for the moment, but will probably be needed later
        }

        if(gameState == GameState.battleIntroState){
            battleIntroController.update();
        }

        if (gameState == GameState.pauseState) {
            pauseController.update();
        }

        if (gameState == GameState.battleState) {
            battleController.update();
        }
    }

    public long getDrawCount() {
        return view.getDrawCount();
    }

    public void startGymBattle() {
        ((BattleController)battleController).startGymBattle();
    }

    public void startWildBattle() {
        ((BattleController)battleController).startWildBattle();
    }
}