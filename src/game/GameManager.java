package game;

import constants.Constants;
import ui.AnimatedGraphics;
import ui.UserInterface;
import world.Level;
import world.Player;

public class GameManager implements GameEventListener {
    private static final int LEVEL_DATA[][] = new int[Constants.LEVEL_NUM][3];

    private Level level;
    private UserInterface userInterface;
    private Player player;
    private AnimatedGraphics animatedGraphics = null;

    private Integer passedObstacles = 0;
    private int levelNum = 4;

    private boolean playerHasControl = true;

    public GameManager() {
        setupGameObjects();
    }

    private void setupGameObjects() {
        setLevelData();
        player = new Player(Constants.PLAYER_START_X, Constants.PLAYER_START_Y, LEVEL_DATA[levelNum][2]);
        level = new Level(this, player, LEVEL_DATA[levelNum][0], LEVEL_DATA[levelNum][1]);
        userInterface = new UserInterface();
        userInterface.setLevelNum(levelNum + 1);
    }

    public void update() {
        level.update();
        player.update();
        if (player.hasLeftScreen()) {
            player.setMovementDirection(0, 0);
            player.setPlayerMovementSpeed(0);
        }
        userInterface.update();
        if (animatedGraphics != null) {
            animatedGraphics.update();
        }
    }

    public void draw() {
        level.draw();
        userInterface.draw();
        player.draw();
        if (animatedGraphics != null) {
            animatedGraphics.draw();
        }
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
            startNextLevel();
            userInterface.setLevelNum(levelNum + 1);
        }
        userInterface.setPassedObstacles(passedObstacles);
    }

    private void startNextLevel() {
        level.setClearObstacles(false);
        if (levelNum < Constants.LEVEL_NUM) {
            player.setPlayerMovementSpeed(LEVEL_DATA[levelNum][2]);
            level.nextLevel(LEVEL_DATA[levelNum][0], LEVEL_DATA[levelNum][1]);
        }
    }

    private void collisionAnimation() {
        level.setClearObstacles(true);
        player.setPlayerMovementSpeed(LEVEL_DATA[levelNum][1]);
        player.setMovementDirection(0, 1);
        player.setCheckForWallCollision(false);
        animatedGraphics = new AnimatedGraphics(-1 * Constants.COLLISION_IMAGE_HEIGHT, 330, Constants.COLLISION_IMAGE_WIDTH, Constants.COLLISION_IMAGE_HEIGHT, Constants.COLLISION_IMAGE_PATH);
        animatedGraphics.setAnimationSpeed(LEVEL_DATA[levelNum][1]);
    }

    private void resetLevel() {
        passedObstacles = 0;
        userInterface.setPassedObstacles(passedObstacles);
        userInterface.hidePassCount(false);
        animatedGraphics = null;
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
        LEVEL_DATA[12] = new int[]{6, 6, 7};
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
                resetLevel();
                break;
            case (Constants.PLAYER_REPLAY_INPUT):
                levelNum = 0;
                userInterface.setLevelNum(levelNum);
                resetLevel();
                break;
            default:
                break;
        }
    }
}
