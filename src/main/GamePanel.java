package main;

import inputs.KeyBoardInputs;
import inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs; //thanks to this object it will listen to one mouse input
    private float xDelta = 100, yDelta = 100;
    private BufferedImage img, subImg;


    public GamePanel(){ //cleaner constructor -> keyBoardInputs.java
        mouseInputs = new MouseInputs(this);//need to initialize otherwise we get errors :(

        importImg();

        setPanelSize();
        addKeyListener(new KeyBoardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/player_sprites.png");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280,800);
        setPreferredSize(size);
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

        //this will pick the pixel design of the specified position
        subImg = img.getSubimage(1*64,8*40,64,40);
        g.drawImage(subImg, (int) xDelta,(int) yDelta,128,80, null);
    }

}
