package src;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tank extends GameObject{


    Handler handler;
    Game game;
    private final int R = 2;
    private final int ROTATIONSPEED = 4;

    private BufferedImage tank_image;


    public Tank(int x, int y, int angle, ID id, Handler handler, Game game) {
        super(x, y, angle, id);
        this.handler = handler;
        this.game=game;

        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            tank_image = ImageIO.read(Game.class.getResourceAsStream("/tank1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void tick() {

        collision();

        ////////////////src.Tank 1////////////////////
        if (id == ID.Player1) {
            if (handler.isUp()){
                velX = Math.round(R * Math.cos(Math.toRadians(angle)));
                velY = Math.round(R * Math.sin(Math.toRadians(angle)));
                x += velX;
                y += velY;

            }
            else if (!handler.isDown()) {
                velY = 0;
            }

            if (handler.isDown()) {
                velX = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
                velY = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
                x -= velX;
                y -= velY;
            }
            else if (!handler.isUp()){
                velY = 0;
            }

            if (handler.isLeft()) {
                this.angle -= this.ROTATIONSPEED;
            }
            else if (!handler.isRight()) {
                velX = 0;
            }

            if (handler.isRight()) {
                this.angle += this.ROTATIONSPEED;
            }
            else if (!handler.isLeft()) {
                velX = 0;
            }

        }

        ////////////////src.Tank 2////////////////////
        if (id == ID.Player2) {
            if (handler.isUp2()) {
                velX = Math.round(R * Math.cos(Math.toRadians(angle)));
                velY = Math.round(R * Math.sin(Math.toRadians(angle)));
                x += velX;
                y += velY;;}

            else if (!handler.isDown2()) velY = 0;

            if (handler.isDown2()) {
                velX = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
                velY = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
                x -= velX;
                y -= velY;            }

            else if (!handler.isUp2()) velY = 0;

            if (handler.isLeft2()){
                this.angle -= this.ROTATIONSPEED;
            }
            else if (!handler.isRight2()) velX = 0;

            if (handler.isRight2()){
                this.angle += this.ROTATIONSPEED;
            }
            else if (!handler.isLeft2()) velX = 0;
        }


    }

    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Wall) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    if(handler.isUp()){
                        y += velY * -1;
                        x += velX * -1;
                    }
                    if(handler.isDown()){
                        y += velY;
                        x += velX;
                    }
                    if(handler.isUp2()){
                        y += velY * -1;
                        x += velX * -1;
                    }
                    if(handler.isDown2()){
                        y += velY;
                        x += velX;
                    }


                }
            }

            if (tempObject.getId() == ID.BreakableWall) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    x += velX * -1;
                    y += velY * -1;
                }
            }

            if (id == ID.Player1) {
                if (tempObject.getId() == ID.PowerUp1) {
                    if (getBounds().intersects(tempObject.getBounds())) {
                        game.ammo1 += 15;
                        handler.removeObject(tempObject);

                    }
                }
                if (tempObject.getId() == ID.Bullet) {
                    if (((Bullet) tempObject).getOwner() != ID.Player1) {

                        if (getBounds().intersects(tempObject.getBounds())) {
                            game.hp1 -= 25;
                            handler.removeObject(tempObject);
                            if (game.hp1 == 0) {

                                game.hp1 = 100;
                                game.lives1--;

                            }
                        }
                        if (game.lives1 == 0) {
                            System.out.println("Player 2 wins");
                        }
                    }
                }
            }



            if (id == ID.Player2) {
                if (tempObject.getId() == ID.PowerUp1) {
                    if (getBounds().intersects(tempObject.getBounds())) {
                        game.ammo2 += 15;
                        handler.removeObject(tempObject);
                    }
                }
                if (tempObject.getId() == ID.Bullet){
                    if(((Bullet)tempObject).getOwner() !=ID.Player2) {
                        if (getBounds().intersects(tempObject.getBounds())) {
                            game.hp2 -= 25;
                            handler.removeObject(tempObject);
                            if (game.hp2 == 0){

                                game.hp2 = 100;
                                game.lives2--;

                            }
                        }
                        if (game.lives2 == 0){
                            System.out.println("Player 1 wins");
                        }
                    }
                }
            }
        }

    }
    public void render(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        if (id == ID.Player1) {
            AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
            rotation.rotate(Math.toRadians(angle),this.tank_image.getWidth() / 2.0, this.tank_image.getHeight() / 2.0);
            //Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(this.tank_image, rotation,null);
            g.setColor(Color.green);
            g.drawRect(x,y, 34,34);

        }


        if (id == ID.Player2) {
            AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
            rotation.rotate(Math.toRadians(angle),this.tank_image.getWidth() / 2.0, this.tank_image.getHeight() / 2.0);
            g2d.drawImage(this.tank_image, rotation,null);
            g.setColor(Color.green);
            g.drawRect(x,y, 34,34);
        }



    }
    public Rectangle getBounds() {
        return new Rectangle(x,y,34,34);
    }


}
