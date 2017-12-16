package ui;

import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Label;
import constants.Constants;

/**
 * user interface displayed while playing the game
 * updates player on
 * - how many obstacles he passed
 * - what level he is in
 * - keybindings
 */
public class UserInterface {
    private Label labelPassCount;
    private Label labelLevel;
    private Label toolTip;
    private Integer passedObstacles = 0;
    private Integer levelNum = 1;
    private boolean hideToolTip = false;

    public UserInterface() {
        // Number of Obstacles the Player has passed displayed in the upper left
        labelPassCount = new Label(10, 50, "0", Color.WHITE, 50);
        // Current level displayed in the upper right
        labelLevel = new Label(Constants.CANVAS_WIDTH - 9*50/2, 50, "Level: 1", Color.WHITE,50);
        // Tips on keybindings displayed centered at the bottom
        toolTip = new Label(Constants.CANVAS_WIDTH/2 - 11*20/2, Constants.CANVAS_HEIGHT - 30, "press [space] to start", Color.WHITE,20);
    }

    // regularly update passed obstacles and current level
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
