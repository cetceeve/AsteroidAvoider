package world;

import constants.Constants;
import de.ur.mi.geom.Point;
import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Ellipse;

public class Obstacle implements Collidable {
    private Ellipse obstacle;
    private int obstacleSize;

    public Obstacle(int posX, int posY, int size) {
        obstacle = new Ellipse(posX, posY, size, size, Color.GRAY);
        obstacle.setBorder(Color.GRAY, 0.0);
        obstacleSize = size;
    }

    public void update() {
        obstacle.move(0, Constants.obstacleSpeed);
    }

    public void draw() {
        obstacle.draw();
    }

    public void recolor() {
        obstacle.setColor(Color.RED);
    }

    public void setPos(int posX, int poxY) {
        obstacle.setPosition(posX, poxY);
    }

    @Override
    public boolean hasCollidedWith(Collidable other) {
        return false;
    }

    @Override
    public boolean hasLeftScreen() {
        return obstacle.getY() >= Constants.CANVAS_HEIGHT + obstacleSize / 2;
    }

    @Override
    public boolean hitTest(Point[] hitBox) {
        return false;
    }

    @Override
    public Point[] getHitBox() {
        Point[] hitBox = new Point[4];
        hitBox[0] = new Point(obstacle.getX() - obstacleSize/2, obstacle.getY());
        hitBox[1] = new Point(obstacle.getX() + obstacleSize/2, obstacle.getY());
        hitBox[2] = new Point(obstacle.getX(), obstacle.getY() - obstacleSize/2);
        hitBox[3] = new Point(obstacle.getX(), obstacle.getY() + obstacleSize/2);
        return hitBox;
    }
}
