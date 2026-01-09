package main.java.opal.pokemon.game.screens.pokedex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Not sure if this class has any use - it is never instantiated, but perhaps its
 * intention is to create a new / empty database for first time run ...
 * or it is a remnant of earlier work, that needs to go :)
 */
public class CreatePokemonDB {
    private static final String URL = "jdbc:sqlite:pokemonproject.pokedex.db";

    // Get a database connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    // Create tables if they don't exist
    public static void init() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute("""
                    CREATE TABLE IF NOT EXISTS pokemon (
                        id          INTEGER PRIMARY KEY,
                        name        TEXT NOT NULL UNIQUE,
                        height      INTEGER,
                        weight      INTEGER,
                        sprite_url  TEXT,
                        sprite_blob BLOB
                    );
                    """);

            stmt.execute("""
                    CREATE TABLE IF NOT EXISTS pokemon_stat (
                        pokemon_id  INTEGER,
                        stat_name   TEXT,
                        base_stat   INTEGER,
                        PRIMARY KEY (pokemon_id, stat_name),
                        FOREIGN KEY (pokemon_id) REFERENCES pokemon(id)
                    );
                    """);

            stmt.execute("""
                    CREATE TABLE IF NOT EXISTS pokemon_type (
                        pokemon_id  INTEGER,
                        slot        INTEGER,
                        type_name   TEXT,
                        PRIMARY KEY (pokemon_id, slot),
                        FOREIGN KEY (pokemon_id) REFERENCES pokemon(id)
                    );
                    """);

        } catch (SQLException e) {
            throw new RuntimeException("Error initializing database", e);
        }
    }
}
