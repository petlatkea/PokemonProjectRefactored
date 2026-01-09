package main.java.opal.pokemon.game.screens.battle;

import main.java.opal.pokemon.game.GameController;
import main.java.opal.pokemon.game.screens.ScreenController;

public class BattleIntroController extends ScreenController {

    private int grassFadeCounter = 0;
    private final int grassFadeCounterMax = 90;

    public BattleIntroController(GameController gameController) {
        super(gameController);
        // create model and view - but skip model until actually needed
        screen = new BattleIntroScreen(this);
    }

    @Override
    public void update() {
        updateGrassFade();
        if (getGrassFadeCounter()>=grassFadeCounterMax){
            setGrassFadeCounter(0);
            System.out.println("done with battle intro - going into battle");
            gameController.startWildBattle();
        }
    }

    public void updateGrassFade() {
        this.grassFadeCounter++;
    }

    public int getGrassFadeCounter() {
        return grassFadeCounter;
    }

    public void setGrassFadeCounter(int grassFadeCounter) {
        this.grassFadeCounter = grassFadeCounter;
    }
}