package constants;

public final class Constants{

    private Constants() {

    }

    public static final int CANVAS_WIDTH = 1280;
    public static final int CANVAS_HEIGHT = 960;
    public static final int FRAME_RATE = 60;

    public static final String IMAGE_PATH = "./data/assets/ship_bucc_low.png";
    public static final int IMAGE_WIDTH = 120;
    public static final int IMAGE_HEIGHT = 105;

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
}
