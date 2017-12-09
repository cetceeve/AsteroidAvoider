package constants;

public final class Constants{

    private Constants() {

    }

    public static final int CANVAS_WIDTH = 1280;
    public static final int CANVAS_HEIGHT = 960;
    public static final int FRAME_RATE = 60;

    public static final int PLAYER_UP_INPUT = 1;
    public static final int PLAYER_LEFT_INPUT = 2;
    public static final int PLAYER_DOWN_INPUT = 3;
    public static final int PLAYER_RIGHT_INPUT = 4;
    public static final int PLAYER_RESET_INPUT = 5;

    public static final int PLAYER_START_X = CANVAS_WIDTH/2 - 50;
    public static final int PLAYER_START_Y = CANVAS_HEIGHT - 200;

    public static final int OBSTACLE_MIN_SIZE = 20;
    public static final int VIRTUAL_GRID_HEIGHT = 120;
    public static final int VIRTUAL_GRID_WIDTH = 160;
    public static final int VIRTUAL_GRID_COLUMN_NUM = 8;
    public static final int VIRTUAL_GRID_ROW_NUM = 5;

    public static int playerMovementSpeed = 4;
    public static int obstaclePerRow = 4;
    public static int obstacleSpeed = 3;
    public static int totalObstacleNum = obstaclePerRow * VIRTUAL_GRID_ROW_NUM;

    public void setPlayerMovementSpeed(int plSpeed) {
        playerMovementSpeed = plSpeed;
    }

    public void setObstaclePerRow(int amount) {
        obstaclePerRow = amount;
    }

    public void setObstacleSpeed(int obSpeed) {
        obstacleSpeed = obSpeed;
    }
}
