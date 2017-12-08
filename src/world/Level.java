package world;

import constants.Constants;
import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Rect;

public class Level {
    private static final int PLAYER_START_X = 400;
    private static final int PLAYER_START_Y = 700;

    private DeepSpace deepSpace;
    private Obstacle obstacle;
    private Player player;

    public Level() {
        deepSpace = new DeepSpace();
        obstacle = new Obstacle();
        player = new Player(PLAYER_START_X, PLAYER_START_Y);
    }

    public void update() {
        if (player.hasCollidedWith(obstacle)) {
            obstacle.recolor();
        }
        obstacle.hasLeftScreen();
        deepSpace.update();
        obstacle.update();
        player.update();
    }

    public void draw() {
        deepSpace.draw();
        obstacle.draw();
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
