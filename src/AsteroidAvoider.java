import de.ur.mi.graphics.Color;
import de.ur.mi.graphicsapp.*;
import java.awt.event.KeyEvent;

import game.GameManager;
import static constants.Constants.*;

public class AsteroidAvoider extends GraphicsApp {

    private GameManager gameManager;

    public void setup() {
        setupCanvas();
        setupApplication();
    }

    private void setupApplication() {
        gameManager = new GameManager();
    }

    private void setupCanvas() {
        size(CANVAS_WIDTH, CANVAS_HEIGHT);
        frameRate(FRAME_RATE);
        smooth();
    }

    public void draw() {
        background(Color.BLACK);
        gameManager.update();
        gameManager.draw();
    }

    public void keyPressed(KeyEvent e) {
        // translate key presses into input commands for the game manager
        switch (e.getKeyCode()) {
            case (KeyEvent.VK_W):
                gameManager.handleEvent(PLAYER_UP_INPUT);
                break;
            case (KeyEvent.VK_A):
                gameManager.handleEvent(PLAYER_LEFT_INPUT);
                break;
            case (KeyEvent.VK_S):
                gameManager.handleEvent(PLAYER_DOWN_INPUT);
                break;
            case (KeyEvent.VK_D):
                gameManager.handleEvent(PLAYER_RIGHT_INPUT);
                break;
            default:
                break;
        }
    }
}
