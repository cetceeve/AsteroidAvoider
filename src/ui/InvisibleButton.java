package ui;

import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Rect;

/**
 * an invisible rectangle that can be used like a button
 */
public class InvisibleButton implements Clickable{
    private Rect rect;

    public InvisibleButton(int x, int y, int width, int height) {
        rect = new Rect(x, y, width, height, Color.WHITE);
    }

    @Override
    public boolean hitTest(double x, double y) {
        return rect.hitTest(x, y);
    }
}
