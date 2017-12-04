package world;

import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.GraphicsObject;

import de.ur.mi.graphics.Image;

public class Player {
    private GraphicsObject representation;

    public Player(double x, double y, int width, int height, Color color) {
        representation = new Image(x, y, width, height, "./data/assets/car.png");
    }

    public void draw() {
        representation.draw();
    }
}
