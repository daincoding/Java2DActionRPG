package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame(); // <-- Window Object by Java
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // <-- Get closing Button
        window.setResizable(false); // <-- You cant resize it.
        window.setTitle("2D Action RPG");

        GamePanel gamePanel = new GamePanel(); // <-- Call the object GamePanel
        window.add(gamePanel); // <-- add it to the Window (JFrame)
        window.pack();

        window.setLocationRelativeTo(null);  // <-- Window is in the Middle of the Screen
        window.setVisible(true); // <-- You can see it

        gamePanel.startGameThread();
    }
}