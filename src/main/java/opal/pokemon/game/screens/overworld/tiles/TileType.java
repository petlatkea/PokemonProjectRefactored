package main.java.opal.pokemon.game.screens.overworld.tiles;

public class TileType {
    private final int id;
    private final boolean collision;
    private final boolean isGrass;

    public TileType(int id, boolean collision, boolean isGrass) {
        this.id = id;
        this.collision = collision;
        this.isGrass = isGrass;
    }

    public int getId() {
        return id;
    }

    public boolean isCollision() {
        return collision;
    }

    public boolean isGrass() {
        return isGrass;
    }
}
