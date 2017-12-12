package constants;

public final class Constants{

    private Constants() {

    }

    public static final int CANVAS_WIDTH = 1280;
    public static final int CANVAS_HEIGHT = 960;
    public static final int FRAME_RATE = 60;

    public static final String PLAYER_IMAGE_PATH = "./data/assets/ship_bucc_low.png";
    public static final int PLAYER_IMAGE_WIDTH = 120;
    public static final int PLAYER_IMAGE_HEIGHT = 105;
    public static final String COLLISION_IMAGE_PATH = "./data/assets/collision.png";
    public static final int COLLISION_IMAGE_WIDTH = 855;
    public static final int COLLISION_IMAGE_HEIGHT = 330;
    public static final String YOU_WIN_IMAGE_PATH = "./data/assets/youwin.png";
    public static final int YOU_WIN_IMAGE_WIDTH = 606;
    public static final int YOU_WIN_IMAGE_HEIGHT = 80;
    public static final int YOU_WIN_IMAGE_SPEED = 6;
    public static final String PLAYER_CRASHED_IMAGE_PATH = "./data/assets/explo_bucc.png";
    public static final int PLAYER_CRASHED_IMAGE_WIDTH = 150;
    public static final int PLAYER_CRASHED_IMAGE_HEIGHT = 150;
    public static final String MAIN_MENU_IMAGE = "./data/assets/main_menu.png";

    public static final int PLAYER_UP_INPUT = 1;
    public static final int PLAYER_LEFT_INPUT = 2;
    public static final int PLAYER_DOWN_INPUT = 3;
    public static final int PLAYER_RIGHT_INPUT = 4;
    public static final int PLAYER_RESET_INPUT = 5;
    public static final int PLAYER_PAUSE_INPUT = 6;

    public static final int PLAYER_START_X = CANVAS_WIDTH/2 - PLAYER_IMAGE_WIDTH /2;
    public static final int PLAYER_START_Y = CANVAS_HEIGHT - 200;

    public static final int LEVEL_LENGTH = 100;
    public static final int MAX_LEVEL_NUM = 18;

    public static final int OBSTACLE_MIN_SIZE = 30;
    public static final int OBSTACLE_MAX_SIZE = 90;
    public static final int VIRTUAL_GRID_HEIGHT = 120;
    public static final int VIRTUAL_GRID_WIDTH = 160;
    public static final int VIRTUAL_GRID_COLUMN_NUM = 7;
    public static final int VIRTUAL_GRID_ROW_NUM = 5;
    public static final int VIRTUAL_GRID_ROW_SPACING = VIRTUAL_GRID_HEIGHT * 2;

    public static final String TOOL_TIP_COLLISION = "press [space] to restart level";
    public static final String TOOL_TIP_END_SCREEN = "press [space] to restart game";
    public static final String TOOL_TIP_GAME_PAUSED = "press [space] to continue";

    public enum Gamemode {
        challenge,
        endlessLevel
    }
}
