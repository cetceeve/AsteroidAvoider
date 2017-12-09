package world;

import constants.Constants;
import de.ur.mi.geom.Point;
import de.ur.mi.graphics.GraphicsObject;
import de.ur.mi.graphics.Image;

public class Player extends GraphicsObject implements Collidable {
    public int playerMovementSpeed;
    private Image representation;
    private double movementDirX = 0;
    private double movementDirY = 0;

    public Player(double x, double y, int playerMovementSpeed) {
        super(x, y);
        this.playerMovementSpeed = playerMovementSpeed;
        representation = new Image(x, y, Constants.IMAGE_WIDTH, Constants.IMAGE_HEIGHT, Constants.IMAGE_PATH);
    }

    public void update() {
        this.move(movementDirX * playerMovementSpeed, movementDirY * playerMovementSpeed);
        checkWallCollision();
        representation.setPosition(this.getX(), this.getY());
    }

    public void draw() {
        representation.draw();
    }

    public void moveUp() {
        movementDirX = 0;
        movementDirY = -1;
    }

    public void moveLeft() {
        movementDirX = -1;
        movementDirY = 0;
    }

    public void moveDown() {
        movementDirY = 1;
        movementDirX = 0;
    }

    public void moveRight() {
        movementDirX = 1;
        movementDirY = 0;
    }

    @Override
    public boolean hasLeftScreen() {
        return false;
    }

    @Override
    public boolean hasCollidedWith(Collidable other) {
        return false;
    }

    @Override
    public boolean hitTest(Point[] hitBox) {
        return false;
    }

    @Override
    public Point[] getHitBox() {
        Point[] hitBox = new Point[5];
        hitBox[0] = new Point(this.getX() + 59, this.getY() + 2);
        hitBox[1] = new Point(this.getX() + 2, this.getY() + 68);
        hitBox[2] = new Point(this.getX() + 115, this.getY() + 68);
        hitBox[3] = new Point(this.getX() + 41, this.getY()+ 102);
        hitBox[4] = new Point(this.getX() + 75, this.getY()+ 102);
        return hitBox;
    }

    private void checkWallCollision() {
        if (this.getX() <= 0 || this.getX() + representation.getWidth() >= Constants.CANVAS_WIDTH) {
            movementDirX = 0;
        }
        if (this.getY() <= 0 || this.getY() + representation.getHeight() >= Constants.CANVAS_HEIGHT) {
            movementDirY = 0;
        }
    }
}
