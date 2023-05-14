package UI;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import GameState.GameStateManager;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    public static final int WIDTH = 320;
    public static final int HEIGHT = 240;
    public static final int SCALE = 2;

    private Thread thread;
    private boolean running;
    private int FPS = 60;
    private long targetTime = 1000/FPS;

    private BufferedImage image;
    private Graphics2D gr1;
    private GameStateManager gameStateManager;

    public GamePanel(){
        super();
        setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
        setFocusable(true);
        requestFocus();
    }
    public void addNoitify(){
        super.addNotify();
        if (thread == null){
            thread = new Thread(this);
            addKeyListener((this));
            thread.start();
        }
    }
    public void init(){
        image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
        gr1 = (Graphics2D) image.getGraphics();
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
        gameStateManager.draw(gr1);
    }
    public void drawToScreen(){
        Graphics gr2 = getGraphics();
        gr2.drawImage(image,0,0,null);
        gr2.dispose();
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
