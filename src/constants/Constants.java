package constants;

import game.GameEventListener;

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

    public static final int PLAYER_MOVEMENT_SPEED = 4;

    public static final int OBSTACLE_MIN_SIZE = 50;
    public static final int OBSTACLE_PER_ROW = 4;
    public static final int OBSTACLE_SPEED = 3;
    public static final int ROW_NUM = 5;
    public static final int TOTAL_OBSTACLE_NUM = OBSTACLE_PER_ROW * ROW_NUM;
    public static final int VIRTUAL_GRID_HEIGHT = 120;
    public static final int VIRTUAL_GRID_WIDTH = 160;
    public static final int VIRTUAL_GRID_COLUMN_NUM = 8;
}
