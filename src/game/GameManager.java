package game;

import world.Level;

public class GameManager implements GameEventListener{
    private static final int LEVEL_DATA[][] = new int[13][3];

    private Level level;
    private UserInterface userInterface;
    private boolean levelFullStop = false;
    private Integer passedObstacles = 0;
    private int levelNum = 0;

    public GameManager() {
        setupGameObjects();
    }

    private void setupGameObjects() {
        setLevelData();
        level = new Level(this, LEVEL_DATA[levelNum][0], LEVEL_DATA[levelNum][1], LEVEL_DATA[levelNum][2]);
        userInterface = new UserInterface();
    }

    public void update() {
        if (!levelFullStop) {
            level.update();
        }
        userInterface.update();
    }

    public void draw() {
        level.draw();
        userInterface.draw();
    }

    public void handleEvent(int inputEvent) {
        if (inputEvent < 6) {
            level.handleEvent(inputEvent);
        }
    }

    @Override
    public void playerCollided() {
        levelFullStop = true;
        // TODO: remove statement
        if (levelNum >= 13) {
            levelNum = 12;
        }
        if (levelNum < 13) {
            newLevel(levelNum);
        }
    }

    @Override
    public void playerPassed() {
        passedObstacles++;
        userInterface.setPassedObstacles(passedObstacles);
        if (passedObstacles % 100 == 0) {
            levelNum++;
            userInterface.setLevelNum(levelNum + 1);
            if (levelNum < 13) {
                newLevel(levelNum);
            }
        }
    }

    private void newLevel(int levelNum) {
        level = new Level(this, LEVEL_DATA[levelNum][0], LEVEL_DATA[levelNum][1], LEVEL_DATA[levelNum][2]);
        levelFullStop = false;
    }

    private void setLevelData() {
        LEVEL_DATA[0] = new int[]{2, 3, 4};
        LEVEL_DATA[1] = new int[]{3, 3, 4};
        LEVEL_DATA[2] = new int[]{4, 3, 4};
        LEVEL_DATA[3] = new int[]{5, 3, 4};
        LEVEL_DATA[4] = new int[]{4, 4, 5};
        LEVEL_DATA[5] = new int[]{5, 4, 5};
        LEVEL_DATA[6] = new int[]{3, 5, 5};
        LEVEL_DATA[7] = new int[]{4, 5, 6};
        LEVEL_DATA[8] = new int[]{5, 5, 6};
        LEVEL_DATA[9] = new int[]{6, 5, 6};
        LEVEL_DATA[10] = new int[]{4, 6, 7};
        LEVEL_DATA[11] = new int[]{5, 6, 7};
        LEVEL_DATA[12] = new int[]{6, 6, 7};
    }
}
