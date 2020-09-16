import biuoop.DialogManager;
import biuoop.KeyboardSensor;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * The type Game flow.
 */
public class GameFlow {
    /**
     * The constant MAX_TABLE_SIZE.
     */
    public static final int MAX_TABLE_SIZE = 10;
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter lives;
    private Counter score;
    private HighScoresTable loadTable;

    /**
     * Instantiates a new Game flow.
     *
     * @param ar        the ar
     * @param ks        the ks
     * @param loadTable the load table
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, HighScoresTable loadTable) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.lives = new Counter(7);
        this.score = new Counter(0);
        this.loadTable = loadTable;
    }

    /**
     * Run levels.
     *
     * @param levels the levels
     * @throws IOException the io exception
     */
    public void runLevels(List<LevelInformation> levels) throws IOException {


        for (LevelInformation levelInfo : levels) {
            Counter blocksNum = new Counter(levelInfo.numberOfBlocksToRemove());
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner
                    , this.score, this.lives, blocksNum);
            level.initialize();

            while (blocksNum.getValue() > 0 && this.lives.getValue() > 0) {
                level.playOneTurn();
            }

            if (this.lives.getValue() == 0) {
                this.enterScore();
                Animation over = new KeyPressStoppableAnimation(this.keyboardSensor, this.keyboardSensor.SPACE_KEY
                        , new GameOver(this.score.getValue(), this.keyboardSensor));
                animationRunner.run(over);
                Animation highscore = new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                        new HighScoresAnimation(this.loadTable));
                animationRunner.run(highscore);
                return;
            }
        }
        this.enterScore();
        Animation win = new KeyPressStoppableAnimation(this.keyboardSensor, this.keyboardSensor.SPACE_KEY
                , new YouWin(this.score.getValue(), this.keyboardSensor));
        animationRunner.run(win);

        Animation highscore = new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                new HighScoresAnimation(this.loadTable));
        animationRunner.run(highscore);
    }

    /**
     * Enter score.
     *
     * @throws IOException the io exception
     */
    public void enterScore() throws IOException {
        DialogManager dialog = Ass7Game.GUI.getDialogManager();
        String name = dialog.showQuestionDialog("Name", "What is your name?", "");
        if (this.loadTable.getRank(this.score.getValue()) <= this.loadTable.size()) {
            ScoreInfo player = new ScoreInfo(name, this.score.getValue());
            this.loadTable.getHighScores().add(player);
            this.loadTable.save(new File("highscores"));
        }
    }


}
