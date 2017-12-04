package world;

public interface Collidable {
    public boolean hasCollidedWith(Collidable other);
    public boolean hasLeftScreen(int canvasWidth, int canvasHeight);
    public boolean hitTest(double x, double y);
}
