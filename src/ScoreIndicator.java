import biuoop.DrawSurface;

import java.awt.Color;


/**
 * The type Score indicator.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * Instantiates a new Score indicator.
     *
     * @param score the score
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);

        d.drawText(375, 17, "Score: " + score.getValue(), 15);
    }

    @Override
    public void timePassed() {

    }
}
