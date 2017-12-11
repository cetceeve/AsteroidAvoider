package world;

import constants.Constants;
import de.ur.mi.util.RandomGenerator;
import game.GameEventListener;

public class Level {

    private GameEventListener gameManager;
    private Collidable player;
    private DeepSpace deepSpace;
    private RandomGenerator randomGenerator;
    private Obstacle obstacles[];

    private int obstaclesPerRow;
    private int obstacleSpeed;

    private int updateCallCounter = 0;
    private int currentObstacle = 0;
    private int currentRow = 0;
    private boolean clearObstacles = false;
    private boolean allowHitDetection = true;

    public Level(GameEventListener gameManager, Collidable player, int obstaclesPerRow, int obstacleSpeed) {
        this.gameManager = gameManager;
        this.obstaclesPerRow = obstaclesPerRow;
        this.obstacleSpeed = obstacleSpeed;
        this.player = player;
        deepSpace = new DeepSpace(obstacleSpeed);
        randomGenerator = RandomGenerator.getInstance();
        // start level
        initNextObstacleArray();
        nextRow();
    }

    public void update() {
        updateCallCounter++;
        deepSpace.update();
        if (rowCreationController()) {
            nextRow();
            // counter can be reset
            updateCallCounter = 0;
        }
        for (int i = 0; i < obstacles.length; i++) {
            try {
                obstacles[i].update();
                if (allowHitDetection) {
                    if (obstacles[i].hasCollidedWith(player)) {
                        gameManager.playerCollided();
                    }
                }
                if (obstacles[i].hasLeftScreen()) {
                    if (clearObstacles) {
                        obstacles[i] = null;
                    } else {
                        resetObstacle(obstacles[i]);
                    }
                    // must be last statement!
                    gameManager.playerPassed();
                }
            } catch (NullPointerException e) {
            }
        }
    }

    public void draw() {
        deepSpace.draw();
        for (int i = 0; i < obstacles.length; i++) {
            try {
                obstacles[i].draw();
            } catch (NullPointerException e) {
            }
        }
    }

    public void nextLevel(int obstaclesPerRow, int obstacleSpeed) {
        resetRowCreationController();
        this.obstaclesPerRow = obstaclesPerRow;
        this.obstacleSpeed = obstacleSpeed;
        deepSpace.setObstacleSpeed(obstacleSpeed);
        initNextObstacleArray();
    }

    private void initNextObstacleArray() {
        obstacles = new Obstacle[obstaclesPerRow * Constants.VIRTUAL_GRID_ROW_NUM];
    }

    private void nextRow() {
        for (int i = currentObstacle; i < currentObstacle + obstaclesPerRow; i++) {
            // security
            if (obstacles[i] == null) {
                obstacles[i] = nextObstacle();
            }
        }
        currentObstacle += obstaclesPerRow;
        currentRow++;
    }

    private Obstacle nextObstacle() {
        int obstacleSize = nextObstacleSize();
        return new Obstacle(nextObstaclePosX(obstacleSize), nextObstaclePosY(), obstacleSize, obstacleSpeed);
    }

    private void resetObstacle(Obstacle obstacle) {
        int obstacleSize = nextObstacleSize();
        obstacle.setPos(nextObstaclePosX(obstacleSize), nextObstaclePosY());
        obstacle.setSize(obstacleSize);
    }

    private int nextObstacleSize() {
        return randomGenerator.nextInt(Constants.OBSTACLE_MIN_SIZE, Constants.OBSTACLE_MAX_SIZE);
    }

    private int nextObstaclePosX(int obstacleSize) {
        int randomDeviationX = randomGenerator.nextInt(obstacleSize/2, Constants.VIRTUAL_GRID_WIDTH - obstacleSize/2);
        int virtualGridRow = randomGenerator.nextInt(0, Constants.VIRTUAL_GRID_COLUMN_NUM);
        return virtualGridRow * Constants.VIRTUAL_GRID_WIDTH + randomDeviationX;
    }

    private int nextObstaclePosY() {
        int randomDeviationY = randomGenerator.nextInt(0, Constants.VIRTUAL_GRID_HEIGHT);
        int virtalGridSpawnRow = -1 * Constants.VIRTUAL_GRID_HEIGHT * 2;
        return virtalGridSpawnRow + randomDeviationY;
    }

    private boolean rowCreationController() {
        boolean rowNecessary = currentRow < Constants.VIRTUAL_GRID_ROW_NUM;
        boolean correctDistanceFromLastRow = (updateCallCounter * obstacleSpeed) % (Constants.VIRTUAL_GRID_ROW_SPACING) == 0;
        return rowNecessary && correctDistanceFromLastRow;
    }

    private void resetRowCreationController() {
        updateCallCounter = 0;
        currentObstacle = 0;
        currentRow = 0;
    }

    public void setClearObstacles(boolean clearObstacles) {
        this.clearObstacles = clearObstacles;
    }

    public void allowHitDetection(boolean allowHitDetection) {
        this.allowHitDetection = allowHitDetection;
    }
}
