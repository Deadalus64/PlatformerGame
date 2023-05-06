package main;

import inputs.KeyBoardInputs;
import inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import static utilz.Constants.PlayerConstants.*; // we created it manually
import static utilz.Constants.Directions.*;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs; //thanks to this object it will listen to one mouse input
    private float xDelta = 100, yDelta = 100;
    private BufferedImage img;
    private BufferedImage[][] animations;
    private int animationTick, animationIndex, animationSpeed =15; //the lower the value the faster the animation
    private int playerAction = IDLE;
    private int playerDirection = -1; //idle = -1, moving = 0,1,2 or 3
    private boolean moving = false;

    public GamePanel(){ //cleaner constructor -> keyBoardInputs.java
        mouseInputs = new MouseInputs(this);//need to initialize otherwise we get errors :(

        importImg();
        loadAnimations();

        setPanelSize();
        addKeyListener(new KeyBoardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void loadAnimations() {
        animations = new BufferedImage[9][6]; //x as = 9, y as =6

        for (int k = 0; k< animations.length; k++) {
            for (int i = 0; i < animations[k].length; i++) {
                animations[k][i] = img.getSubimage(i*64, k*40, 64, 40);
            }
        }
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/player_sprites.png");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                is.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280,800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }
    public void setDirection(int direction){
        this.playerDirection = direction;
        moving = true;
    }
    public void setMoving(boolean moving){
        this.moving = moving;
    }

    private void updateAnimationTick() {

        animationTick++;
        if (animationTick >= animationSpeed){
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= GetSpriteAmount(playerAction)){
                animationIndex = 0;
            }
        }

    }

    private void setAnimation() {
        if (moving){
            playerAction = RUNNING;
        }else{
            playerAction = IDLE;
        }
    }
    private void updatePosition() {
        if (moving){
            switch(playerDirection){
                case LEFT:
                    xDelta = xDelta - 5;
                    break;
                case UP:
                    yDelta = yDelta - 5;
                    break;
                case RIGHT:
                    xDelta = xDelta + 5;
                    break;
                case DOWN:
                    yDelta = yDelta + 5;
                    break;
            }
        }
    }


    public void paintComponent(Graphics g){
        //to make images show up and stop any possible glitch
        super.paintComponent(g); // calling the super class jPanel
        updateAnimationTick();
        setAnimation();
        updatePosition();
        
        g.drawImage(animations[playerAction][animationIndex], (int) xDelta, (int) yDelta,256,160, null);


    }

}
