package world;

import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Rect;

public class Level {
    private DeepSpace deepSpace;

    public Level() {
        deepSpace = new DeepSpace();
    }

    public void update() {
        deepSpace.update();
    }

    public void draw() {
        deepSpace.draw();
    }
}
