import de.ur.mi.graphicsapp.*;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import game.GameManager;

import constants.Constants;
import ui.StartMenu;

/**
 * main class of the application
 * displays either a start menu or a game manager
 * deals with all system inputs
 */
public class AsteroidAvoider extends GraphicsApp {

    private GameManager gameManager;
    private StartMenu startMenu;

    public void setup() {
        setupCanvas();
        setupApplication();
    }

    // the game manager hosts the game itself
    // the start menu is used to set up certain game parameters
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
        // switch from start menu to game manager
        if (startMenu.getGameStart()) {
            gameManager.update();
            gameManager.draw();
        } else {
            startMenu.draw();
        }
    }

    // translate key presses into input commands for the game manager
    public void keyPressed(KeyEvent e) {
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

    // translate mouse input
    // mouse input is only used in start menu
    @Override
    public void mousePressed(MouseEvent e) {
        if (!startMenu.getGameStart()) {
            startMenu.handleMouseClick(e.getX(), e.getY());
        }
    }
}
