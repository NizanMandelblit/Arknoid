import biuoop.DrawSurface;

import java.awt.Color;


/**
 * The type High scores animation.
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable scores;

    /**
     * Instantiates a new High scores animation.
     *
     * @param scores the scores
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.scores = scores;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.lightGray);
        d.fillRectangle(0, 0, GameLevel.BOARD_WIDTH, GameLevel.BOARD_HEIGHT);
        d.setColor(Color.yellow);
        d.drawText(44, 44, "High Scores", 50);
        d.setColor(Color.green);
        d.drawText(180, 160, "Player Name", 34);
        d.drawText(600, 160, "Scores", 34);

        d.setColor(Color.BLACK);
        d.drawLine(150, 164, 700, 164);
        d.setColor(Color.GREEN);
        d.drawLine(150, 165, 700, 165);
        d.setColor(Color.BLACK);
        d.drawLine(150, 166, 700, 166);
        d.setColor(Color.BLACK);
        d.setColor(Color.green.darker());
        int ylocation = 190;
        for (int i = 0; i < this.scores.getHighScores().size(); i++) {

            d.drawText(200, ylocation, this.scores.getHighScores().get(i).getName(), 23);
            ylocation += 45;

        }
        ylocation = 190;
        for (int i = 0; i < this.scores.getHighScores().size(); i++) {

            d.drawText(620, ylocation, Integer.toString(this.scores.getHighScores().get(i).getScore()), 23);
            ylocation += 45;

        }


        d.setColor(Color.BLACK);
        d.drawText(199, 670, "Press space to continue", 32);

    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
