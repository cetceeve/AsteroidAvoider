package ui;


import constants.Constants;
import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Image;
import de.ur.mi.graphics.Label;
import game.GameManager;

public class StartMenu {
    private GameManager gameManager;
    private Image mainMenuImage;
    private InvisibleButton gameModeChallenge;
    private InvisibleButton gameModeEndless;
    private InvisibleButton startButton;
    private boolean gameStart = false;
    private Label level;
    private Label levelChooser;

    public StartMenu(GameManager gameManager) {
        this.gameManager = gameManager;
        mainMenuImage = new Image(0, 0, Constants.MAIN_MENU_IMAGE);
        gameModeChallenge = new InvisibleButton(130, 348, 320, 60);
        gameModeChallenge.setColor(Color.GREEN);
        gameModeEndless = new InvisibleButton(130, 415, 320, 60);
        startButton = new InvisibleButton(630, 852, 230, 70);
        //level = new Label(200, 450, "Level", Color.BLACK, 50);
        //levelChooser = new Label(200, 525, "0", Color.BLACK, 40);
    }

    public void draw() {
        mainMenuImage.draw();
        gameModeChallenge.draw();
        gameModeEndless.draw();
        startButton.draw();
        //level.draw();
        //levelChooser.draw();
    }

    public void handleMouseClick(int x, int y) {
        if (gameModeChallenge.hitTest(x, y)) {
            gameModeChallenge.setColor(Color.GREEN);
            gameModeEndless.setColor(Color.WHITE);
            gameManager.setGamemode(Constants.Gamemode.challange);
        }
        if (gameModeEndless.hitTest(x, y)) {
            gameModeEndless.setColor(Color.GREEN);
            gameModeChallenge.setColor(Color.WHITE);
            gameManager.setGamemode(Constants.Gamemode.endlessLevel);
        }
        if (startButton.hitTest(x, y)) {
            startButton.setColor(Color.GREEN);
            gameStart = true;
        }
    }

    public boolean getGameStart() {
        return gameStart;
    }
}
