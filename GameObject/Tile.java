/* 
+ Your Team Number: Group 6
+ Member names & IU code:
Tran Phuong Quang Huy - ITCSIU21071
Nguyễn Thi Phương Thao - ITITITIU21214
Nguyễn Thi Anh Tho - ITCSIU21236
Bui Như Y - ITCSIU21247
+ Purpose: Package GameObject: Tile & TileMap to create map for game 
*/
package GameObject;

import java.awt.image.BufferedImage;

public class Tile {
    private BufferedImage image;
    private int type;
    public static final int NORMAL = 0;
    public static final int BLOCKED = 1;

    public Tile(BufferedImage image,int type){
        this.image = image;
        this.type = type;
    }
    public BufferedImage getImage(){
        return image;
    }
    public int getType(){
        return type;
    }
}
