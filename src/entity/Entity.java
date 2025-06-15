package entity;

import java.awt.image.BufferedImage;

public class Entity {

    // -- THIS WILL BE THE PARENT CLASS FOR ALL CHARACTERS

    public int x, y;
    public int speed;


    // -- Buffered Image describes an Image with an accessible buffer of image data. It is like a storage.
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

}
