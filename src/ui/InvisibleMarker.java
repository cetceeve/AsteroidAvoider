package ui;

import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Ellipse;
import de.ur.mi.graphics.Rect;

/**
 * an invisible rectangle and a visible marker
 * (the two combined make up the class name, i hope it is not too confusing)
 * can be used like a button
 */
public class InvisibleMarker implements Clickable{
    private Rect rect;
    private Ellipse marker;

    public InvisibleMarker(int x, int y, int width, int height) {
        rect = new Rect(x, y, width, height, Color.WHITE);
        int markerSize = (int) rect.getHeight() - 25;
        marker = new Ellipse(rect.getX() + width + markerSize / 2, rect.getY() + rect.getHeight() / 2, markerSize, markerSize, Color.WHITE);
    }

    public void draw() {
        marker.draw();
    }

    // both the rectangle and the marker can be clicked on
    @Override
    public boolean hitTest(double x, double y) {
        return rect.hitTest(x, y) || marker.hitTest(x, y);
    }

    public void setColor(Color color) {
        marker.setColor(color);
    }
}
