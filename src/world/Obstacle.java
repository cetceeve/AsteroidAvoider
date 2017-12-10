package world;

import constants.Constants;
import de.ur.mi.geom.Point;
import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Ellipse;

public class Obstacle implements Collidable {
    private Ellipse obstacle;
    private int obstacleSpeed;

    public Obstacle(int posX, int posY, int obstacleSize, int obstacleSpeed) {
        obstacle = new Ellipse(posX, posY, obstacleSize, obstacleSize, Color.GRAY);
        obstacle.setBorder(Color.GRAY, 1.0);
        this.obstacleSpeed = obstacleSpeed;
    }

    public void update() {
        obstacle.move(0, obstacleSpeed);
    }

    public void draw() {
        obstacle.draw();
    }

    public void setPos(int posX, int poxY) {
        obstacle.setPosition(posX, poxY);
    }

    public void setSize(int obstacleSize) {
        obstacle.setSize(obstacleSize, obstacleSize);
    }

    @Override
    public boolean hasCollidedWith(Collidable other) {
        return hitTest(other.getHitBox());
    }

    @Override
    public boolean hasLeftScreen() {
        return obstacle.getY() >= Constants.CANVAS_HEIGHT + obstacle.getHeight() / 2;
    }

    @Override
    public boolean hitTest(Point[] hitBox) {
        for (int i = 0; i < hitBox.length; i++) {
            if (obstacle.hitTest(hitBox[i].getX(), hitBox[i].getY())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Point[] getHitBox() {
        return null;
    }
}
