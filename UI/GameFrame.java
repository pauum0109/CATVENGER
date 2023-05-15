package UI;

import javax.swing.JFrame;

public class GameFrame {
    public static void main(String[] args){
        JFrame window = new JFrame("CATVENGER");
        window.setContentPane(new GamePanel());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.pack();
        window.setVisible(true);
    }
}
