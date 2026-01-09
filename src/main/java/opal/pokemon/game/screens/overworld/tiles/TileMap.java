package main.java.opal.pokemon.game.screens.overworld.tiles;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileMap {
    private final String name;
    public int[][] map;

    private final int cols;
    private final int rows;

    public TileMap(String name, int columns, int rows) {
        this.name = name;
        this.cols = columns;
        this.rows = rows;
        this.map = new int[cols][rows];
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public int getTileType(int col, int row) {
        return map[col][row];
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < cols && row < rows) {
                String line = br.readLine();
                String[] numbers = line.split(", ");

                while (col < cols) {
                    int num = Integer.parseInt(numbers[col]);
                    map[col][row] = num;
                    col++;
                }

                if (col == cols) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}