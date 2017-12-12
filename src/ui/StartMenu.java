package ui;


import constants.Constants;
import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Image;
import de.ur.mi.graphics.Label;
import game.GameManager;

public class StartMenu {
    private GameManager gameManager;
    private Image mainMenuImage;
    private InvisibleMarker gameModeChallenge;
    private InvisibleMarker gameModeEndless;
    private InvisibleMarker startButton;
    private InvisibleButton plusButton;
    private InvisibleButton minusButton;
    private boolean gameStart = false;
    private Label levelChooser;
    private Integer level = 1;

    public StartMenu(GameManager gameManager) {
        this.gameManager = gameManager;
        mainMenuImage = new Image(0, 0, Constants.MAIN_MENU_IMAGE);
        gameModeChallenge = new InvisibleMarker(130, 348, 320, 60);
        gameModeChallenge.setColor(Color.GREEN);
        gameModeEndless = new InvisibleMarker(130, 415, 320, 60);
        startButton = new InvisibleMarker(630, 852, 230, 70);
        levelChooser = new Label(390, 906, "1", Color.WHITE, 60);
        plusButton = new InvisibleButton(490, 857, 60, 60);
        minusButton = new InvisibleButton(320, 857, 60, 60);
    }

    public void draw() {
        mainMenuImage.draw();
        gameModeChallenge.draw();
        gameModeEndless.draw();
        startButton.draw();
        levelChooser.draw();
        plusButton.draw();
        minusButton.draw();
    }

    public void handleMouseClick(int x, int y) {
        if (gameModeChallenge.hitTest(x, y)) {
            gameModeChallenge.setColor(Color.GREEN);
            gameModeEndless.setColor(Color.WHITE);
            gameManager.setGamemode(Constants.Gamemode.challenge);
        }
        if (gameModeEndless.hitTest(x, y)) {
            gameModeEndless.setColor(Color.GREEN);
            gameModeChallenge.setColor(Color.WHITE);
            gameManager.setGamemode(Constants.Gamemode.endlessLevel);
        }
        if (plusButton.hitTest(x,y)) {
            if (level < Constants.MAX_LEVEL_NUM) {
                level++;
                levelChooser.setText(level.toString());
            }
        }
        if (minusButton.hitTest(x,y)) {
            if (level > 1) {
                level--;
                levelChooser.setText(level.toString());
            }
        }
        if (startButton.hitTest(x, y)) {
            startButton.setColor(Color.GREEN);
            gameManager.setLevelNum(level - 1);
            gameManager.setupGameObjects();
            gameStart = true;
        }
    }

    public boolean getGameStart() {
        return gameStart;
    }
}
