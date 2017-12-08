package game;

import constants.Constants;
import world.Level;
import world.Player;

public class GameManager {

    private static final int PLAYER_START_X = 400;
    private static final int PLAYER_START_Y = 700;

    private Player player;
    private Level level;

    public GameManager() {
        setupGameObjects();
    }

    private void setupGameObjects() {
        level = new Level();
        player = new Player(PLAYER_START_X, PLAYER_START_Y);
    }

    public void update() {
        player.update();
        level.update();
    }

    public void draw() {
        level.draw();
        player.draw();
    }

    public void handleEvent(int inputEvent) {
        switch (inputEvent) {
            case (Constants.PLAYER_UP_INPUT):
                player.moveUp();
                break;
            case (Constants.PLAYER_LEFT_INPUT):
                player.moveLeft();
                break;
            case (Constants.PLAYER_DOWN_INPUT):
                player.moveDown();
                break;
            case (Constants.PLAYER_RIGHT_INPUT):
                player.moveRight();
                break;
            default:
                break;
        }
    }
}
