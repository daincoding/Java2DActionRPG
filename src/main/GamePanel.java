package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // -- Extends JPanel means it inherits everything from the build in Class JPanel and adds features on Top.

    // -- GamePanel is the main Class which makes the GameLoop and gets all the Objects.

    //region SCREEN SETTINGS
    final int originalTileSize = 16; // <-- 16x16 Tile size for the Game. Is the standard for Retro
    final int scale = 3; // <-- Scaling Multiplikator to make 16 to 48
    public final int tileSize = originalTileSize * scale; // <-- 48x48

    // -- Decide how many tiles you want to show at the same time?
    final int maxScreenCol = 16;
    final int maxScreenRow = 12; // <-- This means the Ratio is 4 : 3
    final int screenWidth = tileSize * maxScreenCol; // <-- 48 * 16 = 768px
    final int screenHeight = tileSize * maxScreenRow; // <-- 48 * 12 = 576px

    //endregion

    //region ObjectVariables

    Thread gameThread; // <-- A Thread is something you can start and Stop. It keeps a Programm Running we use it in this Panel to keep it running
    // For this to work we need to implement "Runnable" in the ClassName and because of Runnable we need to Override the run() Method from it.

    //endregion

    //region Constructor 🛠️

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // <-- Set the size of this class (JPanel since its inherited)
        this.setBackground(Color.BLACK); // <-- Sets Background of the GamePanel if there is nothing.
        this.setDoubleBuffered(true); // <-- If set to true, all the drawing from this component will be done in an offscreen painting buffer.
        // -- This can help with rendering performance.



    }

    //endregion

    //region Runnable Override Method: Run

    public void startGameThread() {

        gameThread = new Thread(this); // <-- Setting up the Thread from above
        gameThread.start(); // <-- This calls the Run Method
    }

    // This method is used to create a thread.
    // ⚠️ ALSO IT INCLUDES OUR GAME LOOP ⚠️
    @Override
    public void run() {

        // -- While the gameThread is running we continue with this while Loop. 🔁

        while(gameThread !=  null) {

            // -- A Game Loop needs 2 things: Update and Draw ‼️

            // -- UPDATE: Update Information such as character positions
            update();
            // -- DRAW: Draw the screen with the updated Information
            repaint(); // <-- repaint is the official Method name for the paintComponent
        }

    }
    //endregion

    //region Update & Paint Components 🔁 🖼️
    public void update(){

    }

    public void paintComponent(Graphics g) { // <-- This Component comes from Java. It's the main way to paint something on JPanel
        super.paintComponent(g); // <-- that's why we need super here. Super calls the ParentClass because paintComponent is a subclass of JPanel.

        Graphics2D g2 = (Graphics2D) g; // <-- We convert g to 2D and call it g2. We change it because 2D has more functions.

        g2.setColor(Color.WHITE);
        g2.fillRect(100, 100, tileSize, tileSize);
        g2.dispose(); // <-- Practice to save memory.
    }
    //endregion
}
