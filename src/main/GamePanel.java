package main;

import inputs.KeyBoardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs; //thanks to this object it will listen to one mouse input
    private float xDelta = 100, yDelta = 100;
    private float xDir = 1f, yDir = 1f;
    private int frames = 0;
    private long lastCheck = 0;
    private Color color = new Color(34,100,73);
    private Random random;

    public GamePanel(){ //cleaner constructor -> keyBoardInputs.java
        random = new Random();
        mouseInputs = new MouseInputs(this);//need to initialize otherwise we get errors :(
        addKeyListener(new KeyBoardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }


    public void changeXDelta(int value) {
        this.xDelta = this.xDelta + value;

    }
    public void changeYDelta(int value) {
        this.yDelta = this.yDelta + value;

    }
    public void setRectPos(int x, int y){

        this.xDelta = x;
        this.yDelta = y;

    }

    public void paintComponent(Graphics g){
        //to make images show up and stop any possible glitch
        super.paintComponent(g); // calling the super class jPanel

        updateRectangle();

          g.setColor(color);
        // we need to take the panel and put it in frames (JPanes -> JFrame)
          g.fillRect((int) xDelta,(int) yDelta,220, 80);

    }
    private void updateRectangle(){
        xDelta+= xDir;
        if (xDelta > 400 || xDelta < 0){
            xDir *= -1; // the direction will be changed
            color = getRandomColor();
        }
        yDelta+= yDir;
        if (yDelta > 400 || yDelta < 0){
            yDir *= -1;
            color = getRandomColor();
        }
    }

    private Color getRandomColor() {
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return new Color(r,g,b);
    }
}
