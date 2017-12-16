package ui;

import constants.Constants;
import de.ur.mi.graphics.Image;

/**
 * This class allows to add a simple moving image.
 * the animated image is meant to slide down (centered) from the top of the screen.
 * there are two of these in the game:
 * - one for collisions
 * - one if the player beats the last level
 */
public class AnimatedImage {
    private static final int ANIMATION_DIRECTION = 1;
    private Image image;
    private int startPosY;
    private int endPosY;
    private int animationSpeed = 0;

    // the image is always centered in the middle of the screen
    public AnimatedImage(int startPosY, int endPosY, int imageWidth, int imageHeight, String imagePath) {
        image = new Image(Constants.CANVAS_WIDTH/2 - imageWidth/2, startPosY, imageWidth, imageHeight, imagePath);
        this.endPosY = endPosY;
        this.startPosY = startPosY;
    }

    // the image always slides downwards to the end position
    // animation speed is the variable used to start the animation
    public void update() {
        if (image.getY() <= endPosY) {
            image.move(0, ANIMATION_DIRECTION * animationSpeed);
        }
    }

    public void draw() {
        image.draw();
    }

    public void startAnimation(int animationSpeed) {
        this.animationSpeed = animationSpeed;
    }

    // move the image back to start position and stop movement
    public void reset() {
        image.setY(startPosY);
        animationSpeed = 0;
    }
}
