/* 
+ Your Team Number: Group 6
+ Member names & IU code:
Tran Phuong Quang Huy - ITCSIU21071
Nguyen Thi Phuong Thao - ITITITIU21214
Nguyen Thi Anh Tho - ITCSIU21236
Bui Nhu Y - ITCSIU21247
+ Purpose: Package Entity: Add the house in the end of game, when cat reach this, Game Win.
*/
package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import GameObject.TileMap;

public class Teleport extends MapObject {
	
	private BufferedImage[] sprites;
	
	public Teleport(TileMap tm) {
		super(tm);
		facingRight = true;
		width = height = 50;
		cwidth = 20;
		cheight = 20;
		try {
			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Resources/Home/nha.gif"));
			sprites = new BufferedImage[1];
			for(int i = 0; i < sprites.length; i++) {
				sprites[i] = spritesheet.getSubimage(i * width, 0, width, height);
			}
			animation.setFrames(sprites);
			animation.setDelay(1);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		animation.update();
	}
	
	public void draw(Graphics2D g) {
		super.draw(g);
	}
	
}
