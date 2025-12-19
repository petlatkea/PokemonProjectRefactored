package main.java.opal.pokemon.main.view;

import main.java.opal.pokemon.main.controller.GameController;

import javax.swing.*;

public class MainWindow extends javax.swing.JFrame {
    public MainWindow(GameController controller) {
        super();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Pokemon Opal");

        this.add(controller.getView());
        this.pack();
        this.setLocationRelativeTo(null);

        this.setVisible(true);

    }
}
