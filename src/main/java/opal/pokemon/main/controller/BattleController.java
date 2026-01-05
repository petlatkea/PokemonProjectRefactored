package main.java.opal.pokemon.main.controller;

import main.java.opal.pokemon.battleSystem.Battle;
import main.java.opal.pokemon.main.MouseClick;
import main.java.opal.pokemon.main.view.BattleScreen;
import main.java.opal.pokemon.pokedex.Pokedex;
import main.java.opal.pokemon.pokedex.Pokemon;

import java.util.Random;

public class BattleController extends ScreenController {

    private Battle battle;

    public BattleController(GameController gameController) {
        super(gameController);
        // create model and view - but skip model until actually needed
        screen = new BattleScreen(this);
    }

    @Override
    public void update() {
        if(battle != null) {
            battle.update();
        }

        if (gameController.getView().getKeyH().spacePressed && battle != null) {
            battle.endBattle();
        }
    }

    public Battle getBattle() {
        return battle;
    }

    public void startGymBattle() {
        Pokemon playerPokemon = Pokemon.load(String.valueOf(gameController.playerPokemon));
        Pokemon enemyPokemon = Pokemon.load("448");

        battle = new Battle(gameController, playerPokemon, enemyPokemon, gameController.getView().getClickH(), gameController.music);
        gameController.gameState = GameState.battleState;
        gameController.music.updateMusic();

        ((BattleScreen)screen).setPlayerAndEnemyGraphics(playerPokemon, enemyPokemon);
    }

    private Random rng = new Random();

    public void startWildBattle() {
        Pokemon playerPokemon = Pokemon.load(String.valueOf(gameController.playerPokemon));
        int poke = rng.nextInt(2);
        String enemyID;
        int x = (gameController.player.worldX / gameController.tileSize) + 1;
        int y = (gameController.player.worldY / gameController.tileSize) + 1;

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

        battle = new Battle(gameController, playerPokemon, enemyPokemon, gameController.getView().getClickH(), gameController.music);
        gameController.getView().getClickH().clicked = false;
        gameController.gameState = GameState.battleState;
        gameController.music.updateMusic();
        ((BattleScreen)screen).setPlayerAndEnemyGraphics(playerPokemon, enemyPokemon);
    }

    @Override
    public void handleRightClick(MouseClick mouseClick) {
        battle.rightClick();
    }
}
