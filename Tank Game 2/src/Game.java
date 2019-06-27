package src;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Game extends Canvas implements Runnable{

    private boolean isRunning = false;
    private Thread thread;
    private Handler handler;
    private Camera1 camera1;
    private Camera2 camera2;



    private BufferedImage level = null;
    private BufferedImage floor = null;

    private BufferedImage player1wins;
    private BufferedImage player2wins;

    public int ammo1 = 20;
    public int ammo2 = 20;
    public int hp1 = 100;
    public int hp2 = 100;
    public int lives1 = 3;
    public int lives2 = 3;
    int x;
    int y;


    public Game(){
        new Window(1000, 563, "2D src.Tank src.Game", this);
        start();

        handler = new Handler();
        camera1 = new Camera1(0,0);
        camera2 = new Camera2(500,0);

        this.addKeyListener(new TankControl(handler, this));


        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            level = ImageIO.read(Game.class.getResourceAsStream("/Tank_Level.png"));
            floor = ImageIO.read(Game.class.getResourceAsStream("/Background.bmp"));

            player1wins = ImageIO.read(Game.class.getResourceAsStream("/player1wins.png"));

            player2wins = ImageIO.read(Game.class.getResourceAsStream("/player2wins.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        loadLevel(level);

    }

    private void start(){
        isRunning = true;
        thread=new Thread(this);
        thread.start();
    }

    private void stop(){
        isRunning=false;
        try{
            thread.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                tick();
                delta--;
            }
            render();
            frames++;
            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
            }
        }
        stop();
    }

    public void tick(){

        for(int i =0; i < handler.object.size(); i++){
            if(handler.object.get(i).getId() == ID.Player1){
                camera1.tick(handler.object.get(i));
            }
        }

        for(int i =0; i < handler.object.size(); i++){
            if(handler.object.get(i).getId() == ID.Player2){
                camera2.tick(handler.object.get(i));
            }
        }
        handler.tick();
    }

    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        /////////////////////////////////////////


        g2d.translate(-camera1.getX(), -camera1.getY());


        for(int xx = 0; xx < 30*72; xx+=320){
            for(int yy =0; yy < 30*72; yy+=240){
                g.drawImage(floor, xx, yy, null);
            }
        }

        handler.render(g);

        g2d.translate(camera1.getX(), camera1.getY());

        g.setColor(Color.gray);
        g.fillRect(1,1, 200,32);
        g.setColor(Color.green);
        g.fillRect(1,1, hp1*2,32);
        g.setColor(Color.black);
        g.drawRect(1,1, 200,32);
        g.setColor(Color.white);
        g.drawString("Ammo: " + ammo1,5,45);
        g.setColor(Color.white);
        g.drawString("Lives: " + lives1,158,45);

        g.setColor(Color.gray);
        g.fillRect(783,1, 200,32);
        g.setColor(Color.green);
        g.fillRect(783,1, hp2*2,32);
        g.setColor(Color.black);
        g.drawRect(783,1, 200,32);
        g.setColor(Color.white);
        g.drawString("Ammo: " + ammo2,783,45);
        g.setColor(Color.white);
        g.drawString("Lives: " + lives2,942,45);

        if (lives1 == 0){
            g.drawImage(player2wins,x,y,null);
        }
        if (lives2 == 0){
            g.drawImage(player1wins,x,y,null);
        }

        /////////////////////////////////////////
        g.dispose();
        bs.show();
    }

    //Loading the level
    private void loadLevel(BufferedImage image){
        int w = image.getWidth();
        int h = image.getHeight();
        for(int xx=0; xx<w; xx++){
            for(int yy=0; yy<h; yy++){
                int pixel = image.getRGB(xx,yy);
                int red = (pixel >>16) & 0xff;
                int green = (pixel >>8) & 0xff;
                int blue = (pixel) & 0xff;

                if (red== 255)
                    handler.addObject(new Wall(xx*32, yy*32, 0, ID.Wall));

                if(blue == 255 && green == 0)
                    handler.addObject(new Tank(xx*32, yy*32, 0, ID.Player1, handler, this));

                if(blue == 200 && green == 0)
                    handler.addObject(new Tank(xx*32, yy*32, 0, ID.Player2, handler, this));

                if (green == 255 && blue ==0)
                    handler.addObject(new BreakableWall(xx*32, yy*32, 0, ID.BreakableWall, handler));

                if (green == 255 && blue == 255)
                    handler.addObject(new PowerUp1(xx*32,yy*32, 0, ID.PowerUp1));

            }
        }
    }

    public static void main(String args[]){
        new Game();
    }
}
