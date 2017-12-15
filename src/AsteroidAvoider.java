import de.ur.mi.graphicsapp.*;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import game.GameManager;

import constants.Constants;
import ui.StartMenu;

public class AsteroidAvoider extends GraphicsApp {

    private GameManager gameManager;
    private StartMenu startMenu;

    public void setup() {
        setupCanvas();
        setupApplication();
    }

    private void setupApplication() {
        gameManager = new GameManager();
        startMenu = new StartMenu(gameManager);
    }

    private void setupCanvas() {
        size(Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT);
        frameRate(Constants.FRAME_RATE);
        smooth();
    }

    public void draw() {
        if (startMenu.getGameStart()) {
            gameManager.update();
            gameManager.draw();
        } else {
            startMenu.draw();
        }
    }

    public void keyPressed(KeyEvent e) {
        // translate key presses into input commands for the game manager
        switch (e.getKeyCode()) {
            case (KeyEvent.VK_W):
                gameManager.handleEvent(Constants.PLAYER_UP_INPUT);
                break;
            case (KeyEvent.VK_A):
                gameManager.handleEvent(Constants.PLAYER_LEFT_INPUT);
                break;
            case (KeyEvent.VK_S):
                gameManager.handleEvent(Constants.PLAYER_DOWN_INPUT);
                break;
            case (KeyEvent.VK_D):
                gameManager.handleEvent(Constants.PLAYER_RIGHT_INPUT);
                break;
            case (KeyEvent.VK_SPACE):
                gameManager.handleEvent(Constants.PLAYER_RESET_INPUT);
                break;
            case (KeyEvent.VK_ESCAPE):
                gameManager.handleEvent(Constants.PLAYER_PAUSE_INPUT);
                break;
            default:
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!startMenu.getGameStart()) {
            startMenu.handleMouseClick(e.getX(), e.getY());
        }
    }
}
