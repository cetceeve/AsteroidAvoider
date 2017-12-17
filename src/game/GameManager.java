package game;

import constants.Constants;
import ui.AnimatedImage;
import ui.UserInterface;
import world.Level;
import world.Player;

/**
 * Main class for the behaviour of the game.
 * It tracks the players progress.
 *
 * The GameManager handles
 * - all events called from level
 * - all key inputs
 *
 * The GameManager controls:
 * - Level properties and behaviour
 * - Player representation and behaviour
 * - User Interface values and behaviour
 * - all AnimatedImages
 *
 * This class holds the values for each level.
 */
public class GameManager implements GameEventListener {
    private static final int LEVEL_DATA[][] = new int[Constants.MAX_LEVEL_NUM][3];

    private Level level;
    private UserInterface userInterface;
    private Player player;
    private AnimatedImage animatedImageCollision;
    private AnimatedImage animatedImageYouWin;

    private boolean trackPassedObstacles = true;
    private boolean gameIsPaused = true;
    private boolean playerHasControl = true;
    private boolean lastLevelIsComplete = false;
    // values for game progress
    private Integer passedObstacles = 0;
    private int levelNum = 0;
    // standard game mode if nothing is selected
    private Constants.Gamemode gamemode = Constants.Gamemode.challenge;

    public GameManager() {
        setLevelData();
        animatedImageCollision = new AnimatedImage(-1 * Constants.COLLISION_IMAGE_HEIGHT, Constants.CANVAS_HEIGHT / 2 - Constants.COLLISION_IMAGE_HEIGHT / 2, Constants.COLLISION_IMAGE_WIDTH, Constants.COLLISION_IMAGE_HEIGHT, Constants.COLLISION_IMAGE_PATH);
        animatedImageYouWin = new AnimatedImage(-1 * Constants.YOU_WIN_IMAGE_HEIGHT, Constants.CANVAS_HEIGHT / 2 - Constants.YOU_WIN_IMAGE_HEIGHT / 2, Constants.YOU_WIN_IMAGE_WIDTH, Constants.YOU_WIN_IMAGE_HEIGHT, Constants.YOU_WIN_IMAGE_PATH);
    }

    public void setupGameObjects() {
        player = new Player(Constants.PLAYER_START_X, Constants.PLAYER_START_Y, LEVEL_DATA[levelNum][2]);
        level = new Level(this, player, LEVEL_DATA[levelNum][0], LEVEL_DATA[levelNum][1]);
        userInterface = new UserInterface();
        userInterface.setLevelNum(levelNum + 1);
    }

    public void update() {
        if (!gameIsPaused) {
            level.update();
            player.update();
            // this if statements is probably unnecessary
            if (player.hasLeftScreen()) {
                player.setMovementDirection(0, 0);
                player.setPlayerMovementSpeed(0);
            }
            animatedImageCollision.update();
            animatedImageYouWin.update();
        }
        userInterface.update();
    }

    public void draw() {
        level.draw();
        player.draw();
        animatedImageCollision.draw();
        animatedImageYouWin.draw();
        userInterface.draw();
    }

    // game behaviour upon player collision
    @Override
    public void playerCollided() {
        trackPassedObstacles = false;
        playerHasControl = false;
        // make sure the collision animation is only called once
        level.disableHitDetection();
        collisionAnimation();
    }

    // tracking of player progress
    @Override
    public void playerPassed() {
        // disabled after collisions
        if (trackPassedObstacles) {
            passedObstacles++;
        }
        // This is the only change between the two gamemodes
        if (gamemode == Constants.Gamemode.challenge) {
            levelController();
        }
        userInterface.setPassedObstacles(passedObstacles);
    }

    // end + starts levels; triggers end screen upon completion of last level
    private void levelController() {
        // "LEVEL_DATA[levelNum][0] * Constants.VIRTUAL_GRID_ROW_NUM" this is the total number of obstacle instances
        if (passedObstacles + LEVEL_DATA[levelNum][0] * Constants.VIRTUAL_GRID_ROW_NUM == Constants.LEVEL_LENGTH) {
            level.disableObstacleReset();
        }
        if (passedObstacles == Constants.LEVEL_LENGTH) {
            passedObstacles = 0;
            levelNum++;
            if (levelNum == Constants.MAX_LEVEL_NUM) {
                endScreen();
            } else {
                startNextLevel();
                userInterface.setLevelNum(levelNum + 1);
            }
        }
    }

    private void startNextLevel() {
        player.setPlayerMovementSpeed(LEVEL_DATA[levelNum][2]);
        level.nextLevel(LEVEL_DATA[levelNum][0], LEVEL_DATA[levelNum][1]);
    }

    // full reset to level 1
    // only used when completing the last level
    private void resetGame() {
        animatedImageYouWin.reset();
        lastLevelIsComplete = false;
        levelNum = 0;
        userInterface.setLevelNum(levelNum + 1);
        resetLevel();
    }

    private void resetLevel() {
        animatedImageCollision.reset();
        resetPlayer();
        startNextLevel();
        /*
        startNextLevel() is called here
        to make sure all obstacles are cleared out
        before enabling hit detection and tracking progress again
        (this avoids a bunch of bugs)
         */
        level.enableHitDetection();
        trackPassedObstacles = true;
        passedObstacles = 0;
        userInterface.setPassedObstacles(passedObstacles);
    }

    private void resetPlayer() {
        player.newShip();
        player.setMovementDirection(0, 0);
        player.setPosition(Constants.PLAYER_START_X, Constants.PLAYER_START_Y);
        player.enableWallCollision();
        playerHasControl = true;
    }

    // player slides of screen with broken ship,
    // game-over image comes down from the top,
    // asteroids leave the screen
    private void collisionAnimation() {
        level.disableObstacleReset();
        player.setPlayerMovementSpeed(LEVEL_DATA[levelNum][1]);
        player.setMovementDirection(0, 1);
        player.disableWallCollision();
        player.crashedShip();
        userInterface.showToolTip(Constants.TOOL_TIP_COLLISION);
        animatedImageCollision.startAnimation(LEVEL_DATA[levelNum][1]);
    }

    private void endScreen() {
        // space triggers game reset instead of level reset
        lastLevelIsComplete = true;
        userInterface.showToolTip(Constants.TOOL_TIP_END_SCREEN);
        animatedImageYouWin.startAnimation(Constants.YOU_WIN_IMAGE_SPEED);
    }

    // number of obstacles per row | obstacle movement speed | player movement speed
    private void setLevelData() {
        LEVEL_DATA[0] = new int[]{3, 4, 5};
        LEVEL_DATA[1] = new int[]{4, 4, 5};
        LEVEL_DATA[2] = new int[]{3, 5, 6};
        LEVEL_DATA[3] = new int[]{4, 5, 6};
        LEVEL_DATA[4] = new int[]{5, 5, 6};
        LEVEL_DATA[5] = new int[]{3, 6, 7};
        LEVEL_DATA[6] = new int[]{4, 6, 7};
        LEVEL_DATA[7] = new int[]{5, 6, 7};
        LEVEL_DATA[8] = new int[]{3, 8, 9};
        LEVEL_DATA[9] = new int[]{4, 8, 9};
        LEVEL_DATA[10] = new int[]{5, 8, 9};
        LEVEL_DATA[11] = new int[]{3, 10, 11};
        LEVEL_DATA[12] = new int[]{4, 10, 11};
        LEVEL_DATA[13] = new int[]{5, 10, 11};
        LEVEL_DATA[14] = new int[]{3, 12, 13};
        LEVEL_DATA[15] = new int[]{4, 12, 13};
        LEVEL_DATA[16] = new int[]{3, 15, 16};
    }

    public void handleEvent(int inputEvent) {
        switch (inputEvent) {
            case (Constants.PLAYER_UP_INPUT):
                if (playerHasControl) {
                    player.moveUp();
                }
                break;
            case (Constants.PLAYER_LEFT_INPUT):
                if (playerHasControl) {
                    player.moveLeft();
                }
                break;
            case (Constants.PLAYER_DOWN_INPUT):
                if (playerHasControl) {
                    player.moveDown();
                }
                break;
            case (Constants.PLAYER_RIGHT_INPUT):
                if (playerHasControl) {
                    player.moveRight();
                }
                break;
            case (Constants.PLAYER_RESET_INPUT):
                // multiple use of spacebar
                userInterface.hideToolTip();
                if (gameIsPaused) {
                    gameIsPaused = false;
                } else if (lastLevelIsComplete) {
                    resetGame();
                } else {
                    resetLevel();
                }
                break;
            case (Constants.PLAYER_PAUSE_INPUT):
                userInterface.showToolTip(Constants.TOOL_TIP_GAME_PAUSED);
                gameIsPaused = true;
                break;
            default:
                break;
        }
    }

    public void setGamemode(Constants.Gamemode gamemode) {
        this.gamemode = gamemode;
    }

    public void setLevelNum(int levelNum) {
        this.levelNum = levelNum;
    }
}
