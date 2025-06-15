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

    int FPS = 60;

    //endregion

    //region ObjectVariables

    KeyHandler keyH = new KeyHandler();
    Thread gameThread; // <-- A Thread is something you can start and Stop. It keeps a Programm Running we use it in this Panel to keep it running
    // For this to work we need to implement "Runnable" in the ClassName and because of Runnable we need to Override the run() Method from it.


    //endregion

    //region Players Default Position

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 5;

    //endregion

    //region Constructor 🛠️

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // <-- Set the size of this class (JPanel since its inherited)
        this.setBackground(Color.BLACK); // <-- Sets Background of the GamePanel if there is nothing.
        this.setDoubleBuffered(true); // <-- If set to true, all the drawing from this component will be done in an offscreen painting buffer.
        // -- This can help with rendering performance.


        this.addKeyListener(keyH); // <-- Getting the KeyListener from the KeyHandler Object
        this.setFocusable(true); // <-- With this, the GamePanel can be focused to receive key input.

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
        // -- We also set up the FPS here for the Game -- in this case with the Delta Method 📹



        while(gameThread != null) {

            double drawInterval = (double) 1000000000 / FPS; // <-- Nanoseconds durch 60 (FPS)
            double delta = 0;
            long lastTime = System.nanoTime();
            long currentTime;
            long timer = 0;
            int drawCount = 0;

            while (gameThread != null) {

                currentTime = System.nanoTime();
                delta += (currentTime - lastTime) / drawInterval;
                timer += currentTime - lastTime;
                lastTime = currentTime;
                if (delta >= 1) {

                    // -- A Game Loop needs 2 things: Update and Draw ‼️

                    // -- UPDATE: Update Information such as character positions
                    update();
                    // -- DRAW: Draw the screen with the updated Information
                    repaint(); // <-- repaint is the official Method name for the paintComponent
                    delta--;
                    drawCount++;
                }

                if (timer >= 1000000000) {
                    System.out.println("FPS: " + drawCount);
                    drawCount = 0;
                    timer = 0;
                }
            }
        }

    }
    //endregion

    //region Update & Paint Components 🔁 🖼️
    public void update(){

        // -- We use the Update Method for everything that shall change at one point on the screen - like Movement

        if (keyH.upPressed) {
            playerY -= playerSpeed; // <-- In Java the left upper Corner is 0 so going Up is decreasing Y and increasing when go down.
        } else if (keyH.downPressed) {
            playerY += playerSpeed;
        } else if (keyH.leftPressed) {
            playerX -= playerSpeed;
        } else if (keyH.rightPressed) {
            playerX += playerSpeed; // <-- In Java X increases to the right.
        }
    }

    public void paintComponent(Graphics g) { // <-- This Component comes from Java. It's the main way to paint something on JPanel
        super.paintComponent(g); // <-- that's why we need super here. Super calls the ParentClass because paintComponent is a subclass of JPanel.

        Graphics2D g2 = (Graphics2D) g; // <-- We convert g to 2D and call it g2. We change it because 2D has more functions.

        g2.setColor(Color.WHITE);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose(); // <-- Practice to save memory.
    }
    //endregion
}
