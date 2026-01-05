package main.java.opal.pokemon;

import main.java.opal.pokemon.main.controller.GameController;
import main.java.opal.pokemon.main.view.MainWindow;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        GameController controller = new GameController();
        MainWindow window = new MainWindow(controller);

        controller.setupGame();
        controller.startGameThread();

    }
}
