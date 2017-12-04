package game;

import de.ur.mi.graphics.Color;
import world.Level;
import world.Player;

public class GameManager {
    // use constants instead of 400, 700 placeholders
    private static final int PLAYER_WIDTH = (int) (0.1 * 400);
    private static final int PLAYER_HEIGHT = (int) (1.5 * PLAYER_WIDTH);

    private static final int PLAYER_START_X = 400 - PLAYER_WIDTH/2;
    private static final int PLAYER_START_Y = 700 - PLAYER_HEIGHT;

    public static final int PLAYER_LEFT_INPUT = 0;
    public static final int PLAYER_RIGHT_INPUT = 1;
    public static final int PLAYER_UP_INPUT = 2;
    public static final int PLAYER_DOWN_INPUT = 3;
    public static final int PLAYER_RESET_INPUT = 4;

    private Player player;
    private Level level;

    public GameManager() {
       setupGameObjects();
    }

    private void setupGameObjects() {
        level = new Level();
        player = new Player(PLAYER_START_X, PLAYER_START_Y, PLAYER_WIDTH, PLAYER_HEIGHT, Color.RED);
    }

    public void update() {
    }

    public void draw() {
        level.draw();
        player.draw();
    }

    public void handleEvent(int inputEvent) {
    }
}
