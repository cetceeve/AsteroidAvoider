package world;

import constants.Constants;
import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Line;

/**
 * particles are represented as a fine line that runs from the top of the screen downwards
 * line movement speed is calculated from the obstacle movement speed
 */
public class Particle extends Line {
    private int lineSpeed;

    public Particle(int linePosX, int lineStart, int lineEnd, int lineSpeed) {
        super(linePosX, lineStart, linePosX, lineEnd, Color.WHITE);
        this.lineSpeed = lineSpeed;
    }

    // movement (always downwards)
    public void update() {
        this.setStartPoint(this.getStartpointX(), this.getStartpointY() + lineSpeed);
        this.setEndPoint(this.getStartpointX(), this.getEndpointY() + lineSpeed);
    }

    public void draw() {
        super.draw();
    }

    public boolean hasLeftScreen() {
        return this.getEndpointY() >= Constants.CANVAS_HEIGHT;
    }

    public void setNewValues(int linePosX, int lineStart, int lineEnd, int lineSpeed) {
        this.setStartPoint(linePosX, lineStart);
        this.setEndPoint(linePosX, lineEnd);
        this.lineSpeed = lineSpeed;
    }
}
