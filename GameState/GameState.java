/* 
+ Your Team Number: Group 6
+ Member names & IU code:
Tran Phuong Quang Huy - ITCSIU21071
Nguyễn Thi Phương Thao - ITITITIU21214
Nguyễn Thi Anh Tho - ITCSIU21236
Bui Như Y - ITCSIU21247
+ Purpose: Package GameState: Manage different stages of gameplay: MenuState, WinState, DieState,...
*/
package GameState;

public abstract class GameState {
    protected GameStateManager gameStateManager;
    public abstract void init();
    public abstract void update();
    public abstract void draw(java.awt.Graphics2D g);
    public abstract void keyPressed(int k);
    public abstract void keyReleased(int k);
}
