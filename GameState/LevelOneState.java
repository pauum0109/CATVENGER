package GameState;

import Audio.AudioPlayer;
import Entity.*;
import GameObject.*;
import UI.GamePanel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class LevelOneState extends GameState {
	private TileMap tileMap;
    private Background bg;
	private Player player;
    private ArrayList<Enemy> enemies;
	private ArrayList<Explosion> explosions;
	private HUD hud;
	private AudioPlayer bgMusic;
	private Teleport teleport;

	private int eventCount = 0;
	private ArrayList<Rectangle> tb;
	private boolean eventFinish;
	private boolean eventDead;


	public LevelOneState(GameStateManager gameStateManager) {
		this.gameStateManager = gameStateManager;
		init();
	}
	
	public void init() {
		tileMap = new TileMap(30);
		tileMap.loadTiles("/Resources/Tilesets/grasstileset.gif");
		tileMap.loadMap("/Resources/Maps/level1-1.map");
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);
        bg = new Background("/Resources/Backgrounds/grassbg1.gif",0.1);
        player = new Player(tileMap);
		player.setPosition(100, 100);
		populateEnemies();
		explosions = new ArrayList<Explosion>();
		hud = new HUD(player);
		// teleport
		teleport = new Teleport(tileMap);
		teleport.setPosition(200, 190);

		bgMusic = new AudioPlayer("/Resources/Music/level1-1.mp3");
		bgMusic.play();
	}
    private void populateEnemies() {
		
		enemies = new ArrayList<Enemy>();
		Rat r;
		Saw s;
		Point[] pointsRat = new Point[] {new Point(1525, 200),new Point(1680, 200),new Point(1800, 200)};
		Point[] pointsSaw = new Point[] {new Point(860, 200)};
		for(int i = 0; i < pointsRat.length; i++) {
			r = new Rat(tileMap);
			r.setPosition(pointsRat[i].x, pointsRat[i].y);
			enemies.add(r);
		}
		for(int i = 0; i < pointsSaw.length; i++) {
			s = new Saw(tileMap);
			s.setPosition(pointsSaw[i].x, pointsSaw[i].y);
			enemies.add(s);
		}
	}
    public void update() {
		
		// check if end of level event should start
		if(teleport.intersects(player)) {
			eventFinish = true;
		}
		// check if player dead event should start
		if(player.getHealth() == 0 || player.gety() > (GamePanel.HEIGHT-30)) {
			eventDead = true;
		}
		// play events
		if(eventDead) eventDead();
		if(eventFinish) eventFinish();
		// update player
		player.update();
		tileMap.setPosition(
			GamePanel.WIDTH / 2 - player.getx(),
			GamePanel.HEIGHT / 2 - player.gety()
		);
		tileMap.fixBounds();
		// set background
		bg.setPosition(tileMap.getx(), tileMap.gety());
		
		// attack enemies
		player.checkAttack(enemies);
		
		// update all enemies
		for(int i = 0; i < enemies.size(); i++) {
			Enemy e = enemies.get(i);
			e.update();
			if(e.isDead()) {
				enemies.remove(i);
				i--;
				explosions.add(
					new Explosion(e.getx(), e.gety()));
			}
		}
		
		// update explosions
		for(int i = 0; i < explosions.size(); i++) {
			explosions.get(i).update();
			if(explosions.get(i).shouldRemove()) {
				explosions.remove(i);
				i--;
			}
		}

		teleport.update();
	}
	
	public void draw(Graphics2D g) {
		
		// draw bg
		bg.draw(g);
		// draw tilemap
		tileMap.draw(g);
		// draw player
		player.draw(g);
		// draw enemies
		for(int i = 0; i < enemies.size(); i++) enemies.get(i).draw(g);
		// draw explosions
		for(int i = 0; i < explosions.size(); i++) {
			explosions.get(i).setMapPosition(
				(int)tileMap.getx(), (int)tileMap.gety());
			explosions.get(i).draw(g);
		}
		// draw hud
		hud.draw(g);
		
		// draw teleport
		teleport.draw(g);
		// draw transition boxes
		g.setColor(java.awt.Color.BLACK);
		for(int i = 0; i < tb.size(); i++) {
			g.fill(tb.get(i));
		}
	}
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_LEFT) player.setLeft(true);
		if(k == KeyEvent.VK_RIGHT) player.setRight(true);
		if(k == KeyEvent.VK_UP) player.setUp(true);
		if(k == KeyEvent.VK_DOWN) player.setDown(true);
		if(k == KeyEvent.VK_W) player.setJumping(true);
		if(k == KeyEvent.VK_E) player.setGliding(true);
		if(k == KeyEvent.VK_R) player.setScratching();
		if(k == KeyEvent.VK_F) player.setFiring();
	}
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_LEFT) player.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) player.setRight(false);
		if(k == KeyEvent.VK_UP) player.setUp(false);
		if(k == KeyEvent.VK_DOWN) player.setDown(false);
		if(k == KeyEvent.VK_W) player.setJumping(false);
		if(k == KeyEvent.VK_E) player.setGliding(false);
	}

	// reset level
	private void reset() {
		player.reset();
		player.setPosition(300, 161);
		populateEnemies();
		eventCount = 0;
	}
	// player has died
	private void eventDead() {
		eventCount++;
		if(eventCount == 1) {
		if(player.getLives() == 0) {
				gameStateManager.setState(GameStateManager.DIESTATE);
			}
			else {
				eventDead = false;
				eventCount = 0;
				player.loseLife();
				reset();
			}
		}
	}
	// finished level
	private void eventFinish() {
		eventCount++;
		if(eventCount == 1) {
            player.stop();
			gameStateManager.setState(GameStateManager.WINSTATE);
			
		}
		
	}
}
