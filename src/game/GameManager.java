package game;

import constants.Constants;
import world.Level;

public class GameManager {

    private Level level;

    public GameManager() {
        setupGameObjects();
    }

    private void setupGameObjects() {
        level = new Level();
    }

    public void update() {
        level.update();
    }

    public void draw() {
        level.draw();
    }

    public void handleEvent(int inputEvent) {
        if (inputEvent < 6) {
            level.handleEvent(inputEvent);
        }
    }
}
