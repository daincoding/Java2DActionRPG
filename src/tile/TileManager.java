package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class TileManager {

    GamePanel gp;
    Tile[] tile;

    //Constructor 🛠️

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[10];

        getTileImage();
    }

    public void getTileImage(){

        try{

            // -- Load again the Images in the Array. It needs the .image for TypeSafety.

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass.png")));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall.png")));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water.png")));

        }catch(IOException e){
            System.out.println("TileManager getTileImage failed");
        }

    }

    public void draw(Graphics2D g2){

        g2.drawImage(tile[0].image,0,0, gp.tileSize, gp.tileSize, null );

    }
}
