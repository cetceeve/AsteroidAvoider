package game;

import constants.Constants;
import ui.AnimatedImage;
import ui.UserInterface;
import world.Level;
import world.Player;

public class GameManager implements GameEventListener {
    private static final int LEVEL_DATA[][] = new int[Constants.LEVEL_NUM][3];

    private Level level;
    private UserInterface userInterface;
    private Player player;
    private AnimatedImage animatedImageCollision;
    private AnimatedImage animatedImageYouWin;

    private Integer passedObstacles = 0;
    private int levelNum = 12;

    private boolean playerHasControl = true;
    private boolean lastLevelComplete = false;

    public GameManager() {
        setupGameObjects();
    }

    private void setupGameObjects() {
        setLevelData();
        player = new Player(Constants.PLAYER_START_X, Constants.PLAYER_START_Y, LEVEL_DATA[levelNum][2]);
        level = new Level(this, player, LEVEL_DATA[levelNum][0], LEVEL_DATA[levelNum][1]);
        userInterface = new UserInterface();
        userInterface.setLevelNum(levelNum + 1);
        animatedImageCollision = new AnimatedImage(-1 * Constants.STANDARD_IMAGE_HEIGHT, 200, Constants.STANDARD_IMAGE_WIDTH, Constants.STANDARD_IMAGE_HEIGHT, Constants.COLLISION_IMAGE_PATH);
        animatedImageYouWin = new AnimatedImage(-1 * Constants.STANDARD_IMAGE_HEIGHT, 200, Constants.STANDARD_IMAGE_WIDTH, Constants.STANDARD_IMAGE_HEIGHT, Constants.YOU_WIN_IMAGE_PATH);
    }

    public void update() {
        level.update();
        player.update();
        if (player.hasLeftScreen()) {
            player.setMovementDirection(0, 0);
            player.setPlayerMovementSpeed(0);
        }
        userInterface.update();
        animatedImageCollision.update();
        animatedImageYouWin.update();
    }

    public void draw() {
        level.draw();
        userInterface.draw();
        player.draw();
        animatedImageCollision.draw();
        animatedImageYouWin.draw();
    }

    @Override
    public void playerCollided() {
        playerHasControl = false;
        level.allowHitDetection(false);
        userInterface.hidePassCount(true);
        passedObstacles = 0;
        collisionAnimation();
    }

    @Override
    public void playerPassed() {
        passedObstacles++;
        if (passedObstacles + LEVEL_DATA[levelNum][0] * Constants.VIRTUAL_GRID_ROW_NUM == Constants.LEVEL_LENGTH) {
            level.setClearObstacles(true);
        }
        if (passedObstacles == Constants.LEVEL_LENGTH) {
            passedObstacles = 0;
            levelNum++;
            if (levelNum == Constants.LEVEL_NUM) {
                animatedImageYouWin.startAnimation(Constants.YOU_WIN_IMAGE_SPEED);
                lastLevelComplete = true;
            } else {
                startNextLevel();
                userInterface.setLevelNum(levelNum + 1);
            }
        }
        userInterface.setPassedObstacles(passedObstacles);
    }

    private void startNextLevel() {
        level.setClearObstacles(false);
        player.setPlayerMovementSpeed(LEVEL_DATA[levelNum][2]);
        level.nextLevel(LEVEL_DATA[levelNum][0], LEVEL_DATA[levelNum][1]);
    }

    private void collisionAnimation() {
        level.setClearObstacles(true);
        player.setPlayerMovementSpeed(LEVEL_DATA[levelNum][1]);
        player.setMovementDirection(0, 1);
        player.setCheckForWallCollision(false);
        animatedImageCollision.startAnimation(LEVEL_DATA[levelNum][1]);
    }

    private void resetLevel() {
        passedObstacles = 0;
        userInterface.setPassedObstacles(passedObstacles);
        userInterface.hidePassCount(false);
        animatedImageCollision.reset();
        level.allowHitDetection(true);
        resetPlayer();
        startNextLevel();
    }

    private void resetPlayer() {
        player.setMovementDirection(0, 0);
        player.setPosition(Constants.PLAYER_START_X, Constants.PLAYER_START_Y);
        player.setCheckForWallCollision(true);
        playerHasControl = true;
    }

    private void setLevelData() {
        LEVEL_DATA[0] = new int[]{2, 3, 4};
        LEVEL_DATA[1] = new int[]{3, 3, 4};
        LEVEL_DATA[2] = new int[]{4, 3, 4};
        LEVEL_DATA[3] = new int[]{5, 3, 4};
        LEVEL_DATA[4] = new int[]{3, 4, 5};
        LEVEL_DATA[5] = new int[]{4, 4, 5};
        LEVEL_DATA[6] = new int[]{3, 5, 6};
        LEVEL_DATA[7] = new int[]{4, 5, 6};
        LEVEL_DATA[8] = new int[]{5, 5, 6};
        LEVEL_DATA[9] = new int[]{6, 5, 6};
        LEVEL_DATA[10] = new int[]{4, 6, 7};
        LEVEL_DATA[11] = new int[]{5, 6, 7};
        LEVEL_DATA[12] = new int[]{3, 6, 7};
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
                if (!lastLevelComplete) {
                    resetLevel();
                }
                break;
            case (Constants.PLAYER_REPLAY_INPUT):
                lastLevelComplete = false;
                levelNum = 0;
                userInterface.setLevelNum(levelNum);
                animatedImageYouWin.reset();
                resetLevel();
                break;
            default:
                break;
        }
    }
}
