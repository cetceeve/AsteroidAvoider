package world;

import constants.Constants;
import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Rect;
import de.ur.mi.util.RandomGenerator;

public class Level {
    private static final int PLAYER_START_X = 400;
    private static final int PLAYER_START_Y = 700;

    //private DeepSpace deepSpace;
    private Obstacle obstacles[];
    private Player player;
    private int currentObstacle = 0;
    private RandomGenerator randomGenerator;
    private int countDrawCalls = 0;

    public Level() {
        randomGenerator = RandomGenerator.getInstance();
        //deepSpace = new DeepSpace();
        obstacles = new Obstacle[Constants.TOTAL_OBSTACLE_NUM];
        createRow();
        player = new Player(PLAYER_START_X, PLAYER_START_Y);
    }

    public void update() {
        if ((++countDrawCalls * Constants.OBSTACLE_SPEED) % (Constants.VIRTUAL_GRID_HEIGHT * 2) == 0) {
            createRow();
            countDrawCalls = 0;
        }
        //deepSpace.update();
        player.update();
        for (int i = 0; i < Constants.TOTAL_OBSTACLE_NUM; i++) {
            try {
                if (player.hasCollidedWith(obstacles[i])) {
                    obstacles[i].recolor();
                }
                obstacles[i].update();
            } catch (NullPointerException e) {
                return;
            }

        }
    }

    public void draw() {
        //deepSpace.draw();
        player.draw();
        for (int i = 0; i < Constants.TOTAL_OBSTACLE_NUM; i++) {
            try {
                obstacles[i].draw();
            } catch (NullPointerException e) {
                return;
            }
        }
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

    private void createRow() {
        if (currentObstacle == Constants.TOTAL_OBSTACLE_NUM) {
            currentObstacle = 0;
        }
        for (int i = currentObstacle; i < currentObstacle + Constants.OBSTACLE_PER_ROW; i++) {
            if (obstacles[i] == null) {
                obstacles[i] = nextObstacle();
            }
        }
        currentObstacle += Constants.OBSTACLE_PER_ROW;
    }

    private Obstacle nextObstacle() {
        int obstacleSize = randomGenerator.nextInt(Constants.OBSTACLE_MIN_SIZE, Constants.VIRTUAL_GRID_HEIGHT);
        return new Obstacle(obstaclePosX(obstacleSize), obstaclePosY(), obstacleSize);
    }

    private int obstaclePosX(int obstacleSize) {
        int randomDeviationX = randomGenerator.nextInt(obstacleSize/2, Constants.VIRTUAL_GRID_WIDTH - obstacleSize/2);
        int virtualGridRow = randomGenerator.nextInt(0, Constants.VIRTUAL_GRID_COLUMN_NUM);
        return virtualGridRow * Constants.VIRTUAL_GRID_WIDTH + randomDeviationX;
    }

    private int obstaclePosY() {
        int randomDeviationY = randomGenerator.nextInt(0, Constants.VIRTUAL_GRID_HEIGHT);
        int virtalGridSpawnRow = -1 * Constants.VIRTUAL_GRID_HEIGHT;
        return virtalGridSpawnRow + randomDeviationY;
    }
}
