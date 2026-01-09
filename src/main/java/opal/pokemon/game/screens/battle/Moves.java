package main.java.opal.pokemon.game.screens.battle;

public class Moves {

    public String name;
    public int power; // 0 for status moves

    public Moves(String name, int power) {
        this.name = name;
        this.power = power;
    }
}
