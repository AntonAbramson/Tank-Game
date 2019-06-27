package src;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TankControl extends KeyAdapter {

    Handler handler;
    private Game game;

    public TankControl(Handler handler, Game game) {
        this.handler = handler;
        this.game = game;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player1) {
                if (key == KeyEvent.VK_UP) handler.setUp(true);
                if (key == KeyEvent.VK_DOWN) handler.setDown(true);
                if (key == KeyEvent.VK_LEFT) handler.setLeft(true);
                if (key == KeyEvent.VK_RIGHT) handler.setRight(true);
                if (key == KeyEvent.VK_ENTER && game.ammo1 >= 1) {
                    handler.addObject(new Bullet(tempObject.getX(), tempObject.getY(), tempObject.getAngle(), ID.Bullet, handler, ID.Player1));
                    game.ammo1--;
                }

            } else if (tempObject.getId() == ID.Player2) {
                if (key == KeyEvent.VK_W) handler.setUp2(true);
                if (key == KeyEvent.VK_S) handler.setDown2(true);
                if (key == KeyEvent.VK_A) handler.setLeft2(true);
                if (key == KeyEvent.VK_D) handler.setRight2(true);
                if (key == KeyEvent.VK_SPACE && game.ammo2 >= 1) {
                    handler.addObject(new Bullet(tempObject.getX(), tempObject.getY(), tempObject.getAngle(), ID.Bullet, handler, ID.Player2));
                    game.ammo2--;
                }

            }
        }

    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        for (int i =0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.Player1) {
                if (key == KeyEvent.VK_UP) handler.setUp(false);
                if (key == KeyEvent.VK_DOWN) handler.setDown(false);
                if (key == KeyEvent.VK_LEFT) handler.setLeft(false);
                if (key == KeyEvent.VK_RIGHT) handler.setRight(false);
            }

            else if(tempObject.getId() == ID.Player2){
                if(key == KeyEvent.VK_W) handler.setUp2(false);
                if(key == KeyEvent.VK_S) handler.setDown2(false);
                if(key == KeyEvent.VK_A) handler.setLeft2(false);
                if(key == KeyEvent.VK_D) handler.setRight2(false);
            }
        }

    }
}
