package src;

import java.awt.*;

public abstract class GameObject {

    protected int x,y,angle;
    protected double velX =0, velY =0;
    protected ID id;

    public GameObject(int x, int y, int angle, ID id){
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.id = id;
    }



    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

     public int getX() {
         return x;
     }

     public void setX(int x){
         this.x=x;
     }

     public int getY(){
         return y;
     }

     public void setY(){
         this.y=y;
     }

     public void setAngle(int angle) {this.angle = angle;}

     public int getAngle() {return angle;}

     public double getVelX(){
         return velX;
     }

     public void setVelX(float velX){
         this.velX = velX;
     }

     public double getVelY(){
         return velY;
     }

     public void setVelY(float velY){
         this.velY = velY;
     }
}
