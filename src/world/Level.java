package world;

import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Rect;

public class Level {
    public Level() {
    }

    public void draw() {
        // placeholder Rect, use constants and own class for Road
        Rect road = new Rect(100, 0, 600, 800, Color.DARK_GRAY);
        road.draw();
    }
}
