package game;

import constants.Constants;
import world.Level;

public class GameManager implements GameEventListener{

    private Level level;
    private boolean levelFullStop = false;

    public GameManager() {
        setupGameObjects();
    }

    private void setupGameObjects() {
        level = new Level(this);
    }

    public void update() {
        if (!levelFullStop) {
            level.update();
        }
    }

    public void draw() {
        level.draw();
    }

    public void handleEvent(int inputEvent) {
        if (inputEvent < 6) {
            level.handleEvent(inputEvent);
        }
    }

    @Override
    public void playerCollided() {
        levelFullStop = true;
    }

    @Override
    public void playerPassed() {
        //TODO: next level
    }
}
