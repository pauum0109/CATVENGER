package GameState;

import java.awt.*;
import GameObject.*;
import UI.GamePanel;

public class LevelOneState extends GameState{
    private TileMap tileMap;

    public LevelOneState(GameStateManager gameStateManager){
        this.gameStateManager = gameStateManager;
        init();
    }
    public void init(){
        tileMap = new TileMap(30);
        tileMap.loadTiles("/Resources/Tilesets/grasstileset.gif");
        tileMap.loadTiles("/Resources/Map/Level1-1.map");
        tileMap.setPosition(0, 0);
    }
    public void update(){

    }
    public void draw(Graphics2D g){
        g.setColor(Color.WHITE);
        g.fillRect( 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
        tileMap.draw(g);
    }
    public void keyPressed(int k){

    }
    public void keyReleased(int k){

    }
}
