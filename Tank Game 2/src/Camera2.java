package src;

public class Camera2 {

    private float x,y;

    public Camera2(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void tick(GameObject object){
        //For smooth camera movement

        /////////////////Player 2////////////////////////////
        //if (object.getId() == src.ID.Player2) {
            x += ((object.getX() - x) - 500 / 2) * 0.05f;
            y += ((object.getY() - y) - 563 / 2) * 0.05f;
            if (x <= 524) x = 524;
            if (x >= 1048) x = 1048;
            if (y <= 305) y = 305;
            if (y >= 611) y = 611;
        //}

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
