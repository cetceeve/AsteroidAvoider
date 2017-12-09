package game;

import constants.Constants;
import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Label;
import world.Level;

public class GameManager implements GameEventListener{

    private Level level;
    private boolean levelFullStop = false;
    private Label label;
    private Integer passedObstacles = 0;

    public GameManager() {
        setupGameObjects();
    }

    private void setupGameObjects() {
        level = new Level(this);
        label = new Label(10, 50, "0", Color.WHITE, 50);
    }

    public void update() {
        if (!levelFullStop) {
            level.update();
        }
        label.setText(passedObstacles.toString());
    }

    public void draw() {
        level.draw();
        label.draw();
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
    }
}
