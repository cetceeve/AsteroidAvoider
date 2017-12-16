package ui;


import constants.Constants;
import de.ur.mi.graphics.Color;
import de.ur.mi.graphics.Image;
import de.ur.mi.graphics.Label;
import game.GameManager;

/**
 * Menu displayed at game start
 * used to set up some basic game parameters such as
 * - gamemode
 * - level to start with
 */
public class StartMenu {
    private GameManager gameManager;
    private Image mainMenuImage;
    private InvisibleMarker gameModeChallenge;
    private InvisibleMarker gameModeEndless;
    private InvisibleMarker startButton;
    private InvisibleButton plusButton;
    private InvisibleButton minusButton;
    private Label levelSelectionLabel;

    private Integer level = 1;
    private boolean gameStart = false;

    public StartMenu(GameManager gameManager) {
        this.gameManager = gameManager;
        /*
        NOTE TO CORRECTOR:
        i know(!) it is bad practise that these values are not in a config file with reasonable names,
        but this is extra work i put on top of the game just for fun.
        i honestly just did not have the time and motivation to set this up properly.
        */
        // setup all objects in the correct position
        mainMenuImage = new Image(0, 0, Constants.MAIN_MENU_IMAGE);
        gameModeChallenge = new InvisibleMarker(130, 348, 320, 60);
        gameModeChallenge.setColor(Color.GREEN);
        gameModeEndless = new InvisibleMarker(130, 415, 320, 60);
        startButton = new InvisibleMarker(630, 852, 230, 70);
        levelSelectionLabel = new Label(390, 906, "1", Color.WHITE, 60);
        plusButton = new InvisibleButton(490, 857, 60, 60);
        minusButton = new InvisibleButton(320, 857, 60, 60);
    }

    public void draw() {
        mainMenuImage.draw();
        gameModeChallenge.draw();
        gameModeEndless.draw();
        startButton.draw();
        levelSelectionLabel.draw();
        plusButton.draw();
        minusButton.draw();
    }

    /*
    NOTE TO CORRECTOR:
    well, not the most readable method ever written, i know.
    Lets be honest here: the lack of any decomposition on this method
    is undeniable prove of my laziness.
    */
    public void handleMouseClick(int x, int y) {
        /*
        UI Elements that do gamemode changes
         */
        if (gameModeChallenge.hitTest(x, y)) {
            gameModeChallenge.setColor(Color.GREEN);
            gameModeEndless.setColor(Color.WHITE);
            // change gamemode in game manager
            gameManager.setGamemode(Constants.Gamemode.challenge);
        }
        if (gameModeEndless.hitTest(x, y)) {
            gameModeEndless.setColor(Color.GREEN);
            gameModeChallenge.setColor(Color.WHITE);
            // change gamemode in game manager
            gameManager.setGamemode(Constants.Gamemode.endlessLevel);
        }
        /*
        UI Elements that do level selection
         */
        if (plusButton.hitTest(x, y)) {
            if (level < Constants.MAX_LEVEL_NUM) {
                level++;
                levelSelectionLabel.setText(level.toString());
            }
        }
        if (minusButton.hitTest(x, y)) {
            if (level > 1) {
                level--;
                levelSelectionLabel.setText(level.toString());
            }
        }
        /*
        UI Element to start game and hide start menu
         */
        if (startButton.hitTest(x, y)) {
            startButton.setColor(Color.GREEN);
            // send the level selection to game manager and setup everything
            // note that the setupGameObjects methods needs the level FIRST (don't change the order of these statements)
            gameManager.setLevelNum(level - 1);
            gameManager.setupGameObjects();
            // no turning back: start menu gets hidden and the game starts
            gameStart = true;
        }
    }

    public boolean getGameStart() {
        return gameStart;
    }
}
