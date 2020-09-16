import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The type You win.
 */
public class YouWin implements Animation {
    private int finalScore;
    private KeyboardSensor keyboard;
    private boolean shouldStop;

    /**
     * Instantiates a new You win.
     *
     * @param finalScore the final score
     * @param ks         the ks
     */
    public YouWin(int finalScore, KeyboardSensor ks) {
        this.finalScore = finalScore;
        this.keyboard = ks;
        this.shouldStop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.gray);
        d.drawText(300, 200, "YOU WIN!", 40);
        d.drawText(250, 250, "your final score is: " + finalScore + " points", 40);



    }

    @Override
    public boolean shouldStop() {
        return shouldStop;
    }
}
