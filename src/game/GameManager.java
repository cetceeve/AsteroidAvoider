package game;

import constants.Constants;
import world.Level;
import world.Player;

public class GameManager implements GameEventListener{
    private static final int LEVEL_DATA[][] = new int[13][3];

    private Level level;
    private UserInterface userInterface;
    private Player player;
    private boolean levelFullStop = false;
    private Integer passedObstacles = 0;
    private int levelNum = 5;
    private boolean clearObstacles = false;

    public GameManager() {
        setupGameObjects();
    }

    private void setupGameObjects() {
        setLevelData();
        player = new Player(Constants.PLAYER_START_X, Constants.PLAYER_START_Y, LEVEL_DATA[levelNum][2]);
        level = new Level(this, player, LEVEL_DATA[levelNum][0], LEVEL_DATA[levelNum][1]);
        userInterface = new UserInterface();
    }

    public void update() {
        if (!levelFullStop) {
            level.update(clearObstacles);
            player.update();
        }
        userInterface.update();
    }

    public void draw() {
        level.draw();
        userInterface.draw();
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

    @Override
    public void playerCollided() {
        levelFullStop = true;
    }

    @Override
    public void playerPassed() {
        passedObstacles++;
        userInterface.setPassedObstacles(passedObstacles);
        if (passedObstacles + LEVEL_DATA[levelNum][0] * Constants.VIRTUAL_GRID_ROW_NUM == 100) {
            clearObstacles = true;
        }
        if (passedObstacles == 100) {
            clearObstacles = false;
            passedObstacles = 0;
            levelNum++;
            userInterface.setLevelNum(levelNum + 1);
            if (levelNum < 13) {
                player.setPlayerMovementSpeed(LEVEL_DATA[levelNum][2]);
                level.nextLevel(LEVEL_DATA[levelNum][0], LEVEL_DATA[levelNum][1]);
            }
        }
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
}
