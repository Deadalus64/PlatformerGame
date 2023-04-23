package inputs;
import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoardInputs implements KeyListener {

    private GamePanel gamePanel; //globar variable

    public KeyBoardInputs(GamePanel gamePanel){
        //this constructor will take a gamePanel
        this.gamePanel = gamePanel;
        //this will make it so whenever we use the mouse,
        // it will change the value of the gamePanel class
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
                gamePanel.changeYDelta(-5);//UP
                break;
            case KeyEvent.VK_A:
                gamePanel.changeXDelta(-5);//LEFT
                break;
            case KeyEvent.VK_S:
                gamePanel.changeYDelta(5);//DOWN
                break;
            case KeyEvent.VK_D:
                gamePanel.changeXDelta(5);//RIGHT
                break;
        } //movements
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
