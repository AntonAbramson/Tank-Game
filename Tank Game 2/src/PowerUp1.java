package src;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PowerUp1 extends GameObject{
    private BufferedImage powerup1;

    public PowerUp1(int x, int y, int angle, ID id) {
        super(x, y, angle, id);
        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            powerup1 = ImageIO.read(Game.class.getResourceAsStream("/powerup1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.drawImage(powerup1, x, y, null);

    }

    public Rectangle getBounds() {
        return new Rectangle(x,y,32,32);
    }
}
