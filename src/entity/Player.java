package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;

public class Player extends Entity {

    // Inherits everything from Entity and also gets Infos from GamePanel and KeyHandler

    GamePanel gp;
    KeyHandler keyH;


    //region Constructor 🛠️
    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
    }

    //endregion

    public void setDefaultValues(){

        x = 100;
        y = 100;
        speed = 5;

    }

    //region UPDATE & DRAW METHOD

    // -- The Characters get their own update and Draw Methods and the GamePanel will combine everything later.

    public void update(){

        if (keyH.upPressed) {
            y -= speed; // <-- In Java the left upper Corner is 0 so going Up is decreasing Y and increasing when go down.
        } else if (keyH.downPressed) {
            y += speed;
        } else if (keyH.leftPressed) {
            x -= speed;
        } else if (keyH.rightPressed) {
            x += speed; // <-- In Java X increases to the right.
        }

    }

    public void draw(Graphics2D g2){

        g2.setColor(Color.WHITE);
        g2.fillRect(x, y, gp.tileSize, gp.tileSize);

    }

    //endregion
}
