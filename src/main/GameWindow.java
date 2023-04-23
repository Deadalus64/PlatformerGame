package main;

import javax.swing.*;

public class GameWindow {

    private JFrame jFrame;
    public GameWindow(GamePanel gamePanel){
        jFrame = new JFrame(); // our jFrame object

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(gamePanel);
        jFrame.setLocationRelativeTo(null); //window will be in the center of the screen
        jFrame.setResizable(false); // u can't resize window manually
        jFrame.pack(); //fits the size of window to our panel
        jFrame.setVisible(true);

    }
}
