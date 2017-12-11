package ui;

import constants.Constants;
import de.ur.mi.graphics.Image;

public class AnimatedImage {
    private Image image;
    private int endPosY;
    private int animationSpeed = 0;
    private int animationDirection = 1;
    private int startPosY;

    public AnimatedImage(int startPosY, int endPosY, int imageWidth, int imageHeight, String imagePath) {
        image = new Image(Constants.CANVAS_WIDTH/2 - imageWidth/2, startPosY, imageWidth, imageHeight, imagePath);
        this.endPosY = endPosY;
        this.startPosY = startPosY;
    }

    public void update() {
        if (image.getY() <= endPosY) {
            image.move(0, animationDirection * animationSpeed);
        }
    }

    public void draw() {
        image.draw();
    }

    public void startAnimation(int animationSpeed) {
        this.animationSpeed = animationSpeed;
    }

    public void reset() {
        image.setY(startPosY);
        animationDirection = 1;
        animationSpeed = 0;
    }
}
