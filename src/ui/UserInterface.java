package ui;

import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Label;
import constants.Constants;

public class UserInterface {
    private Label labelPassCount;
    private Label labelLevel;
    private Integer passedObstacles = 0;
    private Integer levelNum = 1;
    private boolean hidePassCount = false;

    public UserInterface() {
        labelPassCount = new Label(10, 50, "0", Color.WHITE, 50);
        labelLevel = new Label(Constants.CANVAS_WIDTH - 9*50/2, 50, "Level: 1", Color.WHITE,50);
    }

    public void update() {
        labelPassCount.setText(passedObstacles.toString());
        labelLevel.setText("Level: " + levelNum.toString());
    }

    public void draw() {
        if (!hidePassCount) {
            labelPassCount.draw();
        }
        labelLevel.draw();
    }

    public void hidePassCount(boolean hidePassCount) {
        this.hidePassCount = hidePassCount;
    }

    public void setPassedObstacles(int passedObstacles) {
        this.passedObstacles = passedObstacles;
    }

    public void setLevelNum(int levelNum) {
        this.levelNum = levelNum;
    }
}
