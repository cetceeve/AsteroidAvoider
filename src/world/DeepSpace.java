package world;

import de.ur.mi.graphics.Image;

public class DeepSpace {
    //constants
    private static final String DEEP_SPACE_IMAGE = "data/assets/deepspace_blue.jpg";
    private static final float IMAGE_START_X = -800f;
    private static final float IMAGE_START_Y = -420f;
    // Instance variables
    private Image deepSpace;
    private float imagePosY = IMAGE_START_Y;

    public DeepSpace() {
        deepSpace = new Image(IMAGE_START_X, IMAGE_START_Y, DEEP_SPACE_IMAGE);
    }

    public void update() {
        imagePosY += 0.5f;
        deepSpace.setPosition(IMAGE_START_X, imagePosY);
    }

    public void draw() {
        deepSpace.draw();
    }
}
