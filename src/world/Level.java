package world;

import constants.Constants;
import de.ur.mi.util.RandomGenerator;
import game.GameEventListener;

public class Level {

    private RandomGenerator randomGenerator;
    private DeepSpace deepSpace;
    private Obstacle obstacles[];
    private Player player;
    private GameEventListener gameManager;

    private int obstaclesPerRow;
    private int totalObstacleNum;
    private int obstacleSpeed;

    private int currentObstacle = 0;
    private int countDrawCalls = 0;
    private int countRowCreation = 0;

    public Level(GameEventListener gameManager, Player player, int obstaclesPerRow, int obstacleSpeed) {
        this.gameManager = gameManager;
        this.obstaclesPerRow = obstaclesPerRow;
        this.obstacleSpeed = obstacleSpeed;
        randomGenerator = RandomGenerator.getInstance();
        deepSpace = new DeepSpace(obstacleSpeed);
        initObstacleArray();
        createRow();
        this.player = player;
        //player = new Player(Constants.PLAYER_START_X, Constants.PLAYER_START_Y, playerMovementSpeed);
    }

    private void initObstacleArray() {
        totalObstacleNum = obstaclesPerRow * Constants.VIRTUAL_GRID_ROW_NUM;
        obstacles = new Obstacle[totalObstacleNum];
    }

    public void update(boolean clearObstacles) {
        if ((++countDrawCalls * obstacleSpeed) % (Constants.VIRTUAL_GRID_HEIGHT * 2) == 0 && countRowCreation < Constants.VIRTUAL_GRID_ROW_NUM) {
            createRow();
            countRowCreation++;
            countDrawCalls = 0;
        }
        deepSpace.update();
        //player.update();
        for (int i = 0; i < totalObstacleNum; i++) {
            try {
                obstacles[i].update();
                if (obstacles[i].hasCollidedWith(player)) {
                    gameManager.playerCollided();
                }
                if (obstacles[i].hasLeftScreen() && !clearObstacles) {
                    int obstacleSize = randomGenerator.nextInt(Constants.OBSTACLE_MIN_SIZE, Constants.VIRTUAL_GRID_HEIGHT);
                    obstacles[i].setPos(obstaclePosX(obstacleSize), obstaclePosY());
                    obstacles[i].setObstacleSize(obstacleSize);
                    gameManager.playerPassed();
                }
                if (obstacles[i].hasLeftScreen() && clearObstacles) {
                    obstacles[i] = null;
                    gameManager.playerPassed();
                }
            } catch (NullPointerException e) {
            }
        }
    }

    public void draw() {
        deepSpace.draw();
        //player.draw();
        for (int i = 0; i < totalObstacleNum; i++) {
            try {
                obstacles[i].draw();
            } catch (NullPointerException e) {
            }
        }
    }

    /*
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
    */

    public void nextLevel(int obstaclesPerRow, int obstacleSpeed) {
        this.obstaclesPerRow = obstaclesPerRow;
        this.obstacleSpeed = obstacleSpeed;
        deepSpace.setObstacleSpeed(obstacleSpeed);
        initObstacleArray();
        currentObstacle = 0;
        countDrawCalls = 0;
        countRowCreation = 0;
    }

    private void createRow() {
        if (currentObstacle == totalObstacleNum) {
            currentObstacle = 0;
        }
        for (int i = currentObstacle; i < currentObstacle + obstaclesPerRow; i++) {
            if (obstacles[i] == null) {
                obstacles[i] = nextObstacle();
            }
        }
        currentObstacle += obstaclesPerRow;
    }

    private Obstacle nextObstacle() {
        int obstacleSize = randomGenerator.nextInt(Constants.OBSTACLE_MIN_SIZE, Constants.OBSTACLE_MAX_SIZE);
        return new Obstacle(obstaclePosX(obstacleSize), obstaclePosY(), obstacleSize, obstacleSpeed);
    }

    private int obstaclePosX(int obstacleSize) {
        int randomDeviationX = randomGenerator.nextInt(obstacleSize/2, Constants.VIRTUAL_GRID_WIDTH - obstacleSize/2);
        int virtualGridRow = randomGenerator.nextInt(0, Constants.VIRTUAL_GRID_COLUMN_NUM);
        return virtualGridRow * Constants.VIRTUAL_GRID_WIDTH + randomDeviationX;
    }

    private int obstaclePosY() {
        int randomDeviationY = randomGenerator.nextInt(0, Constants.VIRTUAL_GRID_HEIGHT);
        int virtalGridSpawnRow = -1 * Constants.VIRTUAL_GRID_HEIGHT * 2;
        return virtalGridSpawnRow + randomDeviationY;
    }
}
