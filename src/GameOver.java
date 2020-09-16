import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The type Game over.
 */
public class GameOver implements Animation {
    private int finalScore;
    private KeyboardSensor keyboard;
    private boolean shouldStop;

    /**
     * Instantiates a new Game over.
     *
     * @param finalScore the final score
     * @param ks         the ks
     */
    public GameOver(int finalScore, KeyboardSensor ks) {
        this.finalScore = finalScore;
        this.keyboard = ks;
        this.shouldStop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.gray);
        d.drawText(300, 200, "Game Over.", 40);
        d.drawText(250, 250, "your score is: " + finalScore + " points", 40);



    }

    @Override
    public boolean shouldStop() {
        return this.shouldStop;
    }
}
