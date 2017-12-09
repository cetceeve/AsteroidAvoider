package game;

import constants.Constants;
import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Label;
import world.Level;

public class GameManager implements GameEventListener{

    private Level level;
    private UserInterface userInterface;
    private boolean levelFullStop = false;
    private Integer passedObstacles = 0;

    public GameManager() {
        setupGameObjects();
    }

    private void setupGameObjects() {
        level = new Level(this);
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
    }

    @Override
    public void playerPassed() {
        passedObstacles++;
        userInterface.setPassedObstacles(passedObstacles);
    }
}
