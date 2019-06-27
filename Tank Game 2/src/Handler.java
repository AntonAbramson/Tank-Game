package src;

import java.awt.*;
import java.util.LinkedList;

public class Handler {
    LinkedList<GameObject> object = new LinkedList<GameObject>();

    private boolean up = false, down = false, left = false, right = false;
    private boolean up2 = false, down2 = false, left2 = false, right2 = false;


    public void tick(){
        for(int i=0; i < object.size(); i++){
            GameObject tempObject = object.get(i);

            tempObject.tick();
        }
    }

    public void render(Graphics g){
        for(int i=0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);

            tempObject.render(g);
        }
    }

        public void addObject(GameObject tempObject){
            object.add(tempObject);
        }

        public void removeObject(GameObject tempObject){
        object.remove(tempObject);
        }
////// tank 1 ////////
    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    /// TANK 2 /////////////////////
    public boolean isUp2() {
        return up2;
    }

    public void setUp2(boolean up2) {
        this.up2 = up2;
    }

    public boolean isDown2() {
        return down2;
    }

    public void setDown2(boolean down2) {
        this.down2 = down2;
    }

    public boolean isRight2() {
        return right2;
    }

    public void setRight2(boolean right2) {
        this.right2 = right2;
    }

    public boolean isLeft2() {
        return left2;
    }

    public void setLeft2(boolean left2) {
        this.left2 = left2;
    }
}
