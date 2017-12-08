package world;

import constants.Constants;
import de.ur.mi.graphics.GraphicsObject;
import de.ur.mi.graphics.Image;

public class Player extends GraphicsObject{
    private Image representation;
    private double movementDirX = 0;
    private double movementDirY = 0;

    public Player(double x, double y) {
        super (x, y);
        representation = new Image(x, y, "./data/assets/ship_bucc.png");
        representation.scale(0.25);
    }

    public void update() {
        this.move(movementDirX * Constants.PLAYER_MOVEMENT_SPEED, movementDirY * Constants.PLAYER_MOVEMENT_SPEED);
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
}
