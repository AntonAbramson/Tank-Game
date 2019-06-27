package src;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Bullet extends GameObject{

    private Handler handler;
    private BufferedImage bullet;
    private ID owner;


    public Bullet(int x, int y, int angle, ID id, Handler handler, ID owner) {
        super(x, y, angle, id);
        this.handler = handler;
        this.owner = owner;
        velX =  (float) (7 * Math.cos(Math.toRadians(angle)));
        velY = (float) (7 * Math.sin(Math.toRadians(angle)));

        //src.BufferedImageLoader loader = new src.BufferedImageLoader();
        try {
            bullet = ImageIO.read(Game.class.getResourceAsStream("/bullet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tick() {
        x += velX;
        y += velY;

        for (int i =0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Wall){
                if(getBounds().intersects(tempObject.getBounds())){
                    handler.removeObject(this);
                }
            }
        }
    }

    public void render(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle),this.bullet.getWidth(), this.bullet.getHeight());
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.bullet, rotation,null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x,y,8,8);
    }

    public ID getOwner() {
        return owner;
    }

    public void setOwner(ID owner) {
        this.owner = owner;
    }
}
