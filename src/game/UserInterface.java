package game;

import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Label;

public class UserInterface {
    private Label label;
    private Integer passedObstacles = 0;

    public UserInterface() {
        label = new Label(10, 50, "0", Color.WHITE, 50);
    }

    public void update() {
        label.setText(passedObstacles.toString());
    }

    public void draw() {
        label.draw();
    }

    public void setPassedObstacles(int passedObstacles) {
        this.passedObstacles = passedObstacles;
    }
}
