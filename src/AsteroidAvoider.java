/**
 * Kurs: Objektorientierte Programmierung im WS 17/18
 * Studienleistung 2
 */

import de.ur.mi.graphics.Color;
import de.ur.mi.graphicsapp.*;
import game.GameManager;
import processing.event.KeyEvent;

public class AsteroidAvoider extends GraphicsApp {

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 960;
    private static final int FRAME_RATE = 60;

    private GameManager gameManager;

    public void setup() {
        setupCanvas();
        setupApplication();
    }

    private void setupApplication() {
        gameManager = new GameManager();
    }

    private void setupCanvas() {
        size(WIDTH, HEIGHT);
        frameRate(FRAME_RATE);
        smooth();
    }

    public void draw() {
        background(Color.LIGHT_GRAY);
        gameManager.update();
        gameManager.draw();
    }

    public void keyPressed(KeyEvent e) {
        // translate key presses into input commands for the game manager
    }
}
