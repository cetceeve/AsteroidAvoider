package world;

import constants.Constants;
import de.ur.mi.util.RandomGenerator;
import game.GameEventListener;
import java.util.ArrayList;

public class Level {

    private GameEventListener gameManager;
    private Collidable player;
    private DeepSpace deepSpace;
    private RandomGenerator randomGenerator;
    private ArrayList<Obstacle> obstacles;

    private int obstaclesPerRow;
    private int obstacleSpeed;

    private int updateCallCounter = 0;
    private int currentRow = 0;
    private boolean clearObstacles = false;
    private boolean useHitDetection = true;

    public Level(GameEventListener gameManager, Collidable player, int obstaclesPerRow, int obstacleSpeed) {
        this.gameManager = gameManager;
        this.obstaclesPerRow = obstaclesPerRow;
        this.obstacleSpeed = obstacleSpeed;
        this.player = player;
        deepSpace = new DeepSpace(obstacleSpeed);
        randomGenerator = RandomGenerator.getInstance();
        obstacles  = new ArrayList<>();
        // start level
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
        for (int i = 0; i < obstacles.size(); i++) {
            try {
                obstacles.get(i).update();
                if (useHitDetection) {
                    if (obstacles.get(i).hasCollidedWith(player)) {
                        gameManager.playerCollided();
                    }
                }
                if (obstacles.get(i).hasLeftScreen()) {
                    if (clearObstacles) {
                            obstacles.remove(i);
                    } else {
                        resetObstacle(obstacles.get(i));
                    }
                    // must be last statement!
                    gameManager.playerPassed();
                }
            } catch (IndexOutOfBoundsException e) {
                // this catches a very rare bug that can occur on Level-Reset if
                // obstacles.clear() (line 78) is faster than execution of level.update()
                System.out.println(e.getMessage());
            }
        }
    }

    public void draw() {
        deepSpace.draw();
        for (int i = 0; i < obstacles.size(); i++) {
            obstacles.get(i).draw();
        }
    }

    public void nextLevel(int obstaclesPerRow, int obstacleSpeed) {
        clearObstacles = false;
        obstacles.clear();
        deepSpace.setObstacleSpeed(obstacleSpeed);
        resetRowCreationController();
        this.obstaclesPerRow = obstaclesPerRow;
        this.obstacleSpeed = obstacleSpeed;
    }

    private void nextRow() {
        for (int i = 0; i < obstaclesPerRow; i++) {
            obstacles.add(nextObstacle());
        }
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
        currentRow = 0;
    }

    public void clearObstacles() {
        clearObstacles = true;
    }

    public void enableHitDetection() {
        useHitDetection = true;
    }

    public void disableHitDetection() {
        useHitDetection = false;
    }
}
