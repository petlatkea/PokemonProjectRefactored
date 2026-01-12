package main.java.opal.pokemon.game.screens.overworld.characters;

public class EntityModel {
    public int worldX;
    public int worldY;
    public int speed;
    public Direction direction;
    public boolean collisionOn = false;
    public boolean isGrassOn = false;

    public boolean moving = false;

    public EntityModel() {
    }

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
}