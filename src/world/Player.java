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
    private boolean checkForWallCollision = true;

    public Player(double x, double y, int playerMovementSpeed) {
        super(x, y);
        this.playerMovementSpeed = playerMovementSpeed;
        representation = new Image(x, y, Constants.PLAYER_IMAGE_WIDTH, Constants.PLAYER_IMAGE_HEIGHT, Constants.PLAYER_IMAGE_PATH);
    }

    public void update() {
        this.move(movementDirX * playerMovementSpeed, movementDirY * playerMovementSpeed);
        if (checkForWallCollision) {
            checkWallCollision();
        }
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
        movementDirX = 0;
        movementDirY = 1;
    }

    public void moveRight() {
        movementDirX = 1;
        movementDirY = 0;
    }

    @Override
    public boolean hasLeftScreen() {
        return this.getY() >= Constants.CANVAS_HEIGHT + Constants.VIRTUAL_GRID_HEIGHT;
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
        Point[] hitBox = new Point[7];
        hitBox[0] = new Point(this.getX() + 59, this.getY() + 2);
        hitBox[1] = new Point(this.getX() + 2, this.getY() + 68);
        hitBox[2] = new Point(this.getX() + 115, this.getY() + 68);
        hitBox[3] = new Point(this.getX() + 41, this.getY() + 102);
        hitBox[4] = new Point(this.getX() + 75, this.getY() + 102);
        hitBox[5] = new Point(this.getX() + 38, this.getY() + 47);
        hitBox[6] = new Point(this.getX() + 78, this.getY() + 47);
        return hitBox;
    }

    public void setPlayerMovementSpeed(int playerMovementSpeed) {
        this.playerMovementSpeed = playerMovementSpeed;
    }

    public void setMovementDirection(int x, int y) {
        this.movementDirX = x;
        this.movementDirY = y;
    }

    public void setCheckForWallCollision(boolean checkForWallCollision) {
        this.checkForWallCollision = checkForWallCollision;
    }

    private void checkWallCollision() {
        if (this.getX() <= 0 || this.getX() + representation.getWidth() >= Constants.CANVAS_WIDTH) {
            this.move(-1 * movementDirX * playerMovementSpeed, 0);
            representation.setPosition(this.getX(), this.getY());
            movementDirX = 0;
        }
        if (this.getY() <= 0 || this.getY() + representation.getHeight() >= Constants.CANVAS_HEIGHT) {
            this.move(0, -1 * movementDirY * playerMovementSpeed);
            representation.setPosition(this.getX(), this.getY());
            movementDirY = 0;
        }
    }
}
