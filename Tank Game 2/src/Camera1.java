package src;

public class Camera1 {

    private float x,y;

    public Camera1(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void tick(GameObject object){
        //For smooth camera movement

        /////////////////Player 1////////////////////////////
        //if (object.getId() == src.ID.Player1) {
            x += ((object.getX() - x) - 1000 / 2) * 0.05f;
            y += ((object.getY() - y) - 563 / 2) * 0.05f;
            if (x <= 0) x = 0;
            if (x >= 1048) x = 1048;
            if (y <= 0) y = 0;
            if (y >= 611) y = 611;
        //}

        //////////////////Player 2///////////////////////

    }


    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
