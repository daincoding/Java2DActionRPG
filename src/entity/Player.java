package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {

    // Inherits everything from Entity and also gets Infos from GamePanel and KeyHandler

    GamePanel gp;
    KeyHandler keyH;

    //Camera Fixed

    public final int screenX;
    public final int screenY;


    //region Constructor 🛠️
    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2); // <-- The second part is to middle it because the normal tile starts in the right upper corner.
        screenY = gp.screenHeight/2 - (gp.tileSize/2);


        // -- Creating the Rectangle fully with the values for understanding purpose.
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;


        setDefaultValues();
        getPlayerImage();
    }

    //endregion

    //region Player Defaults &  Images

    public void setDefaultValues(){

        // -- STARTING POSITIONS

        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 5;
        direction = "down";

    }

    public void getPlayerImage() {

        // -- We load the pngs here in this segment to deliver it further.

        try {

            // -- requireNonNull Object means that it prevents the ImageIO to get something that is empty.

            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_2.png")));

        }catch(IOException e) {
            System.err.println("Failed to load player images: " + e.getMessage()); // <-- Message if there is an Error.
        }

    }

    //endregion

    //region UPDATE & DRAW METHOD

    // -- The Characters get their own update and Draw Methods and the GamePanel will combine everything later.

    public void update(){

        // -- ALL OF THIS IS JUST HAPPENING IF U PRESS A KEY

        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {

            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else {
                direction = "right";
            }

            collisionOn = false;
            gp.cChecker.checkTile(this); // <-- Since Player is a subclass of Entity we can send THIS class over as entity component.

            if(!collisionOn) {

                switch (direction) {
                    case "up":
                        worldY -= speed; // <-- In Java the left upper Corner is 0 so going Up is decreasing Y and increasing when go down.
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed; // <-- In Java X increases to the right.
                        break;
                }
            }

            // This is the Switcher for the Pictures since this method is called 60 times.

            spriteCounter++;
            if (spriteCounter > 15) { // <-- now this means that every X Frames it will change the sprite.
                if (spriteNum == 1) {
                    spriteNum = 2;
                }
                else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

        }



    }

    public void draw(Graphics2D g2){

        BufferedImage image = null; // <-- Storage for Switch Statement

        switch (direction) {
            case "up":
                if(spriteNum == 1) {
                    image = up1;
                }
                if(spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1) {
                    image = down1;
                }
                if(spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2) {
                    image = right2;
                }
                break;
            default:
                System.out.println("Invalid direction: " + direction);
                break;
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

    }

    //endregion

}
