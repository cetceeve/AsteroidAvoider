package ui;

import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Rect;

public class InvisibleButton {
    private Rect rect;

    public InvisibleButton(int x, int y, int width, int height) {
        rect = new Rect(x, y, width, height, Color.WHITE);
    }

    public void draw() {
        //rect.draw();
    }

    public boolean hitTest(double x, double y) {
        return rect.hitTest(x, y);
    }
}
