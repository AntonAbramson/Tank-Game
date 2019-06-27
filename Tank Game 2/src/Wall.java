package src;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Wall extends GameObject {

    private BufferedImage wall1;

    public Wall(int x, int y, int angle, ID id) {
        super(x, y, angle, id);

        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            wall1 = ImageIO.read(Game.class.getResourceAsStream("/Wall1.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.drawImage(wall1, x, y, null);

    }

    public Rectangle getBounds() {
        return new Rectangle(x,y, 32,32);
    }
}
