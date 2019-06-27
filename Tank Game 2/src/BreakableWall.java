package src;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BreakableWall extends GameObject{

    private Handler handler;
    private BufferedImage wall2;



    public BreakableWall(int x, int y, int angle, ID id, Handler handler) {
        super(x, y, angle, id);
        this.handler = handler;

        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            wall2 = ImageIO.read(Game.class.getResourceAsStream("/Wall2.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tick() {
        for (int i =0; i < handler.object.size(); i++) {
            try {
                GameObject tempObject = handler.object.get(i);


                if (tempObject.getId() == ID.Bullet) {
                    if (getBounds().intersects(tempObject.getBounds())) {
                        handler.removeObject(tempObject);
                        handler.removeObject(this);
                    }
                }
            } catch (Exception e) { }
        }
    }

    public void render(Graphics g) {
        g.drawImage(wall2, x, y, null);

    }

    public Rectangle getBounds() {
        return new Rectangle(x,y, 32,32);
    }
}
