package world;

import de.ur.mi.geom.Point;
import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Ellipse;
import static constants.Constants.*;

public class Obstacle implements Collidable {
    private Ellipse obstacle;

    public Obstacle() {
        obstacle = new Ellipse(600, -100, 100, 100, Color.GRAY);
    }

    public void update() {
        obstacle.move(0, 2);
    }

    public void draw() {
        obstacle.draw();
    }

    public void recolor() {
        obstacle.setColor(Color.RED);
    }

    @Override
    public boolean hasCollidedWith(Collidable other) {
        return false;
    }

    @Override
    public boolean hasLeftScreen() {
        if (obstacle.getY() >= CANVAS_HEIGHT + 50) {
            obstacle.setPosition(600, -100);
            return true;
        }
        return false;
    }

    @Override
    public boolean hitTest(Point[] hitBox) {
        return false;
    }

    @Override
    public Point[] getHitBox() {
        Point[] hitBox = new Point[4];
        hitBox[0] = new Point(obstacle.getX() - 50, obstacle.getY());
        hitBox[1] = new Point(obstacle.getX() + 50, obstacle.getY());
        hitBox[2] = new Point(obstacle.getX(), obstacle.getY() - 50);
        hitBox[3] = new Point(obstacle.getX(), obstacle.getY() + 50);
        return hitBox;
    }
}
