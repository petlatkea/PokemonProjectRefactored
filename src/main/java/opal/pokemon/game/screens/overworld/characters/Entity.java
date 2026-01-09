package main.java.opal.pokemon.game.screens.overworld.characters;

import main.java.opal.pokemon.game.GameController;

import java.awt.*;

public abstract class Entity {
    public EntityModel model;// = new EntityModel();
    public EntityView view; // = new EntityView();
    protected GameController gp;

    // NOTE: This is used for the collision handling - should also be part of the model
    public Rectangle solidArea = new Rectangle(0, 0, 64, 64);
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;

    public Entity(GameController gp) {
        this.gp = gp;
        model = new EntityModel();
        view = new EntityView(gp, model);
    }

    // NOTE: Only implemented by the player - maybe not necessary?
    public void update() {
    }


}
