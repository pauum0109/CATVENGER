package GameState;

import UI.GamePanel;
import GameObject.*;

import java.awt.*;

public class LevelOneState extends GameState {
	
	private TileMap tileMap;
	
	public LevelOneState(GameStateManager gsm) {
		this.gameStateManager = gsm;
		init();
	}
	
	public void init() {
		
		tileMap = new TileMap(30);
		tileMap.loadTiles("/Resources/Tilesets/grasstileset.gif");
		tileMap.loadMap("/Resources/Maps/level1-1.map");
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
