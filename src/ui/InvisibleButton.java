package ui;

import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Ellipse;
import de.ur.mi.graphics.Rect;

public class InvisibleButton {
    private Rect rect;
    private Ellipse marker;

    public InvisibleButton(int x, int y, int width, int height) {
        rect = new Rect(x, y, width, height, Color.WHITE);
        int markerSize = (int)rect.getHeight() - 25;
        marker = new Ellipse(rect.getX() + width + markerSize/2, rect.getY() + rect.getHeight()/2, markerSize, markerSize, Color.WHITE);
    }

    public void draw() {
        //rect.draw();
        marker.draw();
    }

    public boolean hitTest(double x, double y) {
        return rect.hitTest(x, y) || marker.hitTest(x, y);
    }

    public void setColor(Color color) {
        marker.setColor(color);
    }
}
