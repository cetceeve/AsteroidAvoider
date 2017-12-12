package ui;

import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Label;
import constants.Constants;

public class UserInterface {
    private Label labelPassCount;
    private Label labelLevel;
    private Label toolTip;
    private Integer passedObstacles = 0;
    private Integer levelNum = 1;
    private boolean hideToolTip = false;

    public UserInterface() {
        labelPassCount = new Label(10, 50, "0", Color.WHITE, 50);
        labelLevel = new Label(Constants.CANVAS_WIDTH - 9*50/2, 50, "Level: 1", Color.WHITE,50);
        toolTip = new Label(Constants.CANVAS_WIDTH/2 - 10*20/2, Constants.CANVAS_HEIGHT - 30, "press [space] to start", Color.WHITE,20);
    }

    public void update() {
        labelPassCount.setText(passedObstacles.toString());
        labelLevel.setText("Level: " + levelNum.toString());
    }

    public void draw() {
        labelPassCount.draw();
        labelLevel.draw();
        if (!hideToolTip) {
            toolTip.draw();
        }
    }

    public void setPassedObstacles(int passedObstacles) {
        this.passedObstacles = passedObstacles;
    }

    public void setLevelNum(int levelNum) {
        this.levelNum = levelNum;
    }

    public void showToolTip(String toolTipText) {
        toolTip.setText(toolTipText);
        toolTip.setPosition(Constants.CANVAS_WIDTH/2 - (toolTipText.length()*20/4), Constants.CANVAS_HEIGHT - 30);
        hideToolTip = false;
    }

    public void hideToolTip() {
        hideToolTip = true;
    }
}
