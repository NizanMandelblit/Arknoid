import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Countdown animation.
 */
// The CountdownAnimation will display the given gameScreen,
// for numOfSeconds seconds, and on top of them it will show
// a countdown from countFrom back to 1, where each number will
// appear on the screen for (numOfSeconds / countFrom) secods, before
// it is replaced with the next one.
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean isDone;
    private boolean isFirst;
    private long startMillisecond;
    // private Sprite background;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.isDone = false;
        this.isFirst = true;
        //  this.background = background;
    }

    /**
     * Do one frame.
     *
     * @param d the d
     */
    public void doOneFrame(DrawSurface d) {
        if (this.isFirst) {
            this.startMillisecond = System.currentTimeMillis();
            this.isFirst = false;
        }
        //   this.background.drawOn(d);
        this.gameScreen.drawAllOn(d);
        long currentMillisecond = System.currentTimeMillis();
        double singleCountLength = this.numOfSeconds / (double) this.countFrom;
        int currentNumber = (int) ((double) (1 + this.countFrom)
                - (double) (currentMillisecond - startMillisecond) / (singleCountLength * 1000.0D));
        if (currentNumber > 0) {
            d.setColor(Color.BLACK);
            d.drawText(d.getWidth() / 2 + 2, d.getHeight() / 2 + 100, "" + currentNumber, 40);
            d.drawText(d.getWidth() / 2 - 2, d.getHeight() / 2 + 100, "" + currentNumber, 40);
            d.drawText(d.getWidth() / 2, d.getHeight() / 2 + 102, "" + currentNumber, 40);
            d.drawText(d.getWidth() / 2, d.getHeight() / 2 + 98, "" + currentNumber, 40);
            d.setColor(Color.WHITE);
            d.drawText(d.getWidth() / 2, d.getHeight() / 2 + 100, "" + currentNumber, 40);
        }

        if ((double) (currentMillisecond - startMillisecond) > this.numOfSeconds * 1000.0D) {
            this.isDone = true;
        }

    }

    /**
     * Should stop boolean.
     *
     * @return the boolean
     */
    public boolean shouldStop() {
        return this.isDone;
    }
}