package UI;

import GameState.GameStateManager;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    public static final int WIDTH = 440;
    public static final int HEIGHT = 260;
    public static final int SCALE = 2;

    private Thread thread;
    private boolean running;
    private int FPS = 60;
    private long targetTime = 1000/FPS;

    private BufferedImage image;
    private Graphics2D g1;
    private GameStateManager gameStateManager;

    public GamePanel(){
        super();
        setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
        setFocusable(true);
        requestFocus();
    }
    public void addNotify(){
        super.addNotify();
        if (thread == null){
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }
    private void init(){
        image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
        g1 = (Graphics2D) image.getGraphics();
        running = true;
        gameStateManager = new GameStateManager();
    }
    public void run(){
        init();

        long start,elapsed,wait;

        while (running){
            start = System.nanoTime();

            update();
            draw();
            drawToScreen();

            elapsed = System.nanoTime() - start;

            wait = targetTime - elapsed/1000000;
            if (wait<0) wait = 5;
            try{
                Thread.sleep(wait);
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
    }
    public void update(){
        gameStateManager.update();
    }
    public void draw(){
        gameStateManager.draw(g1);
    }
    public void drawToScreen(){
        Graphics g2 = getGraphics();
        g2.drawImage(image,0,0,WIDTH*SCALE,HEIGHT*SCALE,null);
        g2.dispose();
    }

    public void keyTyped(KeyEvent key){

    }
    public void keyPressed(KeyEvent key){
        gameStateManager.keyPressed(key.getKeyCode());
    }
    public void keyReleased(KeyEvent key){
        gameStateManager.keyReleased(key.getKeyCode());
    }
}
