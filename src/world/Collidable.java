package world;

import de.ur.mi.geom.Point;

public interface Collidable {
    public boolean hasCollidedWith(Collidable other);
    public boolean hasLeftScreen();
    public boolean hitTest(Point[] hitBox);
    public Point[] getHitBox();
}
