package world;

import constants.Constants;
import de.ur.mi.util.RandomGenerator;
import game.GameEventListener;
import java.util.ArrayList;

/**
 * This class controls the Level
 * specifically:
 * - Obstacle positioning
 * - Obstacle movement speed
 * - Particle movement speed
 * This class also checks for many game events ans sends information back to the game manger
 * specifically:
 * - player collisions with obstacles
 * - player passing an obstacle
 *
 * The Levels behaviour is controlled by the Game Manager
 */
public class Level {
    private GameEventListener gameManager;
    private Collidable player;
    private DeepSpace deepSpace;
    private RandomGenerator randomGenerator;
    private ArrayList<Obstacle> obstacles;

    // variables for level design
    private int obstaclesPerRow;
    private int obstacleSpeed;

    // variables for obstacleCreationController
    private int updateCallCounter = 0;
    private int currentRow = 0;

    // variables for level 'animations'
    private boolean useObstacleReset = true;
    private boolean useHitDetection = true;

    public Level(GameEventListener gameManager, Collidable player, int obstaclesPerRow, int obstacleSpeed) {
        randomGenerator = RandomGenerator.getInstance();
        this.gameManager = gameManager;
        this.obstaclesPerRow = obstaclesPerRow;
        this.obstacleSpeed = obstacleSpeed;
        // level Objects
        this.player = player;
        setupLevelObjects();
        // start level
        nextRow();
    }

    private void setupLevelObjects() {
        deepSpace = new DeepSpace(obstacleSpeed);
        obstacles  = new ArrayList<>();
    }

    ///////////////////////////////////////////////////////////
    /**
     * This is the main control method for the Level class.
     * From here obstacles get:
     * - created
     * - reset
     * - removed
     * The Level also checks if an obstacle has left the screen or collided with the player.
     * The Level communicates from here back to the Game Manager.
     */
    public void update() {
        updateCallCounter++;
        deepSpace.update();
        // create rows of obstacles if necessary
        if (rowCreationController()) {
            nextRow();
            // counter can be reset
            updateCallCounter = 0;
        }
        /*
        saving the obstacle locally together with the try-catch-block are necessary to
        avoid a very rare, game breaking bug that can occur when levelReset() is called
        during the execution of this method
         */
        Obstacle obstacle;
        for (int i = 0; i < obstacles.size(); i++) {
            try {
                obstacle = obstacles.get(i);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                return;
            }
            obstacle.update();
            // check obstacle for collision and if it has left the screen
            if (useHitDetection) {
                if (obstacle.hasCollidedWith(player)) {
                    gameManager.playerCollided();
                }
            }
            if (obstacle.hasLeftScreen()) {
                if (useObstacleReset) {
                    resetObstacle(obstacle);
                } else {
                    obstacles.remove(i);
                }
                // must be last statement!
                gameManager.playerPassed();
            }

        }
    }

    ///////////////////////////////////////////////////////////
    public void draw() {
        deepSpace.draw();
        for (int i = 0; i < obstacles.size(); i++) {
            obstacles.get(i).draw();
        }
    }

    ///////////////////////////////////////////////////////////
    // called on level reset or when enough obstacles were passed
    public void nextLevel(int obstaclesPerRow, int obstacleSpeed) {
        // stop removing obstacles from array
        useObstacleReset = true;
        // clear out current obstacles
        obstacles.clear();
        // set new level parameters
        deepSpace.setObstacleSpeed(obstacleSpeed);
        this.obstaclesPerRow = obstaclesPerRow;
        this.obstacleSpeed = obstacleSpeed;
        // enable the creation of new rows
        // this actually "starts" a new level
        resetRowCreationController();
    }

    /*
    create one row of obstacles
    save every obstacle in the obstacle array list
    */
    private void nextRow() {
        for (int i = 0; i < obstaclesPerRow; i++) {
            obstacles.add(nextObstacle());
        }
        currentRow++;
    }

    // create one obstacle with random values
    private Obstacle nextObstacle() {
        int obstacleSize = nextObstacleSize();
        return new Obstacle(nextObstaclePosX(obstacleSize), nextObstaclePosY(), obstacleSize, obstacleSpeed);
    }

    // put obstacle back above the screen with new random values
    private void resetObstacle(Obstacle obstacle) {
        int obstacleSize = nextObstacleSize();
        obstacle.setPos(nextObstaclePosX(obstacleSize), nextObstaclePosY());
        obstacle.setSize(obstacleSize);
    }

    ///////////////////////////////////////////////////////////
    /**
     * HERE THE ACTUAL LEVEL DESIGN IS IMPLEMENTED using the idea of a virtual grid.
     * The virtual gird currently has 8 columns and 10 rows of which 5 hold Obstacles and 8 are always visible.
     *
     * The obstacles position in X direction is relatively controlled.
     * The obstacles positioning in Y direction gets continuously more random (see below),
     * which can result in unbeatable levels but this is rather rare.
     * I prioritised more random and challenging levels over definitely beatable levels.
     */
    /*
    Obstacles spawn on a random position inside one of the virtual columns.
    More than one Obstacle can spawn in the same virtual column.
     */
    private int nextObstaclePosX(int obstacleSize) {
        int randomDeviationX = randomGenerator.nextInt(obstacleSize/2, Constants.VIRTUAL_GRID_WIDTH - obstacleSize/2);
        int virtualGridRow = randomGenerator.nextInt(0, Constants.VIRTUAL_GRID_COLUMN_NUM);
        return virtualGridRow * Constants.VIRTUAL_GRID_WIDTH + randomDeviationX;
    }

    /*
    Obstacles are spawned inside the second virtual row above the screen.
    They have a random deviation dependent to the height of one virtual row.
    Note:
    This gets continuously more random because the Obstacle is repositioned as soon as it leaves the screen,
    not taking into account its original randomDeviationY.
     */
    private int nextObstaclePosY() {
        int randomDeviationY = randomGenerator.nextInt(0, Constants.VIRTUAL_GRID_HEIGHT);
        return -1 * Constants.VIRTUAL_GRID_HEIGHT - randomDeviationY;
    }

    private int nextObstacleSize() {
        return randomGenerator.nextInt(Constants.OBSTACLE_MIN_SIZE, Constants.OBSTACLE_MAX_SIZE);
    }

    ///////////////////////////////////////////////////////////
    // controller to determine if a new row needs to be created
    private boolean rowCreationController() {
        boolean rowNecessary = currentRow < Constants.VIRTUAL_GRID_ROW_NUM;
        // the current spacing basically populates every second virtual row
        // to keep the row distance equal in every level the obstacle movement speed is taken into account
        boolean correctDistanceFromLastRow = (updateCallCounter * obstacleSpeed) % (Constants.VIRTUAL_GRID_ROW_SPACING) == 0;
        return rowNecessary && correctDistanceFromLastRow;
    }

    private void resetRowCreationController() {
        updateCallCounter = 0;
        currentRow = 0;
    }

    ///////////////////////////////////////////////////////////
    public void disableObstacleReset() {
        useObstacleReset = false;
    }

    public void enableHitDetection() {
        useHitDetection = true;
    }

    public void disableHitDetection() {
        useHitDetection = false;
    }
}
