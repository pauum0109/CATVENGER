/* 
+ Your Team Number: Group 6
+ Member names & IU code:
Tran Phuong Quang Huy - ITCSIU21071
Nguyen Thi Phuong Thao - ITITITIU21214
Nguyen Thi Anh Tho - ITCSIU21236
Bui Nhu Y - ITCSIU21247
+ Purpose: User Interface: 'Game Frame' represents the main game window, while the `GamePanel` class 
handles the rendering of game graphics and user input.
*/
package UI;

import javax.swing.JFrame;

public class GameFrame {
    public static void main(String[] args){
        JFrame window = new JFrame("CATVENTURE");
        window.setContentPane(new GamePanel());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.pack();
        window.setVisible(true);
    }
}
