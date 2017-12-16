package world;

import constants.Constants;
import de.ur.mi.geom.Point;
import de.ur.mi.graphics.GraphicsObject;
import de.ur.mi.graphics.Image;

/**
 * This is the player represented by an image (spaceship in this case).
 * The player can move and crash into obstacles.
 * The players movement speed can change every level.
 * The players parameters are controlled by the game manager.
 */
public class Player extends GraphicsObject implements Collidable {
    private int playerMovementSpeed;
    private Image representation;
    private double movementDirX = 0;
    private double movementDirY = 0;
    private boolean useWallCollision = true;

    public Player(double x, double y, int playerMovementSpeed) {
        super(x, y);
        this.playerMovementSpeed = playerMovementSpeed;
        newShip();
    }

    /*
    actual player movement is a calculation of movement direction and player speed
    (movementDir can have three values: '-1' '0' '1')
    */
    public void update() {
        this.move(movementDirX * playerMovementSpeed, movementDirY * playerMovementSpeed);
        if (useWallCollision) {
            wallCollision();
        }
        representation.setPosition(this.getX(), this.getY());
    }

    public void draw() {
        representation.draw();
    }

    @Override
    public boolean hasLeftScreen() {
        return this.getY() >= Constants.CANVAS_HEIGHT + Constants.VIRTUAL_GRID_HEIGHT;
    }

    /*
    The HitBoxes values need to be costum made for the specific image
     */
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

    // used for player animations and reset
    public void setMovementDirection(int x, int y) {
        this.movementDirX = x;
        this.movementDirY = y;
    }

    public void enableWallCollision() {
        useWallCollision = true;
    }

    public void disableWallCollision() {
        useWallCollision = false;
    }

    // movement speed can change every level
    public void setPlayerMovementSpeed(int playerMovementSpeed) {
        this.playerMovementSpeed = playerMovementSpeed;
    }

    /*
    change the player representation
    (used for visual indication of collisions)
     */
    public void crashedShip() {
        representation = new Image(this.getX(), this.getY(), Constants.PLAYER_CRASHED_IMAGE_WIDTH, Constants.PLAYER_CRASHED_IMAGE_HEIGHT, Constants.PLAYER_CRASHED_IMAGE_PATH);
    }

    public void newShip() {
        representation = new Image(this.getX(), this.getY(), Constants.PLAYER_IMAGE_WIDTH, Constants.PLAYER_IMAGE_HEIGHT, Constants.PLAYER_IMAGE_PATH);
    }

    /*
    Movement changes by 'w''a''s''d' keys.
    Note that the player cannot stand still ones moving; unless he uses the behaviour of the
    wallCollision() method. This is on purpose in order to introduce some 'skill' to the game.
     */
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

    /*
    Check if the player has reached the windows borders ('walls').
    Player actually "bounces back" from the wall,
    but this is not visible because the movement is also stopped.
    */
    private void wallCollision() {
        if (this.getX() <= 0 || this.getX() + representation.getWidth() >= Constants.CANVAS_WIDTH) {
            // bounce back
            this.move(-1 * movementDirX * playerMovementSpeed, 0);
            // stop moving
            movementDirX = 0;
        }
        if (this.getY() <= 0 || this.getY() + representation.getHeight() >= Constants.CANVAS_HEIGHT) {
            // bounce back
            this.move(0, -1 * movementDirY * playerMovementSpeed);
            // stop moving
            movementDirY = 0;
        }
    }

    // not needed but part of Collidable interface
    @Override
    public boolean hasCollidedWith(Collidable other) {
        return false;
    }

    @Override
    public boolean hitTest(Point[] hitBox) {
        return false;
    }
}
