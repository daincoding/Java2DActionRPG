package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // -- Extends JPanel means it inherits everything from the build in Class JPanel and adds features on Top.

    // -- GamePanel is the main Class which makes the GameLoop and gets all the Objects.

    //region SCREEN SETTINGS
    final int originalTileSize = 16; // <-- 16x16 Tile size for the Game. Is the standart for Retro
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

        // While the gameThread is running we continue with this while Loop.

        while(gameThread !=  null) {

        }

    }

    //endregion
}
